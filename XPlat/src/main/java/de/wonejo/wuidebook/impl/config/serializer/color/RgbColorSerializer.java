package de.wonejo.wuidebook.impl.config.serializer.color;

import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Optional;

final class RgbColorSerializer implements ConfigSerializer<Color> {

    private static RgbColorSerializer INSTANCE;

    public String serialize( @NotNull Color pValue ) {
        return "rgb(" + pValue.getRed() + "," + pValue.getGreen() + "," + pValue.getBlue() + ")";
    }

    public Optional<Color> deserialize( String pValue ) {
        pValue = pValue.trim();
        if ( pValue.startsWith("rgb(") && pValue.endsWith(")") ) pValue = pValue.substring(4, pValue.length() - 1);

        String[] components = pValue.split(",");
        int red = Integer.parseInt(components[0]);
        int green = Integer.parseInt(components[1]);
        int blue = Integer.parseInt(components[2]);

        return Optional.of(new Color(red, green, blue));
    }

    static RgbColorSerializer get () {
        if ( RgbColorSerializer.INSTANCE == null ) RgbColorSerializer.INSTANCE = new RgbColorSerializer();
        return RgbColorSerializer.INSTANCE;
    }

}
