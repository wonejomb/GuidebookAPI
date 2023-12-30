package de.mrbunny.guidebook.book;

import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.book.IBookBuilder;
import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.util.References;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class BookBuilder implements IBookBuilder {

    private final ResourceLocation id;
    private ResourceLocation outlineTexture = new ResourceLocation(References.GUIDEBOOKAPI_ID, "textures/gui/book/outline_texture.png");
    private ResourceLocation pagesTextures = new ResourceLocation(References.GUIDEBOOKAPI_ID, "textures/gui/book/pages_texture.png");;

    private Component title;
    @Nullable
    private Component header;
    @Nullable
    private Component itemName;
    @Nullable
    private Component author;
    private Color color = new Color(210, 110,40);

    private Consumer<List<IBookCategory>> contentProvider;

    private boolean shouldSpawnWithBook = false;

    public BookBuilder ( ResourceLocation pId ) {
        this.id = pId;
    }

    public IBook build() {
        if ( this.header == null ) this.header = this.title;
        if ( this.itemName == null ) this.itemName = this.title;
        if ( this.author == null ) this.author = Component.translatable("guidebook.book.author.unknown");
        if ( this.contentProvider == null ) throw new NullPointerException("Content provider can not be null! \n  -Book: " + this.id);

        return new Book(this.id, this.outlineTexture, this.pagesTextures, this.header, this.itemName, this.author, this.color, this.shouldSpawnWithBook, this.contentProvider);
    }

    public BookBuilder setPagesTexture(ResourceLocation pLoc) {
        this.pagesTextures = pLoc;
        return this;
    }

    public BookBuilder setOutlineTexture(ResourceLocation pLoc) {
        this.outlineTexture = pLoc;
        return this;
    }

    public BookBuilder setTitle(Component title) {
        this.title = title;
        return this;
    }

    public BookBuilder setAuthor(@Nullable Component author) {
        this.author = author;
        return this;
    }

    public BookBuilder setColor(Color color) {
        this.color = color;
        return this;
    }

    public BookBuilder setHeader(@Nullable Component header) {
        this.header = header;
        return this;
    }

    public BookBuilder setItemName(@Nullable Component itemName) {
        this.itemName = itemName;
        return this;
    }

    public BookBuilder contentProvider(Consumer<List<IBookCategory>> pProvider) {
        this.contentProvider = pProvider;
        return this;
    }

    public BookBuilder shouldSpawnWithBook() {
        this.shouldSpawnWithBook = true;
        return this;
    }

    public @Nullable Component getAuthor() {
        return author;
    }

    public @Nullable Component getHeader() {
        return header;
    }

    public @Nullable Component getItemName() {
        return itemName;
    }

    public Component getTitle() {
        return title;
    }

    public Color getColor() {
        return color;
    }

    public ResourceLocation getId() {
        return id;
    }
}
