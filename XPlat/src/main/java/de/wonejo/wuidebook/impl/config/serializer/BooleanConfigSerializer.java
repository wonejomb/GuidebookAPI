package de.wonejo.wuidebook.impl.config.serializer;

import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import de.wonejo.wuidebook.api.util.Constants;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public final class BooleanConfigSerializer implements ConfigSerializer<Boolean> {
    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_bool");

    @NotNull
    public String serialize(Boolean pValue) {
        return "bool(" + pValue + ")";
    }

    public @NotNull Optional<Boolean> deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("bool(") && pValue.endsWith(")") ) pValue = pValue.substring(5, pValue.length() - 1);
        boolean value = Boolean.parseBoolean(pValue);
        return Optional.of(value);
    }

}
