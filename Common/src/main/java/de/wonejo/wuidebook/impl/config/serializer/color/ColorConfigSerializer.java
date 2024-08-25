package de.wonejo.wuidebook.impl.config.serializer.color;

import de.wonejo.wuidebook.WuidebookCommon;
import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class ColorConfigSerializer implements ConfigSerializer<Color> {

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(WuidebookCommon.MOD_ID, "color_cfg");

    public String serialize(Color pValue) {
        return RGBColorConfigSerializer.get().serialize(pValue);
    }

    public Color deserialize(@NotNull String pValue) {
        if ( pValue.startsWith("rgb") ) return RGBColorConfigSerializer.get().deserialize(pValue);
        else if ( pValue.startsWith("color") ) return ColorCodeConfigSerializer.get().deserialize(pValue);
        else throw new UnsupportedOperationException("Color config serializer does not know how deserialize color format: " + pValue);
    }

    public String serializeDefaultComment(@NotNull Color pValue) {
        return this.serialize(pValue);
    }

}
