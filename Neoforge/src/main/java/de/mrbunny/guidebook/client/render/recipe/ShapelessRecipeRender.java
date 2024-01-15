package de.mrbunny.guidebook.client.render.recipe;

import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.util.IngredientCycler;
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
                int stackX = (x + 1) * 17 + (pScreen.getXOffset() + 53) + x;
                int stackY = (y + 1) * 17 + (pScreen.getYOffset() + 38) + y;
                if ( i < recipe.getIngredients().size() ) {
                    Ingredient ingredient = recipe.getIngredients().get(i);

                    this.ingredientCycler.getCycledIngredientStack(ingredient, i).ifPresent((stack) -> {
                        ScreenUtils.drawItemStack(pGraphics, stack, stackX, stackY);
                        if ( ScreenUtils.isMouseBetween(pMouseX, pMouseY, stackX, stackY, 15, 15) )
                            tooltips = ScreenUtils.getTooltip(stack);
                    });
                }
            }
        }
    }
}
