package de.mrbunny.guidebook.api.book.component;


import de.mrbunny.guidebook.api.client.book.IPageRender;

public interface IPage {

    IPageRender getRender ();

    default void init () {}
}
