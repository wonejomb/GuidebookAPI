package de.wonejo.gapi.api.book.components;

import de.wonejo.gapi.api.client.render.IPageRender;
import de.wonejo.gapi.api.util.BookAccessible;

public interface IBookPage extends BookAccessible {

    void init ();

    default IPageRender render () { return (IPageRender) this; }
}
