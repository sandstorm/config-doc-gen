package de.sandstorm.configdocgen.core.model

import com.fasterxml.jackson.annotation.JsonValue
import javax.lang.model.element.Element

data class PropertyName(
        @get:JsonValue val name: String
) {
    companion object {
        fun fromJavaField(field: Element) = PropertyName(field.simpleName.toString())
        fun fromJavaMethod(method: Element) = PropertyName(method.simpleName.toString())
    }
}