package de.wonejo.gapi.client.render.page;

import com.mojang.blaze3d.vertex.PoseStack;
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

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont) {
        float scaleX = 1.0F;
        float scaleY = 1.0F;

        if ( this.imgWidth > pScreen.widthSize() )
            scaleX = (float) this.imgWidth / pScreen.widthSize();
        if ( this.imgHeight > pScreen.heightSize() )
            scaleY = (float) this.imgHeight / pScreen.heightSize();


        float scale = Math.min(scaleX, scaleY);

        PoseStack pose = pGraphics.pose();
        pose.pushPose();

        pose.translate(pScreen.xOffset() + (float) pScreen.widthSize() / 2 - this.imgWidth / scale,
                pScreen.yOffset() + (float) pScreen.heightSize() / 2 - this.imgHeight / scale,
                1.0F);

        pose.scale(scale, scale, 1.0F);
        pGraphics.blit(this.imageLoc, 0, 0, 0, 0, this.imgWidth, this.imgHeight, this.imgWidth, this.imgHeight);
        pose.popPose();
    }
}
