package de.sandstorm.configdocgen.processor.standalone

import de.sandstorm.configdocgen.core.test.ConfigurationDocTest
import org.junit.jupiter.api.Test
import javax.lang.model.element.ElementKind.FIELD

class StandaloneProcessorTest : ConfigurationDocTest(StandaloneProcessor()) {
    @Test
    fun testConfiguration() {
        testSession("TestConfiguration")
                .successfulCompilation()
                .withNoDocWarning(FIELD, "propertyWithoutDocShouldProduceACompilerWarning", 44, 20)
                .andTotalCount(1)
                .assertOutput()
    }

}