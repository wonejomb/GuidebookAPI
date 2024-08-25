package de.wonejo.wuidebook.impl.config.serializer.color;

import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

class RGBColorConfigSerializer implements ConfigSerializer<Color> {
    private static RGBColorConfigSerializer INSTANCE;

    public String serialize(@NotNull Color pValue) {
        return "rgb(" + pValue.getRed() + "," + pValue.getGreen() + "," + pValue.getBlue() + ")";
    }

    public String serializeDefaultComment(@NotNull Color pValue) {
        return this.serialize(pValue);
    }

    public Color deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("rgb(") && pValue.endsWith(")") ) {
            pValue = pValue.substring(4, pValue.length() - 1);

            String[] components = pValue.split(",");
            int red = Integer.parseInt(components[0]);
            int green = Integer.parseInt(components[1]);
            int blue = Integer.parseInt(components[2]);
            if ( !isOnRange(red) || !isOnRange(green) || !isOnRange(blue) ) throw new UnsupportedOperationException("Wuidebook Color serializer can not serialize RGB value that is below or above range [0, 255]. Color channels: red=%s, green=%s, blue=%s".formatted(red, green, blue));

            return new Color(red, green, blue, 255);
        }

        return Color.BLACK;
    }

    private boolean isOnRange ( int pChannelValue ) {
        return pChannelValue >= 0 && pChannelValue <= 255;
    }

    public static RGBColorConfigSerializer get () {
        if ( RGBColorConfigSerializer.INSTANCE == null ) return RGBColorConfigSerializer.get();
        return RGBColorConfigSerializer.get();
    }

}
