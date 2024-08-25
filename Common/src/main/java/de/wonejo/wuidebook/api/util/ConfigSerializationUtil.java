package de.wonejo.wuidebook.api.util;

import de.wonejo.wuidebook.api.config.ConfigSpec;
import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import org.jetbrains.annotations.NotNull;

public class ConfigSerializationUtil {

    public static <T> void setValue (@NotNull ConfigSpec<T> pSpec, T pValue ) {
        pSpec.set(pValue);
    }

    public static <T> T deserialize (@NotNull ConfigSpec<T> pSpec) {
        ConfigSerializer<T> serializer = pSpec.serializer();
        String value = ConfigSerializationUtil.serialize(pSpec);
        return serializer.deserialize(value);
    }

    public static <T> T deserialize ( @NotNull ConfigSpec<T> pSpec, String pValue ) {
        ConfigSerializer<T> serializer = pSpec.serializer();
        return serializer.deserialize(pValue);
    }

    public static <T> String serializeComment (@NotNull ConfigSpec<T> pSpec ) {
        return pSpec.serializer().serializeDefaultComment(pSpec.getValue());
    }

    public static <T> String serialize (@NotNull ConfigSpec<T> pSpec) {
        ConfigSerializer<T> serializer = pSpec.serializer();
        T deserializedValue = pSpec.getValue();
        return serializer.serialize(deserializedValue);
    }

}
