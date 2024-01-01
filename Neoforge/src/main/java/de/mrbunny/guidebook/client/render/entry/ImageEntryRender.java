package de.mrbunny.guidebook.client.render.entry;

import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.client.book.IEntryRender;
import de.mrbunny.guidebook.util.ScreenUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;

public class ImageEntryRender extends EntryRender {

    private final ResourceLocation image;
    private final int imageWidth;
    private final int imageHeight;

    public ImageEntryRender ( ResourceLocation pImage, int pImageWidth, int pImageHeight ) {
        this.image = pImage;
        this.imageWidth = pImageWidth;
        this.imageHeight = pImageHeight;
    }

    public ImageEntryRender ( ResourceLocation pImage ){
        this ( pImage, 16, 16 );
    }

    public void renderExtras(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IBookEntry pEntry, IModScreen pScreen, Font pFont) {
        if (ScreenUtils.isMouseBetween(pMouseX, pMouseY, pEntry.getX(), pEntry.getY(), pEntry.getWidth(), pEntry.getHeight())) {
            ScreenUtils.drawScaledImage(pGraphics, this.image, pEntry.getX(), pEntry.getY() + 1, this.imageWidth, this.imageHeight, 1.0F);
        } else {
            ScreenUtils.drawScaledImage(pGraphics, this.image, pEntry.getX(), pEntry.getY(), this.imageWidth, this.imageHeight, 1.0F);
        }

        super.renderExtras(pGraphics, pAccess, pMouseX, pMouseY, pEntry, pScreen, pFont);
    }

}
