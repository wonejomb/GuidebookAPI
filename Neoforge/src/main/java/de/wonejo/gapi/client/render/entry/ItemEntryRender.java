package de.wonejo.gapi.client.render.entry;

import de.wonejo.gapi.api.book.components.IBookEntry;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.RenderUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public final class ItemEntryRender extends EntryRender {

    private final ItemStack item;

    public ItemEntryRender(Component pName, ItemStack pStack) {
        super(pName);
        this.item = pStack;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pEntryX, int pEntryY, int pMouseX, int pMouseY, IBookEntry pCategory, IModScreen pScreen, Font pFont) {
        if (RenderUtils.isMouseBetween(pMouseX, pMouseY, pEntryX - 16, pEntryY, 116, 12)){
            RenderUtils.renderScaledItem(pGraphics, this.item, pEntryX - 19, pEntryY - 5, 1.1F);
        } else {
            RenderUtils.renderScaledItem(pGraphics, this.item, pEntryX - 19, pEntryY - 6, 1.1F);
        }

        super.render(pGraphics, pAccess, pEntryX, pEntryY, pMouseX, pMouseY, pCategory, pScreen, pFont);
    }
}
