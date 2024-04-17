package de.wonejo.gapi.api.impl.book;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.IBookInformation;
import de.wonejo.gapi.api.book.components.IBookCategory;
import de.wonejo.gapi.api.util.GuideTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.compress.utils.Lists;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public final class Book implements IBook {

    private final ResourceLocation id;
    private final ResourceLocation modelLocation;

    private final GuideTexture bookTextures;
    private final GuideTexture infoTextures;
    private final GuideTexture pageTextures;

    private boolean init;

    private final IBookInformation bookInformation;

    private final Component title;
    private final Component header;
    private final Component subHeader;
    private final Component itemName;
    private final Component author;

    private final boolean shouldSpawnWithBook;
    private final Color bookColor;
    private final Color pagesColor;

    private final Consumer<List<IBookCategory>> contentProvider;
    private final List<IBookCategory> categories = Lists.newArrayList();

    public Book ( ResourceLocation pId,
                  Color pBookColor, Color pPagesColor,
                  Component pTitle, Component pHeader, Component pSubHeader, Component pItemName, Component pAuthor,
                  GuideTexture pBookTextures, GuideTexture pInfoTextures, GuideTexture pPagesTexture, ResourceLocation pModelLocation,
                  boolean pShouldSpawnWithBook, Consumer<List<IBookCategory>> pContentProvider, IBookInformation pInformation ) {

        this.bookTextures = pBookTextures;
        this.infoTextures = pInfoTextures;
        this.pageTextures = pPagesTexture;
        this.modelLocation = pModelLocation;

        this.id = pId;

        this.bookColor = pBookColor;
        this.pagesColor = pPagesColor;

        this.title = pTitle;
        this.header = pHeader;
        this.subHeader = pSubHeader;
        this.itemName = pItemName;
        this.author = pAuthor;
        this.shouldSpawnWithBook = pShouldSpawnWithBook;
        this.contentProvider = pContentProvider;
        this.bookInformation = pInformation;
    }

    public void initializeContent() {
        if (!init) {
            this.contentProvider.accept(this.categories);
            this.init = true;
        }
    }

    public ResourceLocation id() {
        return this.id;
    }

    public GuideTexture topTexture() {
        return this.bookTextures;
    }

    public GuideTexture infoTexture() {
        return this.infoTextures;
    }

    public GuideTexture pagesTexture() {
        return this.pageTextures;
    }

    public ResourceLocation modelLocation() {
        return this.modelLocation;
    }

    public Component title() {
        return this.title;
    }

    public Component header() {
        return this.header;
    }

    public Component subHeader() {
        return this.subHeader;
    }

    public Component itemName() {
        return this.itemName;
    }

    public Component author() {
        return this.author;
    }

    public Color bookColor() {
        return this.bookColor;
    }
    public Color pagesColor () { return this.pagesColor; }

    public boolean shouldSpawnWithBook() {
        return this.shouldSpawnWithBook;
    }

    public IBookInformation information() {
        return this.bookInformation;
    }

    public List<IBookCategory> categories() {
        return this.categories;
    }
}
