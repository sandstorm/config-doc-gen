package de.sandstorm.configdocgen.processor.spring

import com.google.auto.service.AutoService
import de.sandstorm.configdocgen.core.AbstractConfigurationDocumentationProcessor
import de.sandstorm.configdocgen.core.DocumentationModelWriter
import de.sandstorm.configdocgen.core.isField
import de.sandstorm.configdocgen.core.isSetterForFieldPublic
import de.sandstorm.configdocgen.core.model.*
import org.springframework.boot.context.properties.ConfigurationProperties
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.TypeElement


@SupportedAnnotationTypes(
        "de.sandstorm.configdocgen.annotations.ConfigNamespace",
        "de.sandstorm.configdocgen.annotations.ConfigProperty",
        "de.sandstorm.configdocgen.annotations.ConfigApi",
        "org.springframework.boot.context.properties.ConfigurationProperties"
)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor::class)
class SpringProcessor(
        writer: DocumentationModelWriter
) : AbstractConfigurationDocumentationProcessor(writer) {

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        var configurationPropertiesClasses: Set<Element> = emptySet()
        for (annotation in annotations!!) {
            val annotatedElements = roundEnv!!.getElementsAnnotatedWith(annotation)
            if (annotation.qualifiedName.contentEquals(ConfigurationProperties::class.java.name)) {
                configurationPropertiesClasses = annotatedElements
            }
        }

        var namespaces = configurationPropertiesClasses.map(::namespaceFromSpringConfigurationPropertiesClass)
        var properties = configurationPropertiesClasses.flatMap(::propertiesFromSpringConfigurationPropertiesClass)
    }

    private fun namespaceFromSpringConfigurationPropertiesClass(clazz: Element) = ConfigurationNamespace(
            name = namespaceNameFromSpringAnnotation(clazz),
            documentationContent = DocumentationContent.fromJavaElement(clazz, processingEnv)
    )

    private fun namespaceNameFromSpringAnnotation(clazz: Element): NamespaceName {
        val annotation = clazz.getAnnotation(ConfigurationProperties::class.java)
        return NamespaceName(annotation.prefix)
    }

    private fun propertiesFromSpringConfigurationPropertiesClass(clazz: Element) =
            clazz.enclosedElements
                    // only fields ...
                    .filter(::isField)
                    // that have a public setter
                    .filter(::isSetterForFieldPublic)
                    .map(::propertyFromSpringConfigurationPropertiesClassField)

    private fun propertyFromSpringConfigurationPropertiesClassField(field: Element) = ConfigurationProperty(
            name = PropertyName.fromJavaField(field),
            // TODO!!!
    )

}