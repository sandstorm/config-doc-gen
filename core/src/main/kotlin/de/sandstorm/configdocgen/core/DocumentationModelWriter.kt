package de.sandstorm.configdocgen.core

import de.sandstorm.configdocgen.core.model.ConfigurationDoc
import javax.annotation.processing.Filer

@FunctionalInterface
interface DocumentationModelWriter {
    fun write(moduleName: String, model: ConfigurationDoc, filer: Filer)
}
