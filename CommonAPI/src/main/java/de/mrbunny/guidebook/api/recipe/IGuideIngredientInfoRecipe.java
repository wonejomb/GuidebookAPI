package de.mrbunny.guidebook.api.recipe;

import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public interface IGuideIngredientInfoRecipe {

    Ingredient getIngredients ();

    List<FormattedText> getDescription ();

}
