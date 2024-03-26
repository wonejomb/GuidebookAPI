package de.wonejo.gapi.api.client;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface IModScreen {

    int xOffset ();
    int yOffset ();

    int widthSize ();
    int heightSize ();

    ItemStack bookStack ();
    Player player ();

}
