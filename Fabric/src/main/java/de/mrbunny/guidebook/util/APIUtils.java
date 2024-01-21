package de.mrbunny.guidebook.util;

import de.mrbunny.guidebook.api.GuidebookAPI;
import de.mrbunny.guidebook.api.IGuidebook;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.util.References;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public class APIUtils {

    public static void registerBook ( IBook pBook ) {
        securityCheck();

        try {

            Field books = GuidebookAPI.class.getDeclaredField("BOOKS");
            books.setAccessible(true);
            Map<ResourceLocation, IBook> BOOKS = (Map<ResourceLocation, IBook>) books.get(null);
            BOOKS.put(pBook.getId(), pBook);
        } catch ( Exception pException ) {
            pException.printStackTrace();
        }
    }

    public static void setBookForStack (IBook pBook, Supplier<ItemStack> pStack) {
        securityCheck();

        try {
            Field stacks = GuidebookAPI.class.getDeclaredField("BOOK_TO_STACK");
            stacks.setAccessible(true);
            Map<IBook, Supplier<ItemStack>> BOOK_TO_STACK = (Map<IBook, Supplier<ItemStack>>) stacks.get(null);
            BOOK_TO_STACK.put(pBook, pStack);
        } catch ( Exception pException ) {
            pException.printStackTrace();
        }
    }

    public static void setIndexedBooks (List<IBook> pBooks) {
        securityCheck();

        try {
            Field indexedBooks = GuidebookAPI.class.getDeclaredField("indexedBooks");
            indexedBooks.setAccessible(true);
            List<IBook> list = (List<IBook>) indexedBooks.get(null);
            list.addAll(pBooks);
        } catch ( Exception pException ) {
            pException.printStackTrace();
        }
    }

    private static void securityCheck () {
        try {
            securityCheckInternal();
        } catch (IllegalAccessException pException) {
            throw new RuntimeException(pException);
        }
    }

    private static void securityCheckInternal ( ) throws IllegalAccessException {
        for (ModContainer container : FabricLoader.getInstance().getAllMods()) {

        }
    }
}
