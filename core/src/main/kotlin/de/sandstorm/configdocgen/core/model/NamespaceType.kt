package de.sandstorm.configdocgen.core.model

import javax.lang.model.element.Element

data class NamespaceType(
        val typeName: String,
        val documentationContent: DocumentationContent
) {
    companion object {
        fun fromJavaElement(element: Element, documentationContent: DocumentationContent) = NamespaceType(
                typeName = element.asType().toString(),
                documentationContent = documentationContent
        )
    }
}