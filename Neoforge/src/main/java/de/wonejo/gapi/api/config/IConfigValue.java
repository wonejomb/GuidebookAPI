package de.wonejo.gapi.api.config;

import java.util.function.Supplier;

public interface IConfigValue<T> extends Supplier<T> {

    String key ();
    String comment ();

    T get ();
    T defaultValue ();
}
