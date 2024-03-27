package de.wonejo.gapi.api.util;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface Clickable {

    default void onClick ( double pMouseX, double pMouseY, Player pPlayer, ItemStack pStack, int pClickType) {};

}
