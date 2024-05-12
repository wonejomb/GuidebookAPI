package de.wonejo.gapi.client.render.page;

import com.mojang.blaze3d.vertex.PoseStack;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.client.render.IPageRender;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;

public class ImagePageRender implements IPageRender {

    private final ResourceLocation imageLoc;
    private final int imgWidth;
    private final int imgHeight;

    public ImagePageRender ( ResourceLocation pImageLoc, int pImgWidth, int pImgHeight ) {
        this.imageLoc = pImageLoc;
        this.imgWidth = pImgWidth;
        this.imgHeight = pImgHeight;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, IBook pBook, Font pFont) {

        PoseStack pose = pGraphics.pose();
        pose.pushPose();

        pose.translate(pScreen.xOffset() + (float) pScreen.screenWidth() / 2 - (float) this.imgWidth / 2, pScreen.yOffset() + (float) pScreen.screenHeight() / 2 - (float) this.imgHeight / 2, 1.0F);
        pGraphics.blit(this.imageLoc, 0, 0, 0, 0, this.imgWidth, this.imgHeight, this.imgWidth, this.imgHeight);
        pose.popPose();
    }
}
