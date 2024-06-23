package de.wonejo.gapi.client.item;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DelegatedModel implements BakedModel {

    protected final BakedModel originalModel;

    public DelegatedModel ( BakedModel pOriginalModel ) {
        this.originalModel = pOriginalModel;
    }

    public boolean useAmbientOcclusion() {
        return this.originalModel.useAmbientOcclusion();
    }

    public boolean usesBlockLight() {
        return this.originalModel.usesBlockLight();
    }

    public boolean isGui3d() {
        return this.originalModel.isGui3d();
    }

    public boolean isCustomRenderer() {
        return this.originalModel.isCustomRenderer();
    }

    public @NotNull TextureAtlasSprite getParticleIcon() {
        return this.originalModel.getParticleIcon();
    }

    public @NotNull ItemTransforms getTransforms() {
        return this.originalModel.getTransforms();
    }

    public @NotNull ItemOverrides getOverrides() {
        return this.originalModel.getOverrides();
    }

    public @NotNull List<BakedQuad> getQuads(@Nullable BlockState blockState, @Nullable Direction direction, @NotNull RandomSource randomSource) {
        return this.originalModel.getQuads(blockState, direction, randomSource);
    }
}
