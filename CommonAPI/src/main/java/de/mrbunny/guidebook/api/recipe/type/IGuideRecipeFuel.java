package de.mrbunny.guidebook.api.recipe.type;

import net.minecraft.world.item.ItemStack;

import java.util.List;

public interface IGuideRecipeFuel {

    List<ItemStack> getInputs ();

    int getBurningTime ();
}
