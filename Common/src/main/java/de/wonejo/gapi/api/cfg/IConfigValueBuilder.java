package de.wonejo.gapi.api.cfg;

public interface IConfigValueBuilder<T> {

    IConfigValueBuilder<T> comment ( String pComment );
    IConfigValueBuilder<T> defaultValue ( T pDefaultValue );

    IConfigValue<T> build ();
}
