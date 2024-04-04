package de.wonejo.gapi.client.render.recipe;

import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.api.util.ItemRotation;
import de.wonejo.gapi.api.util.RenderUtils;
import de.wonejo.gapi.config.ModConfigurations;
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

public class FurnaceRecipeRender extends AbstractRecipeRender<AbstractCookingRecipe> {

    private static final ResourceLocation FURNACE_GRID = new ResourceLocation(Constants.MOD_ID, "textures/gui/recipe/furnace_grid.png");

    private final Component title;

    public FurnaceRecipeRender  ( AbstractCookingRecipe pRecipe ) {
        this ( pRecipe, Component.translatable("gapi.recipe.furnace"));
    }

    public FurnaceRecipeRender(AbstractCookingRecipe pRecipe, Component pTitle) {
        super(pRecipe);
        this.title = pTitle;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont, ItemRotation pRotation) {
        pRotation.tick(Minecraft.getInstance());

        RenderUtils.drawCenteredStringWithoutShadow(pGraphics, pFont, this.title, pScreen.xOffset() + pScreen.widthSize() / 2, pScreen.yOffset() + 10, ModConfigurations.CLIENT.textColor.get());
        RenderUtils.renderImage(pGraphics, FURNACE_GRID, pScreen.xOffset() + pScreen.widthSize() / 2 - 57, pScreen.yOffset() + pScreen.heightSize() / 2 - 63,  57, 63);

        int x = pScreen.xOffset() + pScreen.widthSize() / 2 - 52;
        int y = pScreen.yOffset() + pScreen.heightSize() / 2 - 58;
        Ingredient input = this.recipe.getIngredients().get(0);
        pRotation.cycleIngredientStack(input, 0).ifPresent((stack) -> {
            RenderUtils.renderItem(pGraphics, stack, x, y);
            if ( RenderUtils.isMouseBetween(pMouseX, pMouseY, x, y, 16, 16) )
                tooltips = RenderUtils.getTooltips(stack);
        });

        ItemStack output = this.recipe.getResultItem(pAccess);
        int outputX = pScreen.xOffset() + pScreen.widthSize() / 2 + 39;
        int outputY = pScreen.yOffset() + pScreen.heightSize() / 2 + 24;
        RenderUtils.renderItem(pGraphics, output, outputX, outputY);
        if ( RenderUtils.isMouseBetween(pMouseX, pMouseY, outputX, outputY, 16, 16) )
            tooltips = RenderUtils.getTooltips(output);

        int fuelX = pScreen.xOffset() + pScreen.widthSize() / 2 - 52;
        int fuelY = pScreen.yOffset() + pScreen.heightSize() / 2 + 25;

        RenderUtils.renderItem(pGraphics, new ItemStack(Items.COAL), fuelX, fuelY);
    }
}
