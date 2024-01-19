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
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;


public class FurnaceRecipeRender extends RecipeRenderBase<AbstractCookingRecipe> {

    private static final ResourceLocation FURNACE_GRID_LOC = new ResourceLocation(References.GUIDEBOOKAPI_ID, "textures/gui/recipes/furnace_grid.png");
    private final Component title = Component.translatable("guidebook.recipe.furnace.smelting");
    private final IngredientCycler ingredientCycler = new IngredientCycler();

    public FurnaceRecipeRender(AbstractCookingRecipe pRecipe) {
        super(pRecipe);
    }

    public void renderRecipe(GuiGraphics pGraphics, RegistryAccess pRegAccess, IModScreen pScreen, int pMouseX, int pMouseY, Font pFont) {
        this.ingredientCycler.tick(Minecraft.getInstance());
        ScreenUtils.drawScaledImage(pGraphics, FURNACE_GRID_LOC,
                pScreen.getXOffset() + pScreen.getWidthSize() / 2 - 48,
                pScreen.getYOffset() + pScreen.getHeightSize() / 2 - 48,
                48,
                48,
                2);

        pGraphics.drawString(pFont, this.title,
                pScreen.getXOffset() + pScreen.getWidthSize() / 2 - pFont.width(this.title) / 2,
                pScreen.getYOffset() + 15,
                ChatFormatting.DARK_GRAY.getColor(),
                false);

        int x = pScreen.getXOffset() + pScreen.getWidthSize() / 2 - 44;
        int y = pScreen.getYOffset() + pScreen.getHeightSize() / 2 - 44;

        Ingredient input = this.recipe.getIngredients().get(0);
        this.ingredientCycler.cycleItems(input, 0).ifPresent((stack) -> {
            ScreenUtils.drawScaledItemStack(pGraphics, stack, x, y, 1.4F);

            if ( ScreenUtils.isMouseBetween(pMouseX, pMouseY, x, y, 15, 15) )
                tooltips = ScreenUtils.getTooltip(stack);
        });

        ItemStack output = this.recipe.getResultItem(pRegAccess);
        int outputX = pScreen.getXOffset() + pScreen.getWidthSize() / 2 + 20 ;
        int outputY = pScreen.getYOffset() + pScreen.getHeightSize() / 2 - 12;

        ScreenUtils.drawScaledItemStack(pGraphics, output, outputX, outputY, 1.4F);

        int fuelX = pScreen.getXOffset() + pScreen.getWidthSize() / 2 - 44;
        int fuelY = pScreen.getYOffset() + pScreen.getHeightSize() / 2 + 20;

        ScreenUtils.drawScaledItemStack(pGraphics, new ItemStack(Items.COAL), fuelX, fuelY, 1.4F);

        if ( ScreenUtils.isMouseBetween(pMouseX, pMouseY, outputX, outputY, 15, 15) )
            this.tooltips = ScreenUtils.getTooltip(output);
    }
}
