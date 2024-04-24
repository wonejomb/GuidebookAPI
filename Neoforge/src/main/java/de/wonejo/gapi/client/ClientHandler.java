package de.wonejo.gapi.client;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.registry.BookRegistry;
import de.wonejo.gapi.api.util.Constants;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;

@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientHandler {

    @SubscribeEvent
    public static void onRegisterModel (ModelEvent.RegisterAdditional pEvent) {
        for ( IBook book : BookRegistry.getLoadedBooks() ) {
            ResourceLocation id = book.modelLocation();

            if ( id != null )
                pEvent.register(new ModelResourceLocation(id, "inventory"));
        }
    }

    @SubscribeEvent
    public static void onBakeModel (ModelEvent.ModifyBakingResult pEvent) {
        for  ( IBook book : BookRegistry.getLoadedBooks() ) {
            ResourceLocation id = book.modelLocation();

            if (id != null) {
                ModelResourceLocation newModel = new ModelResourceLocation(id, "inventory");
                Item item = BookRegistry.getBookItem(book).getItem();
                ModelResourceLocation oldModel = new ModelResourceLocation(BuiltInRegistries.ITEM.getKey(item), "inventory");
                BakedModel model = pEvent.getModels().get(newModel);

                pEvent.getModels().put(oldModel, model);
            }
        }
    }

}
