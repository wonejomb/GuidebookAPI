package de.wonejo.gapi.api.impl.book;

import de.wonejo.gapi.api.book.IBookInformation;
import net.minecraft.network.chat.Component;

public class BookInformation implements IBookInformation {

    private final Component title;
    private final Component modName ;
    private final Component description;
    private final Component credits;

    public BookInformation ( Component pTitle, Component pModName, Component pDescription, Component pCredits ) {
        this.title = pTitle;
        this.modName = pModName;
        this.description = pDescription;
        this.credits = pCredits;
    }

    public Component title() {
        return this.title;
    }

    public Component modName() {
        return this.modName;
    }

    public Component description() {
        return this.description;
    }

    public Component credits() {
        return this.credits;
    }

}
