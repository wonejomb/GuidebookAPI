package de.wonejo.gapi.core;

import de.wonejo.gapi.api.util.Constants;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public class ModDataComponents {

    public static final DataComponentType<Integer> PAGE = DataComponentType.<Integer>builder().persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT).build();
    public static final DataComponentType<Integer> CATEGORY = DataComponentType.<Integer>builder().persistent(ExtraCodecs.NON_NEGATIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT).build();
    public static final DataComponentType<ResourceLocation> ENTRY = DataComponentType.<ResourceLocation>builder().persistent(ResourceLocation.CODEC).networkSynchronized(ResourceLocation.STREAM_CODEC).build();

    public static void registerComponents (@NotNull BiConsumer<DataComponentType<?>, ResourceLocation> pConsumer) {
        pConsumer.accept(PAGE, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "page"));
        pConsumer.accept(CATEGORY, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "category"));
        pConsumer.accept(ENTRY, ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "entry"));
    }

}
