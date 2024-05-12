package de.wonejo.gapi.client.render.recipe;

import de.wonejo.gapi.api.book.IBook;
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

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont, IBook pBook, ItemRotation pRotation) {
        super.render(pGraphics, pAccess, pMouseX, pMouseY, pScreen, pFont, pBook, pRotation);

        for ( int y =  0; y < this.recipe.getWidth(); y++ ) {
            for ( int x = 0; x < this.recipe.getHeight(); x++ ) {
                int i = y * this.recipe.getWidth() + x;

                int stackX = x * 24 + pScreen.xOffset() + pScreen.screenWidth() / 2 - 59 + x;
                int stackY = y * 24 + pScreen.yOffset() + pScreen.screenHeight() / 2 - 32 + y;

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
