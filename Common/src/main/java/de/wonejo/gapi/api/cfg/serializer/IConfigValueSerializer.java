package de.wonejo.gapi.api.cfg.serializer;

public interface IConfigValueSerializer<T> {

    String serialize ( T pValue );

    T deserialize ( String pValue );

}
