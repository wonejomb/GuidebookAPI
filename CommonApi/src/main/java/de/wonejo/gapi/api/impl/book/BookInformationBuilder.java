package de.wonejo.gapi.api.impl.book;

import de.wonejo.gapi.api.book.IBookInformation;
import de.wonejo.gapi.api.book.IBookInformationBuilder;
import net.minecraft.network.chat.Component;

public class BookInformationBuilder implements IBookInformationBuilder {

    public static BookInformationBuilder of () { return new BookInformationBuilder(); }

    private Component title;
    private Component modName;
    private Component description;
    private Component credits;

    private BookInformationBuilder () {}

    public IBookInformationBuilder title(Component pTitle) {
        this.title = pTitle;
        return this;
    }

    public IBookInformationBuilder modName(Component pModName) {
        this.modName = pModName;
        return this;
    }

    public IBookInformationBuilder description(Component pDescription) {
        this.description = pDescription;
        return this;
    }

    public IBookInformationBuilder credits(Component pCredits) {
        this.credits = pCredits;
        return this;
    }

    public IBookInformation build() {
        if ( this.title == null )
            this.title = Component.translatable("text.gapi.unknown");

        if ( this.modName == null )
            this.modName = this.title;

        if ( this.description == null )
            this.description = Component.literal("");

        if ( this.credits == null )
            this.credits = Component.literal("");

        return new BookInformation(title, modName, description, credits);
    }
}
