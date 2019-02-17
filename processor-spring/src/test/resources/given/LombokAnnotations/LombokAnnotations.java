package testSource;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Test for nested list/set types together with properties.
 */
@ConfigurationProperties("de.sandstorm.testSource.lombokAnnotations")
class LombokAnnotations {

    /**
     * No setter -> no prop!
     */
    private boolean ignoredProp;

    /**
     * String -> no getter (IMPLEMENTATION)
     */
    @Setter
    private String stringProp;

    /**
     * Integer -> public getter (API)
     */
    @Getter
    @Setter
    private Integer apiIntegerProp;

    /**
     * Inner pojo namespace.
     */
    @Getter
    private InnerPojo innerPojo = new InnerPojo();

    /**
     * Inner Pojo namespace type.
     */
    @Data
    private static class InnerPojo {
        /**
         * inner long
         */
        private long innerPropLong;
        /**
         * inner String
         */
        private String innerPropString;
    }

}
