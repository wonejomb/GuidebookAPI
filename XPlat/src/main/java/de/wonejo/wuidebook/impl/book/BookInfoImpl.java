package de.wonejo.wuidebook.impl.book;

import de.wonejo.wuidebook.api.book.BookInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class BookInfoImpl implements BookInfo {

    @NotNull public static Builder builderImpl () {
        return BuilderImpl.builderImpl();
    }

    private final ResourceLocation bookId;
    private final ResourceLocation modelLocation;
    private final ResourceLocation mainBookTexture;
    private final ResourceLocation pageBookTexture;
    private final Component title;
    private final Component header;
    private final Component itemName;
    private final Component author;
    private final Color bookColor;
    private final boolean shouldSpawnWithBook;

    private BookInfoImpl ( ResourceLocation pBookId, ResourceLocation pModelLocation, ResourceLocation pMainBookTexture, ResourceLocation pPageBookTexture, Component pTitle, Component pHeader, Component pItemName, Component pAuthor, Color pColor, boolean pShouldSpawnWithBook ) {
        this.bookId = pBookId;
        this.modelLocation = pModelLocation;
        this.mainBookTexture = pMainBookTexture;
        this.pageBookTexture = pPageBookTexture;
        this.title = pTitle;
        this.header = pHeader;
        this.itemName = pItemName;
        this.bookColor = pColor;
        this.author = pAuthor;
        this.shouldSpawnWithBook = pShouldSpawnWithBook;
    }

    public ResourceLocation bookId() {
        return this.bookId;
    }

    public ResourceLocation modelLocation() {
        return this.modelLocation;
    }

    public ResourceLocation mainBookGUITexture() {
        return this.mainBookTexture;
    }

    public ResourceLocation pageBookGUITexture() {
        return this.pageBookTexture;
    }

    public Component title() {
        return this.title;
    }

    public Component header() {
        return this.header;
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

    public boolean shouldSpawnWithBook() {
        return this.shouldSpawnWithBook;
    }

    private static class BuilderImpl implements BookInfo.Builder {

        @NotNull static BuilderImpl builderImpl () {
            return new BuilderImpl();
        }

        private ResourceLocation bookId;
        private ResourceLocation modelLocation;
        private ResourceLocation mainBookTexture;
        private ResourceLocation pageBookTexture;
        private Component title = Component.translatable("wuidebook.book.info.title.undefined");
        private Component header;
        private Component itemName;
        private Component author = Component.translatable("wuidebook.book.info.author.unknown");
        private Color bookColor = new Color(154, 21, 63);
        private boolean shouldSpawnWithBook = false;

        private BuilderImpl () {}

        public BookInfo.Builder withId(ResourceLocation pBookId) {
            this.bookId = pBookId;
            return this;
        }

        public BookInfo.Builder withModelLocation(ResourceLocation pLocation) {
            this.modelLocation = pLocation;
            return this;
        }

        public BookInfo.Builder withMainBookTexture(ResourceLocation pLocation) {
            this.mainBookTexture = pageBookTexture;
            return this;
        }

        public BookInfo.Builder withPageBookTexture(ResourceLocation pLocation) {
            this.pageBookTexture = pLocation;
            return this;
        }

        public BookInfo.Builder setTitle(Component pTitle) {
            this.title = pTitle;
            return this;
        }

        public BookInfo.Builder setHeader(Component pHeader) {
            this.header = pHeader;
            return this;
        }

        public BookInfo.Builder setItemName(Component pItemName) {
            this.itemName = pItemName;
            return this;
        }

        public Builder setAuthor(Component pAuthor) {
            this.author = pAuthor;
            return this;
        }

        public BookInfo.Builder withBookColor(Color pColor) {
            this.bookColor = pColor;
            return this;
        }

        public BookInfo.Builder shouldSpawnWithBook() {
            this.shouldSpawnWithBook = true;
            return this;
        }

        @NotNull public BookInfo build() {
            if ( this.bookId == null ) throw new IllegalArgumentException("Can not build book info with null id.");
            if ( this.header == null ) this.header = this.title;
            if ( this.itemName == null ) this.itemName = this.title;

            return new BookInfoImpl(this.bookId, this.modelLocation, this.mainBookTexture, this.pageBookTexture, this.title, this.header, this.itemName, this.author, this.bookColor, this.shouldSpawnWithBook);
        }

    }
}
