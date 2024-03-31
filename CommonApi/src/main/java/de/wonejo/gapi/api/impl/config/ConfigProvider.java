package de.wonejo.gapi.api.impl.config;

import com.google.common.collect.Maps;
import de.wonejo.gapi.api.config.IConfigProvider;
import de.wonejo.gapi.api.config.IConfigValue;

import java.util.Map;

public abstract class ConfigProvider implements IConfigProvider {

    private final Map<String, IConfigValue<?>> configurations = Maps.newHashMap();

    @SuppressWarnings("unchecked")
    public <T> IConfigValue<T> createConfig(String pKey, String pComment, T pValue) {
        IConfigValue<T> value = (IConfigValue<T>) ConfigValueBuilder.of(pKey).comment(pComment).defaultValue(pValue).build();
        this.configurations.put(pKey, value);
        return value;
    }

    public <T> IConfigValue<T> getOrDefault(String pKey, IConfigValue<T> pDefaultReturn) {
        IConfigValue<T> defaultConfig = getConfigById(pKey);
        return defaultConfig == null ? pDefaultReturn : defaultConfig;
    }

    @SuppressWarnings("unchecked")
    public <T> IConfigValue<T> getConfigById(String pKey) {
        return (IConfigValue<T>) this.configurations.get(pKey);
    }

    public Map<String, IConfigValue<?>> configurations() {
        return this.configurations;
    }
}
