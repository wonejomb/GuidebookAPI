package de.wonejo.gapi.handler;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.registry.BookRegistry;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.item.BookItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRegistryHandler {


    @SubscribeEvent
    public static void registerItems (RegisterEvent pEvent)  {
        if ( !pEvent.getRegistryKey().equals(Registries.ITEM) ) return;

        BookRegistry.registerAllBooks();

        for (IBook book : BookRegistry.getLoadedBooks().values()) {
            ResourceLocation id = new ResourceLocation(Constants.MOD_ID, book.id().toString().replace(":", "."));
            pEvent.register(Registries.ITEM, id, ()-> new BookItem(book));
            BookRegistry.getBookToStack().put(book, () -> new ItemStack(BuiltInRegistries.ITEM.get(id)));
        }
    }
}
