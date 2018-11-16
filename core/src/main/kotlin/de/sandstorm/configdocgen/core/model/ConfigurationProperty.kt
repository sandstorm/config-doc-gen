package de.sandstorm.configdocgen.core.model

data class ConfigurationProperty(
        val namespace: NamespaceName,
        val name: PropertyName,
        val qualifiedName: QualifiedName = QualifiedName.build(
                namespaceName = namespace,
                propertyName = name
        ),
        val documentationText: DocumentationText,
        val accessibility: Accessibility,
        val valueType: ValueType
)