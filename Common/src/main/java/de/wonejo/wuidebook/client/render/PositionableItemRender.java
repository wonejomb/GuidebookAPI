package de.wonejo.wuidebook.client.render;

import de.wonejo.wuidebook.api.client.PositionableRender;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public final class PositionableItemRender implements PositionableRender {

    private final ItemStack stack;

    public PositionableItemRender ( ItemStack pStack ) {
        this.stack = pStack;
    }

    public void render(@NotNull GuiGraphics pGraphics, int pX, int pY) {
        pGraphics.renderItem(this.stack, pX, pY);
    }

}
