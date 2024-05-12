package de.wonejo.gapi;

import de.wonejo.gapi.api.util.Constants;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(value = Constants.MOD_ID)
public class GapiNeoForgeMod {

    public GapiNeoForgeMod (IEventBus pBus) {
        CommonGapiMod.init();

        pBus.addListener(this::onClientSetup);
    }

    private void onClientSetup (final FMLClientSetupEvent pEvent) {
        CommonGapiMod.CLIENT_PROXY.tintBooks();
    }

}
