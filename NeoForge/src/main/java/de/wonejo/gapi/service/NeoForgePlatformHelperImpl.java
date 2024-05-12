package de.wonejo.gapi.service;

import de.wonejo.gapi.api.service.IPlatformHelper;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;

import java.nio.file.Path;

public final class NeoForgePlatformHelperImpl implements IPlatformHelper {
    public String getPlatformName() {
        return "NeoForge";
    }

    public String getActiveNamespace() {
        return ModLoadingContext.get().getActiveNamespace();
    }

    public Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }

    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }
}
