package de.mrbunny.guidebook.client.render.category;

import com.google.common.collect.Lists;
import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.client.book.ICategoryRender;

import de.mrbunny.guidebook.util.ComponentUtils;
import de.mrbunny.guidebook.util.ScreenUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class ItemStackCategoryRender extends CategoryRender {

    private final ItemStack itemStack;

    public ItemStackCategoryRender ( ItemStack pStack ) {
        this.itemStack = pStack;
    }

    public boolean canView() {
        return true;
    }

    public void render(GuiGraphics pGraphics, int pMouseX, int pMouseY, Font pFont) {

    }

    public void renderExtras(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IBookCategory pCategory, IModScreen pScreen, Font pFont) {

        if ( ScreenUtils.isMouseBetween(pMouseX, pMouseY, pCategory.getX(), pCategory.getY(), pCategory.getWidth(), pCategory.getHeight()) && canView() ) {
            ScreenUtils.drawScaledItemStack(pGraphics, this.itemStack, pCategory.getX(), pCategory.getY() + 1, 1.3f);
        } else {
            ScreenUtils.drawScaledItemStack(pGraphics, this.itemStack, pCategory.getX(), pCategory.getY(), 1.3f);
        }

        super.renderExtras(pGraphics, pAccess, pMouseX, pMouseY, pCategory, pScreen, pFont);
    }

    public void init() {

    }

    public void leftClick(double pMouseX, double pMouseY) {

    }

    public void rightClick(double pMouseX, double pMouseY) {

    }
}
