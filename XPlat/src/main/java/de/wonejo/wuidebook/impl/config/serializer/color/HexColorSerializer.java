package de.wonejo.wuidebook.impl.config.serializer.color;

import de.wonejo.wuidebook.api.config.serialization.ConfigValueSerializer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

class HexColorSerializer implements ConfigValueSerializer<Color> {

    @NotNull @ApiStatus.Internal
    static HexColorSerializer getHex () {
        return new HexColorSerializer();
    }

    private HexColorSerializer () {}

    public String serialize(@NotNull Color pValue) {
        return "hex(" + Integer.toHexString(pValue.getRGB()) + ")";
    }

    public DeserializeResult<Color> deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("hex(") ) {
            if ( !pValue.endsWith(")") ) return DeserializeResult.fail(List.of("Hexadecimal Color can not be deserialized! It seems to be open but not closed."));
            pValue = pValue.substring(4, pValue.length() - 1);
        }

        Color color = Color.decode(pValue);
        return DeserializeResult.success(color);
    }

    public boolean isValid(Color pValue) {
        return true;
    }

    public String getValidValuesDescription() {
        return "Any hexadecimal color code in the 'hex()'";
    }

}
