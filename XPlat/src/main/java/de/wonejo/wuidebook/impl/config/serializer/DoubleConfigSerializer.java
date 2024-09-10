package de.wonejo.wuidebook.impl.config.serializer;

import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import de.wonejo.wuidebook.api.util.Constants;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public final class DoubleConfigSerializer implements ConfigSerializer<Double> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_double");

    private double minValue;
    private double maxValue;

    @NotNull
    public String serialize( @NotNull Double pValue ) {
        return "double(" + pValue + ")";
    }

    @NotNull
    public Optional<Double> deserialize( @NotNull String pValue ) {
        pValue = pValue.trim();
        if ( pValue.startsWith("double(") && pValue.endsWith(")") ) pValue = pValue.substring(7, pValue.length() - 1);

        double value = Double.parseDouble(pValue);
        if ( value < this.minValue || value > this.maxValue ) throw new IllegalArgumentException("Double serializer can not serialize a value that is above or below the range: [%s,%s]. Value: %s".formatted(this.minValue, this.maxValue, value));

        return Optional.of(value);
    }

    public void onRange(Double pMinValue, Double pMaxValue) {
        this.minValue = pMinValue;
        this.maxValue = pMaxValue;
    }

}
