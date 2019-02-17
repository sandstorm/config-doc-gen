package de.sandstorm.configdocgen.processor.standalone

import de.sandstorm.configdocgen.core.WriterType
import de.sandstorm.configdocgen.core.test.ConfigurationDocTest
import org.junit.jupiter.api.Test

class StandaloneProcessorTest : ConfigurationDocTest(StandaloneProcessor()) {
    @Test
    fun testConfiguration() {
        testSession("TestConfiguration")
            .successfulCompilation()
            .warnings()
            .withNoDocWarning("field: de.sandstorm.testSource.TestConfiguration#propertyWithoutDocShouldProduceACompilerWarning", 44, 20)
            .and()
            .totalCount(1)
            .assertOutput(WriterType.JSON.defaultOutputFileName)
    }

}
