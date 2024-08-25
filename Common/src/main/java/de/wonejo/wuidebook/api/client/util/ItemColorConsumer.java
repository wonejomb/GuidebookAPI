package de.wonejo.wuidebook.api.client.util;

import net.minecraft.world.item.ItemStack;

@FunctionalInterface
public interface ItemColorConsumer {

    void accept (ItemStack pStack, int pColor);

}
