package de.wonejo.wuidebook;

import de.wonejo.wuidebook.api.WuidebookAPIAbstraction;
import de.wonejo.wuidebook.api.book.BookRegistry;
import de.wonejo.wuidebook.api.compat.WuidebookAbstraction;
import de.wonejo.wuidebook.api.config.ConfigSerializerRegistry;
import de.wonejo.wuidebook.api.config.WuidebookConfigManager;
import de.wonejo.wuidebook.api.logger.DebugLogger;
import de.wonejo.wuidebook.api.util.McEnvironment;
import de.wonejo.wuidebook.config.WuidebookConfig;
import de.wonejo.wuidebook.impl.config.serializer.*;
import de.wonejo.wuidebook.impl.config.serializer.color.ColorConfigSerializer;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.ServiceLoader;

public class WuidebookCommon {
    private static WuidebookCommon INSTANCE;

    public static final String MOD_ID = "wuidebookapi";
    private final WuidebookAPIAbstraction abstraction = ServiceLoader.load(WuidebookAPIAbstraction.class).findFirst().orElseThrow(() -> new NullPointerException("Can not get instance for WuidebookAPIAbstraction."));
    private static boolean INIT = false;
    public WuidebookConfigManager configManager = WuidebookConfigManager.get();
    private final ConfigSerializerRegistry serializerRegistry = ConfigSerializerRegistry.get();
    private final BookRegistry bookRegistry = BookRegistry.get();

    private WuidebookCommon () {}

    public void init () {
        if (INIT) throw new IllegalStateException("Can not re-initialize WuidebookAPI base!");
        INIT = true;

        List<WuidebookAbstraction> gatheredCombats = this.abstraction.onGatherWuidebookAbstractions();
        gatheredCombats.forEach((compat) -> compat.setupGuidesRegistry(this.bookRegistry));

        this.setupConfigurationAPI (gatheredCombats);

        if ( WuidebookConfig.get().getValue(McEnvironment.SERVER, "debugLogging") ) {
            DebugLogger.initializeLogger();
           DebugLogger.log("Wuidebook Logger is enabled! More debug information will display on console.");
        }
    }

    private void setupConfigurationAPI (@NotNull List<WuidebookAbstraction> pGatheredCompats) {
        this.serializerRegistry.registerSerializer(StringConfigSerializer.ID, StringConfigSerializer::new);
        this.serializerRegistry.registerSerializer(IntConfigSerializer.ID, IntConfigSerializer::new);
        this.serializerRegistry.registerSerializer(FloatConfigSerializer.ID, FloatConfigSerializer::new);
        this.serializerRegistry.registerSerializer(DoubleConfigSerializer.ID, DoubleConfigSerializer::new);
        this.serializerRegistry.registerSerializer(BooleanConfigSerializer.ID, BooleanConfigSerializer::new);
        this.serializerRegistry.registerSerializer(ColorConfigSerializer.ID, ColorConfigSerializer::new);

        pGatheredCompats.forEach((compat) -> compat.setupCustomSerializers(this.serializerRegistry));

        WuidebookConfig.setupConfig(this.configManager);
        this.configManager.initialize();
    }

    public ConfigSerializerRegistry getSerializerRegistry() {
        return serializerRegistry;
    }

    public WuidebookConfigManager getConfigManager() {
        return configManager;
    }

    public WuidebookAPIAbstraction getAbstraction() {
        return abstraction;
    }

    public BookRegistry getBookRegistry() {
        return bookRegistry;
    }

    public static WuidebookCommon get () {
        if ( WuidebookCommon.INSTANCE == null ) WuidebookCommon.INSTANCE = new WuidebookCommon();
        return WuidebookCommon.INSTANCE;
    }
}
