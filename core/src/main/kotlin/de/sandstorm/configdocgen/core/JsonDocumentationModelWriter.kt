package de.sandstorm.configdocgen.core

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.sandstorm.configdocgen.core.model.ConfigurationDoc
import javax.annotation.processing.Filer
import javax.tools.StandardLocation

class JsonDocumentationModelWriter : DocumentationModelWriter {
    override fun write(model: ConfigurationDoc, filer: Filer): Unit = filer.createResource(
            StandardLocation.CLASS_OUTPUT,
            "",
            "config-doc.json"
    ).openOutputStream().use { out ->
        jacksonObjectMapper().writeValue(out, model)
    }
}