package de.wonejo.gapi.api.registry;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.IBookBuilder;
import de.wonejo.gapi.api.registry.data.BookData;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.api.util.Id;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.*;

public class BookRegistry {

    private static final Logger LOGGER = LoggerFactory.getLogger("REGISTRY");

    private static final Gson GSON = (new GsonBuilder()).create();

    private static final Map<String, BookData> REGISTRY = Maps.newHashMap();
    private static final Map<ResourceLocation, IBook> LOADED_BOOKS = Maps.newHashMap();
    private static boolean loaded = false;

    private static final Type gsonListType = new TypeToken<List<BookData>>() {}.getType();

    public static void parseAllBooks (@NotNull ResourceManager pManager ) {
        loaded = true;

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
                String bookId = bookData.getId();

                registerBook(bookId, bookData);
            }
        }
    }

    private static void parseBookData ( Set<BookData> pToLoad, Resource pResource ) throws IOException {
        try ( InputStream stream = pResource.open() ) {
            List<BookData> bookDataList = GSON.fromJson(new InputStreamReader(stream), gsonListType);

            for ( BookData bookData : bookDataList ) {
                REGISTRY.putIfAbsent(bookData.getId(), bookData);
            }
        }
    }

    private static void registerBook (String pId, @NotNull BookData pData ) {
        try {
            IBookBuilder builder = (IBookBuilder) pData.getClazz().getConstructors()[0].newInstance();

            IBook book = builder.build();
            ResourceLocation id = new ResourceLocation(Constants.MOD_ID, pId);

            LOADED_BOOKS.put(id, book);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<ResourceLocation, IBook> getLoadedBooks () {
        if (!loaded)
            parseAllBooks(Minecraft.getInstance().getResourceManager());

        return ImmutableMap.copyOf(LOADED_BOOKS);
    }

}
