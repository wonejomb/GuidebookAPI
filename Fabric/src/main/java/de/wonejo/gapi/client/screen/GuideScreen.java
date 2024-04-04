package de.wonejo.gapi.client.screen;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.GuideScreenType;
import de.wonejo.gapi.api.util.GuideTexture;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public abstract class GuideScreen extends Screen implements IModScreen {

    private final IBook book;
    private final GuideScreenType type;

    private final ResourceLocation topTexture;
    private final ResourceLocation pagesTexture;

    private final int widthSize;
    private final int heightSize;

    public GuideScreen ( @NotNull IBook pBook, GuideScreenType pType ) {
        super (pBook.header());
        this.book = pBook;
        this.type = pType;

        this.topTexture = getTextureByType(pType).getTopTexture();
        this.pagesTexture = getTextureByType(pType).getPagesTexture();

        this.widthSize = pType.getTextureWidth();
        this.heightSize = pType.getTextureHeight();
    }

    private GuideTexture getTextureByType (GuideScreenType pType ) {
        return switch (pType) {
            case INFO -> this.book.infoTexture();
            case PAGE -> this.book.pagesTexture();
            default -> this.book.topTexture();
        };
    }

    public boolean isPauseScreen() {
        return false;
    }

    public ResourceLocation topTexture () {
        return this.topTexture;
    }

    public ResourceLocation pagesTexture () {
        return this.pagesTexture;
    }

    public GuideScreenType getType() {
        return type;
    }

    public IBook getBook() {
        return book;
    }

    public int xOffset() {
        return (this.width - this.widthSize) / 2;
    }

    public int yOffset() {
        return (this.height - this.heightSize) / 2;
    }

    public int widthSize() {
        return this.widthSize;
    }

    public int heightSize() {
        return this.heightSize;
    }
}
