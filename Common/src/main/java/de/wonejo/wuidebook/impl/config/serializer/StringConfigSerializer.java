package de.wonejo.wuidebook.impl.config.serializer;

import de.wonejo.wuidebook.WuidebookCommon;
import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class StringConfigSerializer implements ConfigSerializer<String> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(WuidebookCommon.MOD_ID, "string_cfg");

    public String serialize(String pValue) {
        return "str('" + pValue + "')";
    }

    public String deserialize(@NotNull String pValue) {
        if ( pValue.startsWith("str('") && pValue.endsWith("')") ) {
            pValue = pValue.substring(5, pValue.length() - 2);

            return pValue;
        }

        throw new UnsupportedOperationException("Wuidebook String serializer can not deserialize value: " + pValue);
    }
}
