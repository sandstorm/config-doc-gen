package de.sandstorm.configdocgen.core.model

import com.fasterxml.jackson.annotation.JsonValue
import javax.lang.model.element.Element

data class NamespaceName(
        @get:JsonValue val name: String
) {
    fun isDefaultNamespace() = name.isEmpty()

    companion object {
        fun defaultNamespace() = NamespaceName("")

        fun fromJavaClass(clazz: Element) = NamespaceName(clazz.toString())

        fun fromJavaField(clazz: Element) = NamespaceName(clazz.enclosingElement.toString())

        fun fromJavaMethod(clazz: Element) = NamespaceName(clazz.enclosingElement.toString())
    }
}