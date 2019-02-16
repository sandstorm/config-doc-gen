package de.sandstorm.configdocgen.core.model

import com.fasterxml.jackson.annotation.JsonValue

data class Version(
    @get:JsonValue val version: String
) {

    companion object {
        fun get() = Version(
            Version::class.java.getResourceAsStream("/de.sandstorm.configdocgen.processor-VERSION.txt").use {
                String(it.readAllBytes())
            } + " / " + Version::class.java.getResourceAsStream("/de.sandstorm.configdocgen.core-VERSION.txt").use {
                String(it.readAllBytes())
            }
        )
    }

}
