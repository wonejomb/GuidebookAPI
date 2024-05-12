package de.wonejo.gapi.mixin;

import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.renderer.entity.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemRenderer.class)
public interface ItemColorsAccessor {
    @Accessor("itemColors")
    ItemColors getColors ();
}
