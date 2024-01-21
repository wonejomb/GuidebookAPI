package de.mrbunny.guidebook.client.render.entry;

import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.util.ScreenUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.item.ItemStack;

public class ItemStackEntryRender extends EntryRender {

    private final ItemStack itemStack;

    public ItemStackEntryRender ( ItemStack pStack ) {
        this.itemStack = pStack;
    }

    public void renderExtras(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IBookEntry pEntry, IModScreen pScreen, Font pFont) {
        if (ScreenUtils.isMouseBetween(pMouseX, pMouseY, pEntry.getX(), pEntry.getY(), pEntry.getWidth(), pEntry.getHeight() )) {
            ScreenUtils.drawScaledItemStack( pGraphics, this.itemStack, pEntry.getX(), pEntry.getY() + 1, 1.1F);
        } else {
            ScreenUtils.drawScaledItemStack( pGraphics, this.itemStack, pEntry.getX(), pEntry.getY(), 1.1F);
        }

        super.renderExtras(pGraphics, pAccess, pMouseX, pMouseY, pEntry, pScreen, pFont);
    }

}
