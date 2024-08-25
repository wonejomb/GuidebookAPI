package de.wonejo.wuidebook.api.config;

import com.google.common.collect.Maps;
import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;
import java.util.function.Supplier;

public class ConfigSerializerRegistry {
    private static ConfigSerializerRegistry INSTANCE;
    private final Map<ResourceLocation, ConfigSerializer<?>> serializers = Maps.newHashMap();

    private ConfigSerializerRegistry () {}

    public void registerSerializer ( String pId, Supplier<ConfigSerializer<?>> pSerializer ) {
        ResourceLocation id = ResourceLocation.tryParse(pId);
        this.registerSerializer(id, pSerializer);
    }

    public void registerSerializer ( ResourceLocation pId, Supplier<ConfigSerializer<?>> pSerializer ) {
        if ( this.serializers.containsKey(pId) ) throw new IllegalArgumentException("Can not register a serializer with the same id.");
        this.serializers.put(pId, pSerializer.get());
    }

    @SuppressWarnings("unchecked")
    public <T> ConfigSerializer<T> getSerializer ( ResourceLocation pId ) {
        return (ConfigSerializer<T>) this.serializers.get(pId);
    }

    public static ConfigSerializerRegistry get () {
        if (ConfigSerializerRegistry.INSTANCE == null) ConfigSerializerRegistry.INSTANCE = new ConfigSerializerRegistry();
        return ConfigSerializerRegistry.INSTANCE;
    }
}
