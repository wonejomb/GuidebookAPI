package de.wonejo.gapi.api.util;

public interface BookAccessible {

    void enableAccess ();
    void disableAccess ();

    boolean canAccess ();
}
