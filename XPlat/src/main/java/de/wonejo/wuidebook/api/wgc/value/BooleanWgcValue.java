package de.wonejo.wuidebook.api.wgc.value;

import de.wonejo.wuidebook.api.util.ResourceLocationUtils;
import de.wonejo.wuidebook.api.util.TriState;

public final class BooleanWgcValue implements WgcTagValue<TriState> {
    public static final WgcValueTagType<TriState> TYPE = new WgcValueTagType<>(ResourceLocationUtils.wuidebook("wgc_bool"), TriState.class);

    private final TriState boolState;

    public BooleanWgcValue(TriState pBoolState) {
        this.boolState = pBoolState;
    }

    public WgcTagValue<TriState> copy() {
        return new BooleanWgcValue(this.boolState);
    }

    public WgcValueTagType<TriState> type() {
        return TYPE;
    }

    public TriState getValue() {
        return this.boolState;
    }
}

