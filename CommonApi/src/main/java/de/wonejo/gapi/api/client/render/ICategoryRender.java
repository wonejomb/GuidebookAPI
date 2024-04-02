package de.wonejo.gapi.api.client.render;

import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.CanView;
import de.wonejo.gapi.api.util.Clickable;
import de.wonejo.gapi.api.book.components.IBookCategory;
import de.wonejo.gapi.api.util.ITick;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;

public interface ICategoryRender extends CanView, ITick {

    void render (GuiGraphics pGraphics, RegistryAccess pAccess, int pCategoryX, int pCategoryY, int pMouseX, int pMouseY, IBookCategory pCategory, IModScreen pScreen, Font pFont);
    void init ();

    Component name ();

}
