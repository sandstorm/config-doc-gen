package de.sandstorm.testSource;

import de.sandstorm.configdocgen.annotations.ConfigNamespace;
import de.sandstorm.configdocgen.annotations.ConfigProperty;

/**
 * This is a POJO configuration class. Used in unit tests...
 * The class documentation should be used as source for
 * the configuration namespace.
 */
@ConfigNamespace
public class TestConfiguration {

    /**
     * Foo configuration documentation ...
     *
     * @return returns a hardcoded string
     */
    @ConfigProperty
    public String getFooConfig() {
        return "some hardcoded config string";
    }

}