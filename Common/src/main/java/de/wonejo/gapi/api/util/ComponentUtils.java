package de.wonejo.gapi.api.util;

import net.minecraft.client.resources.language.I18n;

public class ComponentUtils {

    public static String getFormattedText ( String pString ) {
        return pString.replaceAll("&", "\u00A7");
    }

    public static String parseEffect (String pInput, Object... pFormat ) {
        return getFormattedText(parse(pInput, pFormat));
    }

    public static String parse (String pInput, Object... pFormat ) {
        return I18n.get(pInput, pFormat);
    }
}
