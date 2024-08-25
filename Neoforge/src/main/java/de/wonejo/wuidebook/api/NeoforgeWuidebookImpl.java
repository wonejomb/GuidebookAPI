package de.wonejo.wuidebook.api;

import de.wonejo.wuidebook.api.client.util.ItemColorConsumer;
import de.wonejo.wuidebook.api.compat.WuidebookAbstraction;
import de.wonejo.wuidebook.api.compat.WuidebookCompat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.neoforged.fml.ModLoader;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.loading.FMLPaths;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Type;

import java.nio.file.Path;
import java.util.List;

public class NeoforgeWuidebookImpl implements WuidebookAPIAbstraction {

    private static final Type WUIDEBOOK_TYPE = Type.getType(WuidebookCompat.class);

    public void applyModelColors(ItemColorConsumer pConsumer) {

    }

    public List<WuidebookAbstraction> onGatherWuidebookAbstractions() {
        return List.of();
    }

    public void playSound(SoundEvent pSound) {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(pSound, 1.0F));
    }

    public <T> HolderSet.Named<T> getOrCreateTag( @NotNull Registry<T> pRegistry, TagKey<T> pKey ) {
        return pRegistry.getOrCreateTag(pKey);
    }

    public String getActiveNamespace() {
        return ModLoadingContext.get().getActiveNamespace();
    }

    public Path getConfigPath() {
        return FMLPaths.CONFIGDIR.get();
    }

    public boolean isNeoforge() {
        return true;
    }

    public boolean isFabric() {
        return false;
    }

    public boolean isForge() {
        return false;
    }

}
