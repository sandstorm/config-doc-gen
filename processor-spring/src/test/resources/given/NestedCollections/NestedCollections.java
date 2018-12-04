package testSource;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

/**
 * Test for nested list/set types.
 */
@ConfigurationProperties("de.sandstorm.testSource.nestedCollections")
class NestedCollections {

    /**
     * Collection example.
     */
    private final Collection<String> collectionProp = new ArrayList<>();

    /**
     * Set example.
     */
    private final Set<String> setProp = new HashSet<>();

    /**
     * List example.
     */
    private final List<String> listProp = new ArrayList<>();

    /**
     * Boolean primitive list.
     */
    private final List<Boolean> boolProp = new ArrayList<>();

    /**
     * Character primitive list.
     */
    private final List<Character> charProp = new ArrayList<>();

    /**
     * Byte primitive list.
     */
    private final List<Byte> byteProp = new ArrayList<>();

    /**
     * Short primitive list.
     */
    private final List<Short> shortProp = new ArrayList<>();

    /**
     * Integer primitive list.
     */
    private final List<Integer> intProp = new ArrayList<>();

    /**
     * Long primitive list.
     */
    private final List<Long> longProp = new ArrayList<>();

    /**
     * Float primitive list.
     */
    private final List<Float> floatProp = new ArrayList<>();

    /**
     * Double primitive list.
     */
    private final List<Double> doubleProp = new ArrayList<>();

    /**
     * Object type is unsupported!!!
     */
    private final List<Object> objectProp = new ArrayList<>();

    /**
     * Nested POJO list.
     */
    private final List<InnerPojo> pojoProp = new ArrayList<>();

    public Collection<String> getCollectionProp() {
        return collectionProp;
    }

    public Set<String> getSetProp() {
        return setProp;
    }

    public List<String> getListProp() {
        return listProp;
    }

    public List<Boolean> getBoolProp() {
        return boolProp;
    }

    public List<Character> getCharProp() {
        return charProp;
    }

    public List<Byte> getByteProp() {
        return byteProp;
    }

    public List<Short> getShortProp() {
        return shortProp;
    }

    public List<Integer> getIntProp() {
        return intProp;
    }

    public List<Long> getLongProp() {
        return longProp;
    }

    public List<Float> getFloatProp() {
        return floatProp;
    }

    public List<Double> getDoubleProp() {
        return doubleProp;
    }

    public List<Object> getObjectProp() {
        return objectProp;
    }

    public List<InnerPojo> getPojoProp() {
        return pojoProp;
    }

    /**
     * Inner POJO class.
     */
    static class InnerPojo {
        /**
         * Nested String list.
         */
        private final List<String> nestedStringProp = new ArrayList<>();

        public List<String> getNestedStringProp() {
            return nestedStringProp;
        }
    }

}