package de.sandstorm.configdocgen.core.model

import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.Modifier

data class ConfigurationProperty(
        val namespace: NamespaceName,
        val name: PropertyName,
        val qualifiedName: QualifiedName = QualifiedName.build(
                namespaceName = namespace,
                propertyName = name
        ),
        val documentationContent: DocumentationContent,
        val accessibility: Accessibility,
        val valueType: ValueType
) {
    companion object {

        fun fromJavaField(field: Element, processingEnvironment: ProcessingEnvironment) = ConfigurationProperty(
                namespace = NamespaceName.fromJavaField(field),
                name = PropertyName.fromJavaField(field),
                accessibility = Accessibility.fromJavaField(field),
                valueType = ValueType.fromJavaField(field),
                documentationContent = DocumentationContent.fromJavaElement(field, processingEnvironment)
        )

        fun fromJavaMethod(method: Element, processingEnvironment: ProcessingEnvironment) = ConfigurationProperty(
                namespace = NamespaceName.fromJavaMethod(method),
                name = PropertyName.fromJavaMethod(method),
                accessibility = Accessibility.fromJavaMethod(method),
                valueType = ValueType.fromJavaMethod(method),
                documentationContent = DocumentationContent.fromJavaElement(method, processingEnvironment)
        )

    }
}