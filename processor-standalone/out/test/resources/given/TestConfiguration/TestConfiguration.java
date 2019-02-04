package de.sandstorm.testSource;

import de.sandstorm.configdocgen.annotations.ConfigApi;
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
     * This property is field-annotated.
     * The accessibility should be considered "API", since this property has a public getter.
     */
    @ConfigProperty
    private String configPropertyFromField;

    public String getConfigPropertyFromField() {
        return configPropertyFromField;
    }

    /**
     * This property is also field-annotated.
     * The accessibility should be considered "IMPLEMENTATION", since this property has no public getter.
     */
    @ConfigProperty
    private String internalConfigPropertyFromField;

    /**
     * Foo configuration documentation ...
     *
     * @return returns a hardcoded string
     */
    @ConfigProperty
    public String getFooConfig() {
        return "some hardcoded config string";
    }

    @ConfigProperty
    private String propertyWithoutDocShouldProduceACompilerWarning;

    /**
     * This config property should be considered "IMPLEMENTATION", since this method is not public.
     *
     * @return a hardcoded string
     */
    @ConfigProperty
    protected String getInternalConfigFromMethod() {
        return "some hardcoded config string";
    }

    /**
     * This config property should be considered "API", since it is explicitly annotated as @ConfigApi (even if this method is not public).
     *
     * @return some config value
     */
    @ConfigProperty
    @ConfigApi
    protected String getApiConfigFromAnnotatedMethod() {
        return "foo";
    }

}