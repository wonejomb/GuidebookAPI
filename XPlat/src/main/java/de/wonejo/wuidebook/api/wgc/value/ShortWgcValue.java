package de.wonejo.wuidebook.api.wgc.value;

import de.wonejo.wuidebook.api.util.ResourceLocationUtils;

public final class ShortWgcValue implements WgcNumericTagValue<Short> {
    public static final WgcValueTagType<Short> TYPE = new WgcValueTagType<>(ResourceLocationUtils.wuidebook("wgc_short"), Short.class);

    private final short value;

    public ShortWgcValue(short pValue) {
        this.value = pValue;
    }

    public Number getAsNumber() {
        return this.value;
    }

    public WgcTagValue<Short> copy() {
        return new ShortWgcValue(this.value);
    }

    public WgcValueTagType<Short> type() {
        return TYPE;
    }

    public Short getValue() {
        return this.value;
    }
}
