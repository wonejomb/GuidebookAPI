package de.wonejo.gapi.client.model;

import de.wonejo.gapi.api.book.IBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.*;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class GuideModel implements BakedModel {

    private final BakedModel original;
    private final ItemOverrides handler;

    public GuideModel ( IBook pBook, BakedModel pOriginal, ModelBakery pLoader ) {
        this.original = pOriginal;

        this.handler = new ItemOverrides(new ModelBaker() {
            public UnbakedModel getModel(ResourceLocation location) {
                return null;
            }

            @Nullable
            public BakedModel bake(ResourceLocation location, ModelState transform) {
                return null;
            }
        }, (BlockModel) pLoader.getModel(ModelBakery.MISSING_MODEL_LOCATION), Collections.emptyList()) {
            public @NotNull BakedModel resolve(BakedModel model, ItemStack stack, @Nullable ClientLevel level, @Nullable LivingEntity entity, int seed) {
                if ( pBook != null ) {
                    ResourceLocation modelLoc = pBook.modelLocation();

                    if ( modelLoc != null ) {
                        ModelResourceLocation modelPath = new ModelResourceLocation(modelLoc, "inventory");

                        return Minecraft.getInstance().getModelManager().getModel(modelPath);
                    }
                }

                return original;
            }
        };
    }

    public @NotNull ItemOverrides getOverrides() {
        return this.handler;
    }

    public @NotNull List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction direction, RandomSource random) {
        return this.original.getQuads(state, direction, random);
    }

    public boolean useAmbientOcclusion() {
        return this.original.useAmbientOcclusion();
    }

    public boolean isGui3d() {
        return this.original.isGui3d();
    }

    public boolean usesBlockLight() {
        return this.original.usesBlockLight();
    }

    public boolean isCustomRenderer() {
        return this.original.isCustomRenderer();
    }

    public @NotNull TextureAtlasSprite getParticleIcon() {
        return this.original.getParticleIcon();
    }

    public @NotNull ItemTransforms getTransforms() {
        return this.original.getTransforms();
    }
}
