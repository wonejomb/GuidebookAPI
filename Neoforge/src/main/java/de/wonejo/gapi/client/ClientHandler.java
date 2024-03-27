package de.wonejo.gapi.client;

import de.wonejo.gapi.api.IGuidebook;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.registry.BookRegistry;
import de.wonejo.gapi.api.util.Constants;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ModelEvent;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = Constants.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientHandler {

    @SubscribeEvent
    public static void onRegisterModel (ModelEvent.RegisterAdditional pEvent) {
        for ( IBook book : BookRegistry.getLoadedBooks().values() ) {
            ResourceLocation id = book.modelLocation();

            if ( id != null )
                pEvent.register(new ModelResourceLocation(id, "inventory"));
        }
    }

    @SubscribeEvent
    public static void onBakeModel (ModelEvent.ModifyBakingResult pEvent) {
        for  ( IBook book : BookRegistry.getLoadedBooks().values() ) {
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
