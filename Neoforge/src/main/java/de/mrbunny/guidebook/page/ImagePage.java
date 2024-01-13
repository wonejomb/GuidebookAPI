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

public class ImagePage implements IPage, IPageRender {

    private final Component text;
    private final ResourceLocation imageLocation;

    private final int imageWidth;
    private final int imageHeight;

    public ImagePage (Component pText, ResourceLocation pImageLocation, int pImageWidth, int pImageHeight) {
        this.text = pText;
        this.imageLocation = pImageLocation;
        this.imageWidth = pImageWidth;
        this.imageHeight = pImageHeight;
    }

    public ImagePage ( Component pText, ResourceLocation pImageLocation ){
        this (pText, pImageLocation, 16, 16);
    }

    public void renderExtras(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont) {
        ScreenUtils.drawImage(pGraphics, this.imageLocation, pScreen.getXOffset() + pScreen.getWidthSize() / 2, pScreen.getYOffset() + 15 + this.imageHeight / 2, this.imageWidth, this.imageHeight);
        PageUtils.drawFormattedText(pGraphics, this.text, pScreen.getXOffset() + 15, pScreen.getHeightSize() + pScreen.getHeightSize() / 2, 186);
    }

    public void render(GuiGraphics pGraphics, int pMouseX, int pMouseY, Font pFont) {

    }
}
