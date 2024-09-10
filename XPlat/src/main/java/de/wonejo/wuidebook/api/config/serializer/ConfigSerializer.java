package de.wonejo.wuidebook.api.config.serializer;

import java.util.Optional;

public interface ConfigSerializer<T> {

    String serialize ( T pValue );

    Optional<T> deserialize ( String pValue );

    default void onRange ( T pMinValue, T pMaxValue ) {
        if (!(pMinValue instanceof Number) || !(pMaxValue instanceof Number)) throw new RuntimeException("Can not set a min/max range value if it is not a number.");
    }

}
