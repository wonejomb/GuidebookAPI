package de.wonejo.gapi.api.util;

public enum ClickType {
    LEFT_CLICK(0),
    RIGHT_CLICK(1)
    ;

    private final int id;

    ClickType ( int pId ) {
        this.id = pId;
    }

    public int getId() {
        return id;
    }
}
