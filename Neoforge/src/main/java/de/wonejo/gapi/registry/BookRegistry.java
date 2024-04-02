package de.wonejo.gapi.registry;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import de.wonejo.gapi.api.GuidebookAPI;
import de.wonejo.gapi.api.IGuidebook;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.util.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforgespi.language.ModFileScanData;
import org.apache.commons.compress.utils.Lists;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;
import org.objectweb.asm.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.util.*;
import java.util.function.Supplier;

public class BookRegistry {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookRegistry.class);

    private static final List<IBook> LOADED_BOOKS = Lists.newArrayList();
    private static final Map<IBook, Supplier<ItemStack>> BOOK_TO_STACK = Maps.newHashMap();

    private static final Type GUIDEBOOK = Type.getType(GuidebookAPI.class);

    public static void registerAllBooks ()  {
        securityCheck();

        final List<ModFileScanData.AnnotationData> annotations = ModList.get().getAllScanData().stream()
                .map(ModFileScanData::getAnnotations)
                .flatMap(Collection::stream)
                .filter(a -> GUIDEBOOK.equals(a.annotationType()))
                .toList();

        for ( ModFileScanData.AnnotationData data : annotations ) {
            try {
                Class<?> genericClass = Class.forName(data.clazz().getClassName());
                if (!IGuidebook.class.isAssignableFrom(genericClass)) continue;
                Constructor<?>[] constructors = genericClass.getConstructors();
                var constructor= constructors[0];
                IGuidebook guide = (IGuidebook) constructor.newInstance();
                IBook book = guide.build().build();
                if ( book == null ) continue;
                LOADED_BOOKS.add(book);
            } catch (Exception pException) {
                LOGGER.error("Error in register book for class: {}", data.clazz().getClassName());
            }
        }
    }

    private static void securityCheck () {
        String modId = ModLoadingContext.get().getActiveNamespace();

        if (!modId.equals(Constants.MOD_ID))
            try {
                throw new IllegalAccessException("Mod %s tried to call a internal registry method of GuidebookAPI. Report this to the developer of the mod.");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
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
