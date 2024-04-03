package de.wonejo.gapi.client.render.recipe;

import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.ItemRotation;
import de.wonejo.gapi.api.util.RenderUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;

public class ShapedRecipeRender extends AbstractCraftingRecipeRender<ShapedRecipe> {

    public ShapedRecipeRender ( ShapedRecipe pRecipe ) {
        this ( pRecipe, Component.translatable("gapi.recipe.shaped"));
    }

    public ShapedRecipeRender(ShapedRecipe pRecipe, Component pTitle) {
        super(pRecipe, pTitle);
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont, ItemRotation pRotation) {
        super.render(pGraphics, pAccess, pMouseX, pMouseY, pScreen, pFont, pRotation);

        for ( int y =  0; y < this.recipe.getRecipeHeight(); y++ ) {
            for ( int x = 0; x < this.recipe.getRecipeWidth(); x++ ) {
                int i = y * this.recipe.getRecipeWidth() + x;

                int stackX = (x + 1) * 17 + (pScreen.xOffset() + pScreen.widthSize() / 2 - 100) + x;
                int stackY = (y + 1) * 17 + (pScreen.xOffset() + pScreen.heightSize() / 2 - 58) + y;

                Ingredient ingredient = this.recipe.getIngredients().get(i);

                pRotation.cycleIngredientStack(ingredient, i).ifPresent((stack) -> {
                    RenderUtils.renderItem(pGraphics, stack, stackX, stackY);
                    if ( RenderUtils.isMouseBetween(pMouseX, pMouseY, stackX, stackY, 16, 16) )
                        tooltips = RenderUtils.getTooltips(stack);
                });
            }
        }
    }
}
