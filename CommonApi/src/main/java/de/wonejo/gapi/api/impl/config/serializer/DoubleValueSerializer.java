package de.wonejo.gapi.api.impl.config.serializer;

import de.wonejo.gapi.api.config.serializer.IConfigValueSerializer;
import de.wonejo.gapi.api.util.ConfigValueSerializationException;

public class DoubleValueSerializer implements IConfigValueSerializer<Double> {

    private final double minValue;
    private final double maxValue;

    protected DoubleValueSerializer ( double pMinValue, double pMaxValue ) {
        this.minValue = pMinValue;
        this.maxValue = pMaxValue;
    }

    public String serialize(Double pValue) {
        return pValue.toString();
    }

    public Double deserialize(String pValue) {
        pValue = pValue.trim();
        try {
            double value = Double.parseDouble(pValue);
            if ( !isValid(value) )
                throw new IllegalArgumentException("Invalid double. Must be in a range of %s(min) and %s(max). Actual value: %s".formatted(this.minValue, this.maxValue, value));

            return value;
        } catch (ConfigValueSerializationException pEx) {
            throw new ConfigValueSerializationException("Unable to parse double. '%s' with error: \n%s".formatted(pValue, pEx.getMessage()));
        }
    }

    private boolean isValid (double pValue ) {
        return pValue >= this.minValue && pValue <= this.maxValue;
    }
}
