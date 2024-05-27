package de.wonejo.gapi.api.util;

import de.wonejo.gapi.api.book.IBook;
import net.minecraft.world.entity.player.Player;

public interface Clickable {

    default void onClick (IBook pBook, Player pPlayer, double pMouseX, double pMouseY, int pClickType) {};

}
