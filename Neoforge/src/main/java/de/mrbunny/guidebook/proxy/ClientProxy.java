package de.mrbunny.guidebook.proxy;

import de.mrbunny.guidebook.api.GuidebookAPI;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.book.IBookItem;
import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.api.event.GuidebookEvent;
import de.mrbunny.guidebook.cfg.ModConfigurations;
import de.mrbunny.guidebook.client.screen.GuideEntryScreen;
import de.mrbunny.guidebook.client.screen.GuideHomeScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.common.NeoForge;

import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class ClientProxy extends CommonProxy {

    @OnlyIn(Dist.CLIENT)
    public void initColors() {
        for (Supplier<ItemStack> bookStack : GuidebookAPI.getBookToStack().values()) {
            Minecraft.getInstance().getItemColors().register((stack, tint) -> {
                IBookItem item = (IBookItem) stack.getItem();
                if ( item.getBook(stack) != null && tint == 0 )
                    return ModConfigurations.CLIENT.bookColors.get(item.getBook(stack)).get();

                return -1;
            }, bookStack.get().getItem());
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void openGuidebook(Player pPlayer, Level pLevel, IBook pBook, ItemStack pStack) {
        if ( !pStack.isEmpty() && pStack.getItem() instanceof IBookItem ) {
            pBook.initializeContent();

            Minecraft.getInstance().setScreen(new GuideHomeScreen(pBook, pPlayer, pStack));
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void openEntry(IBook pBook, IBookCategory pCategory, IBookEntry pEntry, Player pPlayer, ItemStack pStack) {
        GuidebookEvent.Open event = new GuidebookEvent.Open(pBook, pStack, pPlayer);
        if (NeoForge.EVENT_BUS.post(event).isCanceled()) {
            pPlayer.displayClientMessage(event.getCanceledText(), true);
            return;
        }

        Minecraft.getInstance().setScreen(new GuideEntryScreen(pBook, pCategory, pEntry, pPlayer, pStack));
    }
}
