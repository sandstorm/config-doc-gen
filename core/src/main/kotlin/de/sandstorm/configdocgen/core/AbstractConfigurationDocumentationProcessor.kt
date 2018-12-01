package de.sandstorm.configdocgen.core

import de.sandstorm.configdocgen.core.model.ConfigurationDoc
import de.sandstorm.configdocgen.core.model.ConfigurationNamespace
import de.sandstorm.configdocgen.core.model.ConfigurationProperty
import de.sandstorm.configdocgen.core.model.DocumentationContent
import java.io.IOException
import javax.annotation.processing.AbstractProcessor
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.tools.Diagnostic

abstract class AbstractConfigurationDocumentationProcessor(
        private val writer: DocumentationModelWriter,
        private var written: Boolean = false
) : AbstractProcessor() {

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
        processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, buildNoJavadocWarningMessage(element.kind, element.toString()), element)
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
        fun buildNoJavadocWarningMessage(kind: ElementKind, element: String) = "No javadoc comment found on ${kind.name.toLowerCase()}: $element"
        fun buildUnsupportedMapKeyTypeWarningMessage(keyType: String, element: String) = "Unsupported map key type '$keyType' on element: $element"
        fun buildUnsupportedMapValueTypeWarningMessage(valueType: String, element: String) = "Unsupported map value type '$valueType' on element: $element"
    }

}