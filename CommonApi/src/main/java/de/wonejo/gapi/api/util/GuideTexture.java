package de.wonejo.gapi.api.util;

import net.minecraft.resources.ResourceLocation;

public class GuideTexture {

    private final ResourceLocation textureLoc;
    private final int imageWidth;
    private final int imageHeight;

    public GuideTexture ( ResourceLocation pTextureLoc, int pImageWidth, int pImageHeight ) {
        this.textureLoc = pTextureLoc;
        this.imageWidth = pImageWidth;
        this.imageHeight = pImageHeight;
    }

    public ResourceLocation getTextureLoc() {
        return textureLoc;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

}
