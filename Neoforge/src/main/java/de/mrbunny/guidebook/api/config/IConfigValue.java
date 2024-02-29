package de.mrbunny.guidebook.api.config;

import java.util.function.Supplier;

public interface IConfigValue<T> extends Supplier<T> {

    String getKey ();
    String getComment ();

    T getOrDefault ( String pKey, T pDefaultValue );
    T get ();
    T getDefaultValue  ();

}
