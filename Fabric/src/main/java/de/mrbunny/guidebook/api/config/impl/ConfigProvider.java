package de.mrbunny.guidebook.api.config.impl;

import de.mrbunny.guidebook.api.config.IConfigProvider;
import de.mrbunny.guidebook.api.config.IConfigValue;
import de.mrbunny.guidebook.api.config.IConfigValueBuilder;

import java.util.HashMap;
import java.util.Map;

public abstract class ConfigProvider implements IConfigProvider {

    private final Map<String, IConfigValue<?>> configurations = new HashMap<>();
    private final Map<String, IConfigValue<?>> loadedConfigurations = new HashMap<>();


    public abstract void createConfigurations ();
    public abstract void loadConfigurations ();


    @SuppressWarnings("unchecked")
    public <T> IConfigValue<T> getConfigByName ( String pKey ) {
        return (IConfigValue<T>) this.loadedConfigurations.get(pKey);
    }

    public <T> IConfigValue<T> getOrDefault ( String pKey, IConfigValue<T> pDefaultReturn ) {
        IConfigValue<T> defaultConfig = getConfigByName(pKey);
        return defaultConfig == null ? pDefaultReturn : defaultConfig;
    }

    public <T> IConfigValue<T> createConfig(IConfigValueBuilder<T> pBuilder) {
        IConfigValue<T> config = pBuilder.build();
        this.configurations.put(pBuilder.key(), config);
        return config;
    }

    public Map<String, IConfigValue<?>> loadedConfigurations() {
        return this.loadedConfigurations;
    }

    public Map<String, IConfigValue<?>> configurations() {
        return this.configurations;
    }
}
