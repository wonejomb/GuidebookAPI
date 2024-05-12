package de.wonejo.gapi.client.render.page;

import com.mojang.logging.LogUtils;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.client.render.IPageRender;
import de.wonejo.gapi.api.client.render.recipe.IRecipeRender;
import de.wonejo.gapi.api.util.ItemRotation;
import de.wonejo.gapi.client.render.recipe.FurnaceRecipeRender;
import de.wonejo.gapi.client.render.recipe.ShapedRecipeRender;
import de.wonejo.gapi.client.render.recipe.ShapelessRecipeRender;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class RecipePageRender implements IPageRender {


    private static final ItemRotation ROTATION = new ItemRotation();

    protected Recipe<?> recipe;
    protected IRecipeRender render;
    protected boolean valid;

    public RecipePageRender ( Recipe<?> pRecipe ) {
        this ( pRecipe, defaultRender (pRecipe) );
    }

    public RecipePageRender ( Recipe<?> pRecipe, IRecipeRender pRender ) {
        this.recipe = pRecipe;
        this.render = pRender;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, IBook pBook, Font pFont) {
        if (this.valid)
            this.render.render(pGraphics, pAccess, pMouseX, pMouseY, pScreen, pFont, pBook, ROTATION);
    }

    public boolean canView() {
        return this.valid;
    }

    protected static IRecipeRender defaultRender (Recipe<?> pRecipe ) {
        if (pRecipe == null) {
            LogUtils.getLogger().error("Can't get render for a null recipe.");
            return null;
        }

        if ( pRecipe instanceof ShapedRecipe recipe ) {
            return new ShapedRecipeRender(recipe);
        } else if ( pRecipe instanceof ShapelessRecipe recipe ) {
            return new ShapelessRecipeRender(recipe);
        } else if  ( pRecipe instanceof AbstractCookingRecipe recipe ) {
            return new FurnaceRecipeRender(recipe);
        }

        return null;
    }
}
