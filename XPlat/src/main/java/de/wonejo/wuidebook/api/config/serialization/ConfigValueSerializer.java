package de.wonejo.wuidebook.api.config.serialization;

import de.wonejo.wuidebook.impl.config.DeserializeResultImpl;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

/**
 * Serialization and validation for configuration specifications of Wuidebook.
 * <p>
 *  Note: If the value is something like an array or a list use {@link ListConfigValueSerializer}
 * @since 4.0.0-dev2
 */
public interface ConfigValueSerializer<T> {

    /**
     * Turns a value to a string
     * @since 4.0.0-dev2
     */
    String serialize ( T pValue );

    /**
     * Turns a string value to the expected value
     * @since 4.0.0-dev2
     */
    DeserializeResult<T> deserialize ( String pValue );

    /**
     *  Check if a value is valid
     *  @since 4.0.0-dev2
     */
    boolean isValid ( T pValue );

    /**
     * Get all the valid values as a string for string.
     * @since 4.0.0-dev2
     */
    String getValidValuesDescription ();

    /**
     * Set a value range for serialization.
     * @since 4.0.0-dev1
     */
    default void onRange ( T pMinValue, T pMaxValue ) {
        if (!(pMinValue instanceof Number) || !(pMaxValue instanceof Number))
            throw new UnsupportedOperationException("Can not deserialize a value that is below or over the range. [%s, %s]".formatted(pMinValue, pMaxValue));
    }

    /**
     * Definition of a deserialization result, it can get the results or can get the errors.
     * @since 4.0.0-dev2
     */
    interface DeserializeResult<T> {

        /**
         * Create a success deserialization result.
         * @since 4.0.0-dev2
         */
        @NotNull
        static <B> DeserializeResult<B> success ( B pResult ) {
            return DeserializeResultImpl.success(pResult);
        }

        /**
         * Create a deserialization result that can have the result and errors.
         * @since 4.0.0-dev2
         */
        @NotNull
        static <B> DeserializeResult<B> haveBoth ( B pResult, List<String> pErrors ) {
            return DeserializeResultImpl.haveBoth(pResult, pErrors);
        }

        /**
         * Create a success deserialization result.
         * @since 4.0.0-dev2
         */
        @NotNull
        static <B> DeserializeResult<B> fail ( List<String> pErrors ) {
            return DeserializeResultImpl.fail(pErrors);
        }

        /**
         * Returns an optional result for deserialization. ( Not present if {@link DeserializeResult#getErrors()} isn't empty.
         * @since 4.0.0-dev2
         */
        Optional<T> getResult ();

        /**
         * Get all the possible errors when deserialize a value.
         * @since 4.0.0-dev2
         */
        List<String> getErrors ();

        /**
         * Return the present value as a integer.
         * @since 4.0.0-dev2
         */
        default int getAsInt () {
            if (this.getResult().isEmpty()) throw new UnsupportedOperationException("Can not get as int the value because it isn't present.");
            if (!(this.getResult().get() instanceof Integer)) throw new UnsupportedOperationException("Can not get as int value because it doesn't instance of int.");

            T result = this.getResult().get();
            return ((Integer) result);
        }

        /**
         * Return the present value as a float.
         * @since 4.0.0-dev2
         */
        default float getAsFloat () {
            if (this.getResult().isEmpty()) throw new UnsupportedOperationException("Can not get as float the value because it isn't present.");
            if (!(this.getResult().get() instanceof Float)) throw new UnsupportedOperationException("Can not get as int value because it doesn't instance of int.");

            T result = this.getResult().get();
            return ((Float) result);
        }

        /**
         * Return the present value as a double.
         * @since 4.0.0-dev2
         */
        default double getAsDouble () {
            if (this.getResult().isEmpty()) throw new UnsupportedOperationException("Can not get as double the value because it isn't present.");
            if (!(this.getResult().get() instanceof Double)) throw new UnsupportedOperationException("Can not get as double value because it doesn't instance of java.lang.Double.");

            T result = this.getResult().get();
            return ((Double) result);
        }

        /**
         * Return the present value as a string.
         * @since 4.0.0-dev2
         */
        default String getAsString () {
            if (this.getResult().isEmpty()) throw new UnsupportedOperationException("Can not get as String the value because it isn't present.");
            if (!(this.getResult().get() instanceof String)) throw new UnsupportedOperationException("Can not get as string value because it doesn't instance of java.lang.String.");

            T result = this.getResult().get();
            return ((String) result);
        }

        /**
         * Return the present value as a boolean.
         * @since 4.0.0-dev2
         */
        default boolean getAsBoolean () {
            if (this.getResult().isEmpty()) throw new UnsupportedOperationException("Can not get as boolean the value because it isn't present.");
            if (!(this.getResult().get() instanceof Boolean)) throw new UnsupportedOperationException("Can not get as boolean value because it doesn't instance of java.langBoolean.");

            T result = this.getResult().get();
            return ((Boolean) result);
        }


    }

}
