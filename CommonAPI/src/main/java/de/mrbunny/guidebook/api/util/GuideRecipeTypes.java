package de.mrbunny.guidebook.api.util;

import de.mrbunny.guidebook.api.recipe.GuideRecipeType;
import de.mrbunny.guidebook.api.recipe.IGuideIngredientInfoRecipe;
import de.mrbunny.guidebook.api.recipe.type.IGuideAnvilRecipe;
import de.mrbunny.guidebook.api.recipe.type.IGuideBrewingRecipe;
import de.mrbunny.guidebook.api.recipe.type.IGuideComposterRecipe;
import de.mrbunny.guidebook.api.recipe.type.IGuideRecipeFuel;
import net.minecraft.world.item.crafting.*;

public class GuideRecipeTypes {

    public static final GuideRecipeType<RecipeHolder<CraftingRecipe>> CRAFTING = GuideRecipeType.createVanilla(RecipeType.CRAFTING);
    public static final GuideRecipeType<RecipeHolder<SmeltingRecipe>> SMELTING = GuideRecipeType.createVanilla(RecipeType.SMELTING);
    public static final GuideRecipeType<RecipeHolder<BlastingRecipe>> BLASTING = GuideRecipeType.createVanilla(RecipeType.BLASTING);
    public static final GuideRecipeType<RecipeHolder<SmokingRecipe>> SMOKING = GuideRecipeType.createVanilla(RecipeType.SMOKING);
    public static final GuideRecipeType<RecipeHolder<CampfireCookingRecipe>> CAMPFIRE_COOK = GuideRecipeType.createVanilla(RecipeType.CAMPFIRE_COOKING);
    public static final GuideRecipeType<RecipeHolder<StonecutterRecipe>> STONE_CUTTER = GuideRecipeType.createVanilla(RecipeType.STONECUTTING);
    public static final GuideRecipeType<RecipeHolder<SmithingRecipe>> SMITHING = GuideRecipeType.createVanilla(RecipeType.SMITHING);


    public static final GuideRecipeType<IGuideAnvilRecipe> ANVIL = GuideRecipeType.create(References.MINECRAFT_ID, "anvil", IGuideAnvilRecipe.class);
    public static final GuideRecipeType<IGuideComposterRecipe> COMPOSTING = GuideRecipeType.create(References.MINECRAFT_ID, "compostable", IGuideComposterRecipe.class);
    public static final GuideRecipeType<IGuideBrewingRecipe> BREWING = GuideRecipeType.create(References.MINECRAFT_ID, "brewing", IGuideBrewingRecipe.class);
    public static final GuideRecipeType<IGuideRecipeFuel> FUELING = GuideRecipeType.create(References.MINECRAFT_ID, "fuel", IGuideRecipeFuel.class);


    public static final GuideRecipeType<IGuideIngredientInfoRecipe> INFORMATION  = GuideRecipeType.create(References.GUIDEBOOKAPI_ID, "information", IGuideIngredientInfoRecipe.class);

}
