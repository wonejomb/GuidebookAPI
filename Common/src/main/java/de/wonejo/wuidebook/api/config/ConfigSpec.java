package de.wonejo.wuidebook.api.config;

import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import de.wonejo.wuidebook.impl.config.ConfigSpecImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface ConfigSpec<T> {

    static <B> ConfigSpec<B> create ( String pKey, ConfigSerializer<B> pSerializer, B pDefaultValue ) {
        return new ConfigSpecImpl<>(pKey, pSerializer, null, pDefaultValue);
    }

    @NotNull
    static <B> ConfigSpec<B> create ( String pKey, ConfigSerializer<B> pSerializer, String pComment, B pDefaultValue) {
        return new ConfigSpecImpl<>(pKey, pSerializer, pComment, pDefaultValue);
    }

    String getKey ();
    ConfigSerializer<T> serializer ();
    Optional<String> getComment ();
    T getValue ();
    T getDefaultValue ();

    boolean set ( T pNewValue );

}
