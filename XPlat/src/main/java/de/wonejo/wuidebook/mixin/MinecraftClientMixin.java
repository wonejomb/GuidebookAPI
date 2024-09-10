package de.wonejo.wuidebook.mixin;

import de.wonejo.wuidebook.WuidebookCommonMod;
import de.wonejo.wuidebook.api.config.ConfigFile;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {

    @Inject(method = "run", at = @At("HEAD"))
    public void run$wuidebook (CallbackInfo pCallback) {
        WuidebookCommonMod.get().configManager().getClientFiles().forEach(ConfigFile::initializeFile);
    }

}
