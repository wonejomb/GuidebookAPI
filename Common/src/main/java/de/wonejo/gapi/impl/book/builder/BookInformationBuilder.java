package de.wonejo.gapi.impl.book.builder;

import de.wonejo.gapi.api.book.IBookInformation;
import de.wonejo.gapi.api.book.IBookInformationBuilder;
import de.wonejo.gapi.impl.book.BookInformation;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;


public final class BookInformationBuilder implements IBookInformationBuilder {

    @NotNull
    public static BookInformationBuilder of () {
        return new BookInformationBuilder();
    }

    private Component title;
    private Component description;

    private BookInformationBuilder ( ) {}

    public IBookInformationBuilder title(Component pTitle) {
        this.title = pTitle;
        return this;
    }

    public IBookInformationBuilder description(Component pDescription) {
        this.description = pDescription;
        return this;
    }

    public IBookInformation build() {
        if ( this.title == null )
            this.title = Component.translatable("text.gapi.title.unknown");

        if ( this.description == null )
            this.description = Component.empty();

        return new BookInformation(title, description);
    }

}
