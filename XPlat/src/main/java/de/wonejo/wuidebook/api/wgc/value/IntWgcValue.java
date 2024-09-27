package de.wonejo.wuidebook.api.wgc.value;

import de.wonejo.wuidebook.api.util.ResourceLocationUtils;

public final class IntWgcValue implements WgcNumericTagValue<Integer> {
    public static final WgcValueTagType<Integer> TYPE = new WgcValueTagType<>(ResourceLocationUtils.wuidebook("wgc_int"), Integer.class);

    private final int value;

    public IntWgcValue(int pValue) {
        this.value = pValue;
    }

    public Number getAsNumber() {
        return this.value;
    }

    public WgcTagValue<Integer> copy() {
        return new IntWgcValue(this.value);
    }

    public WgcValueTagType<Integer> type() {
        return TYPE;
    }

    public Integer getValue() {
        return this.value;
    }

    public String toString() {
        return "int(" + this.value + ")";
    }
}
