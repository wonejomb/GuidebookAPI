package de.mrbunny.guidebook.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.Random;

public class IngredientCycler {

    private final RandomSource randomSource = RandomSource.create();
    private long lastCycle = -1;
    private int cycleIndex = 0;

    public Optional<ItemStack> getCycledIngredientStack (@NotNull Ingredient pIngredient, int pIndex) {
        ItemStack[] stacks = pIngredient.getItems();

        if ( stacks.length > 0 ) {
            this.randomSource.setSeed(pIndex);
            int id = (pIndex + this.randomSource.nextInt(stacks.length) + this.cycleIndex ) % stacks.length;
            return Optional.of(stacks[id]);
        }

        return Optional.empty();
    }

    public void tick (@NotNull Minecraft pMc) {
        long time = pMc.level != null ? pMc.level.getGameTime() : 0;
        if ( this.lastCycle < 0 || this.lastCycle < time - 20 ) {
            if ( this.lastCycle > 0  )  {
                this.cycleIndex++;
                this.cycleIndex = Math.max(0, this.cycleIndex);
            }

            this.lastCycle = time;
        }
    }
}
