package de.wonejo.wuidebook.impl.config;

import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import de.wonejo.wuidebook.api.config.ConfigBuilder;
import de.wonejo.wuidebook.api.config.ConfigSpec;
import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.Deque;
import java.util.Map;

@ApiStatus.Internal
public final class ConfigBuilderImpl implements ConfigBuilder {

    private final Map<String, ConfigSpec<?>> configurations = Maps.newHashMap();

    public <T> void defineConfig(ConfigSerializer<T> pSerializer, String pKey, @Nullable String pComment, T pDefaultValue) {
        ConfigSpec<T> spec = new ConfigSpecImpl<>(pKey, pSerializer, pComment, pDefaultValue);
        if ( this.configurations.putIfAbsent(pKey, spec) != null )
            throw new IllegalStateException("Can not define a config when the key '%s' already exist.".formatted(pKey));
    }

    public Map<String, ConfigSpec<?>> getConfigurations() {
        return configurations;
    }

}
