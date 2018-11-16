package de.sandstorm.configdocgen.core.model

import com.fasterxml.jackson.annotation.JsonValue

data class NamespaceName(
        @get:JsonValue val name: String
) {
    fun isDefaultNamespace() = name.isEmpty()

    companion object {
        fun defaultNamespace() = NamespaceName("")
    }
}