package de.wonejo.gapi.config;

import com.google.common.collect.Maps;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.config.IConfigValue;
import de.wonejo.gapi.api.impl.config.ConfigFile;
import de.wonejo.gapi.api.impl.config.ConfigProvider;
import de.wonejo.gapi.api.impl.config.serializer.ColorValueSerializer;
import de.wonejo.gapi.api.impl.config.serializer.ConfigValueSerializers;
import de.wonejo.gapi.registry.BookRegistry;
import net.neoforged.fml.loading.FMLPaths;

import java.awt.*;
import java.util.Map;

public class ModConfigurations {

    private static final ConfigFile COMMON_CONFIG_FILE = ConfigFile.of(FMLPaths.CONFIGDIR.get(), "GuidebookApi_Common");
    private static final ConfigFile CLIENT_CONFIG_FILE = ConfigFile.of(FMLPaths.CONFIGDIR.get(), "GuidebookApi_Client");

    public static final CommonConfigurations COMMON = new CommonConfigurations();
    public static final ClientConfigurations CLIENT = new ClientConfigurations();

    public static void setupConfigurations () {
        COMMON_CONFIG_FILE.provider(COMMON);
        CLIENT_CONFIG_FILE.provider(CLIENT);

        CLIENT_CONFIG_FILE.init();
        COMMON_CONFIG_FILE.init();
    }

    public static final class ClientConfigurations extends ConfigProvider {

        public IConfigValue<Color> textColor;
        public IConfigValue<Color> entryColor;
        public IConfigValue<Color> entryBetweenColor;

        public Map<IBook, IConfigValue<Color>> pageBookColors = Maps.newHashMap();
        public Map<IBook, IConfigValue<Color>> bookColors = Maps.newHashMap();

        public void buildConfigurations() {
            this.entryColor = this.createConfig(
                    ConfigValueSerializers.createColorSerializer(ColorValueSerializer.ColorFormat.RGB),
                    "textColor",
                    "Define the color of all the texts in the books",
                    new Color(164, 135, 125)
            );

            this.entryColor =  this.createConfig(
                    ConfigValueSerializers.createColorSerializer(ColorValueSerializer.ColorFormat.RGB),
                    "entryColor",
                    "Define the color of the entries when the mouse isn't between this.",
                    new Color(75, 61, 54));

            this.entryBetweenColor = this.createConfig(
                    ConfigValueSerializers.createColorSerializer(ColorValueSerializer.ColorFormat.RGB),
                    "entryBetweenColor",
                    "Define the color of the entries when the mouse is between this.",
                    new Color(39, 26, 23)
            );

            for ( IBook book : BookRegistry.getLoadedBooks() ) {
                this.bookColors.put(book,
                        this.createConfig(
                                ConfigValueSerializers.createColorSerializer(ColorValueSerializer.ColorFormat.RGB),
                                "bookColor.%s.%s".formatted(book.id().getNamespace(), book.id().getPath()),
                                "Define the color of the book: %s".formatted(book.id().getPath()),
                                book.bookColor()
                        )
                );

                this.pageBookColors .put(book,
                        this.createConfig(
                                ConfigValueSerializers.createColorSerializer(ColorValueSerializer.ColorFormat.RGB),
                                "bookColor.pages.%s.%s".formatted(book.id().getNamespace(), book.id().getPath()),
                                "Define the color of the pages of the book: %s".formatted(book.id().getPath()),
                                book.pagesColor()));
            }
        }

        public void defineConfigurations() {
            this.bookColors.clear();
            this.pageBookColors.clear();

            this.textColor = this.getConfigById("textColor");
            this.entryColor = this.getConfigById("entryColor");
            this.entryBetweenColor = this.getConfigById("entryBetweenColor");

            for ( IBook book : BookRegistry.getLoadedBooks() ) {
                this.bookColors.put(book, this.getConfigById("bookColor.%s.%s".formatted(book.id().getNamespace(), book.id().getPath())));
                this.pageBookColors.put(book, this.getConfigById("bookColor.pages.%s.%s".formatted(book.id().getNamespace(), book.id().getPath())));
            }
        }
    }

    public static final class CommonConfigurations extends ConfigProvider {
        public IConfigValue<Boolean> shouldSpawnWithBook;
        public Map<IBook, IConfigValue<Boolean>> spawnBooks = Maps.newHashMap();

        public void buildConfigurations() {
            this.shouldSpawnWithBook = createConfig(
                    ConfigValueSerializers.createBooleanSerializer(),
                    "shouldSpawnWithBook",
                    "Define if the player should spawn with books. This configurations is for general use and encapsulate all the books",
                    true);

            for ( IBook book : BookRegistry.getLoadedBooks() )
                this.spawnBooks.put(book,
                        this.createConfig(
                                ConfigValueSerializers.createBooleanSerializer(),
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
