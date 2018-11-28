package de.sandstorm.configdocgen.processor.spring

import com.google.auto.service.AutoService
import de.sandstorm.configdocgen.annotations.ConfigNamespace
import de.sandstorm.configdocgen.annotations.ConfigProperty
import de.sandstorm.configdocgen.core.DocumentationModelWriter
import de.sandstorm.configdocgen.core.model.*
import java.io.IOException
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic


@SupportedAnnotationTypes(
        "de.sandstorm.configdocgen.annotations.ConfigNamespace",
        "de.sandstorm.configdocgen.annotations.ConfigProperty",
        "org.springframework.boot.context.properties.ConfigurationProperties"
)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor::class)
class SpringProcessor(
        private val writer: DocumentationModelWriter
) : AbstractProcessor() {

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        var namespaceClasses: MutableSet<out Element> = mutableSetOf()
        var propertiesElements: MutableSet<out Element> = mutableSetOf()
        for (annotation in annotations!!) {
            val annotatedElements = roundEnv!!.getElementsAnnotatedWith(annotation)
            if (annotation.qualifiedName.contentEquals(ConfigNamespace::class.java.name)) {
                namespaceClasses = annotatedElements
            } else if (annotation.qualifiedName.contentEquals(ConfigProperty::class.java.name)) {
                propertiesElements = annotatedElements
            } else if (annotation.qualifiedName.contentEquals(ConfigurationProperty::class.java.name)) {

            }
        }


        val properties = propertiesElements.map(::createProperty)
        val namespaces = namespaceClasses.map(::createNamespace)

        if (properties.isEmpty() && properties.isEmpty()) {
            return false
        }

        val model = ConfigurationDoc(namespaces = namespaces, properties = properties)

        try {
            writer.write(
                    model = model,
                    filer = processingEnv.filer
            )
        } catch (e: IOException) {
            processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, "Failed writing output\n")
            e.printStackTrace()
        }

        return false
    }

    private fun createProperty(element: Element) = when (element.kind) {
        ElementKind.FIELD -> propertyFromField(field = element)
        ElementKind.METHOD -> propertyFromMethod(method = element)
        else -> throw IllegalStateException("Property must be a method or field")
    }

    private fun propertyFromField(field: Element) = ConfigurationProperty(
            namespace = namespaceNameFromClassName(classElement = field.enclosingElement),
            name = PropertyName(name = field.simpleName.toString()),
            accessibility = if (field.modifiers.contains(Modifier.PRIVATE)) Accessibility.IMPLEMENTATION else Accessibility.API,
            valueType = ValueType(typeName = field.asType().toString(), required = false),
            documentationContent = getDocComment(element = field)
    )

    private fun propertyFromMethod(method: Element) = ConfigurationProperty(
            namespace = namespaceNameFromClassName(classElement = method.enclosingElement),
            name = PropertyName(name = method.simpleName.toString()),
            accessibility = if (method.modifiers.contains(Modifier.PRIVATE)) Accessibility.IMPLEMENTATION else Accessibility.API,
            valueType = ValueType(typeName = method.asType().toString(), required = false),
            documentationContent = getDocComment(element = method)
    )

    private fun createNamespace(element: Element) = when (element.kind) {
        ElementKind.CLASS -> namespaceFromClass(classElement = element)
        else -> throw IllegalStateException("Namespace must be a class")
    }

    private fun namespaceFromClass(classElement: Element): ConfigurationNamespace {
        val namespaceName = namespaceNameFromClassName(classElement = classElement)
        return ConfigurationNamespace(
                name = namespaceName,
                documentationContent = getDocComment(element = classElement)
        )
    }

    private fun namespaceNameFromClassName(classElement: Element) = NamespaceName(classElement.toString())

    private fun getDocComment(element: Element) = DocumentationContent(processingEnv.elementUtils.getDocComment(element))

    /*return ConfigurationProperty(
    namespace = NamespaceName()
    )*/

}