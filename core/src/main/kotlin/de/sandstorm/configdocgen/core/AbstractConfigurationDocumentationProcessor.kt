package de.sandstorm.configdocgen.core

import de.sandstorm.configdocgen.core.model.ConfigurationDoc
import de.sandstorm.configdocgen.core.model.ConfigurationNamespace
import de.sandstorm.configdocgen.core.model.ConfigurationProperty
import java.io.IOException
import javax.annotation.processing.AbstractProcessor
import javax.tools.Diagnostic

abstract class AbstractConfigurationDocumentationProcessor(
        private val writer: DocumentationModelWriter
) : AbstractProcessor() {

    protected fun writeModel(namespaces: List<ConfigurationNamespace>, properties: List<ConfigurationProperty>): Boolean {
        if (properties.isEmpty() && properties.isEmpty()) {
            processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, "No configuration properties found\n")
            return false
        }

        try {
            writer.write(
                    model = ConfigurationDoc(namespaces, properties),
                    filer = processingEnv.filer
            )
        } catch (e: IOException) {
            processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, "Failed writing output\n")
            e.printStackTrace()
            return false
        }
        return true
    }

}