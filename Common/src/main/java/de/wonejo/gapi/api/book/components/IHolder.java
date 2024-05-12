package de.wonejo.gapi.api.book.components;

import de.wonejo.gapi.api.client.render.IHolderRender;

public interface IHolder {
    IEntry entryReference ();
    int pageIndex ();

    IHolderRender getRender ();
}
