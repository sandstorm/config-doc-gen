package de.sandstorm.configdocgen.core.model

import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind

data class ConfigurationNamespace(
        val name: NamespaceName,
        val type: NamespaceType,
        val documentationContent: DocumentationContent
) {

    fun nestedNamespaceWithKeyWildcard(element: Element, documentationContent: DocumentationContent): ConfigurationNamespace = ConfigurationNamespace(
            name = name.nestedNamespaceName(NamespaceName.keyWildcard()),
            type = NamespaceType.fromJavaElement(element),
            documentationContent = documentationContent
    )

    fun nestedNamespace(field: Element, documentationContent: DocumentationContent): ConfigurationNamespace = ConfigurationNamespace(
            name = name.nestedNamespaceName(NamespaceName.fromJavaFieldName(field)),
            type = NamespaceType.fromJavaElement(field),
            documentationContent = documentationContent
    )

    companion object {

        fun fromJavaClass(clazz: Element, documentationContent: DocumentationContent) = ConfigurationNamespace(
                name = NamespaceName.fromJavaClass(clazz),
                type = NamespaceType.fromJavaElement(clazz),
                documentationContent = documentationContent
        )

        fun fromJavaElement(element: Element, documentationContent: DocumentationContent): ConfigurationNamespace = when (element.kind) {
            ElementKind.CLASS -> fromJavaClass(element, documentationContent)
            else -> throw IllegalStateException("Namespace must be a class")
        }

    }
}