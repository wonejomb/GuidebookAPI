package de.mrbunny.guidebook.handler;

import de.mrbunny.guidebook.api.GuidebookAPI;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.util.References;
import de.mrbunny.guidebook.cfg.ModConfigManager;
import de.mrbunny.guidebook.cfg.ModConfigurations;
import de.mrbunny.guidebook.item.GuidebookItem;
import de.mrbunny.guidebook.util.APIUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

@Mod.EventBusSubscriber( modid = References.GUIDEBOOKAPI_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ItemRegistryHandler {

    @SubscribeEvent
    public static void registerItems (RegisterEvent pEvent) {
        if ( !pEvent.getRegistryKey().equals(Registries.ITEM)) return;

        AnnotationHandler.gatherBooks();

        for (IBook book : GuidebookAPI.getBooks().values()) {
            ResourceLocation id = new ResourceLocation(References.GUIDEBOOKAPI_ID, book.getId().toString().replace(":", "-"));
            pEvent.register(Registries.ITEM, id, () -> new GuidebookItem(book));
            APIUtils.setBookForStack(book, () -> new ItemStack(BuiltInRegistries.ITEM.get(id)));
        }

    }
}
