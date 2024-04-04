package de.wonejo.gapi.client.render.page;

import com.mojang.logging.LogUtils;
import de.wonejo.gapi.api.client.render.recipe.IRecipeRender;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Recipe;

import java.util.function.Function;

public class JsonRecipePageRender extends RecipePageRender {

    private final ResourceLocation recipeId;
    private final Function<Recipe<?>, IRecipeRender> recipeRenderSupplier;

    public JsonRecipePageRender ( ResourceLocation pRecipeId )  {
        this ( pRecipeId, RecipePageRender::defaultRender );
    }

    public JsonRecipePageRender ( ResourceLocation pRecipeId, Function<Recipe<?>, IRecipeRender> pRecipeRender ) {
        super(null, null);
        this.recipeId = pRecipeId;
        this.recipeRenderSupplier = pRecipeRender;
    }

    public void init() {
        super.init();

        if ( this.recipe == null ) {
            this.recipe = Minecraft.getInstance().getConnection() == null ? null : Minecraft.getInstance().getConnection().getRecipeManager().byKey(this.recipeId).orElse(null).value();

            if ( this.recipe == null ) {
                LogUtils.getLogger().error("Can't find recipe with id {}", this.recipe);
                return;
            }

            if ( this.render == null ) {
                this.render = this.recipeRenderSupplier.apply(this.recipe);

                if (this.render == null )
                    LogUtils.getLogger().error("Can't get render of recipe type {} for recipe with id {}", this.recipe.getClass().getSimpleName(), this.recipeId);
            }
        }

        this.valid = this.recipe != null && this.render != null;
    }

}
