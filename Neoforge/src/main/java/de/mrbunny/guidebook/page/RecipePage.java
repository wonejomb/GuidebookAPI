package de.mrbunny.guidebook.page;

import com.mojang.logging.LogUtils;
import de.mrbunny.guidebook.api.book.component.IPage;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.client.book.IPageRender;
import de.mrbunny.guidebook.api.client.recipe.IRecipeRender;
import de.mrbunny.guidebook.util.IngredientCycler;
import de.mrbunny.guidebook.client.render.recipe.ShapelessRecipeRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class RecipePage implements IPage, IPageRender {

    private Recipe<?> recipe;
    private IRecipeRender recipeRender;
    private boolean valid;

    public RecipePage ( Recipe<?> pRecipe ) {
        this (pRecipe, getDefaultRender(pRecipe));
    }

    public RecipePage ( Recipe<?> pRecipe, IRecipeRender pRecipeRender ) {
        this.recipe = pRecipe;
        this.recipeRender = pRecipeRender;
        this.valid = recipe != null && pRecipeRender != null;
    }

    public void render(GuiGraphics pGraphics, int pMouseX, int pMouseY, Font pFont) {
        if ( this.valid ) {
            this.recipeRender.render(pGraphics, pMouseX, pMouseY, pFont);
        }
    }

    public void renderExtras(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont) {
        if ( this.valid ) {
            this.recipeRender.renderRecipe(pGraphics, pAccess, pScreen, pMouseX, pMouseY, pFont);
        }
    }

    public boolean canView() {
        return this.valid;
    }

    public static IRecipeRender getDefaultRender (Recipe<?> pRecipe) {
        if ( pRecipe == null ) {
            LogUtils.getLogger().error("Can't get render for null recipe.");
            return null;
        }

        if ( pRecipe instanceof ShapelessRecipe recipe ) {
            return new ShapelessRecipeRender(recipe);
        }

        return null;
    }
}
