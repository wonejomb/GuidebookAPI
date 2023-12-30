package de.mrbunny.guidebook.api.client.recipe;

import de.mrbunny.guidebook.api.client.render.IRenderable;
import de.mrbunny.guidebook.api.recipe.IGuideIngredientInfoRecipe;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public interface IRecipeIngredientInfoRender extends IRenderable {

    IGuideIngredientInfoRecipe getIngredientInfo ();

}
