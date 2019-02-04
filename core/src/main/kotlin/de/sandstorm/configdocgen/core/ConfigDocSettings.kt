package de.sandstorm.configdocgen.core

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.InputStream

data class ConfigDocSettings(
    val foo: String
) {
    companion object {
        public fun loadFromYaml(inputStream: InputStream): ConfigDocSettings {
            val mapper = ObjectMapper(YAMLFactory()) // Enable YAML parsing
            mapper.registerModule(KotlinModule()) // Enable Kotlin support

            return inputStream.use {
                mapper.readValue(it, ConfigDocSettings::class.java)
            }
        }
    }
}
