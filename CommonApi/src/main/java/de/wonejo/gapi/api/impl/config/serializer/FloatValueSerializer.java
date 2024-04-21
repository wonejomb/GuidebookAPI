package de.wonejo.gapi.api.impl.config.serializer;

import de.wonejo.gapi.api.config.serializer.IConfigValueSerializer;
import de.wonejo.gapi.api.util.ConfigValueSerializationException;

public class FloatValueSerializer implements IConfigValueSerializer<Float> {

    private final float minValue;
    private final float maxValue;

    protected FloatValueSerializer ( float pMinValue, float pMaxValue ) {
        this.minValue = pMinValue;
        this.maxValue = pMaxValue;
    }

    public String serialize(Float pValue) {
        return pValue.toString();
    }

    public Float deserialize(String pValue) {
        pValue = pValue.trim();
        try {
            float value = Float.parseFloat(pValue);
            if (!isValid(value))
                throw new IllegalArgumentException("Invalid float. Must be in a range of %s(min) and %s(max). Actual value: %s".formatted(this.minValue, this.maxValue, value));

            return value;
        } catch (ConfigValueSerializationException pEx) {
            throw new ConfigValueSerializationException("Unable to parse float: '%s' with error: \n%s".formatted(pValue, pEx.getMessage()));
        }
    }

    private boolean isValid ( float pValue ) {
        return pValue >= this.minValue && pValue <= this.maxValue;
    }
}
