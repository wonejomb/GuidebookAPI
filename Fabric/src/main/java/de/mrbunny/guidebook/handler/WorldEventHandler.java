package de.mrbunny.guidebook.handler;

import de.mrbunny.guidebook.api.GuidebookAPI;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.config.IConfigValue;
import de.mrbunny.guidebook.config.ModConfigManager;
import de.mrbunny.guidebook.ext.IEntityDataExtension;
import de.mrbunny.guidebook.ext.NBTTags;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;


public class WorldEventHandler {

    public static void loadWorldEvents () {
        onJoinEntityEvent();
    }

    private static void onJoinEntityEvent () {
        ServerEntityEvents.ENTITY_LOAD.register((entity, world) -> {
            if ( !entity.level().isClientSide && entity instanceof Player player ) {
                CompoundTag moddedTag = getModTag(player);

                for (IBook book : GuidebookAPI.getBooks().values()) {
                    boolean bookShouldSpawn = book.shouldSpawnWithBook();

                    if ((bookShouldSpawn) && !moddedTag.getBoolean("hasInitial" + book.getId().toString())) {
                        player.getInventory().add(GuidebookAPI.getStackFromBook(book));
                        moddedTag.putBoolean("hasInitial" + book.getId().toString(), true);
                    }
                }
            }
        });
    }

    private static @NotNull CompoundTag getModTag (Player pPlayer) {
        IEntityDataExtension extension = (IEntityDataExtension) pPlayer;

        CompoundTag tag = extension.getPersistentData();
        CompoundTag persistTag;

        if ( tag.contains(NBTTags.PLAYER_NBT_TAG) ) {
            persistTag = tag.getCompound(NBTTags.PLAYER_NBT_TAG);
        } else {
            persistTag = new CompoundTag();
            tag.put(NBTTags.PLAYER_NBT_TAG, persistTag);
        }

        return persistTag;
    }
}
