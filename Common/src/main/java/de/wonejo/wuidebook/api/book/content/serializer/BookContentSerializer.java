package de.wonejo.wuidebook.api.book.content.serializer;

import de.wonejo.wuidebook.api.book.content.BookContent;

public interface BookContentSerializer<T extends BookContent.ContentElement> {
    String serialize ( T pType );

    T deserialize ( String pValue );
}
