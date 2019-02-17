package de.sandstorm.configdocgen.core

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.sandstorm.configdocgen.core.model.ConfigurationDoc
import java.io.OutputStream

class JsonDocumentationModelWriter : DocumentationModelWriter {
    override fun write(model: ConfigurationDoc, outputStream: OutputStream): Unit = outputStream.use { out ->
        jacksonObjectMapper().writeValue(out, model)
    }
}
