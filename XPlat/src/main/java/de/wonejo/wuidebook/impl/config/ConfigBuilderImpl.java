package de.wonejo.wuidebook.impl.config;

import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import de.wonejo.wuidebook.WuidebookCommonMod;
import de.wonejo.wuidebook.api.config.ConfigBuilder;
import de.wonejo.wuidebook.api.config.ConfigSpec;
import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Deque;
import java.util.Map;

public final class ConfigBuilderImpl implements ConfigBuilder {

    private final Map<String, ConfigSpec<?>> configurations = Maps.newHashMap();

    public <T> void defineConfig(ResourceLocation pSerializerId, String pKey, @Nullable String pComment, T pDefaultValue) {
        ConfigSerializer<T> serializer = this.tryToGet(pSerializerId);
        ConfigSpec<T> spec = new ConfigSpecImpl<>(pKey, serializer, pComment, pDefaultValue);
        if ( this.configurations.putIfAbsent(pKey, spec) != null )
            throw new RuntimeException("There is currently a configuration spec with id: '%s'".formatted(pKey));
    }

    public void defineColor(ResourceLocation pSerializerId, String pKey, @Nullable String pComment, Color pDefaultValue) {
        this.defineConfig(pSerializerId, pKey, pComment, pDefaultValue);
    }

    public void defineInteger(ResourceLocation pSerializerId, String pKey, @Nullable String pComment, int pMinValue, int pMaxValue, int pDefaultValue) {
        ConfigSerializer<Integer> serializer = this.tryToGet(pSerializerId);
        serializer.onRange(pMinValue, pMaxValue);

        ConfigSpec<Integer> spec = new ConfigSpecImpl<>(pKey, serializer, pComment, pDefaultValue);
        if ( this.configurations.putIfAbsent(pKey, spec) != null )
            throw new RuntimeException("There is currently a configuration spec with id: '%s'".formatted(pKey));
    }

    public void defineFloat(ResourceLocation pSerializerId, String pKey, @Nullable String pComment, float pMinValue, float pMaxValue, float pDefaultValue) {
        ConfigSerializer<Float> serializer = this.tryToGet(pSerializerId);
        serializer.onRange(pMinValue, pMaxValue);

        ConfigSpec<Float> spec = new ConfigSpecImpl<>(pKey, serializer, pComment, pDefaultValue);
        if ( this.configurations.putIfAbsent(pKey, spec) != null )
            throw new RuntimeException("There is currently a configuration spec with id: '%s'".formatted(pKey));
    }

    public void defineDouble(ResourceLocation pSerializerId, String pKey, @Nullable String pComment, double pMinValue, double pMaxValue, double pDefaultValue) {
        ConfigSerializer<Double> serializer = this.tryToGet(pSerializerId);
        serializer.onRange(pMinValue, pMaxValue);

        ConfigSpec<Double> spec = new ConfigSpecImpl<>(pKey, serializer, pComment, pDefaultValue);
        if ( this.configurations.putIfAbsent(pKey, spec) != null )
            throw new RuntimeException("There is currently a configuration spec with id: '%s'".formatted(pKey));
    }

    public void defineBoolean(ResourceLocation pSerializerId, String pKey, @Nullable String pComment, boolean pDefaultValue) {
        this.defineConfig(pSerializerId, pKey, pComment, pDefaultValue);
    }

    public void defineString(ResourceLocation pSerializerId, String pKey, @Nullable String pComment, String pDefaultValue) {
        this.defineConfig(pSerializerId, pKey, pComment, pDefaultValue);
    }

    private <T> @NotNull ConfigSerializer<T> tryToGet (ResourceLocation pSerializerId ) {
        ConfigSerializer<T> serializer = WuidebookCommonMod.get().getSerializerRegistry().getSerializer(pSerializerId);
        if ( serializer == null ) throw new NullPointerException("There is not serializer with id '%s' present.".formatted(pSerializerId));
        return serializer;
    }

    public Map<String, ConfigSpec<?>> getConfigurations() {
        return configurations;
    }

}
