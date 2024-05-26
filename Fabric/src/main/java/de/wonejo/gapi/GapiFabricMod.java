package de.wonejo.gapi;

import de.wonejo.gapi.api.book.item.IBookItem;
import de.wonejo.gapi.cfg.ModConfigurations;
import de.wonejo.gapi.handler.GapiClientHandler;
import de.wonejo.gapi.handler.GapiRegistryHelper;
import de.wonejo.gapi.impl.service.Services;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class GapiFabricMod implements ModInitializer {

    public void onInitialize() {
        CommonGapiMod.init();

        GapiRegistryHelper.registerGuides();

        ModelLoadingPlugin.register(new GapiClientHandler());

        for (Supplier<ItemStack> bookStack : Services.BOOK_REGISTRY.getBookToStacks().values() ) {

            ColorProviderRegistry.ITEM.register((stack, tint) -> {
                IBookItem item = (IBookItem) stack.getItem();

                if ( item.get() != null && !item.get().useCustomBookModel() && tint == 0 )
                    return ModConfigurations.CLIENT_PROVIDER.getBookColors().get(item.get()).get().getRGB();

                return -1;
            }, bookStack.get().getItem());

        }
    }


}
