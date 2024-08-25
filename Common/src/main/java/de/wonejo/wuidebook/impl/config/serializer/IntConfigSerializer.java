package de.wonejo.wuidebook.impl.config.serializer;

import de.wonejo.wuidebook.WuidebookCommon;
import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class IntConfigSerializer implements ConfigSerializer<Integer> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(WuidebookCommon.MOD_ID, "integer_cfg");

    private int minValue;
    private int maxValue;

    public String serialize(@NotNull Integer pValue) {
        return "int(" + pValue.toString() + ")";
    }

    public Integer deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("int(") && pValue.endsWith(")") ) {
            pValue = pValue.substring(4, pValue.length() - 1);

            int value = Integer.parseInt(pValue);
            if ( value < this.minValue && value > this.maxValue ) throw new IllegalArgumentException("Integer serializer can not serialize a value that is above or below the range: [%s,%s]. Value: %s".formatted(this.minValue, this.maxValue, value));

            return value;
        }

        throw new UnsupportedOperationException("Wuidebook Integer serializer can not deserialize value: " + pValue);
    }

    public void onRange(Integer pMinValue, Integer pMaxRange) {
        ConfigSerializer.super.onRange(pMinValue, pMaxRange);

        this.minValue = pMinValue;
        this.maxValue = pMaxRange;
    }
}
