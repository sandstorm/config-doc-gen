package de.sandstorm.configdocgen.core.test

import de.sandstorm.configdocgen.core.ReactUiDocumentationModelWriter
import de.sandstorm.configdocgen.core.model.ConfigurationDoc
import de.sandstorm.configdocgen.core.model.Version
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import java.io.ByteArrayOutputStream
import java.util.regex.Pattern

class ReactUiDocumentationModelWriterTest {

    @Test
    fun test_writeReactUi() {
        val out = ByteArrayOutputStream()
        ReactUiDocumentationModelWriter(
            javascript = "JS goes here",
            stylesheet = "CSS goes here"
        ).write(ConfigurationDoc(
            "Test Module",
            "Module Version",
            Version("Processor Version", "Core Version", "Annotations Version"),
            properties = emptyList(),
            namespaces = emptyList()
        ), out)

        assertTemplate("JS goes here", "CSS goes here", out)
    }

    @Test
    fun test_compiledUiIsPackaged() {
        val out = ByteArrayOutputStream()
        ReactUiDocumentationModelWriter().write(ConfigurationDoc(
            "Test Module",
            "Module Version",
            Version("Processor Version", "Core Version", "Annotations Version"),
            properties = emptyList(),
            namespaces = emptyList()
        ), out)

        assertTemplate(".+", ".+", out)
    }

    private fun assertTemplate(javascript: String, stylesheet: String, out: ByteArrayOutputStream) {
        val actual = String(out.toByteArray())
        val expectedPattern = buildTemplatePattern(javascript, stylesheet)
        if (!actual.matches(expectedPattern)) {
            println("Template mismatch")
            println("Expected Pattern: \n$expectedPattern")
            println("But was: \n$actual")
            fail("Template mismatch")
        }
    }

    private fun buildTemplatePattern(javascript: String, stylesheet: String): Regex {
        return Regex(Pattern.quote("""
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="theme-color" content="#000000">
    <title>Config Doc (powered by Sandstorm)</title>
    <script type="text/javascript">
        var CONFIG_DOC_JSON_DATA = {"moduleName":"Test Module","moduleVersion":"Module Version","generatorVersion":{"processorVersion":"Processor Version","coreVersion":"Core Version","annotationsVersion":"Annotations Version"},"namespaces":[],"properties":[]};
    </script>
    <style type="text/css">""".trimIndent()) + "\\s*?$stylesheet\\s*?" + Pattern.quote("""</style>
</head>
<body>
<noscript>
    You need to enable JavaScript to run this app.
</noscript>
<div id="root"></div>
<script type="text/javascript">""".trimIndent()) + "\\s*$javascript\\s*" + Pattern.quote("""</script>
</body>
</html>
        """.trimIndent()) + "\\s*", setOf(RegexOption.DOT_MATCHES_ALL, RegexOption.MULTILINE))
    }

}
