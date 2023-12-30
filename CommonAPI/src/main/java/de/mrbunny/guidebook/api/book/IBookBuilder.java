package de.mrbunny.guidebook.api.book;

import net.minecraft.network.chat.Component;

import java.awt.*;
import java.util.function.Consumer;

public interface IBookBuilder {

    IBook build();

    IBookBuilder setHeader ( Component pHeader );
    IBookBuilder setItemName ( Component pItemName );
    IBookBuilder setAuthor ( Component pAuthor );
    IBookBuilder setColor ( Color pColor );
    IBookBuilder contentProvider (Consumer<? extends IBookContentProvider> pProvider);

    Component getHeader ();
    Component getItemName ();
    Component getAuthor ();

    IBookContentProvider getContentProvider ();

    Color getColor();

}
