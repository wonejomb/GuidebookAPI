package de.mrbunny.guidebook.api;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import de.mrbunny.guidebook.api.book.IBook;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class GuidebookAPI {

    private static final Map<ResourceLocation, IBook> BOOKS = Maps.newHashMap();
    private static final Map<IBook, Supplier<ItemStack>> BOOK_TO_STACK = Maps.newHashMap();
    private static final List<IBook> indexedBooks = Lists.newArrayList();

    public static ItemStack getStackFromBook ( IBook pBook ) {
        return BOOK_TO_STACK.get(pBook) == null ? ItemStack.EMPTY : BOOK_TO_STACK.get(pBook).get();
    }

    public static Map<ResourceLocation, IBook> getBooks () {
        return ImmutableMap.copyOf(BOOKS);
    }

    public static Map<IBook, Supplier<ItemStack>> getBookToStack () {
        return ImmutableMap.copyOf(BOOK_TO_STACK);
    }

    public static List<IBook> getIndexedBooks () {
        return ImmutableList.copyOf(indexedBooks);
    }
}
