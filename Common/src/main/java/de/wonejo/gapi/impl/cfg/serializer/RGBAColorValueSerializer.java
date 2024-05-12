package de.wonejo.gapi.impl.cfg.serializer;

import de.wonejo.gapi.api.cfg.serializer.IConfigValueSerializer;
import de.wonejo.gapi.api.util.ConfigValueSerializationException;

import java.awt.*;

class RGBAColorValueSerializer implements IConfigValueSerializer<Color> {
    private static RGBAColorValueSerializer INSTANCE;

    public String serialize(Color pValue) {
        return "rgba(%s,%s,%s,%s)".formatted(pValue.getRed(), pValue.getGreen(), pValue.getBlue(), pValue.getAlpha());
    }

    public Color deserialize(String pValue) {
        pValue = pValue.trim();

        if (pValue.startsWith("rgba(") && pValue.endsWith(")") )
            pValue = pValue.substring(5, pValue.length() - 1);

        try {
            String[] components = pValue.split(",");

            int red = Integer.parseInt(components[0]);
            int green = Integer.parseInt(components[1]);
            int blue = Integer.parseInt(components[2]);
            int alpha = Integer.parseInt(components[3]);

            if ( !componentsAreValid(red, green, blue, alpha) )
                throw new IllegalArgumentException("Someone of the color components has an invalid value. All color components need to be between 0 and 255. RED: %s  GREEN: %s BLUE: %s ALPHA: %s".formatted(red, green, blue, alpha));

            return new Color(red, green, blue, alpha);
        } catch (ConfigValueSerializationException pEx) {
            throw new ConfigValueSerializationException("Unable to parse colors: '%s'".formatted(pValue));
        }
    }

    private boolean componentsAreValid ( int pRed, int pGreen, int pBlue, int pAlpha ) {
        return isValidComponent(pRed) && isValidComponent(pGreen) && isValidComponent(pBlue) && isValidComponent(pAlpha);
    }

    private boolean isValidComponent ( int pComponent ) {
        return pComponent >= 0 && pComponent <= 255;
    }

    public static RGBAColorValueSerializer get () {
        if ( RGBAColorValueSerializer.INSTANCE == null ) RGBAColorValueSerializer.INSTANCE = new RGBAColorValueSerializer();
        return RGBAColorValueSerializer.INSTANCE;
    }
}
