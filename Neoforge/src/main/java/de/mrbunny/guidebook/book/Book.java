package de.mrbunny.guidebook.book;

import com.mojang.logging.LogUtils;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.book.component.IBookCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.compress.utils.Lists;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class Book implements IBook {

    private boolean init;

    private final ResourceLocation id;
    private final ResourceLocation outlineTexture;
    private final ResourceLocation pagesTexture;

    private final Component title;
    private final Component header;
    private final Component subHeaderText;
    private final Component itemName;
    private final Component author;
    private final Color color;

    private final boolean shouldSpawnWithBook;

    private final Consumer<List<IBookCategory>> contentProvider;
    private final List<IBookCategory> categories = Lists.newArrayList();

    public Book ( ResourceLocation pId, ResourceLocation pOutlineTexture, ResourceLocation pPagesTexture
            , Component pTitle, Component pHeader, Component pSubHeaderText, Component pItemName, Component pAuthor,
                  Color pColor, boolean pShouldSpawnWithBook, Consumer<List<IBookCategory>> pContentProvider ) {

        this.id = pId;
        this.outlineTexture = pOutlineTexture;
        this.pagesTexture = pPagesTexture;
        this.title = pTitle;
        this.header = pHeader;
        this.subHeaderText = pSubHeaderText;
        this.itemName = pItemName;
        this.author = pAuthor;
        this.color = pColor;
        this.shouldSpawnWithBook = pShouldSpawnWithBook;

        this.contentProvider = pContentProvider;
    }

    public void initializeContent () {
        if ( !init ) {
            LogUtils.getLogger().debug("Initializing Book content of '{}' for first time", this.id);
            this.contentProvider.accept(this.categories);
            this.init = true;
        }
    }

    public Component getTitle() {
        return this.title;
    }

    public Component getHeader() {
        return this.header;
    }

    public Component getSubHeaderText() { return this.subHeaderText; }

    public Component getItemName() {
        return this.itemName;
    }

    public Component getAuthor() {
        return this.author;
    }

    public Color getColor() {
        return this.color;
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public ResourceLocation getOutlineTexture() {
        return this.outlineTexture;
    }

    public ResourceLocation getPagesTexture() {
        return this.pagesTexture;
    }

    public List<IBookCategory> getCategories() {
        return this.categories;
    }

    public boolean shouldSpawnWithBook() {
        return this.shouldSpawnWithBook;
    }
}
