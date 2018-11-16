package de.sandstorm.configdocgen.core.model

import com.fasterxml.jackson.annotation.JsonValue

data class PropertyName(
        @get:JsonValue val name: String
)