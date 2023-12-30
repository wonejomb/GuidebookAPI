package de.mrbunny.guidebook.api.recipe.type;

import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IGuideAnvilRecipe {

    List<ItemStack> getLeftInputs ();

    List<ItemStack> getRightInputs ();

    ItemStack getOutput ();
}
