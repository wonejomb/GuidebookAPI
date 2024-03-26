package de.wonejo.gapi.api.util;

import de.wonejo.gapi.api.book.IBook;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface Clickable {

    default void onClick (IBook pBook, double pMouseX, double pMouseY, Player pPlayer, ItemStack pStack, int pClickType) {};

}
