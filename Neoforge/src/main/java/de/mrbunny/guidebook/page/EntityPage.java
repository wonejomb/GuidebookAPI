package de.mrbunny.guidebook.page;

import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import de.mrbunny.guidebook.api.book.component.IPage;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.client.book.IPageRender;
import de.mrbunny.guidebook.util.PageUtils;
import de.mrbunny.guidebook.util.ScreenUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.client.NeoForgeRenderTypes;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class EntityPage implements IPage, IPageRender {
    private final FormattedText title;
    private FormattedText text;
    private final Function<Level, ? extends LivingEntity> entitySupplier;
    @Nullable
    private LivingEntity entity;


    public EntityPage (@Nullable FormattedText pTitle, @Nullable FormattedText pText, Function<Level, ? extends LivingEntity> pSupplier) {
        this.title = pTitle;
        this.text = pText;
        this.entitySupplier = pSupplier;
    }

    public EntityPage ( FormattedText pText, Function<Level, ? extends LivingEntity> pSupplier ) {
        this ( null, pText, pSupplier );
    }

    public EntityPage ( Component pText, Function<Level, ? extends LivingEntity> pSupplier ) {
        this(null, pText, pSupplier);
    }

    public EntityPage ( Function<Level, ? extends LivingEntity> pSupplier ) {
        this ( null, null, pSupplier );
    }

    public EntityPage ( EntityType<? extends LivingEntity> pEntity ) {
        this ( pEntity.getDescription(), null, pEntity::create );
    }

    @SuppressWarnings("unchecked")
    public void renderExtras(GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen, Font pFont) {
        pGraphics.drawString(pFont, (this.title != null ? this.title.getString() : this.entity.getName().getString()), pScreen.getXOffset() + pScreen.getWidthSize() / 2 - pFont.width((this.title != null ? this.title.getString() : this.entity.getName().getString())) / 2, pScreen.getYOffset() + 10, ChatFormatting.DARK_GRAY.getColor(), false);

        PageUtils.drawFormattedText(pGraphics, this.text, pScreen.getXOffset() + 25, pScreen.getYOffset() + 25, 100);

        if ( this.entity != null ) {

            PoseStack pPoseStack = pGraphics.pose();

            EntityRenderDispatcher renderDispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
            EntityRenderer<LivingEntity> render = (EntityRenderer<LivingEntity>) renderDispatcher.getRenderer(this.entity);

            if ( render instanceof LivingEntityRenderer<LivingEntity,?> renderer ) {
                EntityModel<LivingEntity> model = renderer.getModel();
                VertexConsumer vertexConsumer = pGraphics.bufferSource().getBuffer(NeoForgeRenderTypes.getUnlitTranslucent(renderer.getTextureLocation(this.entity)));

                pPoseStack.pushPose();
                pPoseStack.translate(pScreen.getXOffset() + (float) pScreen.getWidthSize() / 2 + 65,
                        pScreen.getYOffset() + (float) pScreen.getHeightSize() / 2, 50.0F);
                pPoseStack.mulPose(Axis.YP.rotationDegrees(((float) Util.getMillis() / 30) % 360));
                pPoseStack.mulPose(Axis.ZP.rotationDegrees(180));
                Lighting.setupForEntityInInventory();
                renderDispatcher.setRenderShadow(false);
                RenderSystem.runAsFancy(() -> renderDispatcher.render(this.entity, 0.0, 0.0, 0.0, 0.0F, 0.0F, pPoseStack, pGraphics.bufferSource(), 15728880));
                renderDispatcher.setRenderShadow(true);

                pPoseStack.popPose();
            }

        }
    }

    public void render(GuiGraphics pGraphics, int pMouseX, int pMouseY, Font pFont) {

    }

    public void init() {
        if (Minecraft.getInstance().level != null) this.entity = this.entitySupplier.apply(Minecraft.getInstance().level);
        if ( this.text == null ) this.text = Component.literal("");
    }
}
