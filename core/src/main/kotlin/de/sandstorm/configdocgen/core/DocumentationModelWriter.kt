package de.sandstorm.configdocgen.core

import de.sandstorm.configdocgen.core.model.ConfigurationDoc
import java.io.OutputStream

@FunctionalInterface
interface DocumentationModelWriter {
    fun write(model: ConfigurationDoc, outputStream: OutputStream)
}
