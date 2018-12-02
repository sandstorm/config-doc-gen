package de.sandstorm.configdocgen.core

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.sandstorm.configdocgen.core.model.ConfigurationDoc
import javax.annotation.processing.Filer
import javax.tools.StandardLocation

const val DEFAULT_OUTPUT_FILE_NAME = "config-doc.json"

class JsonDocumentationModelWriter(
        private val outputFileName: String = DEFAULT_OUTPUT_FILE_NAME
) : DocumentationModelWriter {
    override fun write(model: ConfigurationDoc, filer: Filer): Unit = filer.createResource(
            StandardLocation.CLASS_OUTPUT,
            "",
            outputFileName
    ).openOutputStream().use { out ->
        jacksonObjectMapper().writeValue(out, model)
    }
}