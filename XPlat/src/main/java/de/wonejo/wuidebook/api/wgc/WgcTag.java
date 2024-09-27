package de.wonejo.wuidebook.api.wgc;

import de.wonejo.wuidebook.api.wgc.value.WgcTagValue;
import de.wonejo.wuidebook.impl.wgc.WgcTagImpl;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface WgcTag {

    @NotNull static WgcTag createTag (String pKey, Map<ResourceLocation, Property<?>> pProperties) {
        return WgcTagImpl.createTag(pKey, pProperties);
    }

    <T> Property<T> getProperty ( ResourceLocation pKey );
    boolean haveProperty ( ResourceLocation pKey );

    String key ();
    Collection<? extends Property<?>> properties ();
    Set<ResourceLocation> propertiesKeySet ();

    interface Property<T> {

        @NotNull static <B> WgcTag.Property<B> createProperty ( ResourceLocation pKey, ResourceLocation pValueType, String pValue ) {
            return WgcTagImpl.PropertyImpl.createProperty(pKey, pValueType, pValue);
        }

        @NotNull static <B> WgcTag.Property<B> createProperty ( ResourceLocation pKey, WgcTagValue<B> pValue ) {
            return WgcTagImpl.PropertyImpl.createProperty(pKey, pValue);
        }

        ResourceLocation key ();
        WgcTagValue<T> value ();

    }
}
