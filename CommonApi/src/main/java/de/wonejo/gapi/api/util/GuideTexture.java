package de.wonejo.gapi.api.util;

import net.minecraft.resources.ResourceLocation;

public class GuideTexture {

    public static GuideTexture of ( ResourceLocation pPagesTexture, ResourceLocation pTopTexture ) {
        return new GuideTexture(pPagesTexture, pTopTexture);
    }

    private final ResourceLocation pagesTexture;
    private final ResourceLocation topTexture;

    private GuideTexture ( ResourceLocation pPagesTexture, ResourceLocation pTopTexture ) {
        this.pagesTexture = pPagesTexture;
        this.topTexture = pTopTexture;
    }

    public ResourceLocation getPagesTexture() {
        return pagesTexture;
    }

    public ResourceLocation getTopTexture() {
        return topTexture;
    }
}
