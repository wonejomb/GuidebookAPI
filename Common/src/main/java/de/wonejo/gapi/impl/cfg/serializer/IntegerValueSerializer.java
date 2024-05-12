package de.wonejo.gapi.impl.cfg.serializer;

import de.wonejo.gapi.api.cfg.serializer.IConfigValueSerializer;
import de.wonejo.gapi.api.util.ConfigValueSerializationException;

public class IntegerValueSerializer implements IConfigValueSerializer<Integer> {
    private final int minValue;
    private final int maxValue;

    protected IntegerValueSerializer ( int pMinValue, int pMaxValue ) {
        this.minValue = pMinValue;
        this.maxValue = pMaxValue;
    }

    public String serialize(Integer pValue) {
        return pValue.toString();
    }

    public Integer deserialize(String pValue) {
        pValue = pValue.trim();
        try {
            int value = Integer.parseInt(pValue);
            if (!isValid(value))
                throw new IllegalArgumentException("Invalid integer. Must be in a range of: %s(min) and %s(max). Actual value: %s".formatted(this.minValue, this.maxValue, value));

            return value;
        } catch (ConfigValueSerializationException pEx) {
            throw new ConfigValueSerializationException("Unable to parse int: '%s' with error: \n%s".formatted(pValue, pEx.getMessage()));
        }
    }

    private boolean isValid ( int pValue ) {
        return pValue >= this.minValue && pValue <= this.maxValue;
    }
}
