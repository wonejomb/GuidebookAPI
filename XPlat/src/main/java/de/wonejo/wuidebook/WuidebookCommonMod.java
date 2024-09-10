package de.wonejo.wuidebook;

import de.wonejo.wuidebook.api.compat.WuidebookImplementation;
import de.wonejo.wuidebook.api.config.ConfigFile;
import de.wonejo.wuidebook.api.config.ConfigManager;
import de.wonejo.wuidebook.api.config.ConfigSerializerRegistry;
import de.wonejo.wuidebook.config.ClientConfig;
import de.wonejo.wuidebook.config.CommonConfig;
import de.wonejo.wuidebook.config.ServerConfig;
import de.wonejo.wuidebook.impl.config.serializer.*;
import de.wonejo.wuidebook.impl.config.serializer.color.ColorConfigSerializer;
import de.wonejo.wuidebook.impl.service.ModServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WuidebookCommonMod {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final WuidebookCommonMod INSTANCE = new WuidebookCommonMod();

    private ConfigSerializerRegistry serializerRegistry;
    private ConfigManager configManager;

    private ClientConfig clientConfig;
    private ServerConfig serverConfig;
    private CommonConfig commonConfig;

    private WuidebookCommonMod () {}

    public void setup () {
        this.configManager = ConfigManager.get();
        this.serializerRegistry = ConfigSerializerRegistry.get();

        List<WuidebookImplementation> implementations = ModServices.ABSTRACTION.gatherImplementations();

        this.setupConfigAPI (implementations);
        this.configManager.getFiles().forEach(ConfigFile::initializeFile);
    }

    private void setupConfigAPI (@NotNull List<WuidebookImplementation> pImplementation) {
        this.serializerRegistry.registerSerializer(StringConfigSerializer.ID, StringConfigSerializer::new);
        this.serializerRegistry.registerSerializer(IntConfigSerializer.ID, IntConfigSerializer::new);
        this.serializerRegistry.registerSerializer(FloatConfigSerializer.ID, FloatConfigSerializer::new);
        this.serializerRegistry.registerSerializer(DoubleConfigSerializer.ID, DoubleConfigSerializer::new);
        this.serializerRegistry.registerSerializer(BooleanConfigSerializer.ID, BooleanConfigSerializer::new);
        this.serializerRegistry.registerSerializer(ColorConfigSerializer.ID, ColorConfigSerializer::new);

        this.clientConfig = ClientConfig.get();
        this.serverConfig = ServerConfig.get();
        this.commonConfig = CommonConfig.get();

        this.clientConfig.setupFile(this.configManager);
        this.serverConfig.setupFile(this.configManager);
        this.commonConfig.setupFile(this.configManager);

        for ( WuidebookImplementation implementation : pImplementation ) {
            implementation.onRegisterConfigSerializer(this.serializerRegistry);
            implementation.onRegisterCustomConfigFiles(this.configManager);
        }
    }

    public static WuidebookCommonMod get () {
        return WuidebookCommonMod.INSTANCE;
    }

    public ConfigManager configManager() {
        return configManager;
    }

    public ConfigSerializerRegistry getSerializerRegistry() {
        return serializerRegistry;
    }

    public ClientConfig clientConfig () {
        return clientConfig;
    }

    public ServerConfig serverConfig () {
        return serverConfig;
    }

    public CommonConfig commonConfig () {
        return commonConfig;
    }

}
