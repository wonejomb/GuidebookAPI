package de.mrbunny.guidebook.api.proxy;

import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.book.component.IBookCategory;
import de.mrbunny.guidebook.api.book.component.IBookEntry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IProxy {

    void initColors ();

    void openGuidebook (Player pPlayer, Level pLevel, IBook pBook, ItemStack pStack);

    void openEntry ( IBook pBook, IBookCategory pCategory, IBookEntry pEntry, Player pPlayer, ItemStack pStack );

}
