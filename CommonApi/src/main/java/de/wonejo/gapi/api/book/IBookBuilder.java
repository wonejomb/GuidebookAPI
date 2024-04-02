package de.wonejo.gapi.api.book;


import de.wonejo.gapi.api.book.components.IBookCategory;
import de.wonejo.gapi.api.util.GuideTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.Color;
import java.util.List;
import java.util.function.Consumer;

public interface IBookBuilder {

    IBookBuilder color (Color pColor);

    IBookBuilder spawnWithBook ();
    IBookBuilder header ( Component pHeader );
    IBookBuilder subHeader ( Component pHeader );
    IBookBuilder itemName ( Component pItemName );
    IBookBuilder bookTextures ( GuideTexture pBookTextures );
    IBookBuilder infoTextures ( GuideTexture pInfoTextures );
    IBookBuilder pagesTexture ( GuideTexture pPageTextures );
    IBookBuilder model ( ResourceLocation pModelLocation );
    IBookBuilder author ( Component pAuthor );
    IBookBuilder information ( IBookInformation pInformation );
    IBookBuilder contentProvider (Consumer<List<IBookCategory>> pContentProvider);


    IBook build ( );

}
