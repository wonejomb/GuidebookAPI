package de.wonejo.wuidebook.api.wgc.value;

import de.wonejo.wuidebook.api.util.ResourceLocationUtils;

public class NullWgcValue implements WgcTagValue<Object> {
    public static final WgcValueTagType<Object> TYPE = new WgcValueTagType<>(ResourceLocationUtils.wuidebook("wgc_null"), Object.class);

    public WgcTagValue<Object> copy() {
        return null;
    }

    public WgcValueTagType<Object> type() {
        return null;
    }

    public Object getValue() {
        return null;
    }
}
