package de.mrbunny.guidebook.api.client;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface IModScreen {

    int getXOffset ();
    int getYOffset ();
    ItemStack getBookStack ();
    Player getPlayer ();

}
