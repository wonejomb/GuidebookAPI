package de.mrbunny.guidebook;

import de.mrbunny.guidebook.api.IGuidebook;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.util.References;
import de.mrbunny.guidebook.client.model.ClientModelHandler;
import de.mrbunny.guidebook.config.ModConfigManager;
import de.mrbunny.guidebook.config.ModConfigurations;
import de.mrbunny.guidebook.handler.GuidebooksRegister;
import de.mrbunny.guidebook.handler.WorldEventHandler;
import de.mrbunny.guidebook.item.GuidebookItem;
import de.mrbunny.guidebook.proxy.ClientProxy;
import de.mrbunny.guidebook.util.APIUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GuidebookMod implements ModInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(References.GUIDEBOOKAPI_ID);

    @Environment(EnvType.CLIENT)
    public static final ClientProxy CLIENT_PROXY = new ClientProxy();

    public void onInitialize() {
        LOGGER.info("Initializing GuidebookAPI");

        GuidebooksRegister.gatherBooks();

        ModConfigManager.setupConfigurations();

        this.registerItems();

        ModelLoadingPlugin.register(new ClientModelHandler());

        CLIENT_PROXY.initColors();

        WorldEventHandler.loadWorldEvents();
    }

    private void registerItems () {
        for (Pair<IGuidebook, IBook> pair : GuidebooksRegister.getRegisteredBooks()) {
            ResourceLocation itemId = new ResourceLocation(References.GUIDEBOOKAPI_ID, pair.getRight().getId().getPath().toLowerCase().replace(":", "_"));

            Item item = new GuidebookItem(pair.getRight());

            Registry.register(
                    BuiltInRegistries.ITEM,
                    itemId,
                    item
            );
            APIUtils.setBookForStack(pair.getRight(), () -> new ItemStack(item));
        }
    }
}
