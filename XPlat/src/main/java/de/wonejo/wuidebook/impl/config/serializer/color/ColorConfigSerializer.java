package de.wonejo.wuidebook.impl.config.serializer.color;

import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import de.wonejo.wuidebook.api.util.Constants;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Optional;

public final class ColorConfigSerializer implements ConfigSerializer<Color> {

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_color");

    public String serialize(Color pValue) {
        return RgbColorSerializer.get().serialize(pValue);
    }

    public Optional<Color> deserialize(@NotNull String pValue) {
        if (pValue.startsWith("rgb")) return RgbColorSerializer.get().deserialize(pValue);
        else if ( pValue.startsWith("color") ) return CodeColorSerializer.get().deserialize(pValue);
        else if ( pValue.startsWith("hex") ) return HexColorSerializer.get().deserialize(pValue);

        throw new UnsupportedOperationException("Color config does not know how deserialize color format: " + pValue);
    }

}
