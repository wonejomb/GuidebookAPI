package de.wonejo.gapi.handler;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.client.color.ItemColorHandler;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.impl.service.Services;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModelHandler {

    @SubscribeEvent
    public static void onRegisterModel (ModelEvent.RegisterAdditional pEvent) {
        for ( IBook book : Services.BOOK_REGISTRY.getLoadedBooks() ) {
            ResourceLocation id = book.modelLocation();

            if ( id != null )
                pEvent.register(new ModelResourceLocation(id, "inventory"));
        }
    }

    @SubscribeEvent
    public static void onBakeModel (ModelEvent.ModifyBakingResult pEvent) {
        for  ( IBook book : Services.BOOK_REGISTRY.getLoadedBooks() ) {
            ResourceLocation id = book.modelLocation();

            if (id != null) {
                ModelResourceLocation newModel = new ModelResourceLocation(id, "inventory");
                Item item = Services.BOOK_REGISTRY.getBookItem(book).getItem();
                ModelResourceLocation oldModel = new ModelResourceLocation(BuiltInRegistries.ITEM.getKey(item), "inventory");
                BakedModel model = pEvent.getModels().get(newModel);

                pEvent.getModels().put(oldModel, model);
            }
        }
    }

    @SubscribeEvent
    public static void registerItemColors (RegisterColorHandlersEvent.@NotNull Item pEvent) {
        ItemColorHandler.submitItems(pEvent::register);
    }

}
