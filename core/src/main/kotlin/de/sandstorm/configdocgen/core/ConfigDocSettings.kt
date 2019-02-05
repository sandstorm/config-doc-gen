package de.sandstorm.configdocgen.core

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.InputStream
import javax.tools.Diagnostic

data class ConfigDocSettings(
    val moduleName: String = "No module name set!",
    val processor: ProcessorSettings = ProcessorSettings(),
    val writer: WriterSettings = WriterSettings()
) {
    companion object {
        fun loadFromYaml(inputStream: InputStream): ConfigDocSettings {
            val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()

            return inputStream.use {
                mapper.readValue(it, ConfigDocSettings::class.java)
            }
        }
    }
}

data class ProcessorSettings(
    val missingDocCommentDiagnostic: Diagnostic.Kind = Diagnostic.Kind.WARNING
)

data class WriterSettings(
    val type: WriterType = WriterType.JSON
)

enum class WriterType {
    JSON,
    REACT_UI,
    NONE
}
