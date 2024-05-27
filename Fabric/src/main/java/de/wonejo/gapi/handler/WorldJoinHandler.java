package de.wonejo.gapi.handler;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.cfg.ModConfigurations;
import de.wonejo.gapi.ext.IGuidebookDataExtension;
import de.wonejo.gapi.impl.service.Services;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;

public class WorldJoinHandler {

    public static void onPlayerJoin () {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if ( !entity.level().isClientSide && entity instanceof Player player ) {
                CompoundTag initialBooks = getInitialBooksTag(player);

                if ( ModConfigurations.COMMON_PROVIDER.shouldSpawnWithBook()) {
                    for ( IBook book : Services.BOOK_REGISTRY.getLoadedBooks() ) {
                        boolean bookShouldSpawn = ModConfigurations.COMMON_PROVIDER.getSpawnBooks().get(book).get();

                        if (bookShouldSpawn && !initialBooks.getBoolean("%s.%s".formatted(book.id().getNamespace(), book.id().getPath())))  {
                            player.getInventory().add(Services.BOOK_REGISTRY.getBookItem(book));
                            initialBooks.putBoolean("%s.%s".formatted(book.id().getNamespace(), book.id().getPath()), true);
                        }
                    }
                }
            }
        });
    }

    private static @NotNull CompoundTag getInitialBooksTag (Player pPlayer) {
        IGuidebookDataExtension extension = (IGuidebookDataExtension) pPlayer;

        CompoundTag tag = extension.getGuidebookData ();
        CompoundTag initBooks;
        if ( tag.contains(Constants.NBT_INITIAL_BOOKS) )
            initBooks = tag.getCompound(Constants.NBT_INITIAL_BOOKS);
        else {
            initBooks = new CompoundTag();
            tag.put(Constants.NBT_INITIAL_BOOKS, initBooks);
        }

        return initBooks;
    }
}
