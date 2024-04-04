package de.wonejo.gapi.client.render.entry;

import de.wonejo.gapi.api.book.components.IBookEntry;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.RenderUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class ImageEntryRender extends EntryRender {

    private final ResourceLocation texture;

    public ImageEntryRender(Component pName, ResourceLocation pTexture) {
        super(pName);
        this.texture = pTexture;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pEntryX, int pEntryY, int pMouseX, int pMouseY, IBookEntry pCategory, IModScreen pScreen, Font pFont) {
        if (RenderUtils.isMouseBetween(pMouseX, pMouseY, pEntryX - 16, pEntryY, 116, 12)) {
            RenderUtils.renderImage(pGraphics, this.texture,pEntryX - 19, pEntryY - 5, 16, 16);
        } else {
            RenderUtils.renderImage(pGraphics, this.texture,pEntryX - 19, pEntryY - 6, 16, 16);
        }

        super.render(pGraphics, pAccess, pEntryX, pEntryY, pMouseX, pMouseY, pCategory, pScreen, pFont);
    }

}
