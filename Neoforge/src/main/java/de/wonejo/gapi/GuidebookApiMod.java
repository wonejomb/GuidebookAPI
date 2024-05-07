package de.wonejo.gapi;

import de.wonejo.gapi.api.proxy.IProxy;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.config.ModConfigurations;
import de.wonejo.gapi.proxy.ClientProxy;
import de.wonejo.gapi.proxy.CommonProxy;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.layers.PlayerItemInHandLayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import org.jetbrains.annotations.NotNull;

@Mod(Constants.MOD_ID)
public class GuidebookApiMod {

    public static final IProxy PROXY = FMLEnvironment.dist == Dist.CLIENT ? new ClientProxy() : new CommonProxy();


    public GuidebookApiMod (@NotNull IEventBus pBus) {
        pBus.addListener(this::onCommonSetup);
        pBus.addListener(this::onClientSetup);
    }

    private void onCommonSetup (final FMLCommonSetupEvent pEvent) {
        ModConfigurations.setupConfigurations();
    }

    private void onClientSetup (final FMLClientSetupEvent pEvent) {
        PROXY.registerColors();
    }
}
