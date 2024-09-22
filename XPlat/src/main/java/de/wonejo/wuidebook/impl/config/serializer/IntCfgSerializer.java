package de.wonejo.wuidebook.impl.config.serializer;

import de.wonejo.wuidebook.api.config.serialization.ConfigValueSerializer;

import java.util.List;

public class IntCfgSerializer implements ConfigValueSerializer<Integer> {

    private int min;
    private int max;

    public String serialize(Integer pValue) {
        return "int(" + pValue + ")";
    }

    public DeserializeResult<Integer> deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("int(") ) {
            if ( !pValue.endsWith(")") ) return DeserializeResult.fail(List.of("Int value can not be deserialized! It is open but it don't seem to be closed."));
            pValue = pValue.substring(4, pValue.length() - 1);
        }

        int value = Integer.parseInt(pValue);
        if (!this.isValid(value)) return DeserializeResult.fail(List.of("Can not deserialize int value: " + value + ", it is below or over the min/max value: [%s, %s]".formatted(this.min, this.max)));
        return DeserializeResult.success(value);
    }

    public boolean isValid(Integer pValue) {
        return pValue >= this.min && pValue <= this.max;
    }

    public String getValidValuesDescription() {
        if (min == Integer.MIN_VALUE && max == Integer.MAX_VALUE) return "Any integer";
        if ( max == Integer.MAX_VALUE ) return "Any integer greater than or equal to %s".formatted(this.min);

        return "An integer in range of [%s, %s] (inclusive)".formatted(this.min, this.max);
    }

    public void onRange(Integer pMinValue, Integer pMaxValue) {
        this.min = pMinValue;
        this.max = pMaxValue;
    }

}
