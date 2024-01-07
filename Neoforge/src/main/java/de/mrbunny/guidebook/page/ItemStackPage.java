package de.mrbunny.guidebook.page;

import de.mrbunny.guidebook.api.book.component.IPage;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.client.book.IPageRender;
import de.mrbunny.guidebook.util.PageUtils;
import de.mrbunny.guidebook.util.ScreenUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.HitResult;

public class ItemStackPage implements IPage, IPageRender {

    private final FormattedText text;
    private final Ingredient ingredient;

    public ItemStackPage (FormattedText pText, Ingredient pIngredient) {
        this.text = pText;
        this.ingredient = pIngredient;
    }

    public ItemStackPage ( FormattedText pText, ItemStack pStack ) {
        this ( pText, Ingredient.of(pStack));
    }

    public ItemStackPage ( FormattedText pText, Item pItem ) {
        this (pText, new ItemStack(pItem));
    }

    public ItemStackPage (FormattedText pText, Block pBlock) {
        this (pText, new ItemStack(pBlock));
    }

    public void render(GuiGraphics pGraphics, int pMouseX, int pMouseY, Font pFont) {

    }

    public void renderExtras(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont) {
        ScreenUtils.drawScaledItemStack(pGraphics, this.ingredient.getItems()[0], pScreen.getXOffset() + 101, pScreen.getYOffset() + 10, 3);

        PageUtils.drawFormattedText(pGraphics, pScreen.getXOffset() + 22, pScreen.getYOffset() + 66, this.text);
    }

    public ItemStackPage getRender() {
        return this;
    }
}
