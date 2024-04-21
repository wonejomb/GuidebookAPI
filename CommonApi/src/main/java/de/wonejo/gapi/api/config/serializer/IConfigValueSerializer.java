package de.wonejo.gapi.api.config.serializer;

public interface IConfigValueSerializer<T> {

    String serialize ( T pValue );

    T deserialize ( String pValue );

}
