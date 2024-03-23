package de.mrbunny.guidebook.api.config;

import de.mrbunny.guidebook.api.config.impl.ConfigValue;

public interface IConfigValueBuilder<T> {

    IConfigValueBuilder<T> comment ( String pComment );
    IConfigValueBuilder<T> defaultValue ( T pDefaultValue );

    String comment();
    String key();
    T defaultValue();

    IConfigValue<T> build ();

    @SuppressWarnings("unchecked")
    default IConfigValue<T> buildEmpty () {
        return (IConfigValue<T>) new ConfigValue<>("", "", "");
    }

}
