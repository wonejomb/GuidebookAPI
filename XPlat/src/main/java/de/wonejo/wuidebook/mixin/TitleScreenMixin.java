package de.wonejo.wuidebook.mixin;

import de.wonejo.wuidebook.client.btn.ConfigButton;
import de.wonejo.wuidebook.client.screen.ConfigScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {

    protected TitleScreenMixin(Component title) {
        super(title);
    }

    @Inject( method = "init", at = @At("RETURN") )
    public void initWidgets$wuidebook (CallbackInfo ci) {
        int l = this.height / 4 + 48;

        this.addRenderableWidget(new ConfigButton(this.width / 2 - 146, l + 72 + 13, (btn) -> {
            Minecraft.getInstance().setScreen(new ConfigScreen(this, Minecraft.getInstance().options));
        }));
    }

}
