package de.mrbunny.guidebook.handler;


import de.mrbunny.guidebook.api.GuidebookAPI;
import de.mrbunny.guidebook.api.IGuidebook;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.util.References;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ModelEvent;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber( value = Dist.CLIENT, modid = References.GUIDEBOOKAPI_ID, bus = Mod.EventBusSubscriber.Bus.MOD )
public class ClientModelHandler {

    @SubscribeEvent
    public static void onRegisterModels (ModelEvent.RegisterAdditional pEvent) {
        for ( Pair<IBook, IGuidebook> guide : AnnotationHandler.BOOK_CLASSES ) {
            ResourceLocation id = guide.getRight().getModel();

            if ( id != null ) {
                pEvent.register(new ModelResourceLocation(id, "inventory"));
            }
        }
    }

    @SubscribeEvent
    public static void onBakeModel ( ModelEvent.ModifyBakingResult pEvent ) {
        for ( Pair<IBook, IGuidebook> guide : AnnotationHandler.BOOK_CLASSES )  {
            ResourceLocation id = guide.getRight().getModel();

            if ( id != null ) {
                ModelResourceLocation newMrl = new ModelResourceLocation(id, "inventory");
                Item item = GuidebookAPI.getStackFromBook(guide.getLeft()).getItem();
                ModelResourceLocation oldMrl = new ModelResourceLocation(BuiltInRegistries.ITEM.getKey(item), "inventory");
                BakedModel model = pEvent.getModels().get(newMrl);

                pEvent.getModels().put(oldMrl, model);
            }
        }
    }

}
