package de.wonejo.gapi.handler;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.client.model.GuideModel;
import de.wonejo.gapi.impl.service.Services;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

public class GapiClientHandler implements ModelLoadingPlugin {

    public void onInitializeModelLoader(Context pluginContext) {
        for (IBook book : Services.BOOK_REGISTRY.getLoadedBooks()) {
            ResourceLocation modelLocation = book.modelLocation();

            if (modelLocation != null)
                pluginContext.addModels(new ModelResourceLocation(modelLocation, "inventory"));

            pluginContext.modifyModelAfterBake().register((pOldModel, pContext) -> {
                if ( pContext.id().getNamespace().equals(Constants.MOD_ID) && pContext.id() instanceof ModelResourceLocation key && key.getVariant().equals("inventory") && pOldModel != null ) {
                    return new GuideModel (book, pOldModel, pContext.loader());
                }

                return pOldModel;
            });
        }
    }

}
