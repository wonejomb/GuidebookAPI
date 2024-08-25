package de.wonejo.wuidebook.impl.config.serializer;

import de.wonejo.wuidebook.WuidebookCommon;
import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import net.minecraft.resources.ResourceLocation;

public class FloatConfigSerializer implements ConfigSerializer<Float> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(WuidebookCommon.MOD_ID, "float_cfg");

    private float minValue;
    private float maxValue;

    public String serialize(Float pValue) {
        return "float(" + pValue + ")";
    }

    public Float deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("float(") && pValue.endsWith(")") ) {
            pValue = pValue.substring(6, pValue.length() - 1);

            float value = Float.parseFloat(pValue);
            if ( value < this.minValue || value > this.maxValue ) throw new IllegalArgumentException("Float serializer can not serialize a value that is above or below the range: [%s,%s]. Value: %s".formatted(this.minValue, this.maxValue, value));

            return value;
        }

        throw new UnsupportedOperationException("Wuidebook Float serializer can not deserialize value: " + pValue);
    }

    public void onRange(Float pMinValue, Float pMaxRange) {
        ConfigSerializer.super.onRange(pMinValue, pMaxRange);
        this.minValue = pMinValue;
        this.maxValue = pMaxRange;
    }
}
