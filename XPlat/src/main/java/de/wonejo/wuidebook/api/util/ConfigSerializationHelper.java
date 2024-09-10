package de.wonejo.wuidebook.api.util;

import de.wonejo.wuidebook.api.config.ConfigSpec;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Only internal use of the mod, just to help with the config de/serialization.
 */
@ApiStatus.Internal
public class ConfigSerializationHelper {

    @SuppressWarnings("unchecked")
    public static <T> T deserialize ( @NotNull ConfigSpec<T> pSpec ) {
        String value = ConfigSerializationHelper.serialize(pSpec);
        return (T) pSpec.serializer().deserialize(value);
    }

    public static <T> String serialize ( @NotNull ConfigSpec<T> pSpec ) {
        T value = pSpec.getValue();
        return pSpec.serializer().serialize(value);
    }

    public static <T> void setValue ( ConfigSpec<T> pSpec, T pValue ) {
        pSpec.set(pValue);
    }

    public static <T> void setValue (ConfigSpec<T> pSpec, String pNewValue ) {
        T newValue = ConfigSerializationHelper.deserialize(pSpec);
        ConfigSerializationHelper.setValue(pSpec, newValue);
    }

    private ConfigSerializationHelper () {}
}
