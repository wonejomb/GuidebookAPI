package de.wonejo.wuidebook.impl.config.serializer;

import de.wonejo.wuidebook.api.config.serialization.ConfigValueSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the ONLY ONE serializer that doesn't register on config manager.
 * @since 4.0.0-dev2
 */
public class EnumSerializer<T extends Enum<T>> implements ConfigValueSerializer<T> {

    private final Class<T> enumClass;
    private final Collection<T> validValues;

    public EnumSerializer ( @NotNull Class<T> pEnumClass ) {
        this.enumClass = pEnumClass;
        this.validValues = List.of(pEnumClass.getEnumConstants());
    }

    public String serialize( @NotNull T pValue ) {
        return pValue.name();
    }

    public DeserializeResult<T> deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("\"") && pValue.endsWith("\"") ) pValue = pValue.substring(1, pValue.length() - 1);

        try {
            T value = Enum.valueOf(this.enumClass, pValue);
            return DeserializeResult.success(value);
        } catch ( IllegalArgumentException pException ) {
            return DeserializeResult.fail(List.of("Invalid enum name: %s".formatted(pException.getMessage())));
        }
    }

    public boolean isValid(T pValue) {
        return this.validValues.contains(pValue);
    }

    public String getValidValuesDescription() {
        String names = this.validValues.stream()
                .map(Enum::name)
                .collect(Collectors.joining(", "));

        return "[%s]".formatted(names);
    }

}
