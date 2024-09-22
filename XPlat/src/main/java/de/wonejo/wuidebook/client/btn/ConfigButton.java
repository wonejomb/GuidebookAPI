package de.wonejo.wuidebook.client.btn;

import de.wonejo.wuidebook.api.util.Constants;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ConfigButton extends Button {

    private static final ResourceLocation ACTIVE = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/gui/config/config_highlighted.png");
    private static final ResourceLocation INACTIVE = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "textures/gui/config/config.png");

    public ConfigButton(int x, int y, OnPress onPress) {
        super(x, y, 18, 18, Component.translatable("wuidebook.config.title.button"), onPress, DEFAULT_NARRATION);
    }

    protected void renderWidget(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        if ( this.isHoveredOrFocused() )
            guiGraphics.blit(ACTIVE, this.getX(), this.getY(), 0, 0, 0, 18, 18, 18, 18);
        else
            guiGraphics.blit(INACTIVE, this.getX(), this.getY(), 0, 0, 0, 18, 18, 18, 18);
    }

}
