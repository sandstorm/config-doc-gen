package de.sandstorm.configdocgen.core.model

import com.fasterxml.jackson.annotation.JsonValue

data class DocumentationContent(
        @get:JsonValue val content: String
) {
    companion object {
        fun empty() = DocumentationContent("")
    }
}