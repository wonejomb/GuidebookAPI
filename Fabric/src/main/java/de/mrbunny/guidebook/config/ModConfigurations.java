package de.mrbunny.guidebook.config;

import com.google.common.collect.Maps;
import de.mrbunny.guidebook.api.GuidebookAPI;
import de.mrbunny.guidebook.api.book.IBook;
import de.mrbunny.guidebook.api.config.IConfigProvider;
import de.mrbunny.guidebook.api.config.IConfigValue;
import de.mrbunny.guidebook.api.config.IConfigValueBuilder;
import de.mrbunny.guidebook.api.config.impl.ConfigProvider;
import de.mrbunny.guidebook.api.config.impl.ConfigValue;
import de.mrbunny.guidebook.api.config.impl.ConfigValueBuilder;
import net.minecraft.ChatFormatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ModConfigurations {
    private static final Logger LOGGER = LoggerFactory.getLogger(ModConfigurations.class);

    public static class ClientConfiguration extends ConfigProvider {

        public IConfigValue<Integer> entryBetweenColor;
        public Map<IBook, IConfigValue<Integer>> bookColors = Maps.newHashMap();

        public void createConfigurations() {
            this.entryBetweenColor = this.createConfig(new ConfigValueBuilder<Integer>("entryBetweenColor")
                    .comment("The color of the entries when the mouse is between them")
                    .defaultValue(ChatFormatting.BLUE.getColor()));

            for ( IBook book : GuidebookAPI.getBooks().values() )
                this.bookColors.put(book,
                        this.createConfig(
                                new ConfigValueBuilder<Integer>(book.getId().getNamespace() + "." + book.getId().getPath() + ".color")
                                .comment("The color of this book")
                                .defaultValue(book.getColor().getRGB())
                        )
                );

        }

        public void loadConfigurations() {
            this.entryBetweenColor = this.getConfigByName("entryBetweenColor");

            this.bookColors.clear();
            for ( IBook book : GuidebookAPI.getBooks().values() )
                this.bookColors.put(book, this.getConfigByName(book.getId().getNamespace() + "." + book.getId().getPath() + ".color")
                );
        }

    }

    public static class CommonConfiguration extends ConfigProvider {

        public IConfigValue<Boolean> shouldSpawnWithBook;
        public final Map<IBook, IConfigValue<Boolean>> spawnBooks = Maps.newHashMap();

        public void createConfigurations() {
            this.shouldSpawnWithBook = this.createConfig(new ConfigValueBuilder<Boolean>("shouldSpawnWithBook")
                    .comment("Allows books to spawn with new players, this is a global override for all books if set to false")
                    .defaultValue(true)
            );

            for ( IBook book : GuidebookAPI.getBooks().values() )
                spawnBooks.put(book,
                            this.createConfig(new ConfigValueBuilder<Boolean>(book.getId().getNamespace() + "." + book.getId().getPath() + ".spawn")
                                    .comment("If the player should spawn with this book.")
                                    .defaultValue(book.shouldSpawnWithBook()))
                        );

        }

        public void loadConfigurations() {
            this.shouldSpawnWithBook = this.getConfigByName("shouldSpawnWithBook");

            this.spawnBooks.clear();
            for ( IBook book : GuidebookAPI.getBooks().values() )
                this.spawnBooks.put(book,
                        this.getConfigByName(book.getId().getNamespace() + "." + book.getId().getPath() + ".spawn")
                );
        }

    }
}
