package de.wonejo.wuidebook.client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public sealed interface RenderableImage permits RenderableImage.Image, RenderableImage.ScaledImage {

     static @NotNull RenderableImage image (ResourceLocation pLocation, int pWidth, int pHeight ) {
        return new Image(pLocation, pWidth, pHeight);
    }

    static @NotNull RenderableImage scaledImage (ResourceLocation pLocation, int pWidth, int pHeight, float pScale ) {
        return new ScaledImage(pLocation, pWidth, pHeight, pScale);
    }

    void render ( GuiGraphics pGraphics, int pX, int pY );

    final class Image implements RenderableImage {

        private final ResourceLocation location;
        private final int width;
        private final int height;

        public Image ( ResourceLocation pLocation, int pWidth, int pHeight ) {
            this.location = pLocation;
            this.width = pWidth;
            this.height = pHeight;
        }

        public void render(@NotNull GuiGraphics pGraphics, int pX, int pY ) {
            pGraphics.blit(this.location, pX, pY, width, height, 0, 0, width, height, width, height);
        }

    }

    final class ScaledImage implements RenderableImage {

        private final ResourceLocation location;
        private final int width;
        private final int height;
        private final float scale;

        public ScaledImage ( ResourceLocation pLocation, int pWidth, int pHeight, float pScale ) {
            this.location = pLocation;
            this.width = pWidth;
            this.height = pHeight;
            this.scale = pScale;
        }

        public void render(@NotNull GuiGraphics pGraphics, int pX, int pY) {
            pGraphics.pose().pushPose();
            pGraphics.pose().scale(this.scale, this.scale, 1.0F);
            pGraphics.blit(this.location, pX, pY, width, height, 0, 0, width, height, width, height);
            pGraphics.pose().popPose();
        }

    }

}
