package de.wonejo.wuidebook.impl.config.serializer.color;

import de.wonejo.wuidebook.api.config.serialization.ConfigValueSerializer;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

public class ColorConfigSerializer implements ConfigValueSerializer<Color> {

    public String serialize(Color pValue) {
        return RgbColorSerializer.getRgb().serialize(pValue);
    }

    public DeserializeResult<Color> deserialize(@NotNull String pValue) {
        if ( pValue.startsWith("rgb") ) return RgbColorSerializer.getRgb().deserialize(pValue);
        else if ( pValue.startsWith("hex") ) return HexColorSerializer.getHex().deserialize(pValue);

        return DeserializeResult.fail(List.of("Color can not be deserialized! Unknown color format for deserialization!"));
    }

    public boolean isValid(Color pValue) {
        return true;
    }

    public String getValidValuesDescription() {
        return "Color can be de/serialized if value is something like: rgb(120, 120, 120) or hex(0x787878)";
    }

}
