package de.sandstorm.configdocgen.core

import de.sandstorm.configdocgen.core.model.ConfigurationDoc
import java.io.OutputStream

class NoOpsDocumentationModelWriter : DocumentationModelWriter {
    override fun write(model: ConfigurationDoc, outputStream: OutputStream) {}
}
