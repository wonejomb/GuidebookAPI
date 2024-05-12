package de.wonejo.gapi.impl.cfg;

import de.wonejo.gapi.api.cfg.IConfigValue;
import de.wonejo.gapi.api.cfg.serializer.IConfigValueSerializer;

public class ConfigValue<T> implements IConfigValue<T> {

    private final IConfigValueSerializer<T> serializer;

    private final String key;
    private final String comment;
    private final T defaultValue;
    protected T value;

    protected ConfigValue ( String pKey, String pComment, T pDefaultValue ) {
        this.key = pKey;
        this.comment = pComment;
        this.defaultValue = pDefaultValue;
        this.serializer = null;
    }

    protected ConfigValue ( IConfigValueSerializer<T> pSerializer, String pKey, String pComment, T pDefaultValue ) {
        this.serializer = pSerializer;
        this.key = pKey;
        this.comment = pComment;
        this.defaultValue = pDefaultValue;
        this.value = pDefaultValue;
    }

    public String key() {
        return this.key;
    }

    public String comment() {
        return this.comment;
    }

    public IConfigValueSerializer<T> serializer() {
        return this.serializer;
    }

    public T get() {
        return this.value == null ? this.defaultValue : this.value;
    }

    public T defaultValue() {
        return this.defaultValue;
    }
}
