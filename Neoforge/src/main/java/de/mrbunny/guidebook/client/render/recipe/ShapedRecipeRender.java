package de.mrbunny.guidebook.client.render.recipe;

import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.util.ScreenUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class ShapedRecipeRender extends CraftingRecipeRender<ShapedRecipe> {

    public ShapedRecipeRender ( ShapedRecipe pRecipe ) {
        super(pRecipe, Component.translatable("guidebook.recipe.crafting.shaped"));
    }

    public void renderRecipe(GuiGraphics pGraphics, RegistryAccess pRegAccess, IModScreen pScreen, int pMouseX, int pMouseY, Font pFont) {
        super.renderRecipe(pGraphics, pRegAccess, pScreen, pMouseX, pMouseY, pFont);

        for ( int y = 0; y < this.recipe.getRecipeHeight(); y++ ) {
            for ( int x = 0; x < this.recipe.getRecipeWidth(); x++ ) {
                int i = y * this.recipe.getRecipeWidth() + x;

                int stackX = (x + 1) * 17 + (pScreen.getXOffset() + pScreen.getWidthSize() / 2 - 90) + x;
                int stackY = (y + 1) * 17 + (pScreen.getYOffset() + pScreen.getHeightSize() / 2 - 59) + y;

                Ingredient ingredient = this.recipe.getIngredients().get(i);
                ingredientCycler.getCycledIngredientStack(ingredient, i).ifPresent((stack) -> {
                    ScreenUtils.drawScaledItemStack(pGraphics, stack, stackX, stackY, 1.4F);
                    if ( ScreenUtils.isMouseBetween(pMouseX, pMouseY, stackX, stackY, 16, 16) )
                        this.tooltips = ScreenUtils.getTooltip(stack);
                });
            }
        }
    }
}
