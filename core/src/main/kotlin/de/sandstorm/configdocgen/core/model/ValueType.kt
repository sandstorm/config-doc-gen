package de.sandstorm.configdocgen.core.model

import de.sandstorm.configdocgen.core.isNonNullAnnotationPresent
import de.sandstorm.configdocgen.core.isNonNullAnnotationPresentOnGetter
import de.sandstorm.configdocgen.core.isPrimitiveType
import javax.lang.model.element.Element

data class ValueType(
     val typeName: String,
     val required: Boolean
) {
    companion object {
        fun fromJavaField(field: Element) = ValueType(
                typeName = field.asType().toString(),
                required = explicitlyRequiredField(field) ?: autoDetectRequiredField(field)
        )

        fun fromJavaMethod(method: Element) = ValueType(
                typeName = method.asType().toString(),
                required = explicitlyRequiredMethod(method) ?: autoDetectRequiredMethod(method)
        )

        private fun explicitlyRequiredField(field: Element) : Boolean? = when {
            // TODO explicit annotation?
            else -> null
        }

        private fun explicitlyRequiredMethod(field: Element) : Boolean? = when {
            // TODO explicit annotation?
            else -> null
        }

        private fun autoDetectRequiredField(field: Element) = when {
            isPrimitiveType(field) -> true
            isNonNullAnnotationPresent(field) -> true
            isNonNullAnnotationPresentOnGetter(field) -> true
            // TODO kotlin nullability somehow possible to detect?
            else -> false
        }

        private fun autoDetectRequiredMethod(field: Element) = when {
            isPrimitiveType(field) -> true
            isNonNullAnnotationPresent(field) -> true
            // TODO kotlin nullability somehow possible to detect?
            else -> false
        }
    }
}