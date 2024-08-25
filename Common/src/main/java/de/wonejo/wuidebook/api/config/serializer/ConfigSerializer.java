package de.wonejo.wuidebook.api.config.serializer;

import org.jetbrains.annotations.NotNull;

/**
 * <h1>ConfigSerializer</h1>
 * This class is specialized on the de/serialization of config values from file, to write it on the creation or adding of these or getting the values.
 * <p></p>
 * The class provides methods to de/serialize config values and the default comment value of these
 */
public interface ConfigSerializer<T> {

    /**
     * <h3>Serialize</h3>
     * Define in a string how the serializer should serializer the value
     */
    String serialize ( T pValue );

    /**
     * <h3>Deserialize</h3>
     * Define how the serializer should deserialize the value obtaining a String
     */
    T deserialize ( String pValue );

    /**
     * <h3>Serialize Default Comment</h3>
     *
     * <p>WuidebookAPI provides by default some serializers for basic things, like numbers, booleans and strings so by default it return the "toString" method.</p>
     * <p></p>
     * <p>From the other hand, some things does not have a "toString" method so it need to be defined in the deserializer</p>
     * <p></p>
     * <p>The method should serialize the default value for the comments</p>
     */
    default String serializeDefaultComment ( @NotNull T pValue ) {
        return pValue.toString();
    }

    /**
     * <h3>onRange</h3>
     *
     * WuidebookAPI to handle the value range of numeric types needs this method that can ONLY take numbers. You can not put a min or max value to a string or boolean value right?
     */
    default void onRange ( T pMinValue, T pMaxRange ) {
        if (!(pMinValue instanceof Number) || !(pMaxRange instanceof Number)) throw new RuntimeException("Can not set a min/max range value if it is not a number.");
    }

}
