package de.sandstorm.configdocgen.core.test

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.common.io.ByteSource
import com.google.common.io.Resources
import com.google.common.truth.Truth
import com.google.testing.compile.CompileTester
import com.google.testing.compile.JavaFileObjects
import com.google.testing.compile.JavaSourcesSubjectFactory
import de.sandstorm.configdocgen.core.AbstractConfigurationDocumentationProcessor
import de.sandstorm.configdocgen.core.DEFAULT_OUTPUT_FILE_NAME
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.stream.Collectors
import javax.tools.JavaFileObject
import javax.tools.StandardLocation


open class ConfigurationDocTest(
        private val processor: AbstractConfigurationDocumentationProcessor
) {

    protected fun testSession(sessionIdentifier: String): TestSession = TestSession(sessionIdentifier)

    protected inner class TestSession(
            private val sessionIdentifier: String
    ) {

        private val sourceFileObjects: Map<String, JavaFileObject> = readAllSourceFiles(sessionIdentifier)

        fun successfulCompilation() = Truth.assert_()
                .about(JavaSourcesSubjectFactory.javaSources())
                .that(sourceFileObjects.values)
                .processedWith(processor)
                .compilesWithoutError()
                .let(::Warnings)

        inner class Warnings(
                private var successfulCompilationClause: CompileTester.SuccessfulCompilationClause
        ) {

            inner class SourceFile(
                    private val sourceFile: JavaFileObject
            ) {

                fun withNoDocWarning(element: String, line: Long, column: Long): TestSession.Warnings.SourceFile =
                        withWarningInFile(AbstractConfigurationDocumentationProcessor.buildNoJavadocForElementWarningMessage(element), line, column)

                fun withUnsupportedMapKeyTypeWarning(keyType: String, element: String, line: Long, column: Long): TestSession.Warnings.SourceFile =
                        withWarningInFile(AbstractConfigurationDocumentationProcessor.buildUnsupportedMapKeyTypeWarningMessage(keyType, element), line, column)

                fun withUnsupportedMapValueTypeWarning(valueType: String, element: String, line: Long, column: Long): TestSession.Warnings.SourceFile =
                        withWarningInFile(AbstractConfigurationDocumentationProcessor.buildUnsupportedMapValueTypeWarningMessage(valueType, element), line, column)

                fun and() = this@Warnings

                private fun withWarningInFile(warningMessage: String, line: Long, column: Long): SourceFile {
                    successfulCompilationClause = successfulCompilationClause.withWarningContaining(warningMessage)
                            .`in`(sourceFile)
                            .onLine(line)
                            .atColumn(column)
                            .and()
                    return this
                }
            }

            fun warnings() = when {
                sourceFileObjects.size == 1 -> SourceFile(sourceFileObjects.values.first())
                sourceFileObjects.size > 1 -> throw UnsupportedOperationException("Multiple source files; specify explicitly via 'withWarningInFile'")
                else -> throw UnsupportedOperationException("No source file given")
            }

            fun warnings(sourceFile: String): SourceFile = sourceFileObjects[sourceFile].let {
                when (it) {
                    null -> throw UnsupportedOperationException("Source file $sourceFile not found")
                    else -> SourceFile(it)
                }
            }

            fun totalCount(count: Int) = Content(
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
                                DEFAULT_OUTPUT_FILE_NAME)
                        .withContents(readTestTarget(sessionIdentifier))
            }
        }
    }

    companion object {
        private fun readAllSourceFiles(sessionIdentifier: String) = getResourceFiles("/given/$sessionIdentifier")
                .associateBy({ it }, { JavaFileObjects.forResource("given/$sessionIdentifier/$it") })

        private fun readTestTarget(sessionIdentifier: String): ByteSource {
            val url = ConfigurationDocTest::class.java.getResource("/expected/${sessionIdentifier}_doc.json")
            try {
                return ByteSource.wrap(jacksonObjectMapper().writeValueAsBytes(jacksonObjectMapper().readTree(Resources.asByteSource(url).read())))
            } catch (ex: IOException) {
                throw RuntimeException(ex)
            }
        }

        private fun getResourceFiles(path: String): List<String> = ConfigurationDocTest::class.java.getResourceAsStream(path).use { stream ->
            BufferedReader(InputStreamReader(stream)).use { reader ->
                return reader.lines().collect(Collectors.toList())
            }
        }

    }

}