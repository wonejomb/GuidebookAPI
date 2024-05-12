package de.wonejo.gapi.impl.book.builder;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.IBookBuilder;
import de.wonejo.gapi.api.book.IBookInformation;
import de.wonejo.gapi.api.book.components.ICategory;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.api.util.GuideTexture;
import de.wonejo.gapi.impl.book.Book;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public final class BookBuilder implements IBookBuilder {

    public static BookBuilder newBuilder (ResourceLocation pId) {
        return new BookBuilder(pId);
    }

    private final ResourceLocation id;

    private ResourceLocation modelLocation = new ResourceLocation(Constants.MOD_ID, "guidebook");

    private GuideTexture topTextures = GuideTexture.of(new ResourceLocation(Constants.MOD_ID, "textures/gui/book_pages.png"), new ResourceLocation(Constants.MOD_ID, "textures/gui/book_top.png"));
    private GuideTexture infoTextures = GuideTexture.of(new ResourceLocation(Constants.MOD_ID, "textures/gui/info_page.png"), new ResourceLocation(Constants.MOD_ID, "textures/gui/info_top.png"));
    private GuideTexture pagesTexture = GuideTexture.of(new ResourceLocation(Constants.MOD_ID, "textures/gui/page.png"), new ResourceLocation(Constants.MOD_ID, "textures/gui/page_top.png"));

    private IBookInformation information = BookInformationBuilder.of().build();

    private Component header;
    private Component subHeader;
    private Component itemName;
    private Component author;

    private boolean spawnWithBook;

    private boolean useCustomBookModel;
    private boolean useCustomBookTexture;
    private boolean useCustomPagesTexture;
    private boolean useCustomInfoPagesTexture;

    private Color bookColor = new Color(141, 37, 70);
    private Color textColor;
    private Color pagesColor;
    private Color entryColor;
    private Color entryAboveColor;

    private Consumer<List<ICategory>> contentProvider;

    private BookBuilder ( ResourceLocation pId ) { this.id = pId; }

    public IBookBuilder spawnWithBook() {
        this.spawnWithBook = true;
        return this;
    }

    public IBookBuilder header(Component pHeader) {
        this.header = pHeader;
        return this;
    }

    public IBookBuilder subHeader(Component pSubHeader) {
        this.subHeader = pSubHeader;
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

    public IBookBuilder bookColor(Color pBookColor) {
        this.bookColor = pBookColor;
        return this;
    }

    public IBookBuilder pagesColor(Color pPagesColor) {
        this.pagesColor = pPagesColor;
        return this;
    }

    public IBookBuilder textColor(Color pTextColor) {
        this.textColor = pTextColor;
        return this;
    }

    public IBookBuilder entryColor(Color pEntryColor) {
        this.entryColor = pEntryColor;
        return this;
    }

    public IBookBuilder entryAboveColor(Color pEntryAboveColor) {
        this.entryAboveColor = pEntryAboveColor;
        return this;
    }

    public IBookBuilder topTexture(GuideTexture pTopTexture) {
        this.topTextures = pTopTexture;
        this.useCustomBookTexture = true;
        return this;
    }

    public IBookBuilder pagesTexture(GuideTexture pPagesTexture) {
        this.pagesTexture = pPagesTexture;
        this.useCustomPagesTexture = true;
        return this;
    }

    public IBookBuilder infoTexture(GuideTexture pInfoTexture) {
        this.infoTextures = pInfoTexture;
        this.useCustomInfoPagesTexture = true;
        return this;
    }

    public IBookBuilder modelLocation(ResourceLocation pModelLocation) {
        this.modelLocation = pModelLocation;
        this.useCustomBookModel = true;
        return this;
    }

    public IBookBuilder information(IBookInformation pInformation) {
        this.information = pInformation;
        return this;
    }

    public IBookBuilder contentProvider(Consumer<List<ICategory>> pContentProvider) {
        this.contentProvider = pContentProvider;
        return this;
    }

    public IBook build() {
        if ( this.pagesColor == null )
            this.pagesColor = this.bookColor;
        if ( this.textColor == null )
            this.textColor = this.bookColor;

        if ( this.entryColor == null )
            this.entryColor = new Color(50, 40, 42);
        if ( this.entryAboveColor == null )
            this.entryAboveColor = new Color(70, 57, 59);

        if ( this.header == null )
            this.header = this.information.title();
        if ( this.subHeader == null )
            this.subHeader = Component.empty();

        if ( this.itemName == null )
            this.itemName = this.information.title();

        if ( this.author == null )
            this.author = Component.translatable("text.gapi.author.unknown").withColor(ChatFormatting.AQUA.getChar());

        if ( this.contentProvider == null )
            throw new NullPointerException("Content provider can't be null");

        return new Book(this.id, this.contentProvider, this.information,this.information.title(), this.header, this.subHeader, this.itemName, this.author, this.topTextures, this.infoTextures, this.pagesTexture, this.modelLocation, this.bookColor, this.textColor, this.pagesColor, this.entryColor, this.entryAboveColor, this.spawnWithBook, this.useCustomBookModel, this.useCustomBookTexture, this.useCustomPagesTexture, this.useCustomInfoPagesTexture);
    }
}
