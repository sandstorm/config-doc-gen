package de.sandstorm.configdocgen.core.model

import com.fasterxml.jackson.annotation.JsonValue
import javax.lang.model.element.Element

const val KEY_WILDCARD: String = "<KEY>"

data class NamespaceName(
        @get:JsonValue val name: String
) {

    fun isDefaultNamespace() = name.isEmpty()

    fun nestedNamespaceName(nestedName: NamespaceName) = if (isDefaultNamespace()) nestedName else NamespaceName("$name.${nestedName.name}")

    companion object {
        fun defaultNamespace() = NamespaceName("")

        fun fromJavaClass(clazz: Element) = NamespaceName(clazz.toString())

        fun fromJavaField(field: Element) = NamespaceName(field.enclosingElement.toString())

        fun fromJavaFieldName(field: Element) = NamespaceName(field.simpleName.toString())

        fun keyWildcard() = NamespaceName(KEY_WILDCARD)

        fun fromJavaMethod(method: Element) = NamespaceName(method.enclosingElement.toString())
    }
}