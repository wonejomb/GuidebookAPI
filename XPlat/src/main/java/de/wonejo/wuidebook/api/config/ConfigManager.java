package de.wonejo.wuidebook.api.config;

import com.google.common.collect.ImmutableList;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public final class ConfigManager {
    private static ConfigManager INSTANCE;

    private final List<ConfigFile> files = Lists.newArrayList();

    private ConfigManager () {}

    public void registerFile ( ConfigFile pFile ) {
        this.files.add(pFile);
    }

    public List<ConfigFile> getFiles() {
        return ImmutableList.copyOf(this.files);
    }

    public static ConfigManager get () {
        if ( ConfigManager.INSTANCE == null ) ConfigManager.INSTANCE = new ConfigManager();
        return ConfigManager.INSTANCE;
    }

}
