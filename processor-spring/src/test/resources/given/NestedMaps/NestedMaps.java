package testSource;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Nested maps test.
 */
@ConfigurationProperties(prefix = "de.sandstorm.testSource.nestedMaps")
class NestedMaps {

    /**
     * A key-value namespace.
     */
    private final Map<String, String> keyValueStringMapConfig = new HashMap<>();

    /**
     * A key-value primitive namespace.
     */
    private final Map<String, Boolean> keyValueBooleanMapConfig = new HashMap<>();

    /**
     * A key-value primitive namespace.
     */
    private final Map<String, Character> keyValueCharMapConfig = new HashMap<>();

    /**
     * A key-value primitive namespace.
     */
    private final Map<String, Byte> keyValueByteMapConfig = new HashMap<>();

    /**
     * A key-value primitive namespace.
     */
    private final Map<String, Short> keyValueShortMapConfig = new HashMap<>();

    /**
     * A key-value primitive namespace.
     */
    private final Map<String, Integer> keyValueIntegerMapConfig = new HashMap<>();

    /**
     * A key-value primitive namespace.
     */
    private final Map<String, Long> keyValueLongMapConfig = new HashMap<>();

    /**
     * A key-value primitive namespace.
     */
    private final Map<String, Float> keyValueFloatMapConfig = new HashMap<>();

    /**
     * A key-value primitive namespace.
     */
    private final Map<String, Double> keyValueDoubleMapConfig = new HashMap<>();

    /**
     * Non-String keys are not allowed.
     */
    private final Map<Integer, String> intKeyConfig = new HashMap<>();

    /**
     * Non-String keys are not allowed.
     */
    private final Map<Object, String> objectKeyConfig = new HashMap<>();

    /**
     * This is a map of configurations. The key will be recognised as wildcard.
     */
    private final Map<String, TestNestedMapValuePojo> nestedMapConfig = new HashMap<>();

    /**
     * Nested value POJO.
     */
    static class TestNestedMapValuePojo {

        /**
         * This is a nested String configuration.
         */
        private String nestedMapStringConfig;

        public String getNestedMapStringConfig() {
            return nestedMapStringConfig;
        }

        public void setNestedMapStringConfig(String nestedMapStringConfig) {
            this.nestedMapStringConfig = nestedMapStringConfig;
        }
    }

    public Map<String, String> getKeyValueStringMapConfig() {
        return keyValueStringMapConfig;
    }

    public Map<String, Boolean> getKeyValueBooleanMapConfig() {
        return keyValueBooleanMapConfig;
    }

    public Map<String, Character> getKeyValueCharMapConfig() {
        return keyValueCharMapConfig;
    }

    public Map<String, Byte> getKeyValueByteMapConfig() {
        return keyValueByteMapConfig;
    }

    public Map<String, Short> getKeyValueShortMapConfig() {
        return keyValueShortMapConfig;
    }

    public Map<String, Integer> getKeyValueIntegerMapConfig() {
        return keyValueIntegerMapConfig;
    }

    public Map<String, Long> getKeyValueLongMapConfig() {
        return keyValueLongMapConfig;
    }

    public Map<String, Float> getKeyValueFloatMapConfig() {
        return keyValueFloatMapConfig;
    }

    public Map<String, Double> getKeyValueDoubleMapConfig() {
        return keyValueDoubleMapConfig;
    }

    public Map<Integer, String> getIntKeyConfig() {
        return intKeyConfig;
    }

    public Map<Object, String> getObjectKeyConfig() {
        return objectKeyConfig;
    }

    public Map<String, TestNestedMapValuePojo> getNestedMapConfig() {
        return nestedMapConfig;
    }
}