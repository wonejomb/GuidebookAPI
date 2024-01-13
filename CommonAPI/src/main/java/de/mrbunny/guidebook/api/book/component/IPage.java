package de.mrbunny.guidebook.api.book.component;


import de.mrbunny.guidebook.api.client.book.IPageRender;

public interface IPage {

    default IPageRender getRender() { return (IPageRender) this; };

    default void init () {}
}
