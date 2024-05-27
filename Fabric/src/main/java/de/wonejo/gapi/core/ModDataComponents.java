package de.wonejo.gapi.core;

import de.wonejo.gapi.api.util.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ModDataComponents {

    public static final DataComponentType<Integer> PAGE = DataComponentType.<Integer>builder().persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT).build();
    public static final DataComponentType<Integer> CATEGORY = DataComponentType.<Integer>builder().persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT).build();
    public static final DataComponentType<ResourceLocation> ENTRY = DataComponentType.<ResourceLocation>builder().persistent(ResourceLocation.CODEC).networkSynchronized(ResourceLocation.STREAM_CODEC).build();

    public static void registerDataComponents ( ) {
        registerDataComponent("page", () -> PAGE);
        registerDataComponent("category", () -> CATEGORY);
        registerDataComponent("entry", () -> ENTRY);
    }

    private static @NotNull DataComponentType<?> registerDataComponent (String pId, Supplier<DataComponentType<?>> pSupplier) {
        ResourceLocation id = new ResourceLocation(Constants.MOD_ID, pId);
        return Registry.<DataComponentType<?>>register(BuiltInRegistries.DATA_COMPONENT_TYPE, id.toString(), pSupplier.get());
    }
}
