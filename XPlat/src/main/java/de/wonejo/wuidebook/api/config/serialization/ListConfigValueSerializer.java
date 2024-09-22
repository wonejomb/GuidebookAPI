package de.wonejo.wuidebook.api.config.serialization;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

/**
 * Deserialization provider for Lists / Arrays.
 * @since 4.0.0-dev1
 */
public interface ListConfigValueSerializer<T> extends ConfigValueSerializer<List<T>> {

    /**
     * The de/serializer used for each value in array/list
     * @since 4.0.0-dev2
     */
    void withValueSerializer (ResourceLocation pSerializerId);

    /**
     * Value serializer for the de/serialized values
     * @since 4.0.0-dev1
     */
    ConfigValueSerializer<T> getValueSerializer ();

}
