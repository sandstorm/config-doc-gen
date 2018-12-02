package testSource;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This is the namespace that contains the nested one.
 */
@ConfigurationProperties(prefix = "de.sandstorm.testSource.nestedPojos")
class NestedPojos {
    /**
     * This is a nested config namespace by POJO.
     */
    private final NestedPojo nestedPojo = new NestedPojo();

    /**
     * Nested value POJO.
     */
    static class NestedPojo {

        /**
         * This is a nested String configuration.
         */
        private String nestedStringConfig;

        public String getNestedStringConfig() {
            return nestedStringConfig;
        }

        public void setNestedStringConfig(String nestedStringConfig) {
            this.nestedStringConfig = nestedStringConfig;
        }
    }

    public NestedPojo getNestedPojo() {
        return nestedPojo;
    }
}