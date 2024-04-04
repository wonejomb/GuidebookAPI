package de.wonejo.gapi.client;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.client.model.GuideModel;
import de.wonejo.gapi.registry.BookRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class ClientHandler implements ModelLoadingPlugin {

    public void onInitializeModelLoader(Context pPluginContext) {
        for  (IBook book : BookRegistry.getLoadedBooks()) {
            ResourceLocation modelLocation = book.modelLocation();

            if ( modelLocation != null )
                pPluginContext.addModels(new ModelResourceLocation(modelLocation, "inventory"));

            pPluginContext.modifyModelAfterBake().register((pOldModel, pContext) -> {
                if ( pContext.id().getNamespace().equals(Constants.MOD_ID) && pContext.id() instanceof ModelResourceLocation key && key.getVariant().equals("inventory") && pOldModel != null ) {
                    return new GuideModel(book, pOldModel, pContext.loader());
                }

                return pOldModel;
            });
        }
    }

}
