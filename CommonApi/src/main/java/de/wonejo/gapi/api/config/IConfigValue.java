package de.wonejo.gapi.api.config;

import de.wonejo.gapi.api.config.serializer.IConfigValueSerializer;

import java.util.function.Supplier;

public interface IConfigValue<T> extends Supplier<T> {

    String key ();
    String comment ();

    IConfigValueSerializer<T> serializer ();

    T get ();
    T defaultValue ();
}
