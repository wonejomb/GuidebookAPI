package de.wonejo.gapi.api.book.components;

import de.wonejo.gapi.api.client.render.IPageRender;
import de.wonejo.gapi.api.util.Accessible;
import de.wonejo.gapi.api.util.CanView;
import de.wonejo.gapi.api.util.Clickable;

public interface IPage extends Accessible, CanView, Clickable {
    IPageRender getRender ();
}
