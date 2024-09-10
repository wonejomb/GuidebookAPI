package de.wonejo.wuidebook.impl.config.serializer;

import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import de.wonejo.wuidebook.api.util.Constants;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public final class StringConfigSerializer implements ConfigSerializer<String> {

    public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_str");

    @NotNull
    public String serialize(String pValue) {
        return "str('" + pValue + "')";
    }

    public @NotNull Optional<String> deserialize(@NotNull String pValue) {
        if ( pValue.startsWith("str'") && pValue.endsWith("')") ) pValue = pValue.substring(5, pValue.length() - 2);
        return Optional.of(pValue);
    }

}
