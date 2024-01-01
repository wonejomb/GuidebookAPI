package de.mrbunny.guidebook.util;

import net.neoforged.neoforge.common.I18nExtension;

public class ComponentUtils {

    public static String getFormattedText  ( String pString ) {
        return pString.replaceAll("&", "\u00A7");
    }

    public static String parseEffect ( String pInput, Object... pFormat ) {
        return getFormattedText ( parse(pInput, pFormat) );
    }

    public static String parse ( String pInput, Object... pFormat ) {
        return I18nExtension.parseMessage(pInput, pFormat);
    }
}
