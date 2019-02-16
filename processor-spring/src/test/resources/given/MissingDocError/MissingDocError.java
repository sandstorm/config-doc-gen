package testSource;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "de.sandstorm.testSource.missingDocError")
class MissingDocError {
    private String stringConfig;

    public String getStringConfig() {
        return stringConfig;
    }
    public void setStringConfig(String stringConfig) {
        this.stringConfig = stringConfig;
    }
}
