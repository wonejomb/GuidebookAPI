package de.wonejo.wuidebook.api.config;

import com.google.common.collect.ImmutableList;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public final class ConfigManager {
    private static ConfigManager INSTANCE;

    private final List<ConfigFile> clientFiles = Lists.newArrayList();
    private final List<ConfigFile> serverFiles = Lists.newArrayList();
    private final List<ConfigFile> commonFiles = Lists.newArrayList();

    private ConfigManager () {}

    public void registerClientFile ( ConfigFile pFile ) {
        this.clientFiles.add(pFile);
    }

    public void registerServerFile ( ConfigFile pFile ) {
        this.serverFiles.add(pFile);
    }

    public void registerCommonFiles ( ConfigFile pFile ) {
        this.commonFiles.add(pFile);
    }

    public @NotNull @Unmodifiable List<ConfigFile> getClientFiles () {
        return ImmutableList.copyOf(this.clientFiles);
    }

    public @NotNull @Unmodifiable List<ConfigFile> getServerFiles () {
        return ImmutableList.copyOf(this.serverFiles);
    }

    public @NotNull @Unmodifiable List<ConfigFile> getCommonFiles () {
        return ImmutableList.copyOf(this.commonFiles);
    }

    public static ConfigManager get () {
        if ( ConfigManager.INSTANCE == null ) ConfigManager.INSTANCE = new ConfigManager();
        return ConfigManager.INSTANCE;
    }

}
