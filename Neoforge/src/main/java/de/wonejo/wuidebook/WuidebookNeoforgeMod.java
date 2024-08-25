package de.wonejo.wuidebook;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(WuidebookCommon.MOD_ID)
public class WuidebookNeoforgeMod {

    public WuidebookNeoforgeMod (IEventBus pEventBus) {
        WuidebookCommon.get().init();
    }

}
