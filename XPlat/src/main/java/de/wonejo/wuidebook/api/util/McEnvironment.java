package de.wonejo.wuidebook.api.util;

public enum McEnvironment {
    CLIENT,
    SERVER,
    COMMON
    ;

    public String toString() {
        return this.name().toLowerCase();
    }
}
