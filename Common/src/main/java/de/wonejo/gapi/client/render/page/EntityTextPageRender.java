package de.wonejo.gapi.client.render.page;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.client.render.IPageRender;
import de.wonejo.gapi.api.util.RenderUtils;
import de.wonejo.gapi.cfg.ModConfigurations;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

import java.util.function.Function;

public class EntityTextPageRender implements IPageRender {

    private final Function<Level, ? extends Entity> entityType;
    private final Component content;
    private Component title;
    private Entity entity;

    public EntityTextPageRender ( Function<Level, ? extends Entity> pEntityType, Component pContent ) {
        this.entityType = pEntityType;
        this.content = pContent;
    }

    public void render(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, IBook pBook, Font pFont) {
        RenderUtils.drawCenteredStringWithoutShadow(pGraphics, pFont, this.title, pScreen.xOffset() + pScreen.screenWidth() / 2, pScreen.yOffset() + 10, ModConfigurations.CLIENT_PROVIDER.getBookTextColors().get(pBook).get().getRGB());

        RenderUtils.renderTextInRange(
                pGraphics,
                pFont,
                this.content,
                pScreen.xOffset() + 10,
                pScreen.yOffset() + pScreen.screenWidth() / 2 + 30,
                pScreen.screenWidth() - 18,
                ModConfigurations.CLIENT_PROVIDER.getBookTextColors().get(pBook).get().getRGB()
        );

        if ( this.entity != null ) {
            PoseStack stack = pGraphics.pose();

            double entityScale = 100.0F;
            double bbSize = Math.max(this.entity.getBbWidth(), this.entity.getBbHeight());

            if (bbSize > 1.0)
                entityScale /= bbSize * 1.5F;


            double x = pScreen.xOffset() + (double) pScreen.screenWidth() / 2;
            double y = pScreen.yOffset() + (double) pScreen.screenHeight() / 2 + 10;
            double z = 50.0F;

            RenderSystem.setShaderLights(
                    new Vector3f(1.0F, 1.0F, 1.0F).normalize(),
                    new Vector3f(-1.0F, -1.0F, 0.0F).normalize()
            );

            stack.pushPose();
            stack.translate(x, y, z);
            stack.scale((float) entityScale, (float) entityScale, (float) entityScale);
            stack.mulPose(Axis.YP.rotationDegrees(((float) Util.getMillis() / 15) % 360));
            stack.mulPose(Axis.XP.rotationDegrees(180));
            Minecraft.getInstance().getEntityRenderDispatcher().render(this.entity, 0, 0, 0, 0.0F,
                    Minecraft.getInstance().getTimer().getGameTimeDeltaTicks(), stack, pGraphics.bufferSource(), 15728880);
            stack.popPose();
        }
    }

    public void init() {
        if (Minecraft.getInstance().level != null ) this.entity = this.entityType.apply(Minecraft.getInstance().level);
        this.title = this.entity.getDisplayName();
    }
}
