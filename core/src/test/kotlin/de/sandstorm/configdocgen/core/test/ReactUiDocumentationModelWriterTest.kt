package de.sandstorm.configdocgen.core.test

import de.sandstorm.configdocgen.core.ReactUiDocumentationModelWriter
import de.sandstorm.configdocgen.core.model.ConfigurationDoc
import de.sandstorm.configdocgen.core.model.Version
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream

class ReactUiDocumentationModelWriterTest {

    @Test
    fun test_writeReactUi() {
        val out = ByteArrayOutputStream()
        ReactUiDocumentationModelWriter(
            javascript = "JS goes here",
            stylesheet = "CSS goes here"
        ).write(ConfigurationDoc(
            "Test Module",
            Version("Version"),
            properties = emptyList(),
            namespaces = emptyList()
        ), out)

        Assertions.assertEquals("""
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="theme-color" content="#000000">
    <title>Config Doc (powered by Sandstorm)</title>
    <script type="text/javascript">
        var CONFIG_DOC_JSON_DATA = {"moduleName":"Test Module","processorVersion":"Version","namespaces":[],"properties":[]};
    </script>
    <style type="text/css">
        CSS goes here
    </style>
</head>
<body>
<noscript>
    You need to enable JavaScript to run this app.
</noscript>
<div id="root"></div>
<script type="text/javascript">
    JS goes here
</script>
</body>
</html>

        """.trimIndent(), String(out.toByteArray()))
    }

}
