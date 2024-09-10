package de.wonejo.wuidebook.impl.config;

import de.wonejo.wuidebook.api.config.ConfigSpec;
import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public final class ConfigSpecImpl<T> implements ConfigSpec<T> {

    private final String key;
    private final ConfigSerializer<T> serializer;
    private final String comment;
    private T value;
    private final T defaultValue;

    public ConfigSpecImpl (String pKey, ConfigSerializer<T> pSerializer, @Nullable String pComment, T pDefaultValue ) {
        this.key = pKey;
        this.serializer = pSerializer;
        this.comment = pComment;
        this.defaultValue = pDefaultValue;
        this.value = pDefaultValue;
    }

    public boolean set(T pNewValue) {
        if ( pNewValue != this.defaultValue ) {
            this.value = pNewValue;
            return true;
        }

        return false;
    }

    public String key() {
        return this.key;
    }

    public ConfigSerializer<T> serializer() {
        return this.serializer;
    }

    public Optional<String> comment() {
        return Optional.ofNullable(this.comment);
    }

    public T getValue() {
        return value;
    }

    public T getDefaultValue() {
        return defaultValue;
    }

}
