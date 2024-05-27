package de.wonejo.gapi;

import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.core.ModDataComponents;
import de.wonejo.gapi.network.ReadingStatePayload;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod(value = Constants.MOD_ID)
public class GapiNeoForgeMod {

    public GapiNeoForgeMod (IEventBus pBus) {
        ModDataComponents.registerDataComponents(pBus);
        CommonGapiMod.init();
        pBus.addListener(this::registerPackets);
    }

    private void registerPackets (RegisterPayloadHandlersEvent pEvent) {
        PayloadRegistrar registrar = pEvent.registrar("1");
        registrar.playToServer(ReadingStatePayload.TYPE, ReadingStatePayload.STREAM_CODEC, ((readingStatePayload, iPayloadContext) -> ReadingStatePayload.handle(readingStatePayload, iPayloadContext.player())));
    }
}
