package de.mrbunny.guidebook.api.book;

import de.mrbunny.guidebook.api.book.component.IBookCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public interface IBookBuilder {

    IBook build();

    IBookBuilder setTitle ( Component pTitle );
    IBookBuilder setHeader ( Component pHeader );
    IBookBuilder setItemName ( Component pItemName );
    IBookBuilder setAuthor ( Component pAuthor );
    IBookBuilder setColor ( Color pColor );

    IBookBuilder setOutlineTexture ( ResourceLocation pLoc );
    IBookBuilder setPagesTexture ( ResourceLocation pLoc );

    IBookBuilder contentProvider (Consumer<List<IBookCategory>> pProvider);

    IBookBuilder shouldSpawnWithBook ();

    Component getTitle ();
    Component getHeader ();
    Component getItemName ();
    Component getAuthor ();

    ResourceLocation getId ();

    Color getColor();

}
