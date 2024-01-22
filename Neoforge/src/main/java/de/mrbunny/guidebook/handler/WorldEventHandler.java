package de.mrbunny.guidebook.handler;

import de.mrbunny.guidebook.api.GuidebookAPI;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.util.References;
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
        if (!pEvent.getEntity().getCommandSenderWorld().isClientSide && pEvent.getEntity() instanceof Player player) {
            CompoundTag tag = getModTag(player, References.GUIDEBOOKAPI_ID);
            if (ModConfigurations.COMMON.shouldSpawnWithBooks.get()) {
                for (IBook book : GuidebookAPI.getBooks().values()) {
                    ModConfigSpec.ConfigValue<Boolean> bookSpawnConfig = ModConfigurations.COMMON.spawnBooks.get(book);
                    if ((bookSpawnConfig == null || bookSpawnConfig.get()) && !tag.getBoolean("hasInitial" + book.getId().toString())) {
                        ItemHandlerHelper.giveItemToPlayer(player, GuidebookAPI.getStackFromBook(book));
                        tag.putBoolean("hasInitial" + book.getId().toString(), true);
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
