package de.wonejo.wuidebook.api;

import de.wonejo.wuidebook.api.client.util.ItemColorConsumer;
import de.wonejo.wuidebook.api.compat.WuidebookAbstraction;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.List;

/**
 * WuidebookAPI mod checks and utility methods.
 */
public interface WuidebookAPIAbstraction {

    /**
     * Only to register the model colors on the client proxy
     * <p>Env: Client</p>
     */
    void applyModelColors (ItemColorConsumer pConsumer);

    /**
     *
     */
    List<WuidebookAbstraction> onGatherWuidebookAbstractions ();

    /**
     * Plays a sound in the client with the SoundEngine
     * <p>Env: Client</p>
     */
    void playSound (SoundEvent pSound);

    /**
     * This is only a utility method, used for the ItemRotator
     * <p>Env: Common ( i think )</p>
     */
    <T> HolderSet.Named<T> getOrCreateTag ( @NotNull Registry<T> pRegistry, TagKey<T> pKey );

    /**
     * Returns the active namespace on the load phase of mod loaders.
     */
    String getActiveNamespace ();

    /**
     * Return the config path defined in the loader
     */
    Path getConfigPath ();

    /**
     * Check if it is running Neoforge loader / Fancy Mod Loader.
     * <p>Env: Common</p>
     */
    boolean isNeoforge ();
    /**
     * Check if it is running on Fabric loader.
     * <p>Env: Common</p>
     */
    boolean isFabric ();
    /**
     * Check if it is running on Forge loader / Forge Mod Loader.
     * <p>Env: Common</p>
     */
    boolean isForge ();

}
