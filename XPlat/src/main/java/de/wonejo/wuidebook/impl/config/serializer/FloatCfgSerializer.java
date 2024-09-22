package de.wonejo.wuidebook.impl.config.serializer;

import de.wonejo.wuidebook.api.config.serialization.ConfigValueSerializer;

import java.util.List;

public class FloatCfgSerializer implements ConfigValueSerializer<Float> {

    private float min;
    private float max;

    public String serialize(Float pValue) {
        return "float(" + pValue + ")";
    }

    public DeserializeResult<Float> deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("float(") ) {
            if ( !pValue.endsWith(")") ) return DeserializeResult.fail(List.of("Float value can not be deserialized! It is open but it don't seem to be closed."));
            pValue = pValue.substring(6, pValue.length() - 1);
        }

        float value = Float.parseFloat(pValue);
        if (!this.isValid(value)) return DeserializeResult.fail(List.of("Can not deserialize float value: " + value + ", it is below or over the min/max value: [%s, %s]".formatted(this.min, this.max)));
        return DeserializeResult.success(value);
    }

    public boolean isValid(Float pValue) {
        return pValue >= this.min && pValue <= this.max;
    }

    public String getValidValuesDescription() {
        if ( min == Float.MIN_VALUE && max == Float.MAX_VALUE ) return "Any float";
        if ( max == Float.MAX_VALUE ) return "Any float greater than or equal to %s".formatted(this.min);

        return "An float in range of [%s, %s] (inclusive)".formatted(this.min, this.max);
    }

    public void onRange(Float pMinValue, Float pMaxValue) {
       this.min = pMinValue;
       this.max = pMaxValue;
    }

}
