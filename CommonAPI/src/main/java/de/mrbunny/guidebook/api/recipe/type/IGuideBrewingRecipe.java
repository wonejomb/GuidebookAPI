package de.mrbunny.guidebook.api.recipe.type;

import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IGuideBrewingRecipe {

    List<ItemStack> getInputs ();

    List<ItemStack> getIngredients ();

    ItemStack getOutput();

}
