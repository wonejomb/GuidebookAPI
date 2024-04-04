package de.wonejo.gapi.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import de.wonejo.gapi.api.IGuidebook;
import de.wonejo.gapi.api.book.IBook;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BookRegistry {

    private static final List<IBook> LOADED_BOOKS = Lists.newArrayList();
    private static final Map<IBook, Supplier<ItemStack>> BOOK_TO_STACK = Maps.newHashMap();

    public static void registerAllBooks ()  {
        List<EntrypointContainer<IGuidebook>> entrypointContainers = FabricLoader.getInstance().getEntrypointContainers("guidebookapi", IGuidebook.class);

        for ( EntrypointContainer<IGuidebook> guidebook : entrypointContainers ) {
            IBook book = guidebook.getEntrypoint().build().build();
            if (book == null) continue;
            LOADED_BOOKS.add(book);
        }
    }


    public static @NotNull @Unmodifiable List<IBook> getLoadedBooks () {
        return ImmutableList.copyOf(LOADED_BOOKS);
    }

    public static Map<IBook, Supplier<ItemStack>> getBookToStack() {
        return BOOK_TO_STACK;
    }

    public static ItemStack getBookItem ( IBook pBook ) {
        return BOOK_TO_STACK.get(pBook) == null ? ItemStack.EMPTY : BOOK_TO_STACK.get(pBook).get();
    }
}
