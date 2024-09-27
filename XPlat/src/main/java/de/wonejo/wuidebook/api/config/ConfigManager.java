package de.wonejo.wuidebook.api.config;

import com.google.common.collect.Maps;
import de.wonejo.wuidebook.api.config.serialization.ConfigValueSerializer;
import de.wonejo.wuidebook.api.util.McEnvironment;
import de.wonejo.wuidebook.api.util.WuidebookRegistryException;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.compress.utils.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class ConfigManager {
    private static ConfigManager INSTANCE;
    private static final Logger LOGGER = LogManager.getLogger();
    private final List<ConfigFile> files = Lists.newArrayList();
    private final Map<ResourceLocation, ConfigValueSerializer<?>> serializers = Maps.newHashMap();

    private ConfigManager () {}

    public void registerSerializer ( ResourceLocation pSerializerId, ConfigValueSerializer<?> pSerializer ) {
        if ( this.serializers.putIfAbsent(pSerializerId, pSerializer) != null )
            throw new WuidebookRegistryException("Can not register serializer with id: '%s', there is already a serializer with that id.".formatted(pSerializer));
    }

    public void registerFile ( ConfigFile pFile ) {
        this.files.add(pFile);
    }

    @SuppressWarnings("unchecked")
    public <T> ConfigValueSerializer<T> getSerializer ( ResourceLocation pSerializerId ) {
        ConfigValueSerializer<T> serializer = (ConfigValueSerializer<T>) this.serializers.get(pSerializerId);
        if ( serializer == null ) throw new NullPointerException("Can not get serializer with id: '" + pSerializerId + "'");
        return serializer;
    }

    public List<ConfigFile> getFileList (McEnvironment pEnvironment) {
        return this.files.stream().filter((file) -> file.getEnvironment() == pEnvironment).toList();
    }

    public static ConfigManager get () {
        if ( ConfigManager.INSTANCE == null ) ConfigManager.INSTANCE = new ConfigManager();
        return ConfigManager.INSTANCE;
    }
}
