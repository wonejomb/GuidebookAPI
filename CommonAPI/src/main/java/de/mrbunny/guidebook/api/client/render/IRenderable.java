package de.mrbunny.guidebook.api.client.render;

import de.mrbunny.guidebook.api.client.util.ViewRequirement;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;

public interface IRenderable extends ViewRequirement {

    void render (GuiGraphics pGraphics, int pXOffset, int pYOffset, Font pFont);

}
