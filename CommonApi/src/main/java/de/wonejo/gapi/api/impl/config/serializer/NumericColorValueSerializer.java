package de.wonejo.gapi.api.impl.config.serializer;

import de.wonejo.gapi.api.config.serializer.IConfigValueSerializer;
import de.wonejo.gapi.api.util.ConfigValueSerializationException;

import java.awt.*;

class NumericColorValueSerializer implements IConfigValueSerializer<Color> {
    private static NumericColorValueSerializer INSTANCE;

    public String serialize(Color pValue) {
        return "color(%s)".formatted(pValue.getRGB());
    }

    public Color deserialize(String pValue) {
        pValue = pValue.trim();

        if ( pValue.startsWith("color(") && pValue.endsWith(")") ) pValue = pValue.substring(6, pValue.length() - 1);

        try {
            int value = Integer.parseInt(pValue);

            return new Color(value);
        } catch (ConfigValueSerializationException pEx) {
            throw new ConfigValueSerializationException("Unable to parse color: %s".formatted(pValue));
        }
    }

    public static NumericColorValueSerializer get () {
        if ( NumericColorValueSerializer.INSTANCE == null ) NumericColorValueSerializer.INSTANCE = new NumericColorValueSerializer();
        return NumericColorValueSerializer.INSTANCE;
    }
}
