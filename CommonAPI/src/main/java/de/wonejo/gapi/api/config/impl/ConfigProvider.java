package de.wonejo.gapi.api.config.impl;

import com.google.common.collect.Maps;
import de.wonejo.gapi.api.config.IConfigProvider;
import de.wonejo.gapi.api.config.IConfigValue;
import de.wonejo.gapi.api.config.IConfigValueBuilder;
import de.wonejo.gapi.api.util.Id;

import java.util.Map;

public abstract class ConfigProvider implements IConfigProvider {

    private final Map<Id<String>, IConfigValue<?>> configurations = Maps.newLinkedHashMap();

    public abstract void buildConfigurations();

    @SuppressWarnings("unchecked")
    public <T> IConfigValue<T> createConfig(String pKey, String pComment, T pDefaultValue) {
        IConfigValue<T> value = (IConfigValue<T>) ConfigValueBuilder.of(pKey).comment(pComment).defaultValue(pDefaultValue);
        this.configurations.put(new Id<>(pKey), value);
        return value;
    }

    public Map<Id<String>, IConfigValue<?>> configurations() {
        return this.configurations;
    }

}
