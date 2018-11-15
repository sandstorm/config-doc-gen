package de.sandstorm.configdocgen.core.model

data class ConfigurationNamespace(
        val name: String
) {

    companion object {
        fun empty() = ConfigurationNamespace("")
    }
}