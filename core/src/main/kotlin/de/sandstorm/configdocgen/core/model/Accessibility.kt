package de.sandstorm.configdocgen.core.model

import de.sandstorm.configdocgen.core.isConfigApiAnnotationPresent
import de.sandstorm.configdocgen.core.isConfigApiAnnotationPresentOnGetter
import de.sandstorm.configdocgen.core.isGetterForFieldPublic
import de.sandstorm.configdocgen.core.isPublic
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

        fun fromJavaMethod(method: Element) = when {
            isConfigApiAnnotationPresent(method) -> API
            isConfigApiAnnotationPresent(method.enclosingElement) -> API
            isPublic(method) -> API
            else -> IMPLEMENTATION
        }
    }
}