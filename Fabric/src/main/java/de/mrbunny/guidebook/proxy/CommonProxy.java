package de.mrbunny.guidebook.proxy;

import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.book.component.IBookEntry;
import de.mrbunny.guidebook.api.proxy.IProxy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CommonProxy implements IProxy {

    public void initColors() {

    }

    public void openGuidebook(Player pPlayer, Level pLevel, IBook pBook, ItemStack pStack) {

    }

    public void openEntry(IBook pBook, IBookCategory pCategory, IBookEntry pEntry, Player pPlayer, ItemStack pStack) {

    }
}
