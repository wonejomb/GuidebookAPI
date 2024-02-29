package de.mrbunny.guidebook.api.config.impl;

import de.mrbunny.guidebook.api.config.IConfigValue;
import de.mrbunny.guidebook.api.config.IConfigValueBuilder;

public final class ConfigValueBuilder<T> implements IConfigValueBuilder<T> {

    private final String key;
    private String comment;
    private T defaultValue;

    public ConfigValueBuilder ( String pKey ) {
        this.key = pKey;
    }

    public IConfigValueBuilder<T> comment(String pComment) {
        this.comment = pComment;
        return this;
    }

    public IConfigValueBuilder<T> defaultValue(T pDefaultValue) {
        this.defaultValue = pDefaultValue;
        return this;
    }

    public String comment() {
        return this.comment;
    }

    public String key() {
        return this.key;
    }

    public T defaultValue() {
        return this.defaultValue;
    }

    public IConfigValue<T> build() {
        return this.comment == null ? new ConfigValue<>(this.key, this.defaultValue) : new ConfigValue<>(this.key, this.defaultValue, this.comment);
    }

}
