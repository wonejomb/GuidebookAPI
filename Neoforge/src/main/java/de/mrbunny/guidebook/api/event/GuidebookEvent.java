package de.mrbunny.guidebook.api.event;

import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.cfg.ModConfigurations;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

public class GuidebookEvent extends Event {

    private final IBook book;
    private final ItemStack stack;
    private final Player player;

    public GuidebookEvent( IBook pBook, ItemStack pStack, Player pPlayer ) {
        this.book = pBook;
        this.stack = pStack;
        this.player = pPlayer;
    }

    public IBook getBook() {
        return book;
    }

    public Player getPlayer() {
        return player;
    }

    public ItemStack getStack() {
        return stack;
    }

    public static class Open extends GuidebookEvent implements ICancellableEvent {
        private Component canceledText = Component.translatable("guidebook.open.failed").withStyle(ChatFormatting.DARK_RED);

        public Open(IBook pBook, ItemStack pStack, Player pPlayer) {
            super(pBook, pStack, pPlayer);
        }

        public void setCanceledText(Component canceledText) {
            this.canceledText = canceledText;
        }

        public Component getCanceledText() {
            return canceledText;
        }
    }
}
