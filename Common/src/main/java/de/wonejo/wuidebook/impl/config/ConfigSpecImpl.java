package de.wonejo.wuidebook.impl.config;

import de.wonejo.wuidebook.api.config.ConfigSpec;
import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;

import java.util.Optional;

public class ConfigSpecImpl<T> implements ConfigSpec<T> {

    private final String key;
    private final ConfigSerializer<T> serializer;
    private final String comment;
    private T value;
    private final T defaultValue;

    public ConfigSpecImpl ( String pKey, ConfigSerializer<T> pSerializer, String pComment, T pDefaultValue ) {
        this.key = pKey;
        this.serializer = pSerializer;
        this.comment = pComment;
        this.defaultValue = pDefaultValue;
        this.value = pDefaultValue;
    }

    public String getKey() {
        return this.key;
    }

    public ConfigSerializer<T> serializer() {
        return this.serializer;
    }

    public Optional<String> getComment() {
        return Optional.of(this.comment);
    }

    public T getValue() {
        return this.value;
    }

    public T getDefaultValue() {
        return this.defaultValue;
    }

    public boolean set(T pNewValue) {
        if (this.value != this.defaultValue) {
            this.value = pNewValue;
            return true;
        }

        return false;
    }

}
