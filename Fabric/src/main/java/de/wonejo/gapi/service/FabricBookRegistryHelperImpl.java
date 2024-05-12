package de.wonejo.gapi.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import de.wonejo.gapi.api.IGuidebook;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.service.IBookRegistryHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class FabricBookRegistryHelperImpl implements IBookRegistryHelper {
    private final List<IBook> BOOKS = new ArrayList<>();
    private static final Map<IBook, Supplier<ItemStack>> BOOK_TO_STACK = Maps.newHashMap();

    public void gatherBooks() {
        List<EntrypointContainer<IGuidebook>> entrypointContainers = FabricLoader.getInstance().getEntrypointContainers("guidebook", IGuidebook.class);

        for ( EntrypointContainer<IGuidebook> guidebook : entrypointContainers ) {
            IBook book = guidebook.getEntrypoint().builder().build();
            if (book == null) continue;
            BOOKS.add(book);
        }
    }

    public List<IBook> getLoadedBooks() {
        return ImmutableList.copyOf(BOOKS);
    }

    public Map<IBook, Supplier<ItemStack>> getBookToStacks() {
        return BOOK_TO_STACK;
    }

    public ItemStack getBookItem(IBook pBook) {
        return BOOK_TO_STACK.get(pBook) == null ? ItemStack.EMPTY : BOOK_TO_STACK.get(pBook).get();
    }
}
