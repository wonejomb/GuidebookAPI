package de.mrbunny.guidebook.api.client.recipe;

import de.mrbunny.guidebook.api.client.render.IRenderable;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;

public interface IRecipeRender<R> extends IRenderable {

    void renderRecipe (GuiGraphics pGraphics, RegistryAccess pRegAccess, int pXOffset, int pYOffset, Font pFont);

}
