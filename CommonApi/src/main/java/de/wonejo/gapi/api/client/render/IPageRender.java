package de.wonejo.gapi.api.client.render;

import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.CanView;
import de.wonejo.gapi.api.util.ITick;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;

public interface IPageRender extends CanView, ITick {

    void render (GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont);
    default void init () {};

}
