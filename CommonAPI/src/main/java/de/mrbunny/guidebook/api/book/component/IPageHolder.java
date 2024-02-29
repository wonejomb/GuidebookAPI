package de.mrbunny.guidebook.api.book.component;

import de.mrbunny.guidebook.api.book.IBook;
import net.minecraft.network.chat.Component;

import java.awt.*;

public interface IPageHolder {

    IBook getBook ();
    Component getTitle ();
    IBookEntry getHolderEntryReference ();
    Color getColorDiscriminator ();
    int getPageId();
}
