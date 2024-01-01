package de.mrbunny.guidebook.api.wrapper;

import de.mrbunny.guidebook.api.client.IModScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;

public interface IWrapper {

    boolean canView ();

    void render ( GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen );

    boolean isMouseOnWrapper ( double pMouseX, double pMouseY );

    void onHoverOver ( int pMouseX, int pMouseY );
}

