package testSource;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * This class contains all java primitives and there boxed types.
 * A String is considered as a "configuration primitive".
 */
@ConfigurationProperties(prefix = "de.sandstorm.testSource.primitives")
class Primitives {

    /**
     * This is a configuration of type {@link String}.
     */
    private String stringConfig;

    /**
     * This is a primitive (int) config.
     */
    private int intConfig;

    /**
     * This is a boxed primitive (java.lang.Integer) config.
     */
    private Integer intObjectConfig;

    /**
     * This is a primitive (long) config.
     */
    private long longConfig;

    /**
     * This is a boxed primitive (java.lang.Long) config.
     */
    private Long longObjectConfig;

    /**
     * This is a primitive (float) config.
     */
    private float floatConfig;

    /**
     * This is a boxed primitive (java.lang.Float) config.
     */
    private Float floatObjectConfig;

    /**
     * This is a primitive (double) config.
     */
    private double doubleConfig;

    /**
     * This is a boxed primitive (java.lang.Double) config.
     */
    private Double doubleObjectConfig;

    /**
     * This is a primitive (short) config.
     */
    private short shortConfig;

    /**
     * This is a boxed primitive (java.lang.Short) config.
     */
    private Short shortObjectConfig;

    /**
     * This is a primitive (boolean) config.
     */
    private boolean booleanConfig;

    /**
     * This is a boxed primitive (java.lang.Boolean) config.
     */
    private Boolean booleanObjectConfig;

    /**
     * This is a primitive (byte) config.
     */
    private byte byteConfig;

    /**
     * This is a boxed primitive (java.lang.Boolean) config.
     */
    private Byte byteObjectConfig;

    /**
     * This is a primitive (char) config.
     */
    private char charConfig;

    /**
     * This is a boxed primitive (java.lang.Character) config.
     */
    private Character charObjectConfig;

    public String getStringConfig() {
        return stringConfig;
    }

    public void setStringConfig(String stringConfig) {
        this.stringConfig = stringConfig;
    }

    public int getIntConfig() {
        return intConfig;
    }

    public void setIntConfig(int intConfig) {
        this.intConfig = intConfig;
    }

    public Integer getIntObjectConfig() {
        return intObjectConfig;
    }

    public void setIntObjectConfig(Integer intObjectConfig) {
        this.intObjectConfig = intObjectConfig;
    }

    public long getLongConfig() {
        return longConfig;
    }

    public void setLongConfig(long longConfig) {
        this.longConfig = longConfig;
    }

    public Long getLongObjectConfig() {
        return longObjectConfig;
    }

    public void setLongObjectConfig(Long longObjectConfig) {
        this.longObjectConfig = longObjectConfig;
    }

    public float getFloatConfig() {
        return floatConfig;
    }

    public void setFloatConfig(float floatConfig) {
        this.floatConfig = floatConfig;
    }

    public Float getFloatObjectConfig() {
        return floatObjectConfig;
    }

    public void setFloatObjectConfig(Float floatObjectConfig) {
        this.floatObjectConfig = floatObjectConfig;
    }

    public double getDoubleConfig() {
        return doubleConfig;
    }

    public void setDoubleConfig(double doubleConfig) {
        this.doubleConfig = doubleConfig;
    }

    public Double getDoubleObjectConfig() {
        return doubleObjectConfig;
    }

    public void setDoubleObjectConfig(Double doubleObjectConfig) {
        this.doubleObjectConfig = doubleObjectConfig;
    }

    public short getShortConfig() {
        return shortConfig;
    }

    public void setShortConfig(short shortConfig) {
        this.shortConfig = shortConfig;
    }

    public Short getShortObjectConfig() {
        return shortObjectConfig;
    }

    public void setShortObjectConfig(Short shortObjectConfig) {
        this.shortObjectConfig = shortObjectConfig;
    }

    public boolean isBooleanConfig() {
        return booleanConfig;
    }

    public void setBooleanConfig(boolean booleanConfig) {
        this.booleanConfig = booleanConfig;
    }

    public Boolean getBooleanObjectConfig() {
        return booleanObjectConfig;
    }

    public void setBooleanObjectConfig(Boolean booleanObjectConfig) {
        this.booleanObjectConfig = booleanObjectConfig;
    }

    public byte getByteConfig() {
        return byteConfig;
    }

    public void setByteConfig(byte byteConfig) {
        this.byteConfig = byteConfig;
    }

    public Byte getByteObjectConfig() {
        return byteObjectConfig;
    }

    public void setByteObjectConfig(Byte byteObjectConfig) {
        this.byteObjectConfig = byteObjectConfig;
    }

    public char getCharConfig() {
        return charConfig;
    }

    public void setCharConfig(char charConfig) {
        this.charConfig = charConfig;
    }

    public Character getCharObjectConfig() {
        return charObjectConfig;
    }

    public void setCharObjectConfig(Character charObjectConfig) {
        this.charObjectConfig = charObjectConfig;
    }
}