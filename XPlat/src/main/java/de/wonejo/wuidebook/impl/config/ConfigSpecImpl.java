package de.wonejo.wuidebook.impl.config;

import de.wonejo.wuidebook.api.config.ConfigSpec;
import de.wonejo.wuidebook.api.config.serialization.ConfigValueSerializer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class ConfigSpecImpl<T> implements ConfigSpec<T> {

    @ApiStatus.Internal
    @NotNull
    public static <T> ConfigSpecImpl<T> createSpec ( ConfigValueSerializer<T> pSerializer, String pKey, String pDescription, T pDefaultValue ) {
        return new ConfigSpecImpl<>(pKey, pDescription, pSerializer, pDefaultValue);
    }

    private final String key;
    @Nullable
    private final String description;
    private final T defaultValue;
    private final ConfigValueSerializer<T> serializer;
    private volatile T currentValue;

    private ConfigSpecImpl ( String pKey, @Nullable String pDescription, ConfigValueSerializer<T> pSerializer, T pDefaultValue ) {
        this.key = pKey;
        this.description = pDescription;
        this.serializer = pSerializer;
        this.defaultValue = pDefaultValue;
        this.currentValue = pDefaultValue;
    }

    public boolean set(T pNewValue) {
        if (!this.serializer.isValid(pNewValue)) throw new RuntimeException("Tried to set invalid value: %s\n%s".formatted(pNewValue, this.serializer.getValidValuesDescription()));
        if (!this.currentValue.equals(pNewValue)) {
            this.currentValue = pNewValue;
            return true;
        }

        return false;
    }

    public List<String> setFromSerialized(String pValue) {
        ConfigValueSerializer.DeserializeResult<T> result = this.serializer.deserialize(pValue);
        result.getResult().ifPresent(this::set);
        return result.getErrors();
    }

    public String getSerializedValue() {
        return this.serializer.serialize(this.value());
    }

    public boolean isDefault() {
        return this.currentValue.equals(this.defaultValue);
    }

    public String key() {
        return this.key;
    }

    public Optional<String> description() {
        return Optional.ofNullable(this.description);
    }

    public T defaultValue() {
        return this.defaultValue;
    }

    public ConfigValueSerializer<T> serializer() {
        return this.serializer;
    }

    public T value() {
        return this.currentValue == null ? this.currentValue : this.defaultValue;
    }

    public String toString() {
        return "ConfigSpec=[" +
            "key=" + this.key + ", " +
                "description=" + this.description + ", " +
                "defaultValue=" + this.description + ", " +
                "validValuesDescription=" + this.serializer.getValidValuesDescription() + "]";
    }
}
