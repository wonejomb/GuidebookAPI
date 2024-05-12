package de.wonejo.gapi.api.client.render;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.client.IExtraRender;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.CanView;
import de.wonejo.gapi.api.util.Clickable;
import de.wonejo.gapi.api.util.ITick;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;

public interface IPageRender extends IExtraRender, CanView, ITick, Clickable {

    void render (GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, IBook pBook, Font pFont);
    default void init () {}

}
