package testSource;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This class contains one property. The output file name is configured via config-doc.yaml.
 */
@ConfigurationProperties(prefix = "de.sandstorm.testSource.outputFileName")
class OutputFileName {

    /**
     * This is a configuration of type {@link String}.
     */
    private String stringConfig;

    public String getStringConfig() {
        return stringConfig;
    }

    public void setStringConfig(String stringConfig) {
        this.stringConfig = stringConfig;
    }
}
