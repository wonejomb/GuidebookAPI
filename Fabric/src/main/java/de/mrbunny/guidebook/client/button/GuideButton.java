package de.mrbunny.guidebook.client.button;

import de.mrbunny.guidebook.api.client.IButton;
import de.mrbunny.guidebook.api.client.IModScreen;
import de.mrbunny.guidebook.api.util.References;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class GuideButton extends Button implements IButton {

    protected static final ResourceLocation BUTTONS_LOC = new ResourceLocation(References.GUIDEBOOKAPI_ID, "textures/gui/book/icons.png");
    private final IModScreen screen;

    public GuideButton (Component pName, OnPress pPress, IModScreen pScreen, int pX, int pY, int pWidth, int pHeight ) {
        super(pX, pY, pWidth, pHeight, pName, pPress, DEFAULT_NARRATION);

        this.screen = pScreen;
    }

    @Environment(EnvType.CLIENT)
    protected void renderImage (@NotNull GuiGraphics pGraphics, int pX, int pY, int pImageXOffset, int pImageYOffset, int pWidth, int pHeight ) {
        pGraphics.blit(BUTTONS_LOC, pX, pY, pImageXOffset, pImageYOffset, pWidth, pHeight, 64, 48);
    }


    public List<Component> getHoveringText () {
        ArrayList<Component> list = new ArrayList<>();
        list.add(getMessage());
        return list;
    }

    public IModScreen getScreen() {
        return screen;
    }
}
