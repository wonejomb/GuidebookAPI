package de.wonejo.gapi.api.registry.data;

import de.wonejo.gapi.api.IGuidebook;

public final class BookData {

    private final Class<? extends IGuidebook> clazz;
    private final String id;

    @SuppressWarnings("unchecked")
    public BookData ( String pClazz, String pBookId ) {
        try {
            this.clazz = (Class<? extends IGuidebook>) Class.forName(pClazz);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        this.id = pBookId;
    }

    public Class<? extends IGuidebook> getClazz() {
        return clazz;
    }

    public String getId() {
        return id;
    }
}
