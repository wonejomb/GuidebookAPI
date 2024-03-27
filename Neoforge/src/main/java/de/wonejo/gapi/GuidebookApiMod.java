package de.wonejo.gapi;

import de.wonejo.gapi.api.proxy.IProxy;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.config.ModConfigurations;
import de.wonejo.gapi.proxy.ClientProxy;
import de.wonejo.gapi.proxy.CommonProxy;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(Constants.MOD_ID)
public class GuidebookApiMod {

    public static final IProxy PROXY = FMLEnvironment.dist == Dist.CLIENT ? new ClientProxy() : new CommonProxy();


    public GuidebookApiMod (IEventBus pBus) {

        pBus.addListener(this::onCommonSetup);
        pBus.addListener(this::onLoadComplete);

    }

    private void onCommonSetup (final FMLCommonSetupEvent pEvent) {
        ModConfigurations.setupConfigurations();
    }

    private void onLoadComplete (final FMLLoadCompleteEvent pEvent) {
        PROXY.registerColors();
    }

}
