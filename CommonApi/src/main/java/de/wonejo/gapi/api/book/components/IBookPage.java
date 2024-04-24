package de.wonejo.gapi.api.book.components;

import de.wonejo.gapi.api.client.render.IPageRender;
import de.wonejo.gapi.api.util.BookAccessible;
import de.wonejo.gapi.api.util.Clickable;
import de.wonejo.gapi.api.util.ITick;

public interface IBookPage extends BookAccessible, Clickable, ITick {

    IPageRender render ();

}
