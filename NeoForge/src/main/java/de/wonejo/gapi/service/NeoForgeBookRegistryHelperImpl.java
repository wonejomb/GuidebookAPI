package de.wonejo.gapi.service;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import de.wonejo.gapi.api.GuidebookAPI;
import de.wonejo.gapi.api.IGuidebook;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.service.IBookRegistryHelper;
import de.wonejo.gapi.api.util.DebugLogger;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import net.neoforged.neoforgespi.language.ModFileScanData;
import org.apache.commons.compress.utils.Lists;
import org.objectweb.asm.Type;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class NeoForgeBookRegistryHelperImpl implements IBookRegistryHelper {
    private static final List<IBook> BOOKS = Lists.newArrayList();
    private static final Map<IBook, Supplier<ItemStack>> BOOK_TO_STACK = Maps.newHashMap();
    private static final Type GUIDEBOOKAPI = Type.getType(GuidebookAPI.class);

    public void gatherBooks() {
        this.securityCheck();

        final List<ModFileScanData.AnnotationData> annotations = ModList.get().getAllScanData().stream()
                .map(ModFileScanData::getAnnotations)
                .flatMap(Collection::stream)
                .filter(a -> GUIDEBOOKAPI.equals(a.annotationType())).toList();

        if (!annotations.isEmpty()) {
            for ( ModFileScanData.AnnotationData data : annotations ) {
                try {
                    Class<?> genericClass = Class.forName(data.clazz().getClassName());
                    if (!IGuidebook.class.isAssignableFrom(genericClass)) continue;
                    Constructor<?>[] constructors = genericClass.getConstructors();
                    var constructor = constructors[0];
                    IGuidebook guide = (IGuidebook) constructor.newInstance();
                    IBook book = guide.builder().build();
                    if ( book == null ) continue;
                    BOOKS.add(book);
                } catch (Exception pException) {
                    DebugLogger.debug("Error registering guide from class: {}. Exception: {}", data.clazz().getClassName(), pException);
                }
            }
        } else DebugLogger.debug("There aren't guides with the annotation: @GuidebookAPI");
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
