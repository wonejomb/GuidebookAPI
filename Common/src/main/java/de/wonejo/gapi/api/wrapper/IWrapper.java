package de.wonejo.gapi.api.wrapper;

import de.wonejo.gapi.api.client.IModScreen;
import de.wonejo.gapi.api.util.CanView;
import de.wonejo.gapi.api.util.ITick;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.RegistryAccess;

import java.util.function.Supplier;

public interface IWrapper<T> extends CanView, ITick, Supplier<T> {

    void render (GuiGraphics pGraphics, RegistryAccess pAccess, int pMouseX, int pMouseY, IModScreen pScreen);

    boolean isMouseOnWrapper ( double pMouseX, double pMouseY );
}
