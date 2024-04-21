package de.wonejo.gapi.api.impl.config.serializer;

import de.wonejo.gapi.api.config.serializer.IConfigValueSerializer;

import java.awt.*;

public class ColorValueSerializer implements IConfigValueSerializer<Color> {

    private final ColorFormat format;

    protected ColorValueSerializer ( ColorFormat pFormat ) {
        this.format = pFormat;
    }

    public String serialize(Color pValue) {
        if ( this.format == ColorFormat.RGBA )
            return RGBAColorValueSerializer.get().serialize(pValue);
        else if ( this.format == ColorFormat.RGB )
            return RGBColorValueSerializer.get().serialize(pValue);
        else if ( this.format == ColorFormat.NUMERIC )
            return NumericColorValueSerializer.get().serialize(pValue);
        else
            throw new UnsupportedOperationException("Color value serializer doesn't know how serialize this color format. Color format: %s".formatted(pValue));
    }

    public Color deserialize(String pValue) {
        if ( this.format == ColorFormat.RGBA )
            return RGBAColorValueSerializer.get().deserialize(pValue);
        else if ( this.format == ColorFormat.RGB )
            return RGBColorValueSerializer.get().deserialize(pValue);
        else if ( this.format == ColorFormat.NUMERIC )
            return NumericColorValueSerializer.get().deserialize(pValue);
        else
            throw new UnsupportedOperationException("Color deserializer doesn't know how deserialize this color format. Color format: %s".formatted(pValue));
    }

    public enum ColorFormat {
        RGBA,
        RGB,
        NUMERIC
    }
}
