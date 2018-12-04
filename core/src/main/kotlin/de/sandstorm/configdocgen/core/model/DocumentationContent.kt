package de.sandstorm.configdocgen.core.model

import com.fasterxml.jackson.annotation.JsonValue
import de.sandstorm.configdocgen.core.isCollectionType
import de.sandstorm.configdocgen.core.isMapType
import de.sandstorm.configdocgen.core.isPrimitiveConfigType
import de.sandstorm.configdocgen.core.isStringType
import javax.lang.model.element.Element
import javax.lang.model.util.Types

data class DocumentationContent(
        @get:JsonValue val content: String
) {
    companion object {
        fun empty() = DocumentationContent("!!![NO-DOCUMENTATION]!!!")
        fun rootNamespace() = DocumentationContent("[ROOT-NAMESPACE]")
        fun primitive(element: Element) = element.takeIf(::isPrimitiveConfigType)?.let { "[PRIMITIVE]: ${it.asType()}" }?.let(::DocumentationContent) ?: throw IllegalArgumentException("element $element is not a primitive type")
        fun map(element: Element) = element.takeIf(::isMapType)?.let { "[MAP]: ${it.asType()}" }?.let(::DocumentationContent) ?: throw IllegalArgumentException("element $element is not a map type")
        fun collection(element: Element, typeUtil: Types) = element.takeIf{ isCollectionType(it, typeUtil)}?.let { "[COLLECTION]: ${it.asType()}" }?.let(::DocumentationContent) ?: throw IllegalArgumentException("element $element is not a collection type")
        fun mapKey(element: Element) = element.takeIf(::isStringType)?.let { "[MAP-KEY]: ${it.asType()}" }?.let(::DocumentationContent) ?: throw IllegalArgumentException("element $element is not a map key type")
        fun collectionIndex() = DocumentationContent("[COLLECTION-INDEX]")
    }
}