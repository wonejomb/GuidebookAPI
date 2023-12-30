package de.mrbunny.guidebook.api.client.util;

public final class BooleanState {

    private boolean value = false;

    public void enable () {
        this.value = true;
    }

    public void disable () {
        this.value = false;
    }

    public boolean getValue () {
        return this.value;
    }

}
