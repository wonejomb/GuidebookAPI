package de.wonejo.gapi.impl.cfg.serializer;

import de.wonejo.gapi.api.cfg.serializer.IConfigValueSerializer;
import de.wonejo.gapi.api.util.ConfigValueSerializationException;

import java.awt.*;

class RGBColorValueSerializer implements IConfigValueSerializer<Color> {
    private static RGBColorValueSerializer INSTANCE;

    public String serialize(Color pValue) {
        return "rgb(%s,%s,%s)".formatted(pValue.getRed(), pValue.getGreen(), pValue.getBlue());
    }

    public Color deserialize(String pValue) {
        pValue = pValue.trim();

        if (pValue.startsWith("rgb(") && pValue.endsWith(")"))
            pValue = pValue.substring(4, pValue.length() - 1);

        try {
            String[] components = pValue.split(",");

            int red = Integer.parseInt(components[0]);
            int green = Integer.parseInt(components[1]);
            int blue = Integer.parseInt(components[2]);

            if (!componentsAreValid(red, green, blue))
                throw new IllegalArgumentException("Someone of the color components has an invalid value. All color components need to be between 0 and 255. RED: %s  GREEN: %s BLUE: %s".formatted(red, green, blue));

            return new Color(red, green, blue);
        } catch (ConfigValueSerializationException pEx) {
            throw new ConfigValueSerializationException("Unable to parse colors: '%s'".formatted(pValue));
        }
    }

    private boolean componentsAreValid(int pRed, int pGreen, int pBlue) {
        return isValidComponent(pRed) && isValidComponent(pGreen) && isValidComponent(pBlue);
    }

    private boolean isValidComponent(int pComponent) {
        return pComponent >= 0 && pComponent <= 255;
    }

    public static RGBColorValueSerializer get() {
        if (RGBColorValueSerializer.INSTANCE == null) RGBColorValueSerializer.INSTANCE = new RGBColorValueSerializer();
        return RGBColorValueSerializer.INSTANCE;
    }
}
