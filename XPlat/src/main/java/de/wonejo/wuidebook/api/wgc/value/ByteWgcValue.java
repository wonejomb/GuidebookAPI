package de.wonejo.wuidebook.api.wgc.value;

import de.wonejo.wuidebook.api.util.ResourceLocationUtils;
import org.jetbrains.annotations.NotNull;

public final class ByteWgcValue implements WgcNumericTagValue<Byte> {
    public static final WgcValueTagType<Byte> TYPE = new WgcValueTagType<>(ResourceLocationUtils.wuidebook("wgc_byte"), Byte.class);

    private final byte value;

    public ByteWgcValue(byte pValue) {
        this.value = pValue;
    }

    public Number getAsNumber() {
        return this.value;
    }

    @NotNull public WgcTagValue<Byte> copy() {
        return new ByteWgcValue(this.value);
    }

    public WgcValueTagType<Byte> type() {
        return TYPE;
    }

    public Byte getValue() {
        return this.value;
    }

}
