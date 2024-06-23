package de.wonejo.gapi.api.cfg;

import de.wonejo.gapi.api.cfg.serializer.IConfigValueSerializer;

import java.util.Optional;
import java.util.function.Supplier;

public interface IConfigValue<T> extends Supplier<T> {

    String key ();
    Optional<String> comment ();

    IConfigValueSerializer<T> serializer ();

    T get ();
    T defaultValue ();
}
