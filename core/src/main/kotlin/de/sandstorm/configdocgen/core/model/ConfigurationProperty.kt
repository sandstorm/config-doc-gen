package de.sandstorm.configdocgen.core.model

data class ConfigurationProperty(
        val namespace: ConfigurationNamespace,
        val qualifiedName: QualifiedName,
        val documentation: String,
        val accessibility: Accessibility,
        val valueType: ValueType
)