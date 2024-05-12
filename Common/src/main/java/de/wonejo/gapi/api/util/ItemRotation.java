package de.wonejo.gapi.api.util;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.Optional;
import java.util.Random;

public final class ItemRotation {
    private final Random random = new Random();
    private long lastCycle = -1;
    private int cycleIdx = 0;

    public Optional<ItemStack> cycleIngredientStack ( Ingredient pIngredient, int pIndex ) {
        ItemStack[] stacks = pIngredient.getItems();
        if ( stacks.length > 0 ) {
            this.random.setSeed(pIndex);
            int id = (pIndex + random.nextInt(stacks.length) + cycleIdx) % stacks.length;
            return Optional.of(stacks[id]);
        }
        return Optional.empty();
    }

    public void tick (Minecraft pMinecraft) {
        long time = pMinecraft.level != null ? pMinecraft.level.getGameTime() : 0;

        if ( time < 0 || this.lastCycle < time - 20 ) {
            if ( this.lastCycle > 0 ) {
                this.cycleIdx++;
                this.cycleIdx = Math.max(0, cycleIdx);
            }
            this.lastCycle = time;
        }
    }

}
