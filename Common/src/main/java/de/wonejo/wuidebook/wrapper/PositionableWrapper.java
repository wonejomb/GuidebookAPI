package de.wonejo.wuidebook.wrapper;

import com.mojang.blaze3d.vertex.ByteBufferBuilder;
import de.wonejo.wuidebook.api.client.PositionableRender;
import de.wonejo.wuidebook.client.button.OpenLinkButton;
import de.wonejo.wuidebook.client.render.*;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class PositionableWrapper<T> {

    private final T obj;
    private final int width;
    private final int height;
    private int x;
    private int y;

    public PositionableWrapper ( T pObj, int pWidth, int pHeight ) {
        this.obj = pObj;
        this.width = pWidth;
        this.height = pHeight;
    }

    public void render (GuiGraphics pGraphics, int pMouseX, int pMouseY) {
        this.getRenderer().render(pGraphics, this.x, this.y);
    }

    @NotNull
    private PositionableRender getRenderer () {
        if ( this.obj instanceof Component component ) {
            return new PositionableComponentRender(component);
        } else if ( this.obj instanceof EntityType<?> entityRender ) {
            return new PositionableEntityRender(entityRender);
        } else if ( this.obj instanceof ResourceLocation location ) {
            return new PositionableImageRender(location, this.width, this.height);
        } else if ( this.obj instanceof ItemStack itemStack ) {
            return new PositionableItemRender(itemStack);
        } else if ( this.obj instanceof OpenLinkButton button ) {
            return new PositionableLinkRender(button);
        }

        throw new UnsupportedOperationException("Can not get render for class: " + this.obj.getClass());
    }


}
