package de.sandstorm.configdocgen.core.model

import com.fasterxml.jackson.annotation.JsonValue

data class QualifiedName(
        @get:JsonValue val name: String
) {
    companion object {
        fun build(namespaceName: NamespaceName, propertyName: PropertyName) = QualifiedName(
                if (namespaceName.isDefaultNamespace()) propertyName.name else "${namespaceName.name}.${propertyName.name}"
        )
    }
}