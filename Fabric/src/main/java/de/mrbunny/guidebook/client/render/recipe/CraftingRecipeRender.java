package de.mrbunny.guidebook.client.render.recipe;

import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.util.References;
import de.mrbunny.guidebook.util.IngredientCycler;
import de.mrbunny.guidebook.util.ScreenUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

public abstract class CraftingRecipeRender<T extends Recipe<?>> extends RecipeRenderBase<T> {

    private final ResourceLocation CRAFTING_RECIPE_GRID = new ResourceLocation(References.GUIDEBOOKAPI_ID, "textures/gui/recipes/crafting_recipe_grid.png");

    protected final IngredientCycler ingredientCycler = new IngredientCycler();
    private final Component title;
    private Component customDisplay;

    public CraftingRecipeRender ( T pRecipe, Component pTitle ) {
        super(pRecipe);
        this.title = pTitle;
    }

    public void renderRecipe(GuiGraphics pGraphics, RegistryAccess pRegAccess, IModScreen pScreen, int pMouseX, int pMouseY, Font pFont) {
        this.ingredientCycler.tick(Minecraft.getInstance());

        ScreenUtils.drawScaledImage(pGraphics, this.CRAFTING_RECIPE_GRID,
                pScreen.getXOffset() + pScreen.getWidthSize() / 2 - 80,
                pScreen.getYOffset() + pScreen.getHeightSize() / 2 - 48,
                80,
                48,
                2);

        Component recipeName = this.customDisplay == null ? this.title : this.customDisplay;
        pGraphics.drawString(pFont, recipeName, pScreen.getXOffset() + pScreen.getWidthSize() / 2 - pFont.width(recipeName) / 2, pScreen.getYOffset() + 15, ChatFormatting.DARK_GRAY.getColor(), false);

        int outputX = pScreen.getXOffset() + pScreen.getWidthSize() / 2 + 54 ;
        int outputY = pScreen.getYOffset() + pScreen.getHeightSize() / 2 - 11;

        ItemStack stack = this.recipe.getResultItem(pRegAccess);

        ScreenUtils.drawScaledItemStack(pGraphics, stack, outputX, outputY, 1.3F);

        if ( ScreenUtils.isMouseBetween(pMouseX, pMouseY, outputX, outputY, 15, 15) )
            tooltips = ScreenUtils.getTooltip ( stack );
    }

    public CraftingRecipeRender<T> setCustomTitle ( Component pCustomDisplay ) {
        this.customDisplay = pCustomDisplay;
        return this;
    }
}
