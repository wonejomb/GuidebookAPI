package de.wonejo.gapi.api.client.render;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.components.IHolder;
import de.wonejo.gapi.api.client.IExtraRender;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.CanView;
import de.wonejo.gapi.api.util.ITick;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;

public interface IHolderRender extends CanView, ITick {

    void render (GuiGraphics pGraphics, RegistryAccess pAccess, int pHolderX, int pHolderY, int pHolderIndex, int pMouseX, int pMouseY, IHolder pHolder, IBook pBook, IModScreen pScreen, Font pFont);

}
