package de.sandstorm.configdocgen.processor.standalone

import com.google.auto.service.AutoService
import de.sandstorm.configdocgen.annotations.ConfigNamespace
import de.sandstorm.configdocgen.annotations.ConfigProperty
import de.sandstorm.configdocgen.core.AbstractConfigurationDocumentationProcessor
import de.sandstorm.configdocgen.core.DocumentationModelWriter
import de.sandstorm.configdocgen.core.model.ConfigurationNamespace
import de.sandstorm.configdocgen.core.model.ConfigurationProperty
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement


@SupportedAnnotationTypes(
        "de.sandstorm.configdocgen.annotations.ConfigNamespace",
        "de.sandstorm.configdocgen.annotations.ConfigProperty",
        "de.sandstorm.configdocgen.annotations.ConfigApi"
)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor::class)
class StandaloneProcessor(
        writer: DocumentationModelWriter
) : AbstractConfigurationDocumentationProcessor(writer) {

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        var namespaceClasses: Set<Element> = emptySet()
        var propertiesElements: Set<Element> = emptySet()
        for (annotation in annotations!!) {
            val annotatedElements = roundEnv!!.getElementsAnnotatedWith(annotation)
            if (annotation.qualifiedName.contentEquals(ConfigNamespace::class.java.name)) {
                namespaceClasses = annotatedElements
            } else if (annotation.qualifiedName.contentEquals(ConfigProperty::class.java.name)) {
                propertiesElements = annotatedElements
            }
        }

        val properties: List<ConfigurationProperty> = propertiesElements.map(::createProperty)
        val namespaces = namespaceClasses.map(::createNamespace)

        return writeModel(namespaces, properties)
    }

    private fun createProperty(element: Element): ConfigurationProperty = ConfigurationProperty.fromJavaElement(element, processingEnv)

    private fun createNamespace(element: Element): ConfigurationNamespace = ConfigurationNamespace.fromJavaElement(element, processingEnv)

}