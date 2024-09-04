package de.wonejo.wuidebook.client.render;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.math.Axis;
import de.wonejo.wuidebook.api.client.PositionableRender;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

public final class PositionableEntityRender implements PositionableRender {

    private final Entity entity;

    public PositionableEntityRender (@NotNull EntityType<?> pEntity ) {
        assert Minecraft.getInstance().level != null;
        this.entity = pEntity.create(Minecraft.getInstance().level);
    }

    public void render(GuiGraphics pGraphics, int pX, int pY) {
        double entityScale = 100.0F;
        double bbSize = Math.max(this.entity.getBbWidth(), this.entity.getBbHeight());

        if (bbSize > 1.0)
            entityScale /= bbSize * 1.5F;

        pGraphics.pose().pushPose();
        RenderSystem.setShaderLights( new Vector3f(1.0F, 1.0F, 1.0F).normalize(), new Vector3f(-1.0F, -1.0F, 0.0F).normalize() );
        pGraphics.pose().translate(pX, pY, 50.0F);
        pGraphics.pose().scale((float) entityScale, (float) entityScale, (float) entityScale);
        pGraphics.pose().mulPose(Axis.YP.rotationDegrees(((float) Util.getMillis() / 20) % 360));
        pGraphics.pose().mulPose(Axis.XP.rotationDegrees(180));
        Minecraft.getInstance().getEntityRenderDispatcher().render(this.entity, 0, 0, 0, 0.0F, Minecraft.getInstance().getTimer().getGameTimeDeltaTicks(), pGraphics.pose(), pGraphics.bufferSource(), 15728880);
        pGraphics.pose().popPose();
    }

}
