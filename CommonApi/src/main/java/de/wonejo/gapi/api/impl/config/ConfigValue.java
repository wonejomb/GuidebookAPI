package de.wonejo.gapi.api.impl.config;

import de.wonejo.gapi.api.config.IConfigValue;

public class ConfigValue<T> implements IConfigValue<T> {

    private final String key;
    private final String comment;
    private final T defaultValue;
    protected T value;

    protected ConfigValue ( String pKey, String pComment, T pDefaultValue ) {
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

    public T get() {
        return this.value == null ? this.defaultValue : this.value;
    }

    public T defaultValue() {
        return this.defaultValue;
    }
}
