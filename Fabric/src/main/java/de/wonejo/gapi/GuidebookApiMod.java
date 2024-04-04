package de.wonejo.gapi;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.client.ClientHandler;
import de.wonejo.gapi.config.ModConfigurations;
import de.wonejo.gapi.handler.WorldEventHandler;
import de.wonejo.gapi.item.BookItem;
import de.wonejo.gapi.registry.BookRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class GuidebookApiMod implements ModInitializer {

    public void onInitialize() {
        BookRegistry.registerAllBooks();

        ModConfigurations.setupConfigurations();

        this.registerGuideItems();

        ModelLoadingPlugin.register(new ClientHandler());

        WorldEventHandler.onPlayerJoin();

    }

    private void registerGuideItems () {
        for ( IBook book : BookRegistry.getLoadedBooks() ) {
            ResourceLocation id = new ResourceLocation(Constants.MOD_ID, book.id().toString().replace(":", "."));

            BookItem item = new BookItem(book);

            Registry.register(BuiltInRegistries.ITEM, id, item);
            BookRegistry.getBookToStack().put(book, () -> new ItemStack(item));
        }
    }

}
