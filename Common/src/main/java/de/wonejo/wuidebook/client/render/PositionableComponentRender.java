package de.wonejo.wuidebook.client.render;

import de.wonejo.wuidebook.api.client.PositionableRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

public final class PositionableComponentRender implements PositionableRender {

    private final Component text;

    public PositionableComponentRender ( Component pText ) {
        this.text = pText;
    }

    public void render(@NotNull GuiGraphics pGraphics, int pX, int pY) {
        pGraphics.drawString(Minecraft.getInstance().font, this.text, pX, pY, this.text.getStyle().getColor().getValue());
    }

}
