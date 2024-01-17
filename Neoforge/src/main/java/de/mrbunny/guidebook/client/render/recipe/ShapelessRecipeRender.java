package de.mrbunny.guidebook.client.render.recipe;

import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.util.ScreenUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class ShapelessRecipeRender extends CraftingRecipeRender<ShapelessRecipe> {

    public ShapelessRecipeRender ( ShapelessRecipe pRecipe ) {
        super (pRecipe, Component.translatable("guidebook.recipe.crafting.shapeless"));
    }

    public void renderRecipe(GuiGraphics pGraphics, RegistryAccess pRegAccess, IModScreen pScreen, int pMouseX, int pMouseY, Font pFont) {
        super.renderRecipe(pGraphics, pRegAccess, pScreen, pMouseX, pMouseY, pFont);

        for ( int y = 0 ; y < 3; y++ ) {
            for ( int x = 0; x < 3; x++ ) {
                int i = 3 * y + x;
                int stackX = (x + 1) * 17 + (pScreen.getXOffset() + pScreen.getWidthSize() / 2 - 90) + x;
                int stackY = (y + 1) * 17 + (pScreen.getYOffset() + pScreen.getHeightSize() / 2 - 59) + y;

                if ( i < this.recipe.getIngredients().size() ) {
                    Ingredient ingredient = this.recipe.getIngredients().get(i);

                    this.ingredientCycler.cycleItems(ingredient, i).ifPresent((stack) -> {
                        ScreenUtils.drawScaledItemStack(pGraphics, stack, stackX, stackY, 1.5F);
                        if ( ScreenUtils.isMouseBetween(pMouseX, pMouseY, stackX, stackY, 16, 16) )
                            tooltips = ScreenUtils.getTooltip(stack);
                    });
                }
            }
        }
    }
}
