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
                .warnings()
                .withUnsupportedMapKeyTypeWarning("java.lang.Integer", "intKeyConfig", 62, 40)
                .withUnsupportedMapKeyTypeWarning("java.lang.Object", "objectKeyConfig", 67, 39)
                .and()
                .totalCount(2)
                .assertOutput()
    }

    @Test
    fun test_nestedCollectionsConfiguration_successfulWithWarnings() {
        testSession("NestedCollections")
                .successfulCompilation()
                .warnings()
                .withUnsupportedCollectionValueTypeWarning("java.lang.Object", "objectProp", 75, 32)
                .and()
                .totalCount(1)
                .assertOutput()
    }

    @Test
    fun test_noDocWarningsConfiguration_successfulWithWarnings() {
        testSession("NoDocWarnings")
                .successfulCompilation()
                .warnings("NoDocWarnings.java")
                .withNoDocWarning("class: testSource.NoDocWarnings", 7, 1)
                .withNoDocWarning("field: testSource.NoDocWarnings#stringWithoutDoc", 9, 20)
                .withNoDocWarning("field: testSource.NoDocWarnings#nestedPojo", 11, 34)
                .withNoDocWarning("field: testSource.NoDocWarnings#nestedPojoInOwnClass", 13, 34)
                .withNoDocWarning("field: testSource.NoDocWarnings#nestedPojoMap", 15, 47)
                .withNoDocWarning("class: testSource.NoDocWarnings.NestedPojoWithoutDoc", 41, 12)
                .withNoDocWarning("field: testSource.NoDocWarnings.NestedPojoWithoutDoc#nestedIntWithoutDoc", 43, 21)
                .and()
                .warnings("NestedPojoInOwnClass.java")
                .withNoDocWarning("class: testSource.NestedPojoInOwnClass", 3, 1)
                .withNoDocWarning("field: testSource.NestedPojoInOwnClass#nestedProp", 5, 20)
                .and()
                .totalCount(9)
                .assertOutput()
    }

}