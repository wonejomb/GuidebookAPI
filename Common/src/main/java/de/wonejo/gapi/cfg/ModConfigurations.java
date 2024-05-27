package de.wonejo.gapi.cfg;

import com.google.common.collect.ImmutableMap;
import de.wonejo.gapi.api.book.IBook;
import de.wonejo.gapi.api.cfg.IConfigFile;
import de.wonejo.gapi.api.cfg.IConfigValue;
import de.wonejo.gapi.impl.cfg.ConfigProvider;
import de.wonejo.gapi.impl.cfg.serializer.ConfigValueSerializers;
import de.wonejo.gapi.impl.service.Services;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public final class ModConfigurations {

    public static final CommonConfigurations COMMON_PROVIDER = new CommonConfigurations();
    public static final ClientConfigurations CLIENT_PROVIDER = new ClientConfigurations();
    public static final DebugConfigurations DEBUG_PROVIDER = new DebugConfigurations();

    private static final IConfigFile COMMON_CFG = IConfigFile.of(Services.PLATFORM.getConfigPath(), "guidebookapi_common");
    private static final IConfigFile CLIENT_CFG = IConfigFile.of(Services.PLATFORM.getConfigPath(), "guidebookapi_client");
    private static final IConfigFile DEBUG_CFG = IConfigFile.of(Services.PLATFORM.getConfigPath(), "guidebookapi_debug");

    public static void setupDebugCfg () {
        DEBUG_CFG.provider(DEBUG_PROVIDER).init();
    }

    public static void setupCfg () {
        CLIENT_CFG.provider(CLIENT_PROVIDER).init();
        COMMON_CFG.provider(COMMON_PROVIDER).init();
    }

    public static final class CommonConfigurations extends ConfigProvider {
        private IConfigValue<Boolean> shouldSpawnWithBook;
        private final Map<IBook, IConfigValue<Boolean>> spawnBooks = new HashMap<>();

        public void buildConfigurations() {
            this.shouldSpawnWithBook = this.createConfig(ConfigValueSerializers.createBooleanSerializer(),
                    "shouldSpawnWithBooks",
                    "This overrides all the other configurations of the spawn, if disable, the player spawns with any book.",
                    true);

            for ( IBook book : Services.BOOK_REGISTRY.getLoadedBooks() ) {
                this.spawnBooks.putIfAbsent(book, this.createConfig(ConfigValueSerializers.createBooleanSerializer(),
                        "spawn.%s.%s".formatted(book.id().getNamespace(), book.id().getPath()),
                        "This config define if the player can spawn with the book: %s".formatted(book.id()),
                        book.shouldSpawnWithBook()));
            }
        }

        public void defineConfigurations() {
            this.spawnBooks.clear();
            this.shouldSpawnWithBook = this.getConfigById("shouldSpawnWithBooks");

            for (IBook book : Services.BOOK_REGISTRY.getLoadedBooks()) {
                this.spawnBooks.putIfAbsent(book, this.getConfigById("spawn.%s.%s".formatted(book.id().getNamespace(), book.id().getPath())));
            }

        }

        public boolean shouldSpawnWithBook () {
            return this.shouldSpawnWithBook.get();
        }
        public Map<IBook, IConfigValue<Boolean>> getSpawnBooks() {
            return spawnBooks;
        }
    }

    public static final class ClientConfigurations extends ConfigProvider {
        private final Map<IBook, IConfigValue<Color>> bookTextColors = new HashMap<>();
        private final Map<IBook, IConfigValue<Color>> pageBookColors = new HashMap<>();
        private final Map<IBook, IConfigValue<Color>> bookColors = new HashMap<>();
        private final Map<IBook, IConfigValue<Color>> entryColors = new HashMap<>();
        private final Map<IBook, IConfigValue<Color>> entryAboveColors = new HashMap<>();

        public void buildConfigurations() {
            for (IBook book : Services.BOOK_REGISTRY.getLoadedBooks()) {
                this.bookColors.putIfAbsent(book, this.createConfig(ConfigValueSerializers.createColorSerializer(),
                        "bookColor.%s.%s".formatted(book.id().getNamespace(), book.id().getPath()),
                        "Define the color of the book: %s".formatted(book.id().getPath()),
                        book.bookColor())
                );

                this.pageBookColors.putIfAbsent(book, this.createConfig(ConfigValueSerializers.createColorSerializer(),
                        "bookColor.pages.%s.%s".formatted(book.id().getNamespace(), book.id().getPath()),
                        "Define the color of the pages of the book: %s".formatted(book.id().getPath()),
                        book.pagesColor()));

                this.bookTextColors.putIfAbsent(book, this.createConfig(ConfigValueSerializers.createColorSerializer(),
                        "bookColor.text.%s.%s".formatted(book.id().getNamespace(), book.id().getPath()),
                        "Define the color of the text in the book screens.",
                        book.textColor()));

                this.entryColors.putIfAbsent(book, this.createConfig(ConfigValueSerializers.createColorSerializer(),
                        "bookColor.entry.%s.%s".formatted(book.id().getNamespace(), book.id().getPath()),
                        "The color of the entries of the screens in the book: %s:%s".formatted(book.id().getNamespace(), book.id().getPath()),
                        book.entryColor()));

                this.entryColors.putIfAbsent(book, this.createConfig(ConfigValueSerializers.createColorSerializer(),
                        "bookColor.entry.%s.%s".formatted(book.id().getNamespace(), book.id().getPath()),
                        "The color of the entries of the screens in the book: %s:%s".formatted(book.id().getNamespace(), book.id().getPath()),
                        book.entryColor()));

                this.entryAboveColors.putIfAbsent(book, this.createConfig(ConfigValueSerializers.createColorSerializer(),
                        "bookColor.entry.above.%s.%s".formatted(book.id().getNamespace(), book.id().getPath()),
                        "The color of the entry of the screens in the book when the mouse is above this. Book: %s:%s".formatted(book.id().getNamespace(), book.id().getPath()),
                        book.entryAboveColor()));
            }
        }

        public void defineConfigurations() {
            this.bookColors.clear();
            this.pageBookColors.clear();
            this.bookTextColors.clear();
            this.entryColors.clear();
            this.entryAboveColors.clear();

            for ( IBook book : Services.BOOK_REGISTRY.getLoadedBooks()) {
                this.bookColors.putIfAbsent(book, this.getConfigById("bookColor.%s.%s".formatted(book.id().getNamespace(), book.id().getPath())));
                this.pageBookColors.putIfAbsent(book, this.getConfigById("bookColor.pages.%s.%s".formatted(book.id().getNamespace(), book.id().getPath())));
                this.bookTextColors.putIfAbsent(book, this.getConfigById("bookColor.text.%s.%s".formatted(book.id().getNamespace(), book.id().getPath())));
                this.entryColors.putIfAbsent(book, this.getConfigById("bookColor.entry.%s.%s".formatted(book.id().getNamespace(), book.id().getPath())));
                this.entryAboveColors.putIfAbsent(book, this.getConfigById("bookColor.entry.above.%s.%s".formatted(book.id().getNamespace(), book.id().getPath())));
            }
        }

        public Map<IBook, IConfigValue<Color>> getBookColors() {
            return ImmutableMap.copyOf(bookColors);
        }

        public Map<IBook, IConfigValue<Color>> getPageBookColors() {
            return ImmutableMap.copyOf(pageBookColors);
        }

        public Map<IBook, IConfigValue<Color>> getBookTextColors() {
            return ImmutableMap.copyOf(bookTextColors);
        }

        public Map<IBook, IConfigValue<Color>> getEntryAboveColors() {
            return ImmutableMap.copyOf(entryAboveColors);
        }

        public Map<IBook, IConfigValue<Color>> getEntryColors() {
            return ImmutableMap.copyOf(entryColors);
        }
    }

    public static final class DebugConfigurations extends ConfigProvider {
        private IConfigValue<Boolean> enableDebugOutput;
        private IConfigValue<Boolean> experimentalHolders;

        public void buildConfigurations() {
            this.experimentalHolders = this.createConfig(ConfigValueSerializers.createBooleanSerializer(),
                    "experimental.holders",
                    "If enabled in the categories who have holders in mod should render.",
                    false);

            this.enableDebugOutput = this.createConfig(ConfigValueSerializers.createBooleanSerializer(),
                    "enableDebugOutput",
                    "If enabled the debug logger of the mod will be enable.",
                    false);
        }

        public void defineConfigurations() {
            this.enableDebugOutput = this.getConfigById("enableDebugOutput");
            this.experimentalHolders = this.getConfigById("experimental.holders");
        }

        public boolean experimentalHolders () { return this.experimentalHolders.get(); }

        public boolean enableDebugOutput () {
            return this.enableDebugOutput.get();
        }
    }
}
