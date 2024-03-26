package de.wonejo.gapi.api.registry.json.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import de.wonejo.gapi.api.registry.data.BookData;

import java.io.IOException;
import java.util.List;

public final class BookDataTypeAdapter extends TypeAdapter<BookData> {

    public BookData read(JsonReader in) throws IOException {
        String clazzName = null;
        String id = null;

        in.beginObject();
        while (in.hasNext()) {
            String name = in.nextName();

            if ( name.equals("bookClass") )
                clazzName = in.nextString();
            else if ( name.equals("bookId") )
                id = in.nextString();
            else
                in.skipValue();
        }

        in.endObject();

        return new BookData(clazzName, id);
    }

    public void write(JsonWriter out, BookData value) throws IOException {
        out.beginObject();
        out.name("bookClass").value(value.getClazz().getName());
        out.name("bookId").value(value.getId());
    }
}
