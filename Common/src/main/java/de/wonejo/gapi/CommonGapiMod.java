package de.wonejo.gapi;

import de.wonejo.gapi.api.proxy.IProxy;
import de.wonejo.gapi.api.util.DebugLogger;
import de.wonejo.gapi.cfg.ModConfigurations;
import de.wonejo.gapi.impl.service.Services;
import de.wonejo.gapi.proxy.ClientProxy;
import de.wonejo.gapi.proxy.CommonProxy;

public class CommonGapiMod {

    public static final IProxy CLIENT_PROXY = Services.PLATFORM.isClient() ? new ClientProxy() : new CommonProxy();

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
