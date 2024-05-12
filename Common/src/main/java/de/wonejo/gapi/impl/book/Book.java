package de.wonejo.gapi.impl.book;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.IBookInformation;
import de.wonejo.gapi.api.book.components.ICategory;
import de.wonejo.gapi.api.util.GuideTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class Book implements IBook {

    private boolean init;

    private final ResourceLocation id;
    private final ResourceLocation modelLocation;

    private final GuideTexture topTextures;
    private final GuideTexture infoTextures;
    private final GuideTexture pageTextures;

    private final IBookInformation information;

    private final Component title;
    private final Component header;
    private final Component subHeader;
    private final Component itemName;
    private final Component author;

    private final boolean spawnWithBook;

    private final boolean useCustomBookModel;
    private final boolean useCustomBookTexture;
    private final boolean useCustomPagesTexture;
    private final boolean useCustomInfoPagesTexture;

    private final Color bookColor;
    private final Color textColor;
    private final Color pagesColor;
    private final Color entryColor;
    private final Color entryAboveColor;

    private final Consumer<List<ICategory>> contentProvider;
    private final List<ICategory> categories = new ArrayList<>();

    public Book (ResourceLocation pId, Consumer<List<ICategory>> pContentProvider, IBookInformation pInformation,
                 Component pTitle, Component pHeader, Component pSubHeader, Component pItemName, Component pAuthor,
                 GuideTexture pTopTextures, GuideTexture pInfoTextures, GuideTexture pPagesTexture, ResourceLocation pModelLocation,
                 Color pBookColor, Color pTextColor, Color pPagesColor, Color pEntryColor, Color pEntryAboveColor,
                 boolean pSpawnWithBook, boolean pUseCustomBookModel, boolean pUseCustomBookTexture, boolean pUseCustomPagesTexture, boolean pUseCustomInfoPagesTexture) {
        this.id = pId;

        this.contentProvider = pContentProvider;

        this.information = pInformation;

        this.title = pTitle;
        this.header = pHeader;
        this.subHeader = pSubHeader;
        this.itemName = pItemName;
        this.author = pAuthor;

        this.topTextures = pTopTextures;
        this.infoTextures = pInfoTextures;
        this.pageTextures = pPagesTexture;

        this.modelLocation = pModelLocation;

        this.bookColor = pBookColor;
        this.textColor = pTextColor;
        this.pagesColor = pPagesColor;
        this.entryColor = pEntryColor;
        this.entryAboveColor = pEntryAboveColor;

        this.spawnWithBook = pSpawnWithBook;

        this.useCustomBookModel = pUseCustomBookModel;
        this.useCustomBookTexture = pUseCustomBookTexture;
        this.useCustomPagesTexture = pUseCustomPagesTexture;
        this.useCustomInfoPagesTexture = pUseCustomInfoPagesTexture;
    }

    public ResourceLocation id() {
        return this.id;
    }

    public List<ICategory> categories() {
        return this.categories;
    }

    public IBookInformation information() {
        return this.information;
    }

    public boolean shouldSpawnWithBook() {
        return this.spawnWithBook;
    }

    public boolean useCustomBookModel() {
        return this.useCustomBookModel;
    }

    public boolean useCustomBookTexture() {
        return this.useCustomBookTexture;
    }

    public boolean useCustomPagesTexture() {
        return this.useCustomPagesTexture;
    }

    public boolean useCustomInfoPagesTexture() {
        return this.useCustomInfoPagesTexture;
    }

    public GuideTexture topTexture() {
        return this.topTextures;
    }

    public GuideTexture pagesTexture() {
        return this.pageTextures;
    }

    public GuideTexture infoTexture() {
        return this.infoTextures;
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

    public Color textColor() {
        return this.textColor;
    }

    public Color pagesColor() {
        return this.pagesColor;
    }

    public Color entryColor() {
        return this.entryColor;
    }

    public Color entryAboveColor() {
        return this.entryAboveColor;
    }

    public void initializeContent() {
        if (!init) {
            this.contentProvider.accept(this.categories);
            this.init = true;
        }
    }
}
