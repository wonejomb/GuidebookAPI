package de.wonejo.wuidebook.impl.config;

import com.google.common.collect.Maps;
import de.wonejo.wuidebook.WuidebookCommon;
import de.wonejo.wuidebook.api.config.ConfigSpec;
import de.wonejo.wuidebook.api.config.ConfigurationBuilder;
import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import de.wonejo.wuidebook.api.logger.DebugLogger;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Map;

public class ConfigurationBuilderImpl implements ConfigurationBuilder {

    public static @NotNull ConfigurationBuilder create () {
        return new ConfigurationBuilderImpl();
    }

    private final Map<String, ConfigSpec<?>> configurations = Maps.newHashMap();

    private ConfigurationBuilderImpl () {}

    public <T> void createConfig(ResourceLocation pSerializerId, String pKey, @Nullable String pComment, T pDefaultValue) {
        ConfigSerializer<T> serializer = WuidebookCommon.get().getSerializerRegistry().getSerializer(pSerializerId);
        ConfigSpec<T> spec = ConfigSpec.create(pKey, serializer, pComment, pDefaultValue);

        if ( this.configurations.putIfAbsent(pKey, spec) != null )
            DebugLogger.log("There is already a config with key: '{}', skipping this.", pKey);
    }

    public void createColorConfig(ResourceLocation pSerializerId, String pKey, @Nullable String pComment, Color pDefaultValue) {
        ConfigSerializer<Color> serializer = WuidebookCommon.get().getSerializerRegistry().getSerializer(pSerializerId);
        ConfigSpec<Color> spec = ConfigSpec.create(pKey, serializer, pComment, pDefaultValue);

        if ( this.configurations.putIfAbsent(pKey, spec) != null )
            DebugLogger.log("There is already a config with key: '{}', skipping this.", pKey);
    }


    public void createIntegerConfig(ResourceLocation pSerializerId, String pKey, @Nullable String pComment, int pDefaultValue, int pMinRange, int pMaxRange) {
        ConfigSerializer<Integer> serializer = WuidebookCommon.get().getSerializerRegistry().getSerializer(pSerializerId);
        serializer.onRange(pMinRange, pMaxRange);
        ConfigSpec<Integer> spec = ConfigSpec.create(pKey, serializer, pComment, pDefaultValue);

        if ( this.configurations.putIfAbsent(pKey, spec) != null )
            DebugLogger.log("There is already a config with key: '{}', skipping this.", pKey);
    }

    public void createFloatConfig(ResourceLocation pSerializerId, String pKey, @Nullable String pComment, float pDefaultValue, float pMinRange, float pMaxRange) {
        ConfigSerializer<Float> serializer = WuidebookCommon.get().getSerializerRegistry().getSerializer(pSerializerId);
        serializer.onRange(pMinRange, pMaxRange);
        ConfigSpec<Float> spec = ConfigSpec.create(pKey, serializer, pComment, pDefaultValue);

        if ( this.configurations.putIfAbsent(pKey, spec) != null )
            DebugLogger.log("There is already a config with key: '{}', skipping this.", pKey);
    }

    public void createDoubleConfig(ResourceLocation pSerializerId, String pKey, @Nullable String pComment, double pDefaultValue, double pMinRange, double pMaxRange) {
        ConfigSerializer<Double> serializer = WuidebookCommon.get().getSerializerRegistry().getSerializer(pSerializerId);
        serializer.onRange(pMinRange, pMaxRange);
        ConfigSpec<Double> spec = ConfigSpec.create(pKey, serializer, pComment, pDefaultValue);

        if ( this.configurations.putIfAbsent(pKey, spec) != null )
            DebugLogger.log("There is already a config with key: '{}', skipping this.", pKey);
    }

    public void createBooleanConfig(ResourceLocation pSerializerId, String pKey, @Nullable String pComment, boolean pDefaultValue) {
        ConfigSerializer<Boolean> serializer = WuidebookCommon.get().getSerializerRegistry().getSerializer(pSerializerId);
        ConfigSpec<Boolean> spec = ConfigSpec.create(pKey, serializer, pComment, pDefaultValue);

        if ( this.configurations.putIfAbsent(pKey, spec) != null )
            DebugLogger.log("There is already a config with key: '{}', skipping this.", pKey);
    }

    public void createStringConfig(ResourceLocation pSerializerId, String pKey, @Nullable String pComment, String pDefaultValue) {
        ConfigSerializer<String> serializer = WuidebookCommon.get().getSerializerRegistry().getSerializer(pSerializerId);
        ConfigSpec<String> spec = ConfigSpec.create(pKey, serializer, pComment, pDefaultValue);

        if ( this.configurations.putIfAbsent(pKey, spec) != null )
            DebugLogger.log("There is already a config with key: '{}', skipping this.", pKey);
    }

    public Map<String, ConfigSpec<?>> configurations() {
        return this.configurations;
    }
}
