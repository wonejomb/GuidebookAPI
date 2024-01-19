package de.mrbunny.guidebook.page;

import com.mojang.logging.LogUtils;
import de.mrbunny.guidebook.api.client.recipe.IRecipeRender;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.function.Function;

public class JsonRecipePage extends RecipePage {

    @Nonnull
    private final ResourceLocation recipeId;
    @Nonnull
    private final Function<Recipe<?>, IRecipeRender> recipeRenderSupplier;

    public JsonRecipePage ( ResourceLocation pRecipeId ) {
        this ( pRecipeId, RecipePage::getDefaultRender );
    }

    public JsonRecipePage (@NotNull ResourceLocation pRecipeId, @NotNull Function<Recipe<?>, IRecipeRender> pSupplier ) {
        super(null, null);
        this.recipeId = pRecipeId;
        this.recipeRenderSupplier = pSupplier;
    }

    public void init() {
        super.init();

        if  ( this.recipe == null ) {
            this.recipe = Minecraft.getInstance().getConnection() == null ? null : Minecraft.getInstance().getConnection().getRecipeManager().byKey(this.recipeId).orElse(null).value();
            if ( this.recipe == null )
                LogUtils.getLogger().error("Can't fin recipe with id {}", this.recipeId);
            else {
                if ( this.recipeRender == null ) {
                    this.recipeRender = this.recipeRenderSupplier.apply(this.recipe);
                    if ( this.recipeRender == null )
                        LogUtils.getLogger().error("Didn't get render for recipe type {} for recipe with id {}", this.recipe.getClass().getSimpleName(), this.recipeId);
                }
            }
        }

        this.valid = this.recipe != null && this.recipeRender != null;
    }
}
