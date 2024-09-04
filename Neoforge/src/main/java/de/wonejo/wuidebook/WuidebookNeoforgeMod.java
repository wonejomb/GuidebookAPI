package de.wonejo.wuidebook;

import de.wonejo.wuidebook.api.util.RegistrySource;
import de.wonejo.wuidebook.core.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

@Mod(WuidebookCommon.MOD_ID)
public class WuidebookNeoforgeMod {

    public WuidebookNeoforgeMod (IEventBus pEventBus) {
        this.registryInit(pEventBus);

        WuidebookCommon.get().init();
    }

    private void registryInit ( IEventBus pEventBus ) {
        this.bindRegistry(pEventBus, Registries.ITEM, ModItems::setupItemRegistry);
    }

    private <T> void bindRegistry  (@NotNull IEventBus pEventBus, ResourceKey<Registry<T>> pRegistry, Consumer<RegistrySource<T>> pSource) {
        pEventBus.addListener(RegisterEvent.class, (event) -> {
            if ( pRegistry.equals(event.getRegistryKey()) )
                pSource.accept((id, obj) -> event.register(pRegistry, ResourceLocation.fromNamespaceAndPath(WuidebookCommon.MOD_ID, id), () -> obj));
        });
    }

}
