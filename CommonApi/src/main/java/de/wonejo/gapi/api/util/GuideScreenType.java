package de.wonejo.gapi.api.util;

public enum GuideScreenType {
    NORMAL(273, 129),
    INFO(282, 129),
    PAGE(160, 176)
    ;

    private final int textureWidth;
    private final int textureHeight;

    GuideScreenType ( int pTextureWidth, int pTextureHeight ) {
        this.textureWidth = pTextureWidth;
        this.textureHeight = pTextureHeight;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public int getTextureHeight() {
        return textureHeight;
    }
}
