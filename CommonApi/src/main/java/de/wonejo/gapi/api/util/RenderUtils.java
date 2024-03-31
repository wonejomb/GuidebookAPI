package de.wonejo.gapi.api.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.math.Axis;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.joml.Vector3f;

import java.util.List;

public final class RenderUtils {

    public static boolean isMouseBetween ( double pMouseX, double pMouseY, int pX, int pY, int pWidth, int pHeight ) {
        int widthSize = pX + pWidth;
        int heightSize = pY + pHeight;

        return (pMouseX >= pX && pMouseX <= widthSize) && (pMouseY >= pY && pMouseY <= heightSize);
    }

    public static void renderTextInRange ( GuiGraphics pGraphics, Font pFont, Component pText, int pX, int pY, int pTextLarge, int pColor ) {
        List<FormattedCharSequence> cutLines = pFont.split(pText, pTextLarge);

        for ( FormattedCharSequence cut : cutLines ) {
            pGraphics.drawString(pFont, cut, pX, pY, pColor, false);
            pY += 10;
        }
    }

    public static void drawCenteredStringWithoutShadow(GuiGraphics pGraphics, Font pFont, String pString, int pX, int pY, int pColor) {
        pGraphics.drawString(pFont, pString, pX - pFont.width(pString) / 2, pY, pColor, false);
    }

    public static void drawCenteredStringWithoutShadow(GuiGraphics pGraphics, Font pFont, FormattedCharSequence pString, int pX, int pY, int pColor) {
        pGraphics.drawString(pFont, pString, pX - pFont.width(pString) / 2, pY, pColor, false);
    }

    public static void drawCenteredStringWithoutShadow(GuiGraphics pGraphics, Font pFont, Component pString, int pX, int pY, int pColor) {
        pGraphics.drawString(pFont, pString, pX - pFont.width(pString) / 2, pY, pColor, false);
    }


    public static void renderItem ( GuiGraphics pGraphics, ItemStack pStack, int pX, int pY ) {
        pGraphics.renderItem(pStack, pX, pY);
        pGraphics.renderItemDecorations(Minecraft.getInstance().font, pStack, pX, pY);
    }

    public static void renderScaledItem ( GuiGraphics pGraphics, ItemStack pStack, int pX, int pY, float pScale) {
        PoseStack stack = pGraphics.pose();

        stack.pushPose();
        stack.translate(pX, pY, 1.0F);
        stack.scale(pScale, pScale, pScale);
        pGraphics.renderItem(pStack, 0, 0);
        stack.popPose();
    }

    public static void renderImage ( GuiGraphics pGraphics, ResourceLocation pImage, int pX, int pY, int pWidth, int pHeight) {
        pGraphics.blit(pImage, pX, pY, 0, 0, pWidth, pHeight, pWidth, pHeight);
    }

    public static void renderScaledImage ( GuiGraphics pGraphics, ResourceLocation pImage, int pX, int pY, int pWidth, int pHeight, float pScale) {
        PoseStack stack = pGraphics.pose();

        stack.pushPose();
        stack.translate((float) pX / pScale, (float) pY / pScale, 1.0F);
        stack.scale(pScale, pScale, pScale);
        pGraphics.blit(pImage, pX, pY, 0, 0, pWidth, pHeight, pWidth, pHeight);
        stack.popPose();
    }

    public static <T extends Entity> void renderEntity ( PoseStack pPoseStack, MultiBufferSource pSource,  T pEntity, int pX, int pY ) {
        double entityScale = 100.0F;
        double bbSie = Math.max(pEntity.getBbWidth(), pEntity.getBbHeight());

        if (bbSie > 1.0)
            entityScale /= bbSie * 1.5F;

        pPoseStack.pushPose();

        RenderSystem.setShaderLights(
                new Vector3f(1.0F, 1.0F, 1.0F).normalize(),
                new Vector3f(-1.0F, -1.0F, 0.0F).normalize()
        );

        pPoseStack.translate(pX, pY, 50.0F);
        pPoseStack.scale((float) entityScale, (float) entityScale, (float) entityScale);
        pPoseStack.mulPose(Axis.XP.rotationDegrees(180));
        Minecraft.getInstance().getEntityRenderDispatcher().render(pEntity, 0, 0, 0, 0.0F, Minecraft.getInstance().getFrameTime(), pPoseStack, pSource, 15728880);
        pPoseStack.popPose();
    }

    public static <T extends Entity> void renderRotationEntity ( PoseStack pPoseStack, MultiBufferSource pSource,  T pEntity, int pX, int pY ) {
        double entityScale = 100.0F;
        double bbSie = Math.max(pEntity.getBbWidth(), pEntity.getBbHeight());

        if (bbSie > 1.0)
            entityScale /= bbSie * 1.5F;

        pPoseStack.pushPose();

        RenderSystem.setShaderLights(
                new Vector3f(1.0F, 1.0F, 1.0F).normalize(),
                new Vector3f(-1.0F, -1.0F, 0.0F).normalize()
        );

        pPoseStack.translate(pX, pY, 50.0F);
        pPoseStack.scale((float) entityScale, (float) entityScale, (float) entityScale);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(((float) Util.getMillis() / 20) % 360));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(180));
        Minecraft.getInstance().getEntityRenderDispatcher().render(pEntity, 0, 0, 0, 0.0F, Minecraft.getInstance().getFrameTime(), pPoseStack, pSource, 15728880);
        pPoseStack.popPose();
    }

    public static List<Component> getTooltips (ItemStack pStack) {
        Minecraft mc = Minecraft.getInstance();
        List<Component> list = pStack.getTooltipLines(mc.player, mc.options.advancedItemTooltips ? TooltipFlag.ADVANCED : TooltipFlag.NORMAL);

        for ( int i =0; i < list.size(); i++ ) {
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
