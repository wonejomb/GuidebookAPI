package de.mrbunny.guidebook.book.component;

import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.api.book.component.IPageHolder;

import java.awt.*;

public class PageHolder implements IPageHolder {

    private final IBook book;
    private final IBookEntry entry;
    private final Color colorDiscriminator;
    private final int pageId;

    public PageHolder ( IBook pBook, IBookEntry pEntry, Color pColorDiscriminator, int pPageId ) {
        this.book = pBook;
        this.entry = pEntry;
        this.colorDiscriminator = pColorDiscriminator;
        this.pageId = pPageId;
    }

    public PageHolder  ( IBook pBook, IBookEntry pEntry, Color pColorDiscriminator ) {
        this ( pBook, pEntry, pColorDiscriminator, 0 );
    }

    public PageHolder ( IBook pBook, IBookEntry pEntry ) {
        this ( pBook, pEntry, new Color(255, 255, 255));
    }

    public IBook getBook() {
        return this.book;
    }

    public Color getDiscriminatorColor() {
        return this.colorDiscriminator;
    }

    public IBookEntry getHolderEntry() {
        return this.entry;
    }

    public int getPageNumber() {
        return this.pageId;
    }
}
