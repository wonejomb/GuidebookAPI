package de.wonejo.gapi.api.service;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.impl.service.Services;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Please do NOT use this service in any other mod, that will crash the game because the {@link BookRegistryHelper#securityCheck()}
 */
public abstract class BookRegistryHelper {

    private static boolean INIT = false;

    public abstract void gatherBooks ( );

    public abstract List<IBook> getLoadedBooks ();
    public abstract Map<IBook, Supplier<ItemStack>> getBookToStacks ();
    public abstract ItemStack getBookItem ( IBook pBook );

    protected void securityCheck () {
        if (INIT) {
            throw new RuntimeException("Mod %s tried to call a internal registry of GuidebookApi. Report this to the developer of the mod.".formatted(Services.PLATFORM.getActiveNamespace()));
        }
        INIT = true;
    }
}
