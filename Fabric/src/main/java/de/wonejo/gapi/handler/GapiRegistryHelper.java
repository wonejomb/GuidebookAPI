package de.wonejo.gapi.handler;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.impl.service.Services;
import de.wonejo.gapi.item.GuideItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GapiRegistryHelper {

    public static void registerGuides () {
        for (IBook book : Services.BOOK_REGISTRY.getLoadedBooks()) {
            ResourceLocation id = new ResourceLocation(Constants.MOD_ID, book.id().toString().replace(":", "."));
            Registry.<Item, Item>register(BuiltInRegistries.ITEM, id, new GuideItem(book));
            Services.BOOK_REGISTRY.getBookToStacks().put(book, () -> new ItemStack(BuiltInRegistries.ITEM.get(id)));
        }
    }

}
