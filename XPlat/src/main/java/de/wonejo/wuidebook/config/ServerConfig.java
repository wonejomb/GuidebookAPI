package de.wonejo.wuidebook.config;

import de.wonejo.wuidebook.api.compat.WuidebookImplementation;
import de.wonejo.wuidebook.api.config.ConfigBuilder;
import de.wonejo.wuidebook.api.config.ConfigFile;
import de.wonejo.wuidebook.api.config.ConfigManager;
import de.wonejo.wuidebook.api.config.ConfigSpec;
import de.wonejo.wuidebook.api.util.McEnvironment;
import de.wonejo.wuidebook.impl.service.ModServices;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public final class ServerConfig {

    private static ServerConfig INSTANCE;
    private final ConfigFile file;

    private ServerConfig () {
        this.file = ConfigFile.Builder.createBuilder().withName("wuidebook").onSide(McEnvironment.SERVER).configProvider(this::setupConfig).build();
    }

    private void setupConfig(ConfigBuilder pBuilder) {


        for (WuidebookImplementation implementation : ModServices.ABSTRACTION.gatherImplementations())
            implementation.onRegisterCustomServerConfig(pBuilder);
    }

    public void setupFile ( @NotNull ConfigManager pManager ) {
        pManager.registerServerFile(this.file);
    }

    public <T> ConfigSpec<T> getConfig (String pKey ) {
        return this.file.getConfig(pKey);
    }

    public <T> ConfigSpec<T> getNullable  ( String pKey ) {
        return this.file.getNullable(pKey);
    }

    public <T> T get ( String pKey ) {
        return this.file.get(pKey);
    }

    public <T> Optional<T> getOpt (String pKey ) {
        return this.file.getOpt(pKey);
    }

    public static @NotNull ServerConfig get () {
        if ( ServerConfig.INSTANCE == null ) ServerConfig.INSTANCE = new ServerConfig();
        return ServerConfig.INSTANCE;
    }

}
