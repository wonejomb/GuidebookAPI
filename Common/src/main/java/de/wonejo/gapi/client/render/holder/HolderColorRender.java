package de.wonejo.gapi.client.render.holder;

import de.wonejo.gapi.api.util.Constants;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class HolderColorRender extends AbstractHolderRender{

    private static final ResourceLocation COLOR_WIDGET = new ResourceLocation(Constants.MOD_ID, "textures/gui/holder/color_holder.png");

    private final Color color;

    public HolderColorRender(Component pText, Color pColor) {
        super(pText);
        this.color = pColor;
    }


}
