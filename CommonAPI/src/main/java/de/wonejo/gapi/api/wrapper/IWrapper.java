package de.wonejo.gapi.api.wrapper;

import de.wonejo.gapi.api.client.IModScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;

public interface IWrapper {

    void render (GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen );

    boolean isMouseOnWrapper ( double pMouseX, double pMouseY );
    boolean canView ();

}
