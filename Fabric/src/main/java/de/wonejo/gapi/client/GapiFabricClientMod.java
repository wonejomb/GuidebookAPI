package de.wonejo.gapi.client;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.item.IBookItem;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.cfg.ModConfigurations;
import de.wonejo.gapi.client.item.GuideItemModel;
import de.wonejo.gapi.impl.service.Services;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.world.item.ItemStack;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class GapiFabricClientMod implements ClientModInitializer {

    public void onInitializeClient() {
        ModelLoadingPlugin.register(this::onModelLoadingPluginLoad);
        ClientLifecycleEvents.CLIENT_STARTED.register(this::finalizeLoad);
    }

    private void finalizeLoad (Minecraft pMc) {
        for (Supplier<ItemStack> bookStack : Services.BOOK_REGISTRY.getBookToStacks().values() ) {

            ColorProviderRegistry.ITEM.register((stack, tint) -> {
                IBookItem item = (IBookItem) stack.getItem();

                if ( item.get() != null && !item.get().useCustomBookModel() && tint == 0 )
                    return ModConfigurations.CLIENT_PROVIDER.getBookColors().get(item.get()).get().getRGB();

                return -1;
            }, bookStack.get().getItem());

        }
    }

    private void onModelLoadingPluginLoad(ModelLoadingPlugin.@NotNull Context pPluginContext) {
        pPluginContext.modifyModelAfterBake().register((bakedModel, context) -> {
            for (IBook book : Services.BOOK_REGISTRY.getLoadedBooks()) {
                pPluginContext.addModels(book.modelLocation());

                if ( context.resourceId().getNamespace().equals(Constants.MOD_ID) & context.topLevelId().getVariant().equals(ModelResourceLocation.INVENTORY_VARIANT) && !book.useCustomBookModel() )
                    return new GuideItemModel(bakedModel, book);
            }

            return bakedModel;
        });
    }


}
