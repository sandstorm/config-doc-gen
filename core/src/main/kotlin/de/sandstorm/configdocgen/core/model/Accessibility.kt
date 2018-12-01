package de.sandstorm.configdocgen.core.model

import de.sandstorm.configdocgen.core.*
import javax.lang.model.element.Element

enum class Accessibility {
    API,
    IMPLEMENTATION;

    companion object {
        fun fromJavaField(field: Element) = when {
            isConfigApiAnnotationPresent(field) -> API
            isConfigApiAnnotationPresent(field.enclosingElement) -> API
            isConfigApiAnnotationPresentOnGetter(field) -> API
            isGetterForFieldPublic(field) -> API
            else -> IMPLEMENTATION
        }

        fun fromJavaGetterOrField(field: Element) = findGetterForField(field)?.let(::fromJavaMethod) ?: fromJavaField(field)

        fun fromJavaMethod(method: Element) = when {
            isConfigApiAnnotationPresent(method) -> API
            isConfigApiAnnotationPresent(method.enclosingElement) -> API
            isPublic(method) -> API
            else -> IMPLEMENTATION
        }
    }
}