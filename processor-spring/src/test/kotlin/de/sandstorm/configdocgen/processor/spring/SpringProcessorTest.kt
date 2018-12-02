package de.sandstorm.configdocgen.processor.spring

import de.sandstorm.configdocgen.core.test.ConfigurationDocTest
import org.junit.jupiter.api.Test

class SpringProcessorTest : ConfigurationDocTest(
        SpringProcessor()
) {

    @Test
    fun test_primitivesAndBoxedTypesConfiguration_successful() {
        testSession("Primitives")
                .successfulCompilation()
                .noWarnings()
                .assertOutput()
    }

    @Test
    fun test_nestedPojosConfiguration_successful() {
        testSession("NestedPojos")
                .successfulCompilation()
                .noWarnings()
                .assertOutput()
    }

    @Test
    fun test_nestedMapsConfiguration_successfulWithWarnings() {
        testSession("NestedMaps")
                .successfulCompilation()
                .withUnsupportedMapKeyTypeWarning("java.lang.Integer", "intKeyConfig", 62, 40)
                .withUnsupportedMapKeyTypeWarning("java.lang.Object", "objectKeyConfig", 67, 39)
                .andTotalCount(2)
                .assertOutput()
    }

}