package de.wonejo.gapi.impl.cfg.serializer;

import de.wonejo.gapi.api.cfg.serializer.IConfigValueSerializer;

import java.awt.*;

public class ColorValueSerializer implements IConfigValueSerializer<Color> {

    public String serialize(Color pValue) {
        return RGBAColorValueSerializer.get().serialize(pValue);
    }

    public Color deserialize(String pValue) {

        if ( pValue.startsWith("rgba") )
            return ColorFormat.RGBA.deserialize(pValue);
        else if ( pValue.startsWith("rgb") )
            return ColorFormat.RGB.deserialize(pValue);
        else if ( pValue.startsWith("color") )
            return ColorFormat.NUMERIC.deserialize(pValue);

        return Color.BLACK;
    }

    public enum ColorFormat {
        RGBA((value) -> RGBAColorValueSerializer.get().deserialize(value)),
        RGB((value) -> RGBColorValueSerializer.get().deserialize(value)),
        NUMERIC((value) -> NumericColorValueSerializer.get().deserialize(value));

        private final DeserializeStage deserializeStage;

        ColorFormat ( DeserializeStage pDeserializeStage ) {
            this.deserializeStage = pDeserializeStage;
        }

        public Color deserialize ( String pValue )
        {
            return this.deserializeStage.deserialize(pValue);
        }
        @FunctionalInterface
        interface DeserializeStage {
            Color deserialize ( String pValue );
        }
    }
}
