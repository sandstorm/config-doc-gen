package de.sandstorm.configdocgen.core

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.sandstorm.configdocgen.core.model.ConfigurationDoc
import freemarker.template.Configuration
import freemarker.template.Template
import java.io.OutputStream

class ReactUiDocumentationModelWriter(
    val javascript: String = readResourceFile("/react-ui-compiled/main.js"),
    val stylesheet: String = readResourceFile("/react-ui-compiled/main.css")
) : DocumentationModelWriter {
    override fun write(model: ConfigurationDoc, outputStream: OutputStream) {
        val freemarkerConfiguration = Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS)
        freemarkerConfiguration.setClassForTemplateLoading(javaClass, "/")
        val template: Template = freemarkerConfiguration.getTemplate("/templates/react-ui.html.ftl")
        val templateModel = TemplateModel(
            jacksonObjectMapper().writeValueAsString(model),
            javascript,
            stylesheet
        )
        outputStream.bufferedWriter().use { out ->
            template.process(templateModel, out)
        }
    }

    companion object {
        private fun readResourceFile(path: String) =
            ReactUiDocumentationModelWriter::class.java.getResourceAsStream(path).bufferedReader().use { it.readText() }
    }
}

data class TemplateModel(
    val configDocJsonData: String,
    val reactUiJavascript: String,
    val reactUiStylesheet: String
)
