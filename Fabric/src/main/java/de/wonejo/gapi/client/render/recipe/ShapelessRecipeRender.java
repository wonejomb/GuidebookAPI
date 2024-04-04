package de.wonejo.gapi.client.render.recipe;

import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.ItemRotation;
import de.wonejo.gapi.api.util.RenderUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;

public class ShapelessRecipeRender extends AbstractCraftingRecipeRender<ShapelessRecipe> {

    public ShapelessRecipeRender(ShapelessRecipe pRecipe) {
        super(pRecipe, Component.translatable("gapi.recipe.shapeless"));
    }

    public ShapelessRecipeRender(ShapelessRecipe pRecipe, Component pTitle) {
        super(pRecipe, pTitle);
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont, ItemRotation pRotation) {
        super.render(pGraphics, pAccess, pMouseX, pMouseY, pScreen, pFont, pRotation);

        for ( int y = 0; y < 3; y++ ) {
            for ( int x = 0; x < 3; x++ ) {
                int i = 3 * y + x;
                int stackX = x * 24 + pScreen.xOffset() + pScreen.widthSize() / 2 - 59 + x;
                int stackY = y * 24 + pScreen.yOffset() + pScreen.heightSize() / 2 - 32 + y;

                if ( i < this.recipe.getIngredients().size() ) {
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

}
