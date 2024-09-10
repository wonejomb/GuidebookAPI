package de.wonejo.wuidebook.mixin;

import de.wonejo.wuidebook.WuidebookCommonMod;
import de.wonejo.wuidebook.api.config.ConfigFile;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.server.dedicated.DedicatedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({DedicatedServer.class, IntegratedServer.class})
public class MinecraftServerMixin {

    @Inject(method = "initServer", at = @At("HEAD"))
    public void initServer$wuidebook( CallbackInfo pCallback ) {
        WuidebookCommonMod.get().configManager().getServerFiles().forEach(ConfigFile::initializeFile);
    }

    @Inject(method = "stopServer", at = @At("HEAD"))
    public void stopServer$wuidebook ( CallbackInfo pCallback ) {
        WuidebookCommonMod.get().configManager().getServerFiles().forEach(ConfigFile::unloadFile);
    }

}
