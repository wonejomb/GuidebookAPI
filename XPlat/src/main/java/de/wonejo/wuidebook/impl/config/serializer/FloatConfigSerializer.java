package de.wonejo.wuidebook.impl.config.serializer;

import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import de.wonejo.wuidebook.api.util.Constants;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public final class FloatConfigSerializer implements ConfigSerializer<Float> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_float");

    private float minValue;
    private float maxValue;

    @NotNull
    public String serialize( @NotNull Float pValue) {
        return "float(" + pValue + ")";
    }

    public @NotNull Optional<Float> deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("float(") && pValue.endsWith(")") ) pValue = pValue.substring(6, pValue.length() - 1);

        float value = Float.parseFloat(pValue);
        if ( value < this.minValue || value > this.maxValue ) throw new IllegalArgumentException("Float serializer can not serialize a value that is above or below the range: [%s,%s]. Value: %s".formatted(this.minValue, this.maxValue, value));

        return Optional.of(value);
    }

    public void onRange(Float pMinValue, Float pMaxValue) {
        this.minValue = pMinValue;
        this.maxValue = pMaxValue;
    }
}
