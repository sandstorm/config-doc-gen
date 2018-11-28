package de.sandstorm.configdocgen.core.model

import com.fasterxml.jackson.annotation.JsonValue
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element

data class DocumentationContent(
        @get:JsonValue val content: String
) {
    companion object {
        fun fromJavaElement(element: Element, processingEnvironment: ProcessingEnvironment) =
                DocumentationContent(processingEnvironment.elementUtils.getDocComment(element))
    }
}