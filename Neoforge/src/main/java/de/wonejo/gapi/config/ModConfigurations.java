package de.wonejo.gapi.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.config.IConfigValue;
import de.wonejo.gapi.api.impl.config.ConfigFile;
import de.wonejo.gapi.api.impl.config.ConfigProvider;
import de.wonejo.gapi.api.registry.BookRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.loading.FMLPaths;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class ModConfigurations {

    private static final ConfigFile COMMON_CONFIG_FILE = ConfigFile.of(FMLPaths.CONFIGDIR.get(), "GuidebookApi-Common");
    private static final ConfigFile CLIENT_CONFIG_FILE = ConfigFile.of(FMLPaths.CONFIGDIR.get(), "GuidebookApi-Client");

    public static final CommonConfigurations COMMON = new CommonConfigurations();
    public static final ClientConfigurations CLIENT = new ClientConfigurations();

    public static void setupConfigurations () {
        COMMON_CONFIG_FILE.provider(COMMON);
        CLIENT_CONFIG_FILE.provider(CLIENT);

        CLIENT_CONFIG_FILE.init();
        COMMON_CONFIG_FILE.init();
    }

    public static final class ClientConfigurations extends ConfigProvider {

        public IConfigValue<Integer> entryColor;
        public IConfigValue<Integer> entryBetweenColor;

        public Map<IBook, IConfigValue<Integer>> bookColors = Maps.newHashMap();

        public void buildConfigurations() {

            this.entryColor = this.createConfig(
                    "entryColor",
                    "Define the color of the entries when the mouse isn't between this.",
                    new Color(75, 61, 54).getRGB());

            this.entryBetweenColor = this.createConfig(
                    "entryBetweenColor",
                    "Define the color of the entries when the mouse is between this.",
                    new Color(39, 26, 23).getRGB()
            );

            for ( Map.Entry<ResourceLocation, IBook> book : BookRegistry.getLoadedBooks().entrySet() )
                this.bookColors.put(book.getValue(),
                            this.createConfig(
                                    "bookColor.%s".formatted(book.getKey().getPath()),
                                    "Define the color of the book: %s".formatted(book.getKey().getPath()),
                                    book.getValue().color().getRGB()
                            )
                        );


        }
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
