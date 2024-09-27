package de.wonejo.wuidebook.api.book;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import de.wonejo.wuidebook.api.util.Constants;
import de.wonejo.wuidebook.api.util.WuidebookRegistryException;
import de.wonejo.wuidebook.impl.service.ModServices;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;

public final class BookRegistry {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Map<ResourceLocation, Pair<BookInfo, ResourceLocation>> bookInformation = Maps.newHashMap();
    private final Map<ResourceLocation, Book> books = Maps.newHashMap();
    private boolean areBooksBuilt;

    private BookRegistry () {}

    @ApiStatus.Internal
    public void buildBooks () {
        if (this.areBooksBuilt) return;
        if (!ModServices.ABSTRACTION.getCurrentLoadedModId().equals(Constants.MOD_ID)) throw new RuntimeException("Can not use Wuidebook buildBooks on other mods code!");

        this.areBooksBuilt = true;
    }

    public void registerBook ( @NotNull BookInfo pInfo, ResourceLocation pBaseFileLocation ) {
        if ( this.bookInformation.putIfAbsent(pInfo.bookId(), Pair.of(pInfo, pBaseFileLocation)) != null )
            throw new WuidebookRegistryException("Can not register two ( or more ) book information with id: " + pInfo.bookId() + ", it wouldn't be registered!");
    }

    public @NotNull Book getBook (ResourceLocation pBookId ) {
        Book book = this.books.get(pBookId);
        if (!areBooksBuilt) throw new RuntimeException("Can not get wuidebook guide, books aren't build.");
        if ( book == null ) throw new RuntimeException("Can not get book with id: " + pBookId + " because it isn't present.");
        return book;
    }

    public Optional<Book> getOptBook ( ResourceLocation pBookId ) {
        Book book = this.books.get(pBookId);
        if (!areBooksBuilt) throw new RuntimeException("Can not get wuidebook guide, books aren't build.");
        return Optional.ofNullable(book);
    }

    public BookInfo getInfo ( ResourceLocation pBookId ) {
        Pair<BookInfo, ResourceLocation> pair = this.bookInformation.get(pBookId);
        if ( pair == null ) throw new NullPointerException("Can not get book information, there isn't a book ith id: " + pBookId);
        return pair.getFirst();
    }

    public ResourceLocation getBaseFileLocation ( ResourceLocation pBookId ) {
        Pair<BookInfo, ResourceLocation> pair = this.bookInformation.get(pBookId);
        if ( pair == null ) throw new NullPointerException("Can not get base file location, there isn't a book with id: " + pBookId);
        return pair.getSecond();
    }

}
