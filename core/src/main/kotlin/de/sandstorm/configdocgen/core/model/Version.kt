package de.sandstorm.configdocgen.core.model

data class Version(
    val processorVersion: String,
    val coreVersion: String,
    val annotationsVersion: String
) {

    companion object {
        fun get() = Version(
            readVersionFile("de.sandstorm.configdocgen.processor"),
            readVersionFile("de.sandstorm.configdocgen.core"),
            readVersionFile("de.sandstorm.configdocgen.annotations")
        )

        private fun readVersionFile(moduleName: String): String {
            return Version::class.java.getResourceAsStream("/$moduleName-VERSION.txt").use {
                if (it == null) {
                    "!!! NO VERSION FILE: $moduleName !!!"
                } else {
                    String(it.readAllBytes())
                }
            }
        }
    }

}
