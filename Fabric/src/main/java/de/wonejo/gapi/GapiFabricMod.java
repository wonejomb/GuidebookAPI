package de.wonejo.gapi;

import de.wonejo.gapi.core.ModDataComponents;
import de.wonejo.gapi.handler.GapiRegistryHelper;
import de.wonejo.gapi.network.ReadingStatePayload;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class GapiFabricMod implements ModInitializer {

    public void onInitialize() {
        CommonGapiMod.init();

        GapiRegistryHelper.registerGuides();
        ModDataComponents.registerDataComponents();

        PayloadTypeRegistry.playC2S().register(ReadingStatePayload.TYPE, ReadingStatePayload.STREAM_CODEC);
        PayloadTypeRegistry.playS2C().register(ReadingStatePayload.TYPE, ReadingStatePayload.STREAM_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(ReadingStatePayload.TYPE, (payload, context) -> {
            ReadingStatePayload.handle(payload, context.player());
        });
    }




}
