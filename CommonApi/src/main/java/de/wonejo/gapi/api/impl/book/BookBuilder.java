package de.wonejo.gapi.api.impl.book;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.IBookBuilder;
import de.wonejo.gapi.api.book.IBookInformation;
import de.wonejo.gapi.api.book.components.IBookCategory;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.api.util.GuideTexture;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public final class BookBuilder implements IBookBuilder {

    public static BookBuilder of(ResourceLocation pId) { return new BookBuilder(pId); }

    private final ResourceLocation id;

    private GuideTexture bookTextures = GuideTexture.of(new ResourceLocation(Constants.MOD_ID, "textures/gui/book_pages.png"), new ResourceLocation(Constants.MOD_ID, "textures/gui/book_top.png"));
    private GuideTexture infoTextures = GuideTexture.of(new ResourceLocation(Constants.MOD_ID, "textures/gui/info_page.png"), new ResourceLocation(Constants.MOD_ID, "textures/gui/info_top.png"));
    private GuideTexture pagesTexture = GuideTexture.of(new ResourceLocation(Constants.MOD_ID, "textures/gui/page.png"), new ResourceLocation(Constants.MOD_ID, "textures/gui/page_top.png"));
    private ResourceLocation modelLocation = new ResourceLocation(Constants.MOD_ID, "guidebook");

    private IBookInformation bookInformation = BookInformationBuilder.of().build();

    private Consumer<List<IBookCategory>> contentProvider;
    private Component header;
    private Component subHeader;
    private Component itemName;
    private Component author;

    private boolean shouldSpawnWithBook = false;
    private boolean useCustomBookTexture = false;
    private boolean useCustomBookModel = false;
    private boolean useCustomPagesTexture = false;
    private boolean useCustomInfoPagesTexture = false;

    private Color bookColor = new Color(255, 128, 26);
    private Color pagesColor;

    private BookBuilder (ResourceLocation pId) {
        this.id = pId;
    }

    public IBookBuilder information (IBookInformation pInfo) {
        this.bookInformation = pInfo;
        return this;
    }

    public IBookBuilder infoTextures(GuideTexture pInfoTextures) {
        this.infoTextures = pInfoTextures;
        this.useCustomInfoPagesTexture = true;
        return this;
    }

    public IBookBuilder pagesTexture(GuideTexture pPageTextures) {
        this.pagesTexture = pPageTextures;
        this.useCustomPagesTexture = true;
        return this;
    }

    public IBookBuilder bookTextures(GuideTexture pBookTextures) {
        this.bookTextures = pBookTextures;
        this.useCustomBookTexture = true;
        return this;
    }

    public IBookBuilder model(ResourceLocation pModelLocation) {
        this.modelLocation = pModelLocation;
        this.useCustomBookModel = true;
        return this;
    }

    public IBookBuilder bookColor(Color pBookColor) {
        this.bookColor = pBookColor;
        return this;
    }

    public IBookBuilder pagesColor(Color pPagesColor) {
        this.pagesColor = pPagesColor;
        return this;
    }

    public IBookBuilder spawnWithBook() {
        this.shouldSpawnWithBook = true;
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
        Component title = this.bookInformation.title();

        if ( this.pagesColor == null )
            this.pagesColor = this.bookColor;

        if ( this.header == null )
            this.header = title;

        if (this.itemName == null)
            this.itemName = title;

        if ( this.author == null )
            this.author = Component.translatable("text.gapi.author.unknown").withColor(ChatFormatting.AQUA.getColor());

        if ( this.contentProvider == null )
            throw new IllegalStateException("Content provider can't be null");

        return new Book(id, this.contentProvider, this.bookInformation,
                title, this.header, this.subHeader, this.itemName, this.author,
                this.bookTextures, this.infoTextures, this.pagesTexture, this.modelLocation,
                this.bookColor, this.pagesColor,
                this.shouldSpawnWithBook, this.useCustomBookModel, this.useCustomBookTexture, this.useCustomPagesTexture, this.useCustomInfoPagesTexture);
    }
}
