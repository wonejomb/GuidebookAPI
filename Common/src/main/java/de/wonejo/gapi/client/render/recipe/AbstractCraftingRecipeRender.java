package de.wonejo.gapi.client.render.recipe;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.api.util.ItemRotation;
import de.wonejo.gapi.api.util.RenderUtils;
import de.wonejo.gapi.cfg.ModConfigurations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;

public abstract class AbstractCraftingRecipeRender<T extends Recipe<?>> extends AbstractRecipeRender<T> {

    private static final ResourceLocation CRAFTING_GRID = new ResourceLocation(Constants.MOD_ID, "textures/gui/recipe/crafting_grid.png");

    private final Component title;

    public AbstractCraftingRecipeRender(T pRecipe, Component pTitle)  {
        super(pRecipe);
        this.title = pTitle;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont, IBook pBook, ItemRotation pRotation) {
        pRotation.tick(Minecraft.getInstance());

        RenderUtils.renderScaledImage(pGraphics, CRAFTING_GRID, pScreen.xOffset() + pScreen.screenWidth() / 2 - 64, pScreen.yOffset() + pScreen.screenHeight() / 2 - 36, 100, 58, 1.3F);

        RenderUtils.drawCenteredStringWithoutShadow(pGraphics, pFont, this.title, pScreen.xOffset() + pScreen.screenWidth() / 2, pScreen.yOffset() + 12, ModConfigurations.CLIENT_PROVIDER.getBookTextColors().get(pBook).get().getRGB());

        int outputX = pScreen.xOffset() + pScreen.screenWidth() / 2 + 45;
        int outputY = pScreen.yOffset() + pScreen.screenHeight() / 2 - 7;

        ItemStack stack = this.recipe.getResultItem(pAccess);

        RenderUtils.renderItem(pGraphics, stack, outputX, outputY);

        if ( RenderUtils.isMouseBetween(pMouseX, pMouseY, outputX, outputY, 16, 16) )
            this.tooltips = RenderUtils.getTooltips(stack);
    }
}
