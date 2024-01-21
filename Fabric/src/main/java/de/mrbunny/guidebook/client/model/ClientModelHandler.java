package de.mrbunny.guidebook.client.model;

import de.mrbunny.guidebook.api.IGuidebook;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.util.References;
import de.mrbunny.guidebook.handler.GuidebooksRegister;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class ClientModelHandler implements ModelLoadingPlugin {

    public void onInitializeModelLoader(Context pluginContext) {
        for (Pair<IGuidebook, IBook> pair : GuidebooksRegister.getRegisteredBooks()) {
            ResourceLocation modelLocation = pair.getLeft().getModelLocation();

            if  ( modelLocation != null ) {
                pluginContext.addModels(new ModelResourceLocation(modelLocation, "inventory"));
            }

            pluginContext.modifyModelAfterBake().register(
                    (@Nullable BakedModel pOldModel, ModelModifier.AfterBake.Context pCtx) -> {
                        if ( pCtx.id().getNamespace().equals(References.GUIDEBOOKAPI_ID)
                                && pCtx.id() instanceof ModelResourceLocation key
                                && key.getVariant().equals("inventory")
                                && pOldModel != null )
                            return new GuidebookModel(pair.getLeft(), pair.getRight(), pOldModel, pCtx.loader());

                        return pOldModel;
                    });
        }
    }
}
