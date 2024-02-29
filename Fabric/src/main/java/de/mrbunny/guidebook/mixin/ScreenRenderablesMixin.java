package de.mrbunny.guidebook.mixin;

import de.mrbunny.guidebook.ext.IScreenRenderablesAccessor;
import net.minecraft.client.gui.components.Renderable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.gui.screens.Screen;

import java.util.List;

@Mixin(Screen.class)
public class ScreenRenderablesMixin implements IScreenRenderablesAccessor{

    @Shadow @Final private List<Renderable> renderables;

    public List<Renderable> getRenderables() {
        return this.renderables;
    }

}
