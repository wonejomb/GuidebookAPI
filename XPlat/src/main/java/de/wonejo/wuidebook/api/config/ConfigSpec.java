package de.wonejo.wuidebook.api.config;

import de.wonejo.wuidebook.api.config.serialization.ConfigValueSerializer;
import de.wonejo.wuidebook.impl.config.ConfigSpecImpl;

import java.util.List;
import java.util.Optional;

public interface ConfigSpec<T> {

    static <T> ConfigSpec<T> create (ConfigValueSerializer<T> pSerializer, String pKey, String pDescription, T pDefaultValue) {
        return ConfigSpecImpl.createSpec(pSerializer, pKey, pDescription, pDefaultValue);
    }

    boolean set ( T pNewValue );
    List<String> setFromSerialized (String pValue );

    String getSerializedValue ();

    boolean isDefault ();

    String key ();
    Optional<String> description ();
    ConfigValueSerializer<T> serializer ();
    T defaultValue ();
    T value ();

}
