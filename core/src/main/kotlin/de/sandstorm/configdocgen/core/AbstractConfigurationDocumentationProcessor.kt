package de.sandstorm.configdocgen.core

import de.sandstorm.configdocgen.core.model.*
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.annotation.processing.AbstractProcessor
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.tools.Diagnostic
import javax.tools.StandardLocation

const val DEFAULT_SETTINGS_FILENAME = "config-doc.yaml"

abstract class AbstractConfigurationDocumentationProcessor : AbstractProcessor() {

    private val writer: DocumentationModelWriter by lazy {
        when (settings.writer.type) {
            WriterType.JSON -> JsonDocumentationModelWriter()
            WriterType.REACT_UI -> ReactUiDocumentationModelWriter()
            WriterType.NONE -> NoOpsDocumentationModelWriter()
        }
    }
    private val settings: ConfigDocSettings by lazy {
        ConfigDocSettings.loadFromYaml(
            getSettingsFile()
        )
    }

    private var written: Boolean = false
    private val alreadyNoDocWarnedElements: MutableSet<Element> = mutableSetOf()

    override fun getSupportedOptions(): MutableSet<String> {
        return mutableSetOf(
            "de.sandstorm.configdocgen.settingsFile",
            "de.sandstorm.configdocgen.moduleVersion"
        )
    }

    protected fun getModuleVersion() = processingEnv.options["de.sandstorm.configdocgen.moduleVersion"] ?: "No module version set!!!"

    private fun getSettingsFile(): InputStream {
        val explicitSettingsFileLocation = processingEnv.options["de.sandstorm.configdocgen.settingsFile"]
        return if (explicitSettingsFileLocation != null) {
            processingEnv.messager.printMessage(Diagnostic.Kind.NOTE, "Using explicit config doc settings file: $explicitSettingsFileLocation")
            URL(explicitSettingsFileLocation).openStream()
        } else {
            processingEnv.messager.printMessage(Diagnostic.Kind.NOTE, "Auto-detecting config doc settings file ...")
            // TODO this is not working with gradle!!!
            processingEnv.filer.getResource(StandardLocation.SOURCE_PATH, "", DEFAULT_SETTINGS_FILENAME).openInputStream()
        }
    }

    /**
     * Write the annotation processing result with the given writer strategy.
     */
    protected fun writeModel(builder: ConfigurationDocBuilder): Boolean {
        val model = builder.build()
        if (model.isEmpty()) {
            if (!written) {
                processingEnv.messager.printMessage(Diagnostic.Kind.NOTE, "No configuration properties found")
            }
            return true
        }
        try {
            // write data with strategy
            writer.write(
                model,
                processingEnv.filer.createResource(
                    StandardLocation.CLASS_OUTPUT,
                    "",
                    settings.writer.outputFileName
                ).openOutputStream()
            )
            written = true
        } catch (e: IOException) {
            processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, "Failed writing output")
            e.printStackTrace()
            return false
        }
        return true
    }

    protected fun getDocumentationFromJavaElement(element: Element): DocumentationContent {
        val docComment = processingEnv.elementUtils.getDocComment(element)
        if (docComment == null) {
            warnMissingDocumentation(element)
        }
        return docComment?.let(::DocumentationContent) ?: DocumentationContent.empty()
    }

    protected fun getDocumentationFromJavaFieldOrGetter(field: Element): DocumentationContent {
        val fieldDocComment = processingEnv.elementUtils.getDocComment(field)
        if (fieldDocComment == null) {
            val getterDocComment = findGetterForField(field)?.let { getter ->
                processingEnv.elementUtils.getDocComment(getter)
            }
            if (getterDocComment == null) {
                warnMissingDocumentation(field)
            }
            return getterDocComment?.let(::DocumentationContent) ?: DocumentationContent.empty()
        }
        return DocumentationContent(fieldDocComment)
    }

    private fun warnMissingDocumentation(element: Element) {
        if (!alreadyNoDocWarnedElements.contains(element)) {
            processingEnv.messager.printMessage(
                settings.processor.missingDocCommentDiagnostic,
                buildNoJavadocWarningMessage(element),
                element
            )
        }
        alreadyNoDocWarnedElements += element
    }

    inner class ConfigurationDocBuilder {

        private val namespaces: MutableSet<ConfigurationNamespace> = mutableSetOf()
        private val properties: MutableSet<ConfigurationProperty> = mutableSetOf()


        fun namespace(namespace: ConfigurationNamespace): ConfigurationNamespace {
            namespaces += namespace
            return namespace
        }

        fun property(property: ConfigurationProperty) {
            properties += property
        }

        fun build(): ConfigurationDoc {
            return ConfigurationDoc(settings.moduleName, getModuleVersion(), Version.get(), namespaces.toList(), properties.toList())
        }
    }

    protected fun printInfo(processorDescription: String) {
        processingEnv.messager.printMessage(Diagnostic.Kind.NOTE, "Sandstorm ConfigDocGen: $processorDescription")
        processingEnv.messager.printMessage(Diagnostic.Kind.NOTE, "Config Doc Versions: ${Version.get().processorVersion} | ${Version.get().coreVersion} | ${Version.get().annotationsVersion}")
        processingEnv.messager.printMessage(Diagnostic.Kind.NOTE, "Module version: ${getModuleVersion()}")
    }

    companion object {
        fun buildNoJavadocWarningMessage(element: Element): String {
            return when (element.kind) {
                ElementKind.FIELD -> buildNoJavadocForFieldWarningMessage(element.toString(), element.enclosingElement.toString())
                ElementKind.CLASS -> buildNoJavadocForClassWarningMessage(element.toString())
                else -> buildNoJavadocForElementWarningMessage(element.toString())
            }
        }

        fun buildNoJavadocForElementWarningMessage(element: String) = "No javadoc comment found on $element"
        fun buildNoJavadocForFieldWarningMessage(field: String, clazz: String) = buildNoJavadocForElementWarningMessage("field: $clazz#$field")
        fun buildNoJavadocForClassWarningMessage(clazz: String) = buildNoJavadocForElementWarningMessage("class: $clazz")
        fun buildUnsupportedMapKeyTypeWarningMessage(keyType: String, element: String) = "Unsupported map key type '$keyType' on element: $element"
        fun buildUnsupportedMapValueTypeWarningMessage(valueType: String, element: String) = "Unsupported map value type '$valueType' on element: $element"
        fun buildUnsupportedCollectionValueTypeWarningMessage(valueType: String, element: String) = "Unsupported collection value type '$valueType' on element: $element"
    }

}
