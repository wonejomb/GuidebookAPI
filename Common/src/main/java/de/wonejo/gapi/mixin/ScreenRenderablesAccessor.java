package de.wonejo.gapi.mixin;

import de.wonejo.gapi.ext.IScreenRenderablesAccessor;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(Screen.class)
public class ScreenRenderablesAccessor implements IScreenRenderablesAccessor {

    @Shadow @Final private List<Renderable> renderables;

    public List<Renderable> getAllRenderables() {
        return this.renderables;
    }

}
