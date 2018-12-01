package de.sandstorm.configdocgen.core.model

import javax.lang.model.element.Element

data class ConfigurationProperty(
        val namespace: NamespaceName,
        val name: PropertyName,
        val qualifiedName: QualifiedName = QualifiedName.build(
                namespaceName = namespace,
                propertyName = name
        ),
        val documentationContent: DocumentationContent,
        val accessibility: Accessibility,
        val type: PropertyType
) {
    companion object {

        fun fromJavaField(field: Element, namespace: NamespaceName, documentationContent: DocumentationContent): ConfigurationProperty = ConfigurationProperty(
                namespace = namespace,
                name = PropertyName.fromJavaField(field),
                accessibility = Accessibility.fromJavaField(field),
                type = PropertyType.fromJavaField(field),
                documentationContent = documentationContent
        )

        fun fromJavaMethod(method: Element, namespace: NamespaceName, documentationContent: DocumentationContent): ConfigurationProperty = ConfigurationProperty(
                namespace = namespace,
                name = PropertyName.fromJavaMethod(method),
                accessibility = Accessibility.fromJavaMethod(method),
                type = PropertyType.fromJavaMethod(method),
                documentationContent = documentationContent
        )
    }
}