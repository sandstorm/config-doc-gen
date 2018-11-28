package de.sandstorm.configdocgen.core.model

import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind

data class ConfigurationNamespace(
        val name: NamespaceName,
        val documentationContent: DocumentationContent
) {
    companion object {

        fun fromJavaClass(clazz: Element, processingEnvironment: ProcessingEnvironment) = ConfigurationNamespace(
                name = NamespaceName.fromJavaClass(clazz),
                documentationContent = DocumentationContent.fromJavaElement(clazz, processingEnvironment)
        )

        fun fromJavaElement(element: Element, processingEnvironment: ProcessingEnvironment): ConfigurationNamespace = when (element.kind) {
            ElementKind.CLASS -> fromJavaClass(element, processingEnvironment)
            else -> throw IllegalStateException("Namespace must be a class")
        }

    }
}