package testSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import java.util.Map;

@ConfigurationProperties(prefix = "de.sandstorm.testSource.noDocWarnings")
class NoDocWarnings {

    private String stringWithoutDoc;

    private NestedPojoWithoutDoc nestedPojo;

    private NestedPojoInOwnClass nestedPojoInOwnClass;

    private Map<String, NestedPojoWithoutDoc> nestedPojoMap;

    public String getStringWithoutDoc() {
        return stringWithoutDoc;
    }

    public void setStringWithoutDoc(String stringWithoutDoc) {
        this.stringWithoutDoc = stringWithoutDoc;
    }

    public NestedPojoWithoutDoc getNestedPojo() {
        return nestedPojo;
    }

    public void setNestedPojo(NestedPojoWithoutDoc nestedPojo) {
        this.nestedPojo = nestedPojo;
    }

    public Map<String, NestedPojoWithoutDoc> getNestedPojoMap() {
        return nestedPojoMap;
    }

    public void setNestedPojoMap(Map<String, NestedPojoWithoutDoc> nestedPojoMap) {
        this.nestedPojoMap = nestedPojoMap;
    }

    static class NestedPojoWithoutDoc {

        private int nestedIntWithoutDoc;

        public int getNestedIntWithoutDoc() {
            return nestedIntWithoutDoc;
        }

        public void setNestedIntWithoutDoc(int nestedIntWithoutDoc) {
            this.nestedIntWithoutDoc = nestedIntWithoutDoc;
        }
    }

}