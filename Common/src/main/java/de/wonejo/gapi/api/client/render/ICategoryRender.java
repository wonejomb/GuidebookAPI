package de.wonejo.gapi.api.client.render;

import de.wonejo.gapi.api.book.components.ICategory;
import de.wonejo.gapi.api.client.IExtraRender;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.CanView;
import de.wonejo.gapi.api.util.Clickable;
import de.wonejo.gapi.api.util.ITick;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;

public interface ICategoryRender extends IExtraRender, Clickable, CanView, ITick {

    void render (GuiGraphics pGraphics, RegistryAccess pRegAccess, int pCatX, int pCatY, int pMouseX, int pMouseY, ICategory pCategory, IModScreen pScreen, Font pFont );
    default void init () {}

}
