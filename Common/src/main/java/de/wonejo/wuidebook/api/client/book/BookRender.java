package de.wonejo.wuidebook.api.client.book;

import de.wonejo.wuidebook.api.book.BookInformation;
import de.wonejo.wuidebook.api.book.content.BookContent;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.HolderLookup;

public interface BookRender<T extends BookContent.ContentElement> {

    void render (GuiGraphics pGraphics, HolderLookup.Provider pProvider, T pElement, BookInformation pInformation, Font pFont, int pX, int pY, int pMouseX, int pMouseY, float pFrameTime);

    BookContent.ContentElement.TypeProvider type ();

}
