package de.sandstorm.configdocgen.processor.spring

import de.sandstorm.configdocgen.core.*
import de.sandstorm.configdocgen.core.model.*
import org.springframework.boot.context.properties.ConfigurationProperties
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.annotation.processing.SupportedSourceVersion
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

/**
 *
 * Map types:
 * One single namespace is created for the field,
 * as well as one "wildcard" namespace for the map values itself.
 */
@SupportedAnnotationTypes(
        "de.sandstorm.configdocgen.annotations.ConfigNamespace",
        "de.sandstorm.configdocgen.annotations.ConfigProperty",
        "de.sandstorm.configdocgen.annotations.ConfigApi",
        "org.springframework.boot.context.properties.ConfigurationProperties"
)
@SupportedSourceVersion(SourceVersion.RELEASE_8)
class SpringProcessor(writer: DocumentationModelWriter = JsonDocumentationModelWriter()) : AbstractConfigurationDocumentationProcessor(writer) {

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        var configurationPropertiesClasses: Set<Element> = emptySet()
        for (annotation in annotations!!) {
            val annotatedElements = roundEnv!!.getElementsAnnotatedWith(annotation)
            if (annotation.qualifiedName.contentEquals(ConfigurationProperties::class.java.name)) {
                configurationPropertiesClasses = annotatedElements
            }
        }

        val builder = ConfigurationDocBuilder()

        configurationPropertiesClasses
                .forEach { rootNamespaceClass ->
                    val rootNamespace = namespaceFromClass(rootNamespaceClass)
                    builder.namespace(rootNamespace)
                    propertiesFromClass(builder, rootNamespaceClass, rootNamespace)
                }

        return writeModel(builder)
    }

    private fun namespaceFromClass(clazz: Element) = ConfigurationNamespace(
            name = namespaceNameFromSpringAnnotation(clazz),
            type = NamespaceType.fromJavaElement(clazz),
            documentationContent = getDocumentationFromJavaElement(clazz)
    )

    private fun nestedNamespaceFromField(namespace: ConfigurationNamespace, field: Element): ConfigurationNamespace =
            namespace.nestedNamespace(field, getDocumentationFromJavaElement(field))

    private fun namespaceNameFromSpringAnnotation(clazz: Element): NamespaceName {
        val annotation = clazz.getAnnotation(ConfigurationProperties::class.java)
        return NamespaceName(annotation.prefix)
    }

    private fun propertiesFromClass(builder: ConfigurationDocBuilder, clazz: Element, namespace: ConfigurationNamespace) {
        clazz.enclosedElements
                // only fields that have a public setter
                .filter(::isField)
                .forEach { field ->
                    when {
                        // primitives and Strings are considered configuration properties
                        isPrimitiveConfigType(field) -> {
                            if (isSetterForFieldPublic(field)) {
                                builder.property(propertyFromPrimitiveField(namespace, field))
                            }
                        }
                        // TODO list / set types
                        // Maps are considered as two nested namespaces.
                        // First, the field itself with the field documentation.
                        // Second, the key wildcard with the map value type class documentation.
                        // map types with "key placeholders"
                        isMapType(field) -> {
                            val mapKeyType = processingEnv.typeUtils.asElement(getMapKeyTypeParameter(field))
                            if (isStringType(mapKeyType)) {
                                val mapValueType = processingEnv.typeUtils.asElement(getMapValueTypeParameter(field))
                                when {
                                    isPrimitiveConfigType(mapValueType) -> {
                                        // the wildcard namespace has no properties for primitive map value types
                                        builder.namespace(
                                                builder.namespace(nestedNamespaceFromField(namespace, field))
                                                        .nestedNamespaceWithKeyWildcard(mapValueType, DocumentationContent.empty()))
                                    }
                                    isUnsupportedMapValueType(mapValueType) -> {
                                        processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, buildUnsupportedMapValueTypeWarningMessage(mapKeyType.asType().toString(), field.simpleName.toString()), field)
                                    }
                                    else -> {
                                        // POJO -> find all configuration properties in the map value type class
                                        propertiesFromClass(
                                                builder,
                                                mapValueType,
                                                builder.namespace(
                                                        builder.namespace(nestedNamespaceFromField(namespace, field))
                                                                .nestedNamespaceWithKeyWildcard(mapValueType, getDocumentationFromJavaElement(mapValueType)))
                                        )
                                    }
                                }
                            } else {
                                processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, buildUnsupportedMapKeyTypeWarningMessage(mapKeyType.asType().toString(), field.simpleName.toString()), field)
                            }
                        }
                        // complex types (POJOs)
                        // TODO should we consider the class documentation as important?
                        // -> currently we take the field documentation
                        else -> {
                            val nestedNamespaceClass = processingEnv.typeUtils.asElement(field.asType())
                            // find all configuration properties from the nested class
                            propertiesFromClass(
                                    builder,
                                    nestedNamespaceClass,
                                    builder.namespace(nestedNamespaceFromField(namespace, field))
                            )
                        }
                    }
                }
    }

    private fun propertyFromPrimitiveField(namespace: ConfigurationNamespace, field: Element) = ConfigurationProperty(
            name = PropertyName.fromJavaField(field),
            namespace = namespace.name,
            type = PropertyType.fromJavaField(field),
            accessibility = Accessibility.fromJavaGetterOrField(field),
            documentationContent = getDocumentationFromJavaFieldOrGetter(field)
    )

}