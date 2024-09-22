package de.wonejo.wuidebook.impl.config.serializer;

import de.wonejo.wuidebook.api.config.serialization.ConfigValueSerializer;

import java.util.List;

public class DoubleCfgSerializer implements ConfigValueSerializer<Double> {

    private double min;
    private double max;

    public String serialize(Double pValue) {
        return "double(" + pValue + ")";
    }

    public ConfigValueSerializer.DeserializeResult<Double> deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("double(") ) {
            if ( !pValue.endsWith(")") ) return ConfigValueSerializer.DeserializeResult.fail(List.of("Double value can not be deserialized! It is open but it don't seem to be closed."));
            pValue = pValue.substring(7, pValue.length() - 1);
        }

        double value = Double.parseDouble(pValue);
        if (!this.isValid(value)) return ConfigValueSerializer.DeserializeResult.fail(List.of("Can not deserialize double value: " + value + ", it is below or over the min/max value: [%s, %s]".formatted(this.min, this.max)));
        return ConfigValueSerializer.DeserializeResult.success(value);
    }

    public boolean isValid(Double pValue) {
        return pValue >= this.min && pValue <= this.max;
    }

    public String getValidValuesDescription() {
        if ( min == Float.MIN_VALUE && max == Float.MAX_VALUE ) return "Any double";
        if ( max == Float.MAX_VALUE ) return "Any double greater than or equal to %s".formatted(this.min);

        return "An double in range of [%s, %s] (inclusive)".formatted(this.min, this.max);
    }

    public void onRange(Double pMinValue, Double pMaxValue) {
        this.min = pMinValue;
        this.max = pMaxValue;
    }

}
