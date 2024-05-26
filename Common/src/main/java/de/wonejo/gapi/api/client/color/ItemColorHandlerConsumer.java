package de.wonejo.gapi.api.client.color;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.level.ItemLike;

@FunctionalInterface
public interface ItemColorHandlerConsumer {

    void register ( ItemColor pColor, ItemLike... pItems );

}
