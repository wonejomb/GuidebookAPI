package de.wonejo.wuidebook.api.wgc.value;

import de.wonejo.wuidebook.api.util.ResourceLocationUtils;
import org.jetbrains.annotations.NotNull;

public final class StringWgcValue implements WgcTagValue<String> {
    public static final WgcValueTagType<String> TYPE = new WgcValueTagType<>(ResourceLocationUtils.wuidebook("wgc_str"), String.class);

    private final String value;

    public StringWgcValue(String pValue) {
        this.value = pValue;
    }

    @NotNull public WgcTagValue<String> copy() {
        return new StringWgcValue(this.value);
    }

    public WgcValueTagType<String> type() {
        return TYPE;
    }

    public String getValue() {
        return this.value;
    }
}
