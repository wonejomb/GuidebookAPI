package de.wonejo.gapi.service;

import de.wonejo.gapi.api.service.IPlatformHelper;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public final class FabricPlatformHelperImpl implements IPlatformHelper {
    public String getPlatformName() {
        return "Fabric";
    }

    public String getActiveNamespace() {
        return FabricLoader.getInstance().getMappingResolver().getCurrentRuntimeNamespace();
    }

    public Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public boolean isDevelopmentEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
