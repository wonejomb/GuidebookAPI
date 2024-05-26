package de.wonejo.gapi.proxy;

import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.proxy.IProxy;
import de.wonejo.gapi.client.screen.HomeGuideScreen;
import de.wonejo.gapi.item.GuideItem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ClientProxy implements IProxy {

    public void openGuide(Player pPlayer, Level pLevel, IBook pBook, ItemStack pStack) {
        if (!pStack.isEmpty() && pStack.getItem() instanceof GuideItem)
            pBook.initializeContent();

        Minecraft.getInstance().setScreen(new HomeGuideScreen(pBook));
    }

}
