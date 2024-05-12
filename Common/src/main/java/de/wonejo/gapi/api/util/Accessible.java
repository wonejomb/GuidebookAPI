package de.wonejo.gapi.api.util;

public interface Accessible {

    default boolean canAccess () { return true; }
}
