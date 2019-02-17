package de.sandstorm.configdocgen.core.model

import com.fasterxml.jackson.annotation.JsonIgnore

data class ConfigurationDoc(
    val moduleName: String,
    val moduleVersion: String,
    val howToFeatures: Set<HowToFeature>,
    val generatorVersion: Version,
    val namespaces: List<ConfigurationNamespace>,
    val properties: List<ConfigurationProperty>
) {

    @JsonIgnore
    fun isEmpty(): Boolean {
        return namespaces.isEmpty() && properties.isEmpty()
    }
}
