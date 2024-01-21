package de.mrbunny.guidebook.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.List;

public class ScreenUtils {

    public static boolean isMouseBetween ( double pMouseX, double pMouseY,int pX, int pY, int pWidth, int pHeight ) {
        int sizeWidth = pX + pWidth;
        int sizeHeight = pY + pHeight;

        return (pMouseX >= pX && pMouseX <= sizeWidth && pMouseY >= pY && pMouseY <= sizeHeight);
    }

    public static void drawItemStack(@NotNull GuiGraphics pGraphics, ItemStack pStack, int pX, int pY) {
        pGraphics.renderItem(pStack, pX, pY);
        pGraphics.renderItemDecorations(Minecraft.getInstance().font, pStack, pX, pY);
    }

    public static void drawScaledItemStack(@NotNull GuiGraphics pGraphics, ItemStack pStack, int pX, int pY, float pScale) {
        PoseStack poseStack = pGraphics.pose();

        poseStack.pushPose();
        poseStack.translate(pX, pY, 1.0F);
        poseStack.scale(pScale, pScale, 1.0F);
        pGraphics.renderItem(pStack, 0, 0);
        poseStack.popPose();
    }

    public static void drawImage (@NotNull GuiGraphics pGraphics, ResourceLocation pImage, int pX, int pY, int pWidth, int pHeight) {
        pGraphics.blit(pImage, pX, pY, 0, 0, pWidth, pHeight, pWidth, pHeight);
    }

    public static void drawScaledImage (@NotNull GuiGraphics pGraphics, ResourceLocation pImage, int pX, int pY, int pWidth, int pHeight, float pScale ) {
        PoseStack poseStack = pGraphics.pose();

        poseStack.pushPose();
        poseStack.scale(pScale, pScale, pScale);
        pGraphics.blit(pImage, (int) (pX / pScale), (int) (pY / pScale), 0, 0, pWidth, pHeight, pWidth, pHeight);
        poseStack.popPose();
    }

    public static <T extends Entity> void renderLivingEntity(@NotNull PoseStack pPoseStack, MultiBufferSource pBufferSource, T pEntity, int pX, int pY ) {
        double entityScale = 100.0F;
        double bbSize = Math.max(pEntity.getBbWidth(), pEntity.getBbHeight());

        if ( bbSize > 1.0 )
            entityScale /= bbSize * 1.5F;

        pPoseStack.pushPose();

        RenderSystem.setShaderLights(
                new Vector3f(1.0F, 1.0F, 1.0F).normalize(),
                new Vector3f(-1.0F, -1.0F, 0.0F).normalize()
        );

        pPoseStack.translate(pX, pY, 50.0F);
        pPoseStack.scale((float) entityScale, (float) entityScale, (float) entityScale);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(((float) Util.getMillis() / 20) % 360));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(180));
        Minecraft.getInstance().getEntityRenderDispatcher().render(pEntity, 0, 0, 0, 0.0F,
                Minecraft.getInstance().getFrameTime(), pPoseStack, pBufferSource, 15728880);
        pPoseStack.popPose();
    }

    public static List<Component> getTooltip ( ItemStack pStack ) {
        Minecraft mc = Minecraft.getInstance();
        List<Component> list = pStack.getTooltipLines(mc.player, mc.options.advancedItemTooltips ? TooltipFlag.ADVANCED : TooltipFlag.NORMAL);
        for ( int i = 0; i < list.size(); i++ ) {
            Component c = list.get(i);

            if ( c instanceof MutableComponent mutable ) {
                if ( i == 0 )
                    mutable.withStyle(pStack.getRarity().color);
                else
                    mutable.withStyle(Style.EMPTY);
            }
        }

        return list;
    }

}
