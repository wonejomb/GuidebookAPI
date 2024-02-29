package de.mrbunny.guidebook.cfg;

import de.mrbunny.guidebook.api.config.IConfigFile;
import de.mrbunny.guidebook.api.config.impl.ConfigFile;
import de.mrbunny.guidebook.api.util.References;

public class ModConfigManager {

    private static IConfigFile CLIENT_CONFIG;
    private static IConfigFile COMMON_CONFIG;

    public static final ModConfigurations.ClientConfiguration CLIENT = new ModConfigurations.ClientConfiguration();
    public static ModConfigurations.CommonConfiguration COMMON = new ModConfigurations.CommonConfiguration();

    public static void setupConfigurations () {
        CLIENT_CONFIG = ConfigFile.of(References.GUIDEBOOKAPI_ID + "-client").provider(CLIENT);
        COMMON_CONFIG = ConfigFile.of(References.GUIDEBOOKAPI_ID + "-common").provider(COMMON);

        CLIENT_CONFIG.startConfigFile();
        COMMON_CONFIG.startConfigFile();
    }
}
