package de.wonejo.gapi.api.cfg;

import de.wonejo.gapi.api.cfg.serializer.IConfigValueSerializer;

import java.util.Map;

public interface IConfigProvider {

    void buildConfigurations ();
    void defineConfigurations ();

    <T> IConfigValue<T> createConfig (IConfigValueSerializer<T> pSerializer, String pKey, String pComment, T pValue );
    <T> IConfigValue<T> getConfigById ( String pKey );
    <T> IConfigValue<T> getOrDefault ( String pKey, IConfigValue<T> pDefaultReturn );

    Map<String, IConfigValue<?>> configurations ();

}
