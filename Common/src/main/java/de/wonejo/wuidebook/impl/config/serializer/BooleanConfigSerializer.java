package de.wonejo.wuidebook.impl.config.serializer;

import de.wonejo.wuidebook.WuidebookCommon;
import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class BooleanConfigSerializer implements ConfigSerializer<Boolean> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(WuidebookCommon.MOD_ID, "boolean_cfg");

    public String serialize(@NotNull Boolean pValue) {
        return "bool(" + pValue.toString() + ")";
    }

    public Boolean deserialize(String pValue) {
        pValue = pValue.trim();

        if ( pValue.startsWith("bool(") && pValue.endsWith(")") ) {
            pValue = pValue.substring(5, pValue.length() - 1);
            return Boolean.parseBoolean(pValue);
        }

        throw new UnsupportedOperationException("Wuidebook Boolean serializer can not deserialize value: " + pValue);
    }

}
