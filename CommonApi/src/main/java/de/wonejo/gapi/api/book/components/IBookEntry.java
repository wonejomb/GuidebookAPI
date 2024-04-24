package de.wonejo.gapi.api.book.components;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.client.render.IEntryRender;
import de.wonejo.gapi.api.util.BookAccessible;
import de.wonejo.gapi.api.util.Clickable;

import java.util.List;

public interface IBookEntry extends BookAccessible, Clickable {

    IEntryRender render ();

    List<IBookPage> pages ();

    void addPage ( IBookPage pPage );
    void addPages (List<IBookPage> pPages);
    void addPages ( IBookPage... pPages );

    default void onClick(IBook pBook, IBookCategory pCategory, double pMouseX, double pMouseY, int pClickType) {
        this.onClick(pBook, pMouseX, pMouseY, pClickType);
    }

    IBookPage getPage (int pIndex);
}
