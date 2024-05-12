package de.wonejo.gapi.impl.book.component;

import de.wonejo.gapi.api.book.components.IPage;
import de.wonejo.gapi.api.client.render.IPageRender;

public class Page implements IPage {

    private final IPageRender render;

    public Page (IPageRender pRender ) {
        this.render = pRender;
    }

    public IPageRender getRender () {
        return this.render;
    }

}
