package de.wonejo.gapi.api.registry;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.IBookBuilder;
import de.wonejo.gapi.api.registry.data.BookData;
import de.wonejo.gapi.api.registry.json.adapter.BookDataTypeAdapter;
import de.wonejo.gapi.api.registry.json.adapter.ListBookDataTypeAdapter;
import de.wonejo.gapi.api.util.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BookRegistry {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookRegistry.class);

    private static final Gson GSON = (new GsonBuilder())
            .registerTypeAdapter(BookData.class, new BookDataTypeAdapter())
            .registerTypeAdapter(new TypeToken<List<BookData>>(){}.getType(), new ListBookDataTypeAdapter())
            .create();

    private static final Map<String, BookData> REGISTRY = Maps.newConcurrentMap();
    private static final Map<ResourceLocation, IBook> LOADED_BOOKS = Maps.newConcurrentMap();
    private static volatile boolean loaded = false;

    public static void parseAllBooks (@NotNull ResourceManager pManager ) {
        if (loaded) return;

        LOADED_BOOKS.clear();

        Set<BookData> toLoad = new HashSet<>(REGISTRY.values());

        for ( String domain : pManager.getNamespaces() ) {
            try {
                List<Resource> resources = pManager.getResourceStack(new ResourceLocation(domain, "gapi_books.json"));

                for ( Resource res : resources ) {
                    parseBookData(toLoad, res);
                }
            } catch (IOException ignore) {
                LOGGER.error("Error loading books from resource packs");
            }

        }

        for ( BookData bookData : toLoad ) {
            ResourceLocation id = new ResourceLocation(Constants.MOD_ID, bookData.getId());

            if (!LOADED_BOOKS.containsKey(id)) {
                registerBook( bookData);
            }
        }

        loaded = true;
    }

    private static void parseBookData ( Set<BookData> pToLoad, Resource pResource ) throws IOException {
        try ( InputStream stream = pResource.open() ) {
            List<BookData> bookDataList = GSON.fromJson(new InputStreamReader(stream), new TypeToken<List<BookData>>(){}.getType());

            pToLoad.addAll(bookDataList);
        }
    }

    private static void registerBook ( @NotNull BookData pData ) {
        try {
            Constructor<?> constructor = pData.getClazz().getDeclaredConstructor();
            constructor.setAccessible(true);
            IBookBuilder builder = (IBookBuilder) constructor.newInstance();
            IBook book = builder.build();
            ResourceLocation id = new ResourceLocation(Constants.MOD_ID, pData.getId());
            LOADED_BOOKS.put(id, book);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException  pException ) {
            LOGGER.error("Error registering book", pException);
        }
    }

    public static @NotNull @Unmodifiable Map<ResourceLocation, IBook> getLoadedBooks () {
        return ImmutableMap.copyOf(LOADED_BOOKS);
    }

}
