package de.mrbunny.guidebook.handler;

import de.mrbunny.guidebook.api.GuidebookAPI;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.config.IConfigValue;
import de.mrbunny.guidebook.api.util.References;
import de.mrbunny.guidebook.cfg.ModConfigManager;
import de.mrbunny.guidebook.cfg.ModConfigurations;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.neoforged.neoforge.items.ItemHandlerHelper;

@Mod.EventBusSubscriber(modid = References.GUIDEBOOKAPI_ID)
public class WorldEventHandler {

    @SubscribeEvent
    public static void onPlayerJoinWorld (EntityJoinLevelEvent pEvent ) {
        if ( !pEvent.getEntity().level().isClientSide && pEvent.getEntity() instanceof Player player ) {
            CompoundTag moddedTag = getModTag(player, References.GUIDEBOOKAPI_ID);

            if (ModConfigManager.COMMON.shouldSpawnWithBook.get()) {

                for (IBook book : GuidebookAPI.getBooks().values()) {
                    IConfigValue<Boolean> bookShouldSpawn = ModConfigManager.COMMON.spawnBooks.get(book);

                    if ((bookShouldSpawn.get()) && !moddedTag.getBoolean("hasInitial" + book.getId().toString())) {
                        player.getInventory().add(GuidebookAPI.getStackFromBook(book));
                        moddedTag.putBoolean("hasInitial" + book.getId().toString(), true);
                    }
                }
            }
        }
    }

    private static CompoundTag getModTag ( Player pPlayer, String pModId ) {
        CompoundTag tag = pPlayer.getPersistentData();
        CompoundTag persistTag;

        if (tag.contains(Player.PERSISTED_NBT_TAG))
            persistTag = tag.getCompound(Player.PERSISTED_NBT_TAG);
        else {
            persistTag = new CompoundTag();
            tag.put(Player.PERSISTED_NBT_TAG, persistTag);
        }

        CompoundTag modTag;
        if (persistTag.contains(pModId)) {
            modTag = persistTag.getCompound(pModId);
        } else {
            modTag = new CompoundTag();
            persistTag.put(pModId, modTag);
        }
        return modTag;
    }
}
