package de.wonejo.wuidebook;

import de.wonejo.wuidebook.api.compat.WuidebookImplementation;
import de.wonejo.wuidebook.api.config.ConfigFile;
import de.wonejo.wuidebook.api.config.ConfigManager;
import de.wonejo.wuidebook.api.util.Constants;
import de.wonejo.wuidebook.api.util.McEnvironment;
import de.wonejo.wuidebook.impl.config.serializer.*;
import de.wonejo.wuidebook.impl.config.serializer.color.ColorConfigSerializer;
import de.wonejo.wuidebook.impl.service.ModServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WuidebookCommonMod {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final WuidebookCommonMod INSTANCE = new WuidebookCommonMod();
    private static Path CONFIG_DIRECTORY = ModServices.ABSTRACTION.getConfigPath();
    private final ConfigManager configManager = ConfigManager.get();

    private WuidebookCommonMod () {}

    public void setup () {
        List<WuidebookImplementation> implementations = ModServices.ABSTRACTION.gatherImplementations();
        CONFIG_DIRECTORY = CONFIG_DIRECTORY.resolve("wuidebook");
        if (!Files.exists(CONFIG_DIRECTORY)) {
            try {
                Files.createDirectory(CONFIG_DIRECTORY);
            } catch (IOException e) {
                throw new RuntimeException("An error occurred trying to create wuidebook config directory.", e);
            }
        }

        this.setupConfigAPI (implementations);
    }

    private void setupConfigAPI (@NotNull List<WuidebookImplementation> pImplementation) {
        this.configManager.registerSerializer(Constants.CFG_STR_SERIALIZER, new StringCfgSerializer());
        this.configManager.registerSerializer(Constants.CFG_INT_SERIALIZER, new IntCfgSerializer());
        this.configManager.registerSerializer(Constants.CFG_FLOAT_SERIALIZER, new FloatCfgSerializer());
        this.configManager.registerSerializer(Constants.CFG_DOUBLE_SERIALIZER, new DoubleCfgSerializer());
        this.configManager.registerSerializer(Constants.CFG_BOOL_SERIALIZER, new BooleanCfgSerializer());
        this.configManager.registerSerializer(Constants.CFG_LIST_SERIALIZER, new ListCfgSerializer<>());
        this.configManager.registerSerializer(Constants.CFG_COLOR_SERIALIZER, new ColorConfigSerializer());

        for ( WuidebookImplementation impl : pImplementation ) {
            impl.onRegisterConfigSerializer(this.configManager);
            impl.onRegisterConfigFiles(this.configManager);
        }

        this.configManager.getFileList(McEnvironment.COMMON).forEach(ConfigFile::loadFile);
    }

    public static Path getConfigDirectory() {
        return CONFIG_DIRECTORY;
    }

    public static WuidebookCommonMod get () {
        return WuidebookCommonMod.INSTANCE;
    }

}
