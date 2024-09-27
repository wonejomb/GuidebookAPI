package de.wonejo.wuidebook.api.wgc.value;


public interface WgcTagValue<T> {

    default String getAsString () { return this.toString(); }

    WgcTagValue<T> copy ();
    WgcValueTagType<T> type ();

    T getValue ();

    String toString ();

}
