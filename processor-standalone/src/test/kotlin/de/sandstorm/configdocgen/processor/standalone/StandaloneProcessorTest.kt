package de.sandstorm.configdocgen.processor.standalone

import com.google.common.truth.Truth
import com.google.testing.compile.JavaFileObjects
import com.google.testing.compile.JavaSourceSubjectFactory
import org.junit.jupiter.api.Test

class StandaloneProcessorTest {
    @Test
    fun testConfiguration() {
        Truth.assert_()
                .about(JavaSourceSubjectFactory.javaSource())
                .that(JavaFileObjects.forResource("testSource/TestConfiguration.java"))
                .processedWith(StandaloneProcessor())
                .compilesWithoutError()
                //.and()
                //.generatesFiles()
    }
}