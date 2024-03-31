package de.wonejo.gapi.api.util;

import java.util.function.Supplier;

public final class Id<T> implements Supplier<T> {

    private final T id;

    public Id ( T pId ) {
        this.id = pId;
    }

    public String asString () {
        return this.id.toString();
    }

    public T get() {
        return this.id;
    }
}
