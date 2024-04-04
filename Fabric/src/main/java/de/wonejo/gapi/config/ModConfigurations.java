package de.wonejo.gapi.config;

import com.google.common.collect.Maps;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.config.IConfigValue;
import de.wonejo.gapi.api.impl.config.ConfigFile;
import de.wonejo.gapi.api.impl.config.ConfigProvider;
import de.wonejo.gapi.registry.BookRegistry;
import net.fabricmc.loader.api.FabricLoader;


import java.awt.*;
import java.util.Map;

public class ModConfigurations {

    private static final ConfigFile COMMON_CONFIG_FILE = ConfigFile.of(FabricLoader.getInstance().getConfigDir(), "GuidebookApi_Common");
    private static final ConfigFile CLIENT_CONFIG_FILE = ConfigFile.of(FabricLoader.getInstance().getConfigDir(), "GuidebookApi_Client");

    public static final CommonConfigurations COMMON = new CommonConfigurations();
    public static final ClientConfigurations CLIENT = new ClientConfigurations();

    public static void setupConfigurations () {
        COMMON_CONFIG_FILE.provider(COMMON);
        CLIENT_CONFIG_FILE.provider(CLIENT);

        CLIENT_CONFIG_FILE.init();
        COMMON_CONFIG_FILE.init();
    }

    public static final class ClientConfigurations extends ConfigProvider {

        public IConfigValue<Integer> textColor;
        public IConfigValue<Integer> entryColor;
        public IConfigValue<Integer> entryBetweenColor;
        public Map<IBook, IConfigValue<Integer>> bookColors = Maps.newHashMap();

        public void buildConfigurations() {

            this.entryColor = this.createConfig(
                    "textColor",
                    "Define the color of all the texts in the books",
                    new Color(164, 135, 125).getRGB());

            this.entryColor =  this.createConfig(
                    "entryColor",
                    "Define the color of the entries when the mouse isn't between this.",
                    new Color(75, 61, 54).getRGB());

            this.entryBetweenColor = this.createConfig(
                    "entryBetweenColor",
                    "Define the color of the entries when the mouse is between this.",
                    new Color(39, 26, 23).getRGB()
            );

            for ( IBook book : BookRegistry.getLoadedBooks() )
                this.bookColors.put(book,
                        this.createConfig(
                                "bookColor.%s.%s".formatted(book.id().getNamespace(), book.id().getPath()),
                                "Define the color of the book: %s".formatted(book.id().getPath()),
                                book.color().getRGB()
                        )
                );
        }

        public void defineConfigurations() {
            this.bookColors.clear();

            this.textColor = this.getConfigById("textColor");
            this.entryColor = this.getConfigById("entryColor");
            this.entryBetweenColor = this.getConfigById("entryBetweenColor");

            for ( IBook book : BookRegistry.getLoadedBooks() )
                this.bookColors.put(book, this.getConfigById("bookColor.%s.%s".formatted(book.id().getNamespace(), book.id().getPath())));
        }
    }

    public static final class CommonConfigurations extends ConfigProvider {
        public IConfigValue<Boolean> shouldSpawnWithBook;
        public Map<IBook, IConfigValue<Boolean>> spawnBooks = Maps.newHashMap();

        public void buildConfigurations() {
            this.shouldSpawnWithBook = createConfig(
                    "shouldSpawnWithBook",
                    "Define if the player should spawn with books. This configurations is for general use and encapsulate all the books",
                    true);

            for ( IBook book : BookRegistry.getLoadedBooks() )
                this.spawnBooks.put(book,
                        this.createConfig(
                                "spawn.%s.%s".formatted(book.id().getNamespace(), book.id().getPath()),
                                "Define if player should spawn with book: %s. This config apply only to that book. (%s)".formatted(book.id().getPath(), book.id().getPath()),
                                book.shouldSpawnWithBook()
                ));

        }

        public void defineConfigurations() {
            this.spawnBooks.clear();

            this.shouldSpawnWithBook = this.getConfigById("shouldSpawnWithBook");

            for ( IBook book : BookRegistry.getLoadedBooks() )
                this.spawnBooks.put(book, this.getConfigById("spawn.%s.%s".formatted(book.id().getNamespace(), book.id().getPath())));
        }
    }
}
