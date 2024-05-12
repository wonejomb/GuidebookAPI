package de.wonejo.gapi.impl.cfg.serializer;

import de.wonejo.gapi.api.cfg.serializer.IConfigValueSerializer;

import java.awt.*;

public class ConfigValueSerializers {

    public static BooleanValueSerializer createBooleanSerializer () {
        return new BooleanValueSerializer();
    }

    public static IntegerValueSerializer createIntegerSerializer (int pMinValue, int pMaxValue) {
        return new IntegerValueSerializer(pMinValue, pMaxValue);
    }

    public static DoubleValueSerializer createDoubleSerializer (double pMinValue, double pMaxValue) {
        return new DoubleValueSerializer(pMinValue, pMaxValue);
    }

    public static IConfigValueSerializer<Color> createColorSerializer () {
        return new ColorValueSerializer();
    }

}
