package de.sandstorm.configdocgen.core.model

data class ConfigurationDoc(
        val namespaces: List<ConfigurationNamespace>,
        val properties: List<ConfigurationProperty>
)