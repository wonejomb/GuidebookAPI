package de.wonejo.wuidebook.api.book;

import de.wonejo.wuidebook.api.book.content.BookContent;

public interface BuiltBook {

    BookContent getContent ();
    BookInformation getInformation ();

}
