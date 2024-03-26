package de.wonejo.gapi.api.config.impl;

import de.wonejo.gapi.api.config.IConfigValueBuilder;

public final class ConfigValueBuilder<T> implements IConfigValueBuilder<T> {

    public static<B> IConfigValueBuilder<B> of ( String pKey ) {
        return new ConfigValueBuilder<>(pKey);
    }

    private final String key;
    private String comment = "";
    private T defaultValue;

    private ConfigValueBuilder ( String pKey ) {
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

    public ConfigValue<T> build() {
        return new ConfigValue<>(this.key, this.comment, this.defaultValue);
    }
}
