package de.wonejo.gapi;

import de.wonejo.gapi.api.proxy.IProxy;
import de.wonejo.gapi.api.registry.BookRegistry;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.config.ModConfigurations;
import de.wonejo.gapi.proxy.ClientProxy;
import de.wonejo.gapi.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;

@Mod(Constants.MOD_ID)
public class GuidebookApiMod {

    public static final IProxy PROXY = FMLEnvironment.dist == Dist.CLIENT ? new ClientProxy() : new CommonProxy();


    public GuidebookApiMod (IEventBus pBus) {
        pBus.addListener(this::onCommonSetup);
        pBus.addListener(this::onClientSetup);
    }

    private void onCommonSetup (final FMLCommonSetupEvent pEvent) {
        BookRegistry.parseAllBooks(Minecraft.getInstance().getResourceManager());

        ModConfigurations.setupConfigurations();
    }

    private void onClientSetup ( final FMLClientSetupEvent pEvent ) {
    }
}
