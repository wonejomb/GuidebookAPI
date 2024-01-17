package de.mrbunny.guidebook.api.client.render;

import de.mrbunny.guidebook.api.util.ITickable;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public interface IRenderable extends ITickable {

    void render (GuiGraphics pGraphics, int pMouseX, int pMouseY, Font pFont);

    default boolean canView () { return true; };

}
