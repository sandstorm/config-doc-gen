package de.sandstorm.configdocgen.processor.standalone

import com.google.auto.service.AutoService
import de.sandstorm.configdocgen.annotations.ConfigNamespace
import de.sandstorm.configdocgen.annotations.ConfigProperty
import de.sandstorm.configdocgen.core.DocumentationModelWriter
import de.sandstorm.configdocgen.core.model.*
import java.io.IOException
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic


@SupportedAnnotationTypes(
        "de.sandstorm.configdocgen.annotations.ConfigNamespace",
        "de.sandstorm.configdocgen.annotations.ConfigProperty",
        "de.sandstorm.configdocgen.annotations.ConfigApi"
)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor::class)
class StandaloneProcessor(
        private val writer: DocumentationModelWriter
) : AbstractProcessor() {

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        var namespaceClasses: MutableSet<out Element> = mutableSetOf()
        var propertiesElements: MutableSet<out Element> = mutableSetOf()
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

        if (properties.isEmpty() && properties.isEmpty()) {
            return false
        }

        val model = ConfigurationDoc(namespaces, properties)

        try {
            writer.write(
                    model = model,
                    filer = processingEnv.filer
            )
        } catch (e: IOException) {
            processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, "Failed writing output\n")
            e.printStackTrace()
        }

        return true
    }

    private fun createProperty(element: Element): ConfigurationProperty = ConfigurationProperty.fromJavaElement(element, processingEnv)

    private fun createNamespace(element: Element): ConfigurationNamespace = ConfigurationNamespace.fromJavaElement(element, processingEnv)

}