package de.wonejo.gapi.api.book;


import de.wonejo.gapi.api.book.components.IBookCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.Color;
import java.util.List;
import java.util.function.Consumer;

public interface IBookBuilder {

    IBookBuilder color (Color pColor);

    IBookBuilder spawnWithBook ();
    IBookBuilder title ( Component pTitle );
    IBookBuilder header ( Component pHeader );
    IBookBuilder subHeader ( Component pHeader );
    IBookBuilder itemName ( Component pItemName );
    IBookBuilder topTexture (ResourceLocation pId);
    IBookBuilder pagesTexture ( ResourceLocation pPageTextures );
    IBookBuilder model ( ResourceLocation pModelLocation );
    IBookBuilder author ( Component pAuthor );
    IBookBuilder information ( IBookInformation pInformation );
    IBookBuilder contentProvider (Consumer<List<IBookCategory>> pContentProvider);


    IBook build ( );

}
