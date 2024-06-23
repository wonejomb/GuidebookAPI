package de.wonejo.gapi;

import de.wonejo.gapi.core.ModDataComponents;
import de.wonejo.gapi.handler.GapiRegistryHelper;
import de.wonejo.gapi.handler.WorldJoinHandler;
import de.wonejo.gapi.network.ReadingStatePayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;

public class GapiFabricMod implements ModInitializer {

    public void onInitialize() {
        CommonGapiMod.init();

        GapiRegistryHelper.registerGuides();
        ModDataComponents.registerComponents(bind(BuiltInRegistries.DATA_COMPONENT_TYPE));

        PayloadTypeRegistry.playC2S().register(ReadingStatePayload.TYPE, ReadingStatePayload.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(ReadingStatePayload.TYPE, ReadingStatePayload.STREAM_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(ReadingStatePayload.TYPE, (payload, context) -> {
            ReadingStatePayload.handle(payload, context.player());
        });

        WorldJoinHandler.onPlayerJoin();
    }

    private static <T> @NotNull BiConsumer<T, ResourceLocation> bind (Registry<? super T> pRegistry) {
        return (t, id) -> Registry.register(pRegistry, id, t);
    }


}
