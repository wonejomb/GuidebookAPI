package de.mrbunny.guidebook.proxy;

import de.mrbunny.guidebook.api.GuidebookAPI;
import de.mrbunny.guidebook.api.IGuidebook;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.book.IBookItem;
import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.client.screen.GuideEntryScreen;
import de.mrbunny.guidebook.client.screen.GuideHomeScreen;
import de.mrbunny.guidebook.handler.GuidebooksRegister;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class ClientProxy extends CommonProxy {

    @Environment(EnvType.CLIENT)
    public void initColors() {
        for (Supplier<ItemStack> bookStack : GuidebookAPI.getBookToStack().values()) {

            ColorProviderRegistry.ITEM.register((stack, tint) -> {
                IBookItem bookItem = (IBookItem) bookStack.get().getItem();

                if ( bookItem.getBook(stack) != null && tint == 0 )
                    return bookItem.getBook(stack).getColor().getRGB();

                return -1;
            }, bookStack.get().getItem());

        }
    }

    @Environment(EnvType.CLIENT)
    public void openGuidebook(Player pPlayer, Level pLevel, IBook pBook, ItemStack pStack) {
        if ( !pStack.isEmpty() && pStack.getItem() instanceof IBookItem ) {
            pBook.initializeContent();

            Minecraft.getInstance().setScreen(new GuideHomeScreen(pBook, pPlayer, pStack));
        }
    }

    @Environment(EnvType.CLIENT)
    public void openEntry(IBook pBook, IBookCategory pCategory, IBookEntry pEntry, Player pPlayer, ItemStack pStack) {
        Minecraft.getInstance().setScreen(new GuideEntryScreen(pBook, pCategory, pEntry, pPlayer, pStack));
    }
}
