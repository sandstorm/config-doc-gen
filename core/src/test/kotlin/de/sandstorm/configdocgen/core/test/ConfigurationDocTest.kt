package de.sandstorm.configdocgen.core.test

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.common.io.ByteSource
import com.google.common.io.Resources
import com.google.common.truth.Truth
import com.google.testing.compile.CompileTester
import com.google.testing.compile.JavaFileObjects
import com.google.testing.compile.JavaSourceSubjectFactory
import de.sandstorm.configdocgen.core.AbstractConfigurationDocumentationProcessor
import java.io.IOException
import javax.lang.model.element.ElementKind
import javax.tools.StandardLocation

const val OUTPUT_FILE_NAME = "config-doc.json"

open class ConfigurationDocTest(
        private val processor: AbstractConfigurationDocumentationProcessor
) {

    protected fun testSession(sourceFileName: String): TestSession = TestSession(sourceFileName)

    protected inner class TestSession(
            private val sourceFileName: String
    ) {

        private val sourceFileObject = JavaFileObjects.forResource("given/$sourceFileName.java")

        fun successfulCompilation() = Truth.assert_()
                .about(JavaSourceSubjectFactory.javaSource())
                .that(sourceFileObject)
                .processedWith(processor)
                .compilesWithoutError()
                .let(::Warnings)

        inner class Warnings(
                private var successfulCompilationClause: CompileTester.SuccessfulCompilationClause
        ) {

            fun withNoDocWarning(elementKind: ElementKind, element: String, line: Long, column: Long): Warnings =
                    withWarning(AbstractConfigurationDocumentationProcessor.buildNoJavadocWarningMessage(elementKind, element), line, column)

            fun withUnsupportedMapKeyTypeWarning(keyType: String, element: String, line: Long, column: Long): Warnings =
                    withWarning(AbstractConfigurationDocumentationProcessor.buildUnsupportedMapKeyTypeWarningMessage(keyType, element), line, column)

            fun withUnsupportedMapValueTypeWarning(valueType: String, element: String, line: Long, column: Long): Warnings =
                    withWarning(AbstractConfigurationDocumentationProcessor.buildUnsupportedMapValueTypeWarningMessage(valueType, element), line, column)

            private fun withWarning(warningMessage: String, line: Long, column: Long): Warnings {


                successfulCompilationClause = successfulCompilationClause.withWarningContaining(warningMessage)
                        .`in`(sourceFileObject)
                        .onLine(line)
                        .atColumn(column)
                        .and()
                return this
            }

            fun andTotalCount(count: Int) = Content(
                    successfulCompilationClause
                            .withWarningCount(count)
            )

            fun noWarnings() = Content(
                    successfulCompilationClause
                            .withWarningCount(0)
            )
        }

        inner class Content(
                private val withWarningCount: CompileTester.SuccessfulCompilationClause
        ) {

            fun assertOutput() {
                withWarningCount.and()
                        .generatesFileNamed(StandardLocation.CLASS_OUTPUT,
                                "",
                                OUTPUT_FILE_NAME)
                        .withContents(readTestTarget(sourceFileName))
            }
        }
    }

    companion object {
        private fun readTestTarget(resource: String): ByteSource {
            val url = ConfigurationDocTest::class.java.getResource("/expected/${resource}_doc.json")
            try {
                return ByteSource.wrap(jacksonObjectMapper().writeValueAsBytes(jacksonObjectMapper().readTree(Resources.asByteSource(url).read())))
            } catch (ex: IOException) {
                throw RuntimeException(ex)
            }
        }
    }

}