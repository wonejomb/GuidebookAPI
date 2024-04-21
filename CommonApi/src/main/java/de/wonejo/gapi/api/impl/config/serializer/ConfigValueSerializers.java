package de.wonejo.gapi.api.impl.config.serializer;

import de.wonejo.gapi.api.config.serializer.IConfigValueSerializer;

import java.awt.*;

public class ConfigValueSerializers {

    public static BooleanValueSerializer createBooleanSerializer () {
        return new BooleanValueSerializer();
    }

    public static IntegerValueSerializer createIntegerSerializer (int pMinValue, int pMaxValue) {
        return new IntegerValueSerializer(pMinValue, pMaxValue);
    }

    public static FloatValueSerializer createFloatSerializer (float pMinValue, float pMaxValue) {
        return new FloatValueSerializer(pMinValue, pMaxValue);
    }

    public static DoubleValueSerializer createDoubleSerializer (double pMinValue, double pMaxValue) {
        return new DoubleValueSerializer(pMinValue, pMaxValue);
    }

    public static IConfigValueSerializer<Color> createColorSerializer (ColorValueSerializer.ColorFormat pFormat) {
        return new ColorValueSerializer(pFormat);
    }

}
