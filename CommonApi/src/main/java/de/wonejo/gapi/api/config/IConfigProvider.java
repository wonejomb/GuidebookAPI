package de.wonejo.gapi.api.config;

import java.util.Map;

public interface IConfigProvider {

    void buildConfigurations ();
    void defineConfigurations ();

    <T> IConfigValue<T> createConfig ( String pKey, String pComment, T pValue );
    <T> IConfigValue<T> getConfigById ( String pKey );
    <T> IConfigValue<T> getOrDefault ( String pKey, IConfigValue<T> pDefaultReturn );

    Map<String, IConfigValue<?>> configurations ();

}
