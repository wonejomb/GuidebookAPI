package de.wonejo.wuidebook.impl.config.serializer.color;

import de.wonejo.wuidebook.api.config.serialization.ConfigValueSerializer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

class RgbColorSerializer implements ConfigValueSerializer<Color> {

    @NotNull
    @ApiStatus.Internal
    static RgbColorSerializer getRgb () {
        return new RgbColorSerializer();
    }

    private RgbColorSerializer () {}

    public String serialize(Color pValue) {
        return "rgb(" + pValue.getRed() + "," + pValue.getGreen() + "," + pValue.getBlue() + ")";
    }

    public DeserializeResult<Color> deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("rgb(") ) {
            if ( !pValue.endsWith(")") ) return DeserializeResult.fail(List.of("RGB color can not be deserialized! It seems to be open but not closed."));
            pValue = pValue.substring(4, pValue.length() - 1);
        }
        String[] split = pValue.split(",", 3);

        int red = Integer.parseInt(split[0]);
        int green = Integer.parseInt(split[1]);
        int blue = Integer.parseInt(split[2]);

        if (this.isValidChannel(red)) return DeserializeResult.fail(List.of("Can not deserialize RGB color, red channel value is below or above 255!"));
        if (this.isValidChannel(green)) return DeserializeResult.fail(List.of("Can not deserialize RGB color, green channel value is below or above 255!"));
        if (this.isValidChannel(blue)) return DeserializeResult.fail(List.of("Can not deserialize RGB color, blue channel value is below or above 255!"));

        return DeserializeResult.success(new Color(red, green, blue));
    }

    private boolean isValidChannel ( int pValue ) {
        return pValue < 0 || pValue > 255;
    }

    public boolean isValid(Color pValue) {
        return true;
    }

    public String getValidValuesDescription() {
        return "Any value on any RGB channel on a range of: [0, 255]";
    }

}
