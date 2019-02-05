package de.sandstorm.configdocgen.core

import de.sandstorm.configdocgen.core.model.ConfigurationDoc
import javax.annotation.processing.Filer

class NoOpsDocumentationModelWriter : DocumentationModelWriter {
    override fun write(moduleName: String, model: ConfigurationDoc, filer: Filer) {}
}
