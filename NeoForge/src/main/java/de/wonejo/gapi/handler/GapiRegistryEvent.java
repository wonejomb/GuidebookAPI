package de.wonejo.gapi.handler;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.impl.service.Services;
import de.wonejo.gapi.item.GuideItem;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.RegisterEvent;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class GapiRegistryEvent {

    @SubscribeEvent
    public static void onRegisterEvent (RegisterEvent pEvent) {
        if (!pEvent.getRegistryKey().equals(Registries.ITEM)) return;

        for (IBook book : Services.BOOK_REGISTRY.getLoadedBooks()) {
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, book.id().toString().replace(":", "."));
            pEvent.register(Registries.ITEM, id, () -> new GuideItem(book));
            Services.BOOK_REGISTRY.getBookToStacks().put(book, () -> new ItemStack(BuiltInRegistries.ITEM.get(id)));
        }
    }

}
