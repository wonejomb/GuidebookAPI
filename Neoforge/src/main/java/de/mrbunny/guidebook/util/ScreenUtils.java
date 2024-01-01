package de.mrbunny.guidebook.util;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class ScreenUtils {

    public static boolean isMouseBetween ( double pMouseX, double pMouseY,int pX, int pY, int pWidth, int pHeight ) {
        int sizeWidth = pX + pWidth;
        int sizeHeight = pY + pHeight;

        return (pMouseX >= pX && pMouseX <= sizeWidth && pMouseY >= pY && pMouseY <= sizeHeight);
    }

    public static void drawItemStack(@NotNull GuiGraphics pGraphics, ItemStack Stack, int pX, int pY) {
        PoseStack mStack = RenderSystem.getModelViewStack();

        mStack.pushPose();
        RenderSystem.applyModelViewMatrix();
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderSystem.enableDepthTest();
        pGraphics.renderItem(Stack, pX, pY);
        pGraphics.renderItemDecorations(Minecraft.getInstance().font, Stack, pX, pY, null);
        mStack.popPose();
        RenderSystem.applyModelViewMatrix();
    }

    public static void drawScaledItemStack(@NotNull GuiGraphics pGraphics, ItemStack pStack, int pX, int pY, float pScale) {
        PoseStack mStack = RenderSystem.getModelViewStack();

        mStack.pushPose();
        mStack.scale(pScale, pScale, 1f);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        RenderSystem.enableDepthTest();
        RenderSystem.applyModelViewMatrix();
        pGraphics.renderItem(pStack, (int) (pX / pScale), (int) (pY / pScale));
        mStack.popPose();
        RenderSystem.applyModelViewMatrix();
    }

    public static void drawImage (@NotNull GuiGraphics pGraphics, ResourceLocation pImage, int pX, int pY, int pWidth, int pHeight) {
        pGraphics.blit(pImage, pX, pY, 0, 0, pWidth, pHeight, pWidth, pHeight);
    }

    public static void drawScaledImage (@NotNull GuiGraphics pGraphics, ResourceLocation pImage, int pX, int pY, int pWidth, int pHeight, float pScale ) {
        PoseStack stack = pGraphics.pose();

        stack.pushPose();
        pGraphics.blit(pImage, pX, pY, 0, 0, pWidth, pHeight, pWidth, pHeight);
        stack.popPose();
    }

}
