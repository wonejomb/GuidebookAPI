package de.mrbunny.guidebook.api.config;

import de.mrbunny.guidebook.api.config.impl.ConfigProvider;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface IConfigProvider {

    static final IConfigProvider EMPTY = new ConfigProvider() {
        public void createConfigurations() {

        }

        public void loadConfigurations() {

        }

        public <T> IConfigValue<T> createConfig(@NotNull IConfigValueBuilder<T> pBuilder) {
            return pBuilder.buildEmpty();
        }
    };


    <T> IConfigValue<T> createConfig (IConfigValueBuilder<T> pBuilder );
    <T> IConfigValue<T> getConfigByName ( String pKey );
    <T> IConfigValue<T> getOrDefault ( String pKey, IConfigValue<T> pDefaultReturn );

    void createConfigurations ();
    void loadConfigurations ();

    Map<String, IConfigValue<?>> configurations ();
    Map<String, IConfigValue<?>> loadedConfigurations ();
}
