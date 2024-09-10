package de.wonejo.wuidebook.api.config;

import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;

import java.util.Optional;

public interface ConfigSpec<T> {

    boolean set ( T pNewValue );

    String key ();
    Optional<String> comment ();
    T getValue ();
    T getDefaultValue ();
    ConfigSerializer<T> serializer ();
}
