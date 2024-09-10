package de.wonejo.wuidebook.impl.config.serializer;

import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import de.wonejo.wuidebook.api.util.Constants;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public final class IntConfigSerializer implements ConfigSerializer<Integer> {

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_int");

    private int minValue;
    private int maxValue;

    @NotNull
    public String serialize( @NotNull Integer pValue ) {
        return "int(" + pValue + ")";
    }

    @NotNull
    public Optional<Integer> deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("int(") && pValue.endsWith(")") ) pValue = pValue.substring(4, pValue.length() - 1);

        int value = Integer.parseInt(pValue);
        if ( value < this.minValue || value > this.maxValue ) throw new IllegalArgumentException("Integer serializer can not serialize a value that is above or below the range: [%s,%s]. Value: %s".formatted(this.minValue, this.maxValue, value));

        return Optional.of(value);
    }

    /**
     * The check for numbers there is not util, just remove it from numeric serializers.
     */
    public void onRange(Integer pMinValue, Integer pMaxValue) {
        this.minValue = pMinValue;
        this.maxValue = pMaxValue;
    }

}
