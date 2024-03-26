package de.wonejo.gapi.api.impl.book;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.IBookBuilder;
import de.wonejo.gapi.api.book.components.IBookCategory;
import net.minecraft.network.chat.Component;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public final class BookBuilder implements IBookBuilder {

    public static BookBuilder of() { return new BookBuilder(); }

    private Consumer<List<IBookCategory>> contentProvider;
    private Component title;
    private Component header;
    private Component subHeader;
    private Component itemName;
    private Component author;
    private boolean shouldSpawnWithBook = false;
    private Color color = new Color(128, 88, 66);

    private BookBuilder () {}

    public IBookBuilder color(Color pColor) {
        this.color = pColor;
        return this;
    }

    public IBookBuilder spawnWithBook() {
        this.shouldSpawnWithBook = true;
        return this;
    }

    public IBookBuilder title(Component pTitle) {
        this.title = pTitle;
        return this;
    }

    public IBookBuilder header(Component pHeader) {
        this.header = pHeader;
        return this;
    }

    public IBookBuilder subHeader(Component pHeader) {
        this.subHeader = pHeader;
        return this;
    }

    public IBookBuilder itemName(Component pItemName) {
        this.itemName = pItemName;
        return this;
    }

    public IBookBuilder author(Component pAuthor) {
        this.author = pAuthor;
        return this;
    }

    public IBookBuilder contentProvider(Consumer<List<IBookCategory>> pContentProvider) {
        this.contentProvider = pContentProvider;
        return this;
    }

    public IBook build() {
        if ( this.title == null )
            this.title = Component.translatable("text.gapi.unknown");

        if ( this.header == null )
            this.header = title;

        if ( this.subHeader == null )
            this.subHeader = Component.literal("");

        if (this.itemName == null)
            this.itemName = this.title;

        if ( this.author == null )
            this.author = Component.translatable("text.gapi.unknown");

        if ( this.contentProvider == null )
            throw new IllegalStateException("Content provider can't be null");

        return new Book(this.color, this.title, this.header, this.subHeader, this.itemName, this.author, this.shouldSpawnWithBook, this.contentProvider);
    }
}
