package de.sandstorm.configdocgen.processor.standalone

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.common.io.ByteSource
import com.google.common.io.Resources
import com.google.common.truth.Truth
import com.google.testing.compile.JavaFileObjects
import com.google.testing.compile.JavaSourceSubjectFactory
import de.sandstorm.configdocgen.core.JsonDocumentationModelWriter
import org.junit.jupiter.api.Test
import java.io.IOException
import javax.tools.StandardLocation


class StandaloneProcessorTest {
    @Test
    fun testConfiguration() {
        Truth.assert_()
                .about(JavaSourceSubjectFactory.javaSource())
                .that(JavaFileObjects.forResource("testSource/TestConfiguration.java"))
                .processedWith(StandaloneProcessor(JsonDocumentationModelWriter()))
                .compilesWithoutError()
                .and()
                .generatesFileNamed(StandardLocation.CLASS_OUTPUT,
                        "",
                        "config-doc.json")
                .withContents(readTestTarget("TestConfiguration_doc.json"))
    }

    fun readTestTarget(resource: String): ByteSource {
        val url = StandaloneProcessorTest::class.java.getResource("/testTarget/$resource")
        try {
            return ByteSource.wrap(jacksonObjectMapper().writeValueAsBytes(jacksonObjectMapper().readTree(Resources.asByteSource(url).read())))
        } catch (ex: IOException) {
            throw RuntimeException(ex)
        }
    }
}