package de.wonejo.wuidebook.api.wgc.value;

import de.wonejo.wuidebook.api.util.ResourceLocationUtils;
import org.jetbrains.annotations.NotNull;

public final class DoubleWgcValue implements WgcNumericTagValue<Double> {
    public static final WgcValueTagType<Double> TYPE = new WgcValueTagType<>(ResourceLocationUtils.wuidebook("wgc_double"), Double.class);

    private final double value;

    public DoubleWgcValue(double pValue) {
        this.value = pValue;
    }

    public Number getAsNumber() {
        return this.value;
    }

    @NotNull public WgcTagValue<Double> copy() {
        return new DoubleWgcValue(this.value);
    }

    public WgcValueTagType<Double> type() {
        return TYPE;
    }

    public Double getValue() {
        return this.value;
    }
}
