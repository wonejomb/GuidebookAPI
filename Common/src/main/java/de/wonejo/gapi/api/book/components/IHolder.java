package de.wonejo.gapi.api.book.components;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.client.render.IHolderRender;
import de.wonejo.gapi.api.util.Clickable;

public interface IHolder extends Clickable {

    IEntry entryReference ();
    int pageIndex ();

    IHolderRender getRender ();

    void onClick (IBook pBook, ICategory pCategory, double pMouseX, double pMouseY, int pClickType);
}
