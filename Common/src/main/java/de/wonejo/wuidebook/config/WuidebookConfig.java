package de.wonejo.wuidebook.config;

import de.wonejo.wuidebook.WuidebookCommon;
import de.wonejo.wuidebook.api.book.BuiltBook;
import de.wonejo.wuidebook.api.compat.WuidebookAbstraction;
import de.wonejo.wuidebook.api.config.ConfigFile;
import de.wonejo.wuidebook.api.config.ConfigSpec;
import de.wonejo.wuidebook.api.config.ConfigurationBuilder;
import de.wonejo.wuidebook.api.config.WuidebookConfigManager;
import de.wonejo.wuidebook.api.util.McEnvironment;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class WuidebookConfig {

    private static WuidebookConfig INSTANCE;

    private static final ConfigFile CLIENT_CFG = ConfigFile.Builder.createBuilder().filename("wuidebook").env(McEnvironment.CLIENT).provider(WuidebookConfig::clientConfigProvider).build();
    private static final ConfigFile SERVER_CFG = ConfigFile.Builder.createBuilder().filename("wuidebook").env(McEnvironment.SERVER).provider(WuidebookConfig::serverConfigProvider).build();

    public static void setupConfig (@NotNull WuidebookConfigManager pManager) {
        pManager.createFile(CLIENT_CFG);
        pManager.createFile(SERVER_CFG);
    }

    private static void clientConfigProvider(@NotNull ConfigurationBuilder pBuilder ) {
        pBuilder.createColorConfig("default.textColor", "If a book has no 'color.text.example.exampleGuide' config present, this color will appear instead.", new Color(20, 20, 20));
        pBuilder.createColorConfig("default.entryColor", "If a book has no 'color.entry.example.exampleGuide' config present, this color will appear instead.", new Color(50, 50, 50));
        pBuilder.createColorConfig("default.entryAboveColor", "If a book has no 'color.entry.above.example.exampleGuide' config present, this color will appear instead.", new Color(25, 25, 25));
        pBuilder.createColorConfig("default.modelColor", "If a book has no 'color.model.example.exampleGuide' config present, this color will appear instead.", new Color(158, 46, 59));

        for ( BuiltBook book : WuidebookCommon.get().getBookRegistry().getAllBuiltBooks() ) {
            ResourceLocation bookId = book.getInformation().id();

            pBuilder.createColorConfig("color.model.%s.%s".formatted(bookId.getNamespace(), bookId.getPath()), "Define the model color applied by the mod. ( only apply it if there is no custom model )", book.getInformation().bookColor());
            pBuilder.createColorConfig("color.text.%s.%s".formatted(bookId.getNamespace(), bookId.getPath()), "Set the color of the texts in the GUI.", book.getInformation().textColor());
            pBuilder.createColorConfig("color.entry.%s.%s".formatted(bookId.getNamespace(), bookId.getPath()), "Set the color of the entries when the mouse is NOT above these.", book.getInformation().entryColor());
            pBuilder.createColorConfig("color.entry.above.%s.%s".formatted(bookId.getNamespace(), bookId.getPath()), "Set the color of the entries when the mouse is above these.",book.getInformation().entryAboveColor());
        }

        for (WuidebookAbstraction abstraction : WuidebookCommon.get().getAbstraction().onGatherWuidebookAbstractions()) {
            abstraction.registerCustomClientConfigurations(pBuilder);
        }
    }

    private static void serverConfigProvider( @NotNull ConfigurationBuilder pBuilder ) {
        pBuilder.createBooleanConfig("debugLogging", "If enabled, more information from the mod will appear in console.", false);
        pBuilder.createBooleanConfig("general.spawnWithBooks", "If disabled, player can not spawn with the books who has the 'book.example.exampleGuide.shouldSpawnWithBook' enabled.", true);

        for ( BuiltBook book : WuidebookCommon.get().getBookRegistry().getAllBuiltBooks() ) {
            ResourceLocation bookId = book.getInformation().id();

            pBuilder.createBooleanConfig("%s.%s.shouldSpawnWithBook".formatted(bookId.getNamespace(), bookId.getPath()), "If true the player is going to spawn with the guide.", book.getInformation().shouldSpawnWithBook());
        }

        for (WuidebookAbstraction abstraction : WuidebookCommon.get().getAbstraction().onGatherWuidebookAbstractions()) {
            abstraction.registerCustomServerConfigurations(pBuilder);
        }
    }

    public <T> T getValue ( McEnvironment pEnvironment, String pKey ) {
        return pEnvironment == McEnvironment.CLIENT ? CLIENT_CFG.getValue(pKey) : SERVER_CFG.getValue(pKey);
    }

    public <T>Optional<T> getOptValue ( McEnvironment pEnvironment, String pKey ) {
        return pEnvironment == McEnvironment.CLIENT ? CLIENT_CFG.getOptValue(pKey) : SERVER_CFG.getOptValue(pKey);
    }

    public <T> Map<String, ConfigSpec<T>> getConfigMap ( McEnvironment pEnvironment, String pPrefix ) {
        return pEnvironment == McEnvironment.CLIENT ? CLIENT_CFG.getConfigurationsByPrefix(pPrefix) : SERVER_CFG.getConfigurationsByPrefix(pPrefix);
    }

    public <T> Set<ConfigSpec<T>> getConfigSet ( McEnvironment pEnvironment, String pPrefix ) {
        return pEnvironment == McEnvironment.CLIENT ? CLIENT_CFG.getConfigurationsSetByPrefix(pPrefix) : SERVER_CFG.getConfigurationsSetByPrefix(pPrefix);
    }

    public static WuidebookConfig get () {
        if ( WuidebookConfig.INSTANCE == null ) WuidebookConfig.INSTANCE = new WuidebookConfig();
        return WuidebookConfig.INSTANCE;
    }

}
