package de.sandstorm.configdocgen.core

import de.sandstorm.configdocgen.core.model.ConfigurationDoc
import de.sandstorm.configdocgen.core.model.ConfigurationNamespace
import de.sandstorm.configdocgen.core.model.ConfigurationProperty
import de.sandstorm.configdocgen.core.model.DocumentationContent
import java.io.IOException
import java.net.URL
import javax.annotation.processing.AbstractProcessor
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.tools.Diagnostic
import java.io.File
import java.lang.IllegalStateException
import java.nio.file.Files
import java.nio.file.Paths

const val DEFAULT_SETTINGS_FILENAME = "config-doc.yaml"

abstract class AbstractConfigurationDocumentationProcessor : AbstractProcessor() {

    private val writer: DocumentationModelWriter
    private val settings: ConfigDocSettings by lazy {
        ConfigDocSettings.loadFromYaml(
            getSettingsFileUrl().openStream()
        )
    }

    private var written: Boolean = false
    private val alreadyNoDocWarnedElements: MutableSet<Element> = mutableSetOf()


    init {
        writer = JsonDocumentationModelWriter()
    }

    override fun getSupportedOptions(): MutableSet<String> {
        return mutableSetOf(
            "de.sandstorm.configdocgen.settingsFile"
        )
    }

    private fun getSettingsFileUrl(): URL {
        val explicitSettingsFileLocation = processingEnv.options["de.sandstorm.configdocgen.settingsFile"]
        return if (explicitSettingsFileLocation != null) {
            URL(explicitSettingsFileLocation)
        } else {
            findSourcePathAutomatically()
        }
    }

    private fun findSourcePathAutomatically(): URL {
        try {
            val generationForPath = processingEnv.filer.createSourceFile("PathFor" + javaClass.simpleName)
            val writer = generationForPath.openWriter()
            val sourcePath = Paths.get(File(generationForPath.toUri().path).parentFile.path, DEFAULT_SETTINGS_FILENAME)
            if (!Files.exists(sourcePath)) {
                processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, "Could not read settings file: $sourcePath")
            }
            writer.close()
            generationForPath.delete()
            return sourcePath.toUri().toURL()
        } catch (e: IOException) {
            processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, "Unable to determine source file path!")
        }
        throw IllegalStateException("Unable to determine source file path!")
    }

    /**
     * Write the annotation processing result with the given writer strategy.
     */
    protected fun writeModel(builder: ConfigurationDocBuilder): Boolean {
        println(settings.foo)
        val model = builder.build()
        if (model.isEmpty()) {
            if (!written) {
                processingEnv.messager.printMessage(Diagnostic.Kind.NOTE, "No configuration properties found")
            }
            return true
        }
        try {
            // write data with strategy
            writer.write(model, processingEnv.filer)
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
        // TODO mayme configure to throw a ERROR instead of a warning
        if (!alreadyNoDocWarnedElements.contains(element)) {
            processingEnv.messager.printMessage(
                Diagnostic.Kind.WARNING,
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
            return ConfigurationDoc(namespaces.toList(), properties.toList())
        }
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
