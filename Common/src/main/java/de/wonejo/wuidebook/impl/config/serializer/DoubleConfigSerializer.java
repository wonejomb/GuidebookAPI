package de.wonejo.wuidebook.impl.config.serializer;

import de.wonejo.wuidebook.WuidebookCommon;
import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class DoubleConfigSerializer implements ConfigSerializer<Double> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(WuidebookCommon.MOD_ID, "double_cfg");

    private double minValue;
    private double maxValue;

    public String serialize(@NotNull Double pValue) {
        return "double(" + pValue.toString() + ")";
    }

    public Double deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("double(") && pValue.endsWith(")") ) {
            pValue = pValue.substring(8, pValue.length() - 1);

            double value = Double.parseDouble(pValue);
            if ( value < this.minValue || value > this.maxValue ) throw new IllegalArgumentException("Double serializer can not serialize a value that is above or below the range: [%s,%s]. Value: %s".formatted(this.minValue, this.maxValue, value));

            return value;
        }

        throw new UnsupportedOperationException("Wuidebook Double serializer can not deserialize value: " + pValue);
    }

    public void onRange(Double pMinValue, Double pMaxRange) {
        ConfigSerializer.super.onRange(pMinValue, pMaxRange);
        this.minValue = pMinValue;
        this.maxValue = pMaxRange;
    }
}
