package de.sandstorm.configdocgen.core

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import de.sandstorm.configdocgen.core.model.ConfigurationDoc
import freemarker.template.Configuration
import freemarker.template.Template
import javax.annotation.processing.Filer
import javax.tools.StandardLocation

const val DEFAULT_REACT_UI_INDEX_FILE_NAME = "config-doc-react-ui.html"

class ReactUiDocumentationModelWriter(
    private val outputFileName: String = DEFAULT_REACT_UI_INDEX_FILE_NAME
) : DocumentationModelWriter {
    override fun write(moduleName: String, model: ConfigurationDoc, filer: Filer) {
        val freemarkerConfiguration = Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS)
        freemarkerConfiguration.setClassForTemplateLoading(javaClass, "/")
        val template: Template = freemarkerConfiguration.getTemplate("/templates/react-ui.html.ftl")
        val templateModel = TemplateModel(
            moduleName,
            jacksonObjectMapper().writeValueAsString(model),
            readResourceFile("/react-ui-compiled/main.js"),
            readResourceFile("/react-ui-compiled/main.css")
        )
        filer.createResource(
            StandardLocation.CLASS_OUTPUT,
            "",
            outputFileName
        ).openOutputStream().bufferedWriter().use { out ->
            template.process(templateModel, out)
        }
    }

    private fun readResourceFile(path: String) =
        ReactUiDocumentationModelWriter::class.java.getResourceAsStream(path).bufferedReader().use { it.readText() }
}

data class TemplateModel(
    val configDocModuleName: String,
    val configDocJsonData: String,
    val reactUiJavascript: String,
    val reactUiStylesheet: String
)
