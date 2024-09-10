package de.wonejo.wuidebook.impl.config.serializer.color;

import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Optional;

final class HexColorSerializer implements ConfigSerializer<Color> {

    private static HexColorSerializer INSTANCE;

    private HexColorSerializer () {}

    public @NotNull String serialize(@NotNull Color pValue) {
        return "hex(" + Integer.toHexString(pValue.getRGB()) + ")";
    }

    public @NotNull Optional<Color> deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("hex(") && pValue.endsWith(")") ) pValue = pValue.substring(4, pValue.length() - 1);
        return Optional.of(Color.decode(pValue));
    }

    static HexColorSerializer get () {
        if ( HexColorSerializer.INSTANCE == null ) HexColorSerializer.INSTANCE = new HexColorSerializer();
        return HexColorSerializer.INSTANCE;
    }

}
