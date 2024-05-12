package de.wonejo.gapi;

import de.wonejo.gapi.api.util.DebugLogger;
import de.wonejo.gapi.cfg.ModConfigurations;
import de.wonejo.gapi.impl.service.Services;
import de.wonejo.gapi.proxy.ClientProxy;

public class CommonGapiMod {

    public static final ClientProxy CLIENT_PROXY = new ClientProxy();

    public static void init () {
        ModConfigurations.setupDebugCfg();
        if ( ModConfigurations.DEBUG_PROVIDER.enableDebugOutput() || Services.PLATFORM.isDevelopmentEnvironment() ) {
            DebugLogger.initLogger();
            DebugLogger.debug("DEBUG LOGGER IS ENABLE!");
        }

        Services.BOOK_REGISTRY.gatherBooks();

        ModConfigurations.setupCfg();
    }

}
