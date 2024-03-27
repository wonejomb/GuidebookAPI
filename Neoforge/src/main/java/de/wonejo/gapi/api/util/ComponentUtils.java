package de.wonejo.gapi.api.util;

import net.minecraft.client.resources.language.I18n;
import org.jetbrains.annotations.NotNull;

public class ComponentUtils {

    public static String getFormattedText (@NotNull String pString ) {
        return pString.replaceAll("&", "\u00A7");
    }

    public static @NotNull String parseEffect (String pInput, Object... pFormat ) {
        return getFormattedText(parse(pInput, pFormat));
    }

    public static @NotNull String parse (String pInput, Object... pFormat ) {
        return I18n.get(pInput, pFormat);
    }
}
