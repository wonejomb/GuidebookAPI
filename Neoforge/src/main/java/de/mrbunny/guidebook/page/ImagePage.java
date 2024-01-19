package de.mrbunny.guidebook.page;

import de.mrbunny.guidebook.api.book.component.IPage;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.client.book.IPageRender;
import de.mrbunny.guidebook.util.PageUtils;
import de.mrbunny.guidebook.util.ScreenUtils;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class ImagePage implements IPage, IPageRender {

    private final Component text;
    private final ResourceLocation imageLocation;

    private final int imageWidth;
    private final int imageHeight;

    private final float scale;

    public ImagePage (Component pText, ResourceLocation pImageLocation, int pImageWidth, int pImageHeight) {
        this (pText, pImageLocation, pImageWidth, pImageHeight, 1.0F);
    }

    public ImagePage ( Component pText, ResourceLocation pImageLocation, int pImageWidth, int pImageHeight, float pScale ) {
        this.text = pText;
        this.imageLocation = pImageLocation;
        this.imageWidth = pImageWidth;
        this.imageHeight = pImageHeight;
        this.scale = pScale;
    }

    public ImagePage ( Component pText, ResourceLocation pImageLocation ){
        this (pText, pImageLocation, 16, 16);
    }

    public ImagePage  ( Component pText, ResourceLocation pImageLoc, float pScale )  {
        this ( pText, pImageLoc, 16, 16, pScale );
    }

    public void renderExtras(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont) {

        if ( this.scale != 1.0F ) {
            ScreenUtils.drawScaledImage(pGraphics,
                    this.imageLocation,
                    (int) (pScreen.getXOffset() + pScreen.getWidthSize() / 2 - this.imageWidth / 2.0F * this.scale),
                    (int) (pScreen.getYOffset() + pScreen.getHeightSize() / 2 - 38 - this.imageHeight / 2.0F * this.scale),
                    this.imageWidth,
                    this.imageHeight,
                    this.scale);
        } else {
            ScreenUtils.drawImage(pGraphics,
                    this.imageLocation,
                    pScreen.getXOffset() + pScreen.getWidthSize() / 2,
                    pScreen.getYOffset() + pScreen.getHeightSize() / 2 - this.imageHeight / 2,
                    this.imageWidth,
                    this.imageHeight);
        }

                        PageUtils.drawFormattedText(pGraphics, this.text, pScreen.getXOffset() + 25,
                                pScreen.getYOffset() + pScreen.getHeightSize() / 2,
                                186);
    }

    public void render(GuiGraphics pGraphics, int pMouseX, int pMouseY, Font pFont) {

    }
}
