package de.wonejo.gapi.api.registry.json.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import de.wonejo.gapi.api.registry.data.BookData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class ListBookDataTypeAdapter extends TypeAdapter<List<BookData>> {

    public void write(JsonWriter out, List<BookData> value) throws IOException {
        out.beginArray();
        for ( BookData data : value ) {
            out.beginObject();
            out.name("bookClass").value(data.getClazz().getName());
            out.name("bookId").value(data.getId());
            out.endArray();
        }
        out.endArray();
    }

    public List<BookData> read(JsonReader in) throws IOException {
        List<BookData> books = new ArrayList<>();

        in.beginArray();
        while (in.hasNext()) {
            in.beginObject();
            String clazz = null;
            String id = null;

            while (in.hasNext()) {
                String name = in.nextName();

                if(name.equals("bookClass"))
                    clazz = in.nextString();
                if (name.equals("bookId"))
                    id = in.nextString();
                else
                    in.skipValue();

                in.endObject();
            }
            in.endObject();

            books.add(new BookData(clazz, id));
        }

        return books;
    }

}
