package de.mrbunny.guidebook.api.recipe;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;

public final class GuideRecipeType<T> {

    public static <T> GuideRecipeType<T> create (String pNamespace, String pPath, Class<? extends T> pRecipeClass ) {
        ResourceLocation id = new ResourceLocation(pNamespace,pPath);

        return new GuideRecipeType<>(id, pRecipeClass);
    }

    public static <T> GuideRecipeType<T> create (ResourceLocation pId, Class<? extends T> pRecipeClass ) {
        return new GuideRecipeType<>(pId, pRecipeClass);
    }

    @SuppressWarnings("unchecked")
    public static <B extends Recipe<?>> GuideRecipeType<RecipeHolder<B>> createVanilla (RecipeType<B> pRecipeType ) {
        ResourceLocation id = BuiltInRegistries.RECIPE_TYPE.getKey(pRecipeType);
        if ( id == null )
            throw new IllegalArgumentException("Vanilla recipe type must be registered before using it here. %s".formatted(pRecipeType));

        Class<? extends RecipeHolder<B>> holderClass = (Class<? extends RecipeHolder<B>>) RecipeHolder.class;

        return new GuideRecipeType<>(id, holderClass);
     }

    private final ResourceLocation id;
    private final Class<? extends T> recipeClass;

    public GuideRecipeType(ResourceLocation pId, Class<? extends T> pRecipeClass ) {
        if ( pId == null || pRecipeClass == null )
            throw new NullPointerException("Any recipe type parameter can't be null");

        this.id = pId;
        this.recipeClass = pRecipeClass;
    }

    public ResourceLocation getId() {
        return id;
    }

    public Class<? extends T> getRecipeClass() {
        return recipeClass;
    }
}
