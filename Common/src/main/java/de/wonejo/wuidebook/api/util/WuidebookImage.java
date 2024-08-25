package de.wonejo.wuidebook.api.util;

import net.minecraft.resources.ResourceLocation;

public class WuidebookImage {

    private final ResourceLocation location;
    private final int width;
    private final int height;

    public WuidebookImage ( ResourceLocation pLocation, int pWidth, int pHeight ) {
        this.location = pLocation;
        this.width = pWidth;
        this.height = pHeight;
    }

    public ResourceLocation getLocation() {
        return location;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
