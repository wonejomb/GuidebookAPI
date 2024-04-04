package de.wonejo.gapi.book;

import de.wonejo.gapi.api.book.components.IBookPage;
import de.wonejo.gapi.api.client.render.IPageRender;

public class BookPage implements IBookPage {

    private final IPageRender render;

    public BookPage ( IPageRender pRender ) {
        this.render = pRender;
    }

    public IPageRender render() {
        return this.render;
    }

}
