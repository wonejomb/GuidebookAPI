package de.mrbunny.guidebook.page;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import de.mrbunny.guidebook.api.book.component.IPage;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.client.book.IPageRender;
import de.mrbunny.guidebook.util.PageUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector3f;

import java.util.function.Function;

public class EntityPage implements IPage, IPageRender {
    private final FormattedText title;
    private FormattedText text;
    private final Function<Level, ? extends Entity> entitySupplier;
    @Nullable
    private Entity entity;

    public EntityPage (@Nullable FormattedText pTitle, @Nullable FormattedText pText, Function<Level, ? extends Entity> pSupplier) {
        this.title = pTitle;
        this.text = pText;
        this.entitySupplier = pSupplier;
    }

    public EntityPage ( FormattedText pText, Function<Level, ? extends Entity> pSupplier ) {
        this ( null, pText, pSupplier );
    }

    public EntityPage ( Component pText, Function<Level, ? extends Entity> pSupplier ) {
        this(null, pText, pSupplier);
    }

    public EntityPage ( Function<Level, ? extends Entity> pSupplier ) {
        this ( null, null, pSupplier );
    }

    public EntityPage ( EntityType<? extends Entity> pEntity ) {
        this ( pEntity.getDescription(), null, pEntity::create );
    }

    public void renderExtras(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont) {
        pGraphics.drawString(pFont, (this.title != null ? this.title.getString() : this.entity.getName().getString()), pScreen.getXOffset() + pScreen.getWidthSize() / 2 - pFont.width((this.title != null ? this.title.getString() : this.entity.getName().getString())) / 2, pScreen.getYOffset() + 10, ChatFormatting.DARK_GRAY.getColor(), false);

        PageUtils.drawFormattedText(pGraphics, this.text, pScreen.getXOffset() + 25, pScreen.getYOffset() + 25, 100);

        if ( this.entity != null ) {
            PoseStack poseStack = pGraphics.pose();

            double x = pScreen.getXOffset() + (double) pScreen.getWidthSize() / 2 + 60;
            double y = pScreen.getYOffset() + (double) pScreen.getHeightSize() / 2 + 30;
            double z = 50.0F;

            double entityScale = 100.0F;
            double bbSize = Math.max(this.entity.getBbWidth(), this.entity.getBbHeight());

            if ( bbSize > 1.0 )
                entityScale /= bbSize * 1.5F;

            poseStack.pushPose();

            RenderSystem.setShaderLights(
                    new Vector3f(1.0F, 1.0F, 1.0F).normalize(),
                    new Vector3f(-1.0F, -1.0F, 0.0F).normalize()
            );

            poseStack.translate(x, y, z);
            poseStack.scale((float) entityScale, (float) entityScale, (float) entityScale);
            poseStack.mulPose(Axis.YP.rotationDegrees(((float) Util.getMillis() / 15) % 360));
            poseStack.mulPose(Axis.XP.rotationDegrees(180));
            Minecraft.getInstance().getEntityRenderDispatcher().render(this.entity, 0, 0, 0, 0.0F,
                    Minecraft.getInstance().getFrameTime(), poseStack, pGraphics.bufferSource(), 15728880);
            poseStack.popPose();
        }
    }

    public void render(GuiGraphics pGraphics, int pMouseX, int pMouseY, Font pFont) {

    }

    public void init() {
        if (Minecraft.getInstance().level != null) this.entity = this.entitySupplier.apply(Minecraft.getInstance().level);
        if ( this.text == null ) this.text = Component.literal("");
    }
}
