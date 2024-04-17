package de.wonejo.gapi.proxy;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.item.IBookItem;
import de.wonejo.gapi.api.proxy.IProxy;
import de.wonejo.gapi.api.util.Constants;
import de.wonejo.gapi.registry.BookRegistry;
import de.wonejo.gapi.client.screen.HomeGuideScreen;
import de.wonejo.gapi.config.ModConfigurations;
import de.wonejo.gapi.item.BookItem;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class ClientProxy implements IProxy {

    public void registerColors() {
        for (Supplier<ItemStack> bookStack : BookRegistry.getBookToStack().values() ) {

            Minecraft.getInstance().getItemColors().register((stack, tint) -> {
                IBookItem item = (IBookItem) bookStack.get().getItem();

                if ( item.get() != null && !checkIfBookTextureIsCustom(item.get()) && tint == 0 )
                    return ModConfigurations.CLIENT.bookColors.get(item.get()).get();

                return -1;
            },  bookStack.get().getItem());

        }
    }

    public void openGuidebook(Player pPlayer, Level pLevel, IBook pBook, ItemStack pStack) {
        if (!pStack.isEmpty() && pStack.getItem() instanceof BookItem)
            pBook.initializeContent();

        Minecraft.getInstance().setScreen(new HomeGuideScreen(pBook));
    }

    private static boolean checkIfBookTextureIsCustom ( IBook pBook ) {
        if (!pBook.topTexture().getTopTexture().equals(new ResourceLocation(Constants.MOD_ID, "textures/gui/book_pages.png"))) return true;
        return !pBook.topTexture().getPagesTexture().equals(new ResourceLocation(Constants.MOD_ID, "textures/gui/book_top.png"));
    }

}
