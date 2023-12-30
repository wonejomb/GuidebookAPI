package de.mrbunny.guidebook.api.recipe.type;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public interface IVanillaRecipeFactory {

    IGuideAnvilRecipe createAnvilRecipe ( Ingredient pLeftInput, Ingredient pRightInput, ItemStack pOutput );
    IGuideAnvilRecipe createAnvilRecipe ( List<ItemStack> pLeftInputs, List<ItemStack> pRightInputs, ItemStack pOutput );
    IGuideAnvilRecipe createAnvilRecipe ( ItemStack pLeftInput, ItemStack pRightInput, ItemStack pOutput );


    IGuideBrewingRecipe createBrewingRecipe ( Ingredient pInputs, Ingredient pIngredients, int pSteps );
    IGuideBrewingRecipe createBrewingRecipe ( List<ItemStack> pInputs, List<ItemStack> pIngredients, int pSteps );

}
