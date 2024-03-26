package de.wonejo.gapi.api.book;


import de.wonejo.gapi.api.book.components.IBookCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface IBookBuilder {

    IBookBuilder color (Color pColor);

    IBookBuilder spawnWithBook ();
    IBookBuilder title ( Component pTitle );
    IBookBuilder header ( Component pHeader );
    IBookBuilder subHeader ( Component pHeader );
    IBookBuilder itemName ( Component pItemName );
    IBookBuilder author ( Component pAuthor );
    IBookBuilder contentProvider (Consumer<List<IBookCategory>> pContentProvider);


    IBook build ();

}
