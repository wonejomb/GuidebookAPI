package de.wonejo.gapi;

import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.core.ModDataComponents;
import de.wonejo.gapi.network.ReadingStatePayload;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod(value = Constants.MOD_ID)
public class GapiNeoForgeMod {

    public GapiNeoForgeMod (IEventBus pBus) {
        CommonGapiMod.init();
        bind(pBus, Registries.DATA_COMPONENT_TYPE, ModDataComponents::registerComponents);
        pBus.addListener(this::registerPackets);
    }

    private void registerPackets (RegisterPayloadHandlersEvent pEvent) {
        PayloadRegistrar registrar = pEvent.registrar("1");
        registrar.playToServer(ReadingStatePayload.TYPE, ReadingStatePayload.STREAM_CODEC, ((readingStatePayload, iPayloadContext) -> ReadingStatePayload.handle(readingStatePayload, iPayloadContext.player())));
    }

    private static <T> void bind (@NotNull IEventBus pBus, ResourceKey<Registry<T>> pRegistry, Consumer<BiConsumer<T, ResourceLocation>> pSource) {
        pBus.addListener(RegisterEvent.class, (event) -> {
            if  ( pRegistry.equals(event.getRegistryKey()) )
                pSource.accept((t, rl) -> event.register(pRegistry, rl, () -> t));
        });
    }
}
