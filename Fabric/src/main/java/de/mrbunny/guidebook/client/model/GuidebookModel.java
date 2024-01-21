package de.mrbunny.guidebook.client.model;

import de.mrbunny.guidebook.api.IGuidebook;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.book.IBookItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.*;
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

public class GuidebookModel implements BakedModel {

    private final BakedModel original;
    private final ItemOverrides itemHandler;
    private final IGuidebook guidebook;

    public GuidebookModel (IGuidebook pGuidebook, IBook pBook, BakedModel pOriginal, ModelBakery pLoader) {
        this.original = pOriginal;
        this.guidebook = pGuidebook;
        BlockModel missing = (BlockModel) pLoader.getModel(ModelBakery.MISSING_MODEL_LOCATION);

        this.itemHandler = new ItemOverrides(new ModelBaker() {
            public UnbakedModel getModel(ResourceLocation resourceLocation) {
                return null;
            }

            @Nullable
            public BakedModel bake(ResourceLocation resourceLocation, ModelState modelState) {
                return null;
            }
        }, missing, Collections.emptyList()) {
            public BakedModel resolve(@NotNull BakedModel original, @NotNull ItemStack stack,
                                      @Nullable ClientLevel world, @Nullable LivingEntity entity, int seed) {

                if (pBook != null) {
                    ResourceLocation id = GuidebookModel.this.guidebook.getModelLocation();

                    if (id != null) {
                        ModelResourceLocation modelPath = new ModelResourceLocation(id, "inventory");

                        return Minecraft.getInstance().getModelManager().getModel(modelPath);
                    }
                }

                return original;
            }
        };
    }

    public ItemOverrides getOverrides() {
        return this.itemHandler;
    }

    public List<BakedQuad> getQuads(@Nullable BlockState state, @Nullable Direction direction, RandomSource random) {
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

    public TextureAtlasSprite getParticleIcon() {
        return this.original.getParticleIcon();
    }

    @Override
    public ItemTransforms getTransforms() {
        return this.original.getTransforms();
    }
}
