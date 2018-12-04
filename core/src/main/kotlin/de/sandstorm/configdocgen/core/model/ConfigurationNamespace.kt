package de.sandstorm.configdocgen.core.model

import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind

data class ConfigurationNamespace(
        val name: NamespaceName,
        val type: NamespaceType,
        val documentationContent: DocumentationContent
) {

    fun nestedNamespaceWithKeyWildcard(element: Element, elementDocumentationContent: DocumentationContent, typeDocumentationContent: DocumentationContent): ConfigurationNamespace = ConfigurationNamespace(
            name = name.nestedNamespaceName(NamespaceName.keyWildcard()),
            type = NamespaceType.fromJavaElement(element, typeDocumentationContent),
            documentationContent = elementDocumentationContent
    )

    fun nestedNamespaceWithIndexWildcard(element: Element, elementDocumentationContent: DocumentationContent, typeDocumentationContent: DocumentationContent): ConfigurationNamespace = ConfigurationNamespace(
            name = name.nestedNamespaceName(NamespaceName.indexWildcard()),
            type = NamespaceType.fromJavaElement(element, typeDocumentationContent),
            documentationContent = elementDocumentationContent
    )

    fun nestedNamespace(field: Element, elementDocumentationContent: DocumentationContent, typeDocumentationContent: DocumentationContent): ConfigurationNamespace = ConfigurationNamespace(
            name = name.nestedNamespaceName(NamespaceName.fromJavaFieldName(field)),
            type = NamespaceType.fromJavaElement(field, typeDocumentationContent),
            documentationContent = elementDocumentationContent
    )

    companion object {

        fun fromRootJavaElement(element: Element, documentationContent: DocumentationContent): ConfigurationNamespace = when (element.kind) {
            ElementKind.CLASS -> ConfigurationNamespace(
                    name = NamespaceName.fromJavaClass(element),
                    type = NamespaceType.fromJavaElement(element, documentationContent),
                    documentationContent = DocumentationContent.rootNamespace()
            )
            else -> throw IllegalStateException("Namespace must be a class")
        }

    }
}