package de.wonejo.gapi.client.render.category;

import de.wonejo.gapi.api.book.components.ICategory;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.client.render.ICategoryRender;
import de.wonejo.gapi.api.util.RenderUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public final class ItemCategoryRender implements ICategoryRender {

    private final Component name;
    private final ItemStack item;

    public ItemCategoryRender ( Component pName, ItemStack pItem ) {
        this.name = pName;
        this.item = pItem;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pCategoryX, int pCategoryY, int pMouseX, int pMouseY, ICategory pCategory, IModScreen pScreen, Font pFont) {
        RenderUtils.renderScaledItem(pGraphics,this.item, pCategoryX, pCategoryY, 1.3F);

        if ( RenderUtils.isMouseBetween(pMouseX, pMouseY, pCategoryX, pCategoryY, 16, 16) )
            pGraphics.renderTooltip(pFont, this.name, pMouseX, pMouseY);
    }

    public void init() {

    }

    public Component name() {
        return this.name;
    }
}
