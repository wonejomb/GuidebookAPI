package de.wonejo.gapi.handler;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.item.BookItem;
import de.wonejo.gapi.registry.BookRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModRegistryHandler {


    @SubscribeEvent
    public static void registerItems (RegisterEvent pEvent)  {
        if ( !pEvent.getRegistryKey().equals(Registries.ITEM) ) return;

        BookRegistry.registerAllBooks();

        for (IBook book : BookRegistry.getLoadedBooks()) {
            ResourceLocation id = new ResourceLocation(Constants.MOD_ID, book.id().toString().replace(":", "."));
            pEvent.register(Registries.ITEM, id, ()-> new BookItem(book));
            BookRegistry.getBookToStack().put(book, () -> new ItemStack(BuiltInRegistries.ITEM.get(id)));
        }
    }
}
