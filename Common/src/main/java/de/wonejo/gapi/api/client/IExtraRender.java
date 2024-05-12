package de.wonejo.gapi.api.client;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public interface IExtraRender {

    default void renderExtras (GuiGraphics pGraphics, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont) {};

}
