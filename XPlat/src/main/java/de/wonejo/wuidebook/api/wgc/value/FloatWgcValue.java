package de.wonejo.wuidebook.api.wgc.value;

import de.wonejo.wuidebook.api.util.ResourceLocationUtils;
import org.jetbrains.annotations.NotNull;

public final class FloatWgcValue implements WgcNumericTagValue<Float> {
    public static final WgcValueTagType<Float> TYPE = new WgcValueTagType<>(ResourceLocationUtils.wuidebook("wgc_float"), Float.class);

    private final float value;

    public FloatWgcValue(float pValue) {
        this.value = pValue;
    }

    public Number getAsNumber() {
        return this.value;
    }

    @NotNull public WgcTagValue<Float> copy() {
        return new FloatWgcValue(this.value);
    }

    public WgcValueTagType<Float> type() {
        return TYPE;
    }

    public Float getValue() {
        return this.value;
    }
}
