package de.sandstorm.configdocgen.core

import de.sandstorm.configdocgen.core.model.ConfigurationNamespace
import de.sandstorm.configdocgen.core.model.ConfigurationProperty
import javax.lang.model.element.TypeElement

interface DocumentationModelParser {
    fun getModel(): Map<ConfigurationNamespace, List<ConfigurationProperty>>
}

fun foo(a: TypeElement) {

}