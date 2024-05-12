package de.wonejo.gapi.proxy;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.book.item.IBookItem;
import de.wonejo.gapi.api.proxy.IProxy;
import de.wonejo.gapi.cfg.ModConfigurations;
import de.wonejo.gapi.client.screen.HomeGuideScreen;
import de.wonejo.gapi.impl.service.Services;
import de.wonejo.gapi.item.GuideItem;
import de.wonejo.gapi.mixin.ItemColorsAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

public class ClientProxy implements IProxy {

    public void tintBooks() {
        for ( Supplier<ItemStack> bookStack : Services.BOOK_REGISTRY.getBookToStacks().values() ) {
            ((ItemColorsAccessor) Minecraft.getInstance().getItemRenderer()).getColors().register((pStack, pTint) -> {
                IBookItem item = (IBookItem) bookStack.get().getItem();

                if ( item.get() != null && !item.get().useCustomBookModel() && pTint == 0 )
                    return ModConfigurations.CLIENT_PROVIDER.getBookColors().get(item.get()).get().getRGB();

                return -1;
            }, bookStack.get().getItem());
        }
    }

    public void openGuide(Player pPlayer, Level pLevel, IBook pBook, ItemStack pStack) {
        if (!pStack.isEmpty() && pStack.getItem() instanceof GuideItem)
            pBook.initializeContent();

        Minecraft.getInstance().setScreen(new HomeGuideScreen(pBook));
    }

}
