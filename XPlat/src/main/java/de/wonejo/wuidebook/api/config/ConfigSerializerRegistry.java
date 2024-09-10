package de.wonejo.wuidebook.api.config;

import com.google.common.collect.Maps;
import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public final class ConfigSerializerRegistry {
    private static final ConfigSerializerRegistry INSTANCE = new ConfigSerializerRegistry();
    private final Map<ResourceLocation, ConfigSerializer<?>> serializers = Maps.newConcurrentMap();

    private ConfigSerializerRegistry () {}

    public void registerSerializer ( String pId, Supplier<ConfigSerializer<?>> pSerializer ) {
        ResourceLocation id = ResourceLocation.tryParse(pId);
        this.registerSerializer(id, pSerializer);
    }

    public void registerSerializer ( ResourceLocation pId, @NotNull Supplier<ConfigSerializer<?>> pSerializer ) {
        if ( this.serializers.putIfAbsent(pId, pSerializer.get()) != null )
            throw new IllegalArgumentException ("Cannot register a serializer with id: " + pId + " when there is other.");
    }

    public <T> @NotNull Optional<ConfigSerializer<T>> getOptSerializer ( ResourceLocation pId ) {
        return Optional.of(this.getSerializer(pId));
    }

    @SuppressWarnings("unchecked")
    public <T> ConfigSerializer<T> getSerializer ( ResourceLocation pId ) {
        return (ConfigSerializer<T>) this.serializers.get(pId);
    }

    public static ConfigSerializerRegistry get () {
        return ConfigSerializerRegistry.INSTANCE;
    }
}
