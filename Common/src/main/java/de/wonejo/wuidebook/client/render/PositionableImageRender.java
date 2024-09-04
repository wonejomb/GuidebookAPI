package de.wonejo.wuidebook.client.render;

import de.wonejo.wuidebook.api.client.PositionableRender;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public final class PositionableImageRender implements PositionableRender {

    private final ResourceLocation image;
    private final int imageWidth;
    private final int imageHeight;

    public PositionableImageRender(ResourceLocation image, int imageWidth, int imageHeight) {
        this.image = image;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    public void render(@NotNull GuiGraphics pGraphics, int pX, int pY) {
        pGraphics.blit(this.image, pX, pY, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
    }

}
