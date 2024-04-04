package de.wonejo.gapi.client;

import de.wonejo.gapi.proxy.ClientProxy;
import net.fabricmc.api.ClientModInitializer;

public class GapiClientMod implements ClientModInitializer {

    public static final ClientProxy CLIENT_PROXY = new ClientProxy();

    public void onInitializeClient() {
        CLIENT_PROXY.registerColors();
    }
}
