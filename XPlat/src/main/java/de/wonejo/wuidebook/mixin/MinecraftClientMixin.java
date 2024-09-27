package de.wonejo.wuidebook.mixin;

import de.wonejo.wuidebook.api.config.ConfigFile;
import de.wonejo.wuidebook.api.config.ConfigManager;
import de.wonejo.wuidebook.api.util.McEnvironment;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftClientMixin {

    @Inject(method = "run", at = @At(value = "HEAD"))
    public void run$wuidebook ( CallbackInfo pInfo ) {
        ConfigManager.get().getFileList(McEnvironment.CLIENT).forEach(ConfigFile::loadFile);
    }

    @Inject(method = "stop", at = @At("HEAD"))
    public void stop$wuidebook ( CallbackInfo pInfo ) {
        ConfigManager.get().getFileList(McEnvironment.CLIENT).forEach(ConfigFile::unloadFile);
    }

}
