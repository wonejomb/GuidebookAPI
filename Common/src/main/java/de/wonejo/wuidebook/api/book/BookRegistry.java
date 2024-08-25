package de.wonejo.wuidebook.api.book;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import de.wonejo.wuidebook.WuidebookCommon;
import de.wonejo.wuidebook.api.logger.DebugLogger;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BookRegistry {
    private static BookRegistry INSTANCE;
    private final List<ResourceLocation> bookIds = Lists.newArrayList();
    private final Map<ResourceLocation, BuiltBook> books = Maps.newHashMap();

    private BookRegistry () {}

    public void registerBook ( ResourceLocation pId ) {
        if ( this.bookIds.contains(pId) ) {
            DebugLogger.log("WuidebookAPI have already a book with the ID: '{}'. WuidebookAPI will do not add the second book with id: '{}'", pId, pId);
            return;
        }

        this.bookIds.add(pId);
    }

    public void addBuildedBook (BuiltBook pBuiltBook) {
        if ( !WuidebookCommon.get().getAbstraction().getActiveNamespace().equals(WuidebookCommon.MOD_ID) )
            throw new IllegalStateException("Any other mod can register a builded book except WuidebookAPI!");

        if ( this.books.putIfAbsent(pBuiltBook.getDefaultInformation().id(), pBuiltBook) != null )
            throw new IllegalStateException("Can not add a built book to the registry because there is other book with the id: " + pBuiltBook.getDefaultInformation().id());
    }

    public BuiltBook getBookById (ResourceLocation pId ) {
        return this.books.get(pId);
    }

    public List<ResourceLocation> getAllBookIds () {
        return ImmutableList.copyOf(bookIds);
    }

    public Set<BuiltBook> getAllBuiltBooks() {
        return ImmutableSet.copyOf(this.books.values());
    }

    public static BookRegistry get () {
        if ( BookRegistry.INSTANCE == null ) BookRegistry.INSTANCE = new BookRegistry();
        return BookRegistry.INSTANCE;
    }
}
