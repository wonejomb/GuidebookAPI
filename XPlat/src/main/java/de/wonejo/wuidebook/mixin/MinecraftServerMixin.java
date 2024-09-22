package de.wonejo.wuidebook.mixin;

import com.mojang.datafixers.DataFixer;
import de.wonejo.wuidebook.api.config.ConfigFile;
import de.wonejo.wuidebook.api.config.ConfigManager;
import de.wonejo.wuidebook.api.util.McEnvironment;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.Services;
import net.minecraft.server.WorldStem;
import net.minecraft.server.level.progress.ChunkProgressListenerFactory;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.net.Proxy;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    public void init$wuidebook(Thread serverThread, LevelStorageSource.LevelStorageAccess storageSource, PackRepository packRepository, WorldStem worldStem, Proxy proxy, DataFixer fixerUpper, Services services, ChunkProgressListenerFactory progressListenerFactory, CallbackInfo ci) {
        ConfigManager.get().getFileList(McEnvironment.SERVER).forEach(ConfigFile::loadFile);
    }

    @Inject(method = "stopServer", at = @At("HEAD"))
    public void stopServer$wuidebook (CallbackInfo ci) {
        ConfigManager.get().getFileList(McEnvironment.SERVER).forEach(ConfigFile::unloadFile);
    }

}
