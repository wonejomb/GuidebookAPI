package de.wonejo.wuidebook.api.util;

public interface RegistrySource<T> {

    void register ( String pId, T pObject );

}
