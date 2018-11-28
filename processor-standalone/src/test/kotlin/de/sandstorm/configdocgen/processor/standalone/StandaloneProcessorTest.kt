package de.sandstorm.configdocgen.processor.standalone

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.common.io.ByteSource
import com.google.common.io.Resources
import com.google.common.truth.Truth
import com.google.testing.compile.CompileTester
import com.google.testing.compile.JavaFileObjects
import com.google.testing.compile.JavaSourceSubjectFactory
import de.sandstorm.configdocgen.core.JsonDocumentationModelWriter
import org.junit.jupiter.api.Test
import java.io.IOException
import javax.tools.JavaFileObject
import javax.tools.StandardLocation

class StandaloneProcessorTest {
    @Test
    fun testConfiguration() {
        val testConfigurationJavaFile = JavaFileObjects.forResource("testSource/TestConfiguration.java")
        successfulCompilation(testConfigurationJavaFile)
                .and()
                .generatesFileNamed(StandardLocation.CLASS_OUTPUT,
                        "",
                        "config-doc.json")
                .withContents(readTestTarget("TestConfiguration_doc.json"))
        successfulCompilation(testConfigurationJavaFile)
                .withWarningContaining("No javadoc comment found on element: ")
                .`in`(testConfigurationJavaFile)
                .onLine(44)
                .atColumn(20)
                .and()
                .withWarningCount(1)
    }

    private fun successfulCompilation(testConfigurationJavaFile: JavaFileObject): CompileTester.SuccessfulCompilationClause = Truth.assert_()
            .about(JavaSourceSubjectFactory.javaSource())
            .that(testConfigurationJavaFile)
            .processedWith(StandaloneProcessor(JsonDocumentationModelWriter()))
            .compilesWithoutError()

    private fun readTestTarget(resource: String): ByteSource {
        val url = StandaloneProcessorTest::class.java.getResource("/testTarget/$resource")
        try {
            return ByteSource.wrap(jacksonObjectMapper().writeValueAsBytes(jacksonObjectMapper().readTree(Resources.asByteSource(url).read())))
        } catch (ex: IOException) {
            throw RuntimeException(ex)
        }
    }
}