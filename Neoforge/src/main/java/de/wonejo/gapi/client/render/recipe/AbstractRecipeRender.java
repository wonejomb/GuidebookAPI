package de.wonejo.gapi.client.render.recipe;

import de.wonejo.gapi.api.client.render.recipe.IRecipeRender;
import de.wonejo.gapi.api.util.RenderUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Recipe;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public abstract class AbstractRecipeRender<T extends Recipe<?>> implements IRecipeRender {
    protected final T recipe;
    protected List<Component> tooltips = Lists.newArrayList();

    public AbstractRecipeRender ( T pRecipe ) {
        this.recipe = pRecipe;
    }

    public void render (GuiGraphics pGraphics, int pMouseX, int pMouseY, Font pFont) {
        pGraphics.renderComponentTooltip(pFont, this.tooltips, pMouseX, pMouseY);
        this.tooltips.clear();
    }
}
