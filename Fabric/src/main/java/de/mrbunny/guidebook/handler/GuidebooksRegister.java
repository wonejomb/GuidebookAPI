package de.mrbunny.guidebook.handler;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import de.mrbunny.guidebook.api.GuidebookAPI;
import de.mrbunny.guidebook.api.IGuidebook;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.util.References;
import de.mrbunny.guidebook.util.APIUtils;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GuidebooksRegister {
    private static final List<Pair<IGuidebook, IBook>> REGISTERED_BOOKS = Lists.newArrayList();
    private static final Logger LOGGER = LoggerFactory.getLogger(References.GUIDEBOOKAPI_ID + "/GuidebooksRegister");

    public static void gatherBooks () {
        FabricLoader fabricLoader = FabricLoader.getInstance();
        List<IGuidebook> guideBooks = fabricLoader.getEntrypointContainers("guidebookapi", IGuidebook.class)
                .stream().map(EntrypointContainer::getEntrypoint).toList();

        for ( IGuidebook guidebook : guideBooks ) {
            LOGGER.debug("Registering book: " + guidebook.buildBook().getId());

            IBook book = guidebook.buildBook().build();
            if ( book == null ) continue;

            APIUtils.registerBook(book);
            REGISTERED_BOOKS.add(Pair.of(guidebook, book));
        }

        APIUtils.setIndexedBooks(Lists.newArrayList(GuidebookAPI.getBooks().values()));
    }

    public static List<Pair<IGuidebook, IBook>> getRegisteredBooks () {
        return ImmutableList.copyOf(REGISTERED_BOOKS);
    }
}
