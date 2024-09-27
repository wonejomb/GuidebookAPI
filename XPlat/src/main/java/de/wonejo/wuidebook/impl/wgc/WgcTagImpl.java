package de.wonejo.wuidebook.impl.wgc;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import de.wonejo.wuidebook.api.wgc.WgcTag;
import de.wonejo.wuidebook.api.wgc.WgcValueTypeRegistry;
import de.wonejo.wuidebook.api.wgc.value.WgcTagValue;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class WgcTagImpl implements WgcTag {

    @NotNull
    public static WgcTag createTag ( String pKey, Map<ResourceLocation, Property<?>> pProperties ) {
        return new WgcTagImpl(pKey, pProperties);
    }

    private final String key;
    private final Map<ResourceLocation, Property<?>> properties = Maps.newHashMap();

    private WgcTagImpl ( String pKey, Map<ResourceLocation, Property<?>> pProperties ) {
        this.key = pKey;
        this.properties.putAll(pProperties);
    }

    @SuppressWarnings("unchecked")
    public <T> Property<T> getProperty(ResourceLocation pKey) {
        Property<T> prop = (Property<T>) this.properties.get(pKey);
        if (prop == null)
            throw new IllegalArgumentException("Property with key '" + pKey + "' not found.");
        return prop;
    }

    public boolean haveProperty(ResourceLocation pKey) {
        return this.properties.containsKey(pKey);
    }

    public String key() {
        return this.key;
    }

    public Collection<? extends Property<?>> properties() {
        return Collections.unmodifiableCollection(this.properties.values());
    }

    public Set<ResourceLocation> propertiesKeySet() {
        return ImmutableSet.copyOf(this.properties.keySet());
    }

    public static class PropertyImpl<T> implements WgcTag.Property<T> {

        @NotNull
        public static <B> WgcTag.Property<B> createProperty ( ResourceLocation pKey, ResourceLocation pValueType, String pValue ) {
            return new PropertyImpl<>(pKey, pValueType, pValue);
        }

        @NotNull
        public static <B> WgcTag.Property<B> createProperty ( ResourceLocation pKey, WgcTagValue<B> pValue ) {
            return new PropertyImpl<>(pKey, pValue);
        }

        private final ResourceLocation key;
        private final WgcTagValue<T> value;

        private PropertyImpl ( ResourceLocation pKey, ResourceLocation pValueType, String pValue ) {
            this.key = pKey;
            this.value = WgcValueTypeRegistry.get().createTagValue(pValueType, pValue);

            if (this.value == null)
                throw new IllegalArgumentException("Failed to create WgcTagValue for: " + pValueType);
        }

        private PropertyImpl ( ResourceLocation pKey, WgcTagValue<T> pValue ) {
            this.key = pKey;
            this.value = pValue;
        }

        public ResourceLocation key() {
            return this.key;
        }

        public WgcTagValue<T> value() {
            return this.value;
        }

    }
}
