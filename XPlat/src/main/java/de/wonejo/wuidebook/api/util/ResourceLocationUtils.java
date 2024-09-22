package de.wonejo.wuidebook.api.util;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ResourceLocationUtils {

    @NotNull
    public static ResourceLocation minecraft (String pPath) {
        return ResourceLocation.withDefaultNamespace(pPath);
    }

    @NotNull
    public static ResourceLocation wuidebook ( String pPath ) {
        return ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, pPath);
    }

    @NotNull
    public static ResourceLocation common ( String pPath ) {
        return ResourceLocation.fromNamespaceAndPath("c", pPath);
    }

}
