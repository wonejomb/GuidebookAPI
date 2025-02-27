package de.wonejo.wuidebook.impl;

import de.wonejo.wuidebook.api.compat.WuidebookImplementation;
import de.wonejo.wuidebook.api.service.XPlatAbstraction;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;
import java.util.List;

public class FabricPlatformImpl implements XPlatAbstraction {

    public List<WuidebookImplementation> gatherImplementations() {
        return List.of();
    }

    @Override
    public String getCurrentLoadedModId() {
        return FabricLoader.getInstance().getMappingResolver().getCurrentRuntimeNamespace();
    }

    public Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir();
    }

    public boolean isDevWorkspace() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }
}
