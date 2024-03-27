package de.wonejo.gapi.api;

import de.wonejo.gapi.api.book.IBookBuilder;
import de.wonejo.gapi.api.util.Constants;
import net.minecraft.resources.ResourceLocation;

public interface IGuidebook {

    IBookBuilder build ();

    default ResourceLocation getModelLocation () {
        return new ResourceLocation(Constants.MOD_ID, "guidebook");
    }

    default ResourceLocation borderTexture () {
        return new ResourceLocation(Constants.MOD_ID, "textures/item/book_borders.png");
    }

    default ResourceLocation pagesTexture () {
        return new ResourceLocation(Constants.MOD_ID, "textures/item/book_pages.png");
    }


}
