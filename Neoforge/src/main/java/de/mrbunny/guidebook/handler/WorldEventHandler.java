package de.mrbunny.guidebook.handler;

import de.mrbunny.guidebook.api.GuidebookAPI;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.config.IConfigValue;
import de.mrbunny.guidebook.api.util.References;
import de.mrbunny.guidebook.cfg.ModConfigManager;
import de.mrbunny.guidebook.ext.IPersistentDataExtension;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;

@Mod.EventBusSubscriber(modid = References.GUIDEBOOKAPI_ID)
public class WorldEventHandler {

    @SubscribeEvent
    public static void onPlayerJoinWorld (EntityJoinLevelEvent pEvent ) {
        if ( !pEvent.getEntity().level().isClientSide && pEvent.getEntity() instanceof Player player ) {
            CompoundTag moddedTag = ((IPersistentDataExtension) player).guidebookApiPersistentData();

            if (ModConfigManager.COMMON.shouldSpawnWithBook.get()) {

                for (IBook book : GuidebookAPI.getBooks().values()) {
                    IConfigValue<Boolean> bookShouldSpawn = ModConfigManager.COMMON.spawnBooks.get(book);

                    if ((bookShouldSpawn == null || bookShouldSpawn.get()) && !moddedTag.getBoolean("hasInitial" + book.getId().toString())) {
                        player.getInventory().add(GuidebookAPI.getStackFromBook(book));
                        moddedTag.putBoolean("hasInitial" + book.getId().toString(), true);
                    }
                }
            }
        }
    }

}
