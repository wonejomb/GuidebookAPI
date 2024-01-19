package de.mrbunny.guidebook.client.render.category;

import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.client.IModScreen;

import de.mrbunny.guidebook.util.ScreenUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;

public class ItemStackCategoryRender extends CategoryRender {

    private final ItemStack itemStack;

    public ItemStackCategoryRender ( ItemStack pStack ) {
        this.itemStack = pStack;
    }

    public void renderExtras(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IBookCategory pCategory, IModScreen pScreen, Font pFont) {

        if ( ScreenUtils.isMouseBetween(pMouseX, pMouseY, pCategory.getX(), pCategory.getY(), pCategory.getWidth(), pCategory.getHeight()) && canView() ) {
            ScreenUtils.drawScaledItemStack(pGraphics, this.itemStack, pCategory.getX(), pCategory.getY() + 1, 1.3f);
        } else {
            ScreenUtils.drawScaledItemStack(pGraphics, this.itemStack, pCategory.getX(), pCategory.getY(), 1.3f);
        }

        super.renderExtras(pGraphics, pAccess, pMouseX, pMouseY, pCategory, pScreen, pFont);
    }

}
