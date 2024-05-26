package de.wonejo.gapi;

import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.impl.cfg.ConfigValue;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@Mod(value = Constants.MOD_ID)
public class GapiNeoForgeMod {

    public GapiNeoForgeMod (IEventBus pBus) {
        CommonGapiMod.init();
    }

}
