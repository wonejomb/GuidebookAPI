package de.wonejo.gapi.client.item;

import de.wonejo.gapi.api.book.IBook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.block.model.BlockModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.resources.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;

public class GuideItemModel extends DelegatedModel {

    private final IBook book;

    public GuideItemModel(BakedModel pOriginalModel, IBook pBook) {
        super(pOriginalModel);
        this.book = pBook;
    }

    public @NotNull ItemOverrides getOverrides() {
        return new ItemOverrides(new ModelBaker() {
            public @NotNull UnbakedModel getModel(@NotNull ResourceLocation pLocation) {
                return null;
            }

            @Nullable
            public BakedModel bake(@NotNull ResourceLocation pLocation, @NotNull ModelState pTransform) {
                return null;
            }
        }, (BlockModel) Minecraft.getInstance().getModelManager().getModel(ModelBakery.MISSING_MODEL_LOCATION), Collections.emptyList()) {
            public BakedModel resolve(@NotNull BakedModel pBakedModel, @NotNull ItemStack pItemStack, @Nullable ClientLevel pClientLevel, @Nullable LivingEntity pEntity, int pSeed) {
                ModelResourceLocation location = new ModelResourceLocation(GuideItemModel.this.book.modelLocation(), ModelResourceLocation.INVENTORY_VARIANT);
                return Minecraft.getInstance().getModelManager().getModel(location);
            }
        };
    }

}
