package de.wonejo.gapi.api.config;

import de.wonejo.gapi.api.util.Id;

import java.util.Map;

public interface IConfigProvider {

    void buildConfigurations ();

    <T> IConfigValue<T> createConfig ( String pKey, T pDefaultValue );
    <T> IConfigValue<T> createConfig ( String pKey, String pComment, T pDefaultValue );

    Map<Id<String>, IConfigValue<?>> configurations ();

}
