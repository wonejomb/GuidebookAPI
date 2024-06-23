package de.wonejo.gapi.api.proxy;

import de.wonejo.gapi.api.book.IBook;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface IProxy {

    void openGuide (Player pPlayer, Level pLevel, IBook pBook, ItemStack pStack);

    void playSound (SoundEvent pEvent);

}
