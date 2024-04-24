package de.wonejo.gapi.api.client;

import de.wonejo.gapi.api.book.IBook;
import net.minecraft.resources.ResourceLocation;

public interface IModScreen {

    IBook getBook ();

    ResourceLocation topTexture ();
    ResourceLocation pagesTexture ();

    int xOffset ();
    int yOffset ();

    int widthSize ();
    int heightSize ();

}
