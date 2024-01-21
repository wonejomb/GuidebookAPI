package de.mrbunny.guidebook.client.render.recipe;

import de.mrbunny.guidebook.api.client.recipe.IRecipeRender;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Recipe;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public abstract class RecipeRenderBase<T extends Recipe<?>> implements IRecipeRender {

    protected T recipe;
    protected List<Component> tooltips = Lists.newArrayList();

    public RecipeRenderBase( T pRecipe ) {
        this.recipe = pRecipe;
    }

    public void render(GuiGraphics pGraphics, int pMouseX, int pMouseY, Font pFont) {
        pGraphics.renderComponentTooltip(pFont, this.tooltips, pMouseX, pMouseY);
        this.tooltips.clear();
    }
}
