package de.sandstorm.configdocgen.core.model

import javax.lang.model.element.Element

data class ConfigurationNamespace(
        val name: NamespaceName,
        val documentationContent: DocumentationContent
) {
    companion object {

        fun fromJavaClass(clazz: Element) = ConfigurationNamespace(
                name = namespaceNameFromClassName(classElement = classElement),
                documentationContent = getDocComment(element = classElement)
        )

    }
}