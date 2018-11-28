package de.sandstorm.configdocgen.core.model

import com.fasterxml.jackson.annotation.JsonValue
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import javax.tools.Diagnostic

data class DocumentationContent(
        @get:JsonValue val content: String
) {
    companion object {
        fun fromJavaElement(element: Element, processingEnvironment: ProcessingEnvironment) =
                DocumentationContent(getDocumentationContentAndWarnIfNotPresent(element, processingEnvironment))

        private fun getDocumentationContentAndWarnIfNotPresent(element: Element, processingEnvironment: ProcessingEnvironment): String {
            val docString: String? = processingEnvironment.elementUtils.getDocComment(element)
            return if (docString == null) {
                // warn and return empty string
                processingEnvironment.messager.printMessage(Diagnostic.Kind.WARNING, "No javadoc comment found on element: $element", element)
                ""
            } else docString
        }
    }
}