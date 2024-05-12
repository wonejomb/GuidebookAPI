package de.wonejo.gapi.api.service;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.impl.service.Services;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Please do NOT use this service in any other mod, that will crash the game because the {@link IBookRegistryHelper#securityCheck()}
 */
public interface IBookRegistryHelper {

    void gatherBooks ( );

    List<IBook> getLoadedBooks ();
    Map<IBook, Supplier<ItemStack>> getBookToStacks ();
    ItemStack getBookItem ( IBook pBook );

    default void securityCheck () {
        if (!Services.PLATFORM.getActiveNamespace().equals(Constants.MOD_ID))
            throw new RuntimeException("Mod %s tried to call a internal registry of GuidebookApi. Report this to the developer of the mod.".formatted(Services.PLATFORM.getActiveNamespace()));
    }
}
