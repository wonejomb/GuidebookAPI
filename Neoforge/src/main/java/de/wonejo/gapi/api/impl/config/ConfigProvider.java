package de.wonejo.gapi.api.impl.config;

import com.google.common.collect.Maps;
import de.wonejo.gapi.api.config.IConfigProvider;
import de.wonejo.gapi.api.config.IConfigValue;
import de.wonejo.gapi.api.util.Id;

import java.util.Map;

public abstract class ConfigProvider implements IConfigProvider {

    private final Map<Id<String>, IConfigValue<?>> configurations = Maps.newLinkedHashMap();

    public abstract void buildConfigurations();

    public <T> IConfigValue<T> createConfig ( String pKey, T pDefaultValue ) {
        return this.createConfig(pKey, "", pDefaultValue);
    }

    @SuppressWarnings("unchecked")
    public <T> IConfigValue<T> createConfig(String pKey, String pComment, T pDefaultValue) {
        ConfigValue<T> value = (ConfigValue<T>) ConfigValueBuilder.of(pKey).comment(pComment).defaultValue(pDefaultValue).build();
        this.configurations.put(new Id<>(pKey), value);
        return value;
    }

    public Map<Id<String>, IConfigValue<?>> configurations() {
        return this.configurations;
    }

}
