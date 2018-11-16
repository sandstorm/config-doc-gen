package de.sandstorm.configdocgen.core.model

import com.fasterxml.jackson.annotation.JsonValue

data class DocumentationText(
        @get:JsonValue val text: String
)