package de.wonejo.wuidebook.api.config;

import de.wonejo.wuidebook.api.logger.DebugLogger;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WuidebookConfigManager {
    private static WuidebookConfigManager INSTANCE;
    private final List<ConfigFile> configFiles = Lists.newArrayList();

    private WuidebookConfigManager () {}

    public void initialize () {
        String initializedFiles = this.initializeConfigurations();
        DebugLogger.log(initializedFiles);
    }

    private @NotNull String initializeConfigurations () {
        StringBuilder builder = new StringBuilder();

        builder.append("-------------------- WUIDEBOOK / CONFIG --------------------");
        for ( ConfigFile config : this.configFiles ) {
            config.initializeFile();
            builder.append("Initialized config file: ").append(config.getFilename()).append(" on environment: ").append(config.getEnvironment());
        }
        builder.append("------------------------------------------------------------");

        return builder.toString();
    }

    public void createFile ( ConfigFile.@NotNull Builder pBuilder ) {
        this.createFile(pBuilder.build());
    }

    public void createFile ( ConfigFile pBuilder ) {
        this.configFiles.add(pBuilder);
    }

    public static WuidebookConfigManager get () {
        if ( WuidebookConfigManager.INSTANCE == null ) WuidebookConfigManager.INSTANCE = new WuidebookConfigManager();
        return WuidebookConfigManager.INSTANCE;
    }
}
