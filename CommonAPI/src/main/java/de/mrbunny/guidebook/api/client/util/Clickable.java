package de.mrbunny.guidebook.api.client.util;

import de.mrbunny.guidebook.api.book.IBook;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface Clickable {

    default void leftClick (IBook pBook, double pMouseX, double pMouseY, Player pPlayer, ItemStack pBookStack ) {};

    default void rightClick (IBook pBook, double pMouseX, double pMouseY, Player pPlayer, ItemStack pBookStack ) {};

}
