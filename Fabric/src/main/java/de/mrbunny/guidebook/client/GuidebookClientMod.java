package de.mrbunny.guidebook.client;

import de.mrbunny.guidebook.proxy.ClientProxy;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public class GuidebookClientMod implements ClientModInitializer {

    @Environment(EnvType.CLIENT)
    public static final ClientProxy CLIENT_PROXY = new ClientProxy();

    public void onInitializeClient() {
        CLIENT_PROXY.initColors();
    }

}
