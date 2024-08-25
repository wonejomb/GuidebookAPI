package de.wonejo.wuidebook.core;

import de.wonejo.wuidebook.api.util.RegistrySource;
import de.wonejo.wuidebook.item.GuideBaseItem;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

public class ModItems {

    public static final GuideBaseItem GUIDE_BASE = new GuideBaseItem();

    public static void setupItemRegistry (@NotNull RegistrySource<Item> pRegistry) {
        pRegistry.register("guide_base", GUIDE_BASE);
    }

}
