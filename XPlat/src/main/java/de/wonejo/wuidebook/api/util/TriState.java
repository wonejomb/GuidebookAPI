package de.wonejo.wuidebook.api.util;

import org.jetbrains.annotations.NotNull;

public enum TriState {
    TRUE,
    FALSE,
    UNDEFINED
    ;

    public static TriState fromBoolean ( Boolean pBoolValue ) {
        if ( pBoolValue == null ) return UNDEFINED;
        return pBoolValue ? TRUE : FALSE;
    }

    public static TriState fromBooleanText ( @NotNull String pValue ) {
        return switch (pValue) {
            case "true", "True", "TRUE" -> TRUE;
            case "false", "False", "FALSE" -> FALSE;
            case "undefined", "Undefined", "UNDEFINED", "unset", "Unset", "UNSET", "unknown", "Unknown", "UNKNOWN" -> UNDEFINED;
            default -> throw new IllegalStateException("Unexpected tri-state value: " + pValue);
        };
    }

    public TriState turnFalse () {
        return FALSE;
    }

    public TriState turnTrue () {
        return TRUE;
    }

    public TriState turnUndefined () {
        return UNDEFINED;
    }

    public boolean isTrue ( ) {
        return this == TRUE;
    }

    public boolean isFalse ( ) {
        return this == FALSE;
    }

    public boolean isUndefined ( ) {
        return this == UNDEFINED;
    }

    public @NotNull String toString() {
        return this.name().toLowerCase();
    }
}
