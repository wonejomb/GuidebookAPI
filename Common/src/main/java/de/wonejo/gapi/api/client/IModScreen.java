package de.wonejo.gapi.api.client;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.util.ITick;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public interface IModScreen extends ITick {

    void render (GuiGraphics pGraphics, int pMouseX, int pMouseY, float pPartialTicks);

    IBook getBook ();

    ResourceLocation topTexture ();
    ResourceLocation pagesTexture ();

    int xOffset ();
    int yOffset ();

    int screenWidth ();
    int screenHeight ();
}
