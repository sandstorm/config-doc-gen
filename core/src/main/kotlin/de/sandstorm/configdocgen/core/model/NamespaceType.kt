package de.sandstorm.configdocgen.core.model

import javax.lang.model.element.Element

data class NamespaceType(
        val typeName: String
) {
    companion object {
        fun fromJavaElement(field: Element) = NamespaceType(
                typeName = field.asType().toString()
        )
    }
}