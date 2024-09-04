package de.wonejo.wuidebook.api.wcontent;

public interface WContentFile {

    boolean hasStarted ();

    <T> WContentTag<T> getTag ( String pTagName );

    boolean hasEnded ();
}
