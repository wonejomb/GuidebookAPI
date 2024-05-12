package de.wonejo.gapi.impl.book;

import de.wonejo.gapi.api.book.IBookInformation;
import net.minecraft.network.chat.Component;

public final class BookInformation implements IBookInformation {

    private final Component title;
    private final Component description;

    public BookInformation ( Component pTitle, Component pDescription ) {
        this.title = pTitle;
        this.description = pDescription;
    }

    public Component title() {
        return this.title;
    }

    public Component description() {
        return this.description;
    }

}
