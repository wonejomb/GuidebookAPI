package de.wonejo.wuidebook.impl.config.serializer.color;

import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

class ColorCodeConfigSerializer implements ConfigSerializer<Color> {

    private static ColorCodeConfigSerializer INSTANCE;

    public String serialize(Color pValue) {
        return "color(" + pValue + ")";
    }

    public String serializeDefaultComment(@NotNull Color pValue) {
        return this.serialize(pValue);
    }

    public Color deserialize(String pValue) {
        pValue = pValue.trim();

        if ( pValue.startsWith("color(") && pValue.endsWith(")") ) {
            pValue = pValue.substring(6, pValue.length() - 1);

            int color = Integer.parseInt(pValue);
            if ( color < Color.BLACK.getRGB() || color > Color.WHITE.getRGB() ) throw new UnsupportedOperationException("Color serializer can not serialize a value that is above or below [%s, %s]. Color value: %s".formatted(Color.BLACK.getRGB(), Color.WHITE.getRGB(), color));

            return new Color(color);
        }

        return Color.BLACK;
    }

    public static ColorCodeConfigSerializer get () {
        if ( ColorCodeConfigSerializer.INSTANCE == null ) ColorCodeConfigSerializer.INSTANCE = new ColorCodeConfigSerializer();
        return ColorCodeConfigSerializer.INSTANCE;
    }

}
