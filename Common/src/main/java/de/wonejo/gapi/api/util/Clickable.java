package de.wonejo.gapi.api.util;

import de.wonejo.gapi.api.book.IBook;

public interface Clickable {

    default void onClick (IBook pBook, double pMouseX, double pMouseY, int pClickType) {};

}
