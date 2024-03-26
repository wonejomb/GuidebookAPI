package de.wonejo.gapi.config;

import com.google.common.collect.Lists;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.config.IConfigValue;
import de.wonejo.gapi.api.impl.config.ConfigFile;
import de.wonejo.gapi.api.impl.config.ConfigProvider;
import de.wonejo.gapi.api.registry.BookRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.loading.FMLPaths;

import java.util.List;
import java.util.Map;

public class ModConfigurations {

    private static final ConfigFile COMMON_CONFIG_FILE = ConfigFile.of(FMLPaths.CONFIGDIR.get(), "gapi_common");

    public static final CommonConfigurations COMMON = new CommonConfigurations();

    public static void setupConfigurations () {
        COMMON_CONFIG_FILE.provider(COMMON);
        COMMON_CONFIG_FILE.init();
    }

    public static final class CommonConfigurations extends ConfigProvider {
        public IConfigValue<Boolean> shouldSpawnWithBook;
        public List<IConfigValue<Boolean>> spawnBooks = Lists.newArrayList();

        public void buildConfigurations() {
            this.shouldSpawnWithBook = createConfig(
                    "shouldSpawnWithBook",
                    "Define if the player should spawn with books. This configurations is for general use and encapsulate all the books",
                    true);

            for ( Map.Entry<ResourceLocation, IBook> bookEntry : BookRegistry.getLoadedBooks().entrySet() )
                this.spawnBooks.add(
                        this.createConfig(
                                "spawn.%s".formatted(bookEntry.getKey().getPath()),
                                "Define if player should spawn with book: %s. This config apply only to that book. (%s)".formatted(bookEntry.getKey().getPath(), bookEntry.getKey().getPath()),
                            bookEntry.getValue().shouldSpawnWithBook()
                ));

        }
    }
}
