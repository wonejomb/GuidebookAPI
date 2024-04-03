package de.wonejo.gapi.client.render.page;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.client.render.IPageRender;
import de.wonejo.gapi.api.util.RenderUtils;
import de.wonejo.gapi.config.ModConfigurations;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.function.Function;

public class EntityPageRender implements IPageRender {

    private Component title;
    private @Nullable Entity entity;
    private final Function<Level, ? extends Entity> entityType;

    public EntityPageRender( Function<Level, ? extends Entity> pEntity ) {
        this.entityType = pEntity;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont) {
        RenderUtils.drawCenteredStringWithoutShadow(pGraphics, pFont, this.title, pScreen.xOffset() + pScreen.widthSize() / 2, pScreen.yOffset() + 10, ModConfigurations.CLIENT.textColor.get());

        if ( this.entity != null ) {
            PoseStack stack = pGraphics.pose();

            double x = pScreen.xOffset() + (double) pScreen.widthSize() / 2;
            double y = pScreen.yOffset() + (double) pScreen.heightSize() / 2;
            double z = 50.0F;

            double entityScale = 100.0F;
            double bbSize = Math.max(this.entity.getBbWidth(), this.entity.getBbHeight());

            if (bbSize > 1.0)
                entityScale /= bbSize * 1.5F;

            RenderSystem.setShaderLights(
                    new Vector3f(1.0F, 1.0F, 1.0F).normalize(),
                    new Vector3f(-1.0F, -1.0F, 0.0F).normalize()
            );

            stack.translate(x, y, z);
            stack.scale((float) entityScale, (float) entityScale, (float) entityScale);
            stack.mulPose(Axis.YP.rotationDegrees(((float) Util.getMillis() / 15) % 360));
            stack.mulPose(Axis.XP.rotationDegrees(180));
            Minecraft.getInstance().getEntityRenderDispatcher().render(this.entity, 0, 0, 0, 0.0F,
                    Minecraft.getInstance().getFrameTime(), stack, pGraphics.bufferSource(), 15728880);
            stack.popPose();
        }
    }

    public void init() {
        if (Minecraft.getInstance().level != null ) this.entity = this.entityType.apply(Minecraft.getInstance().level);
        this.title = this.entity.getDisplayName();
    }
}
