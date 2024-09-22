package de.wonejo.wuidebook.api.config;

import de.wonejo.wuidebook.api.util.TriState;
import net.minecraft.resources.ResourceLocation;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ConfigProviderBuilder {

    void defineString ( ResourceLocation pSerializerId, String pKey, String pDescription, String pDefaultValue );
    void defineBoolean ( ResourceLocation pSerializerId, String pKey, String pDescription, TriState pDefaultValue );

    void defineInt ( ResourceLocation pSerializerId, String pKey, String pDescription, int pDefaultValue, int pMinValue, int pMaxValue );
    void defneFloat ( ResourceLocation pSerializerId, String pKey, String pDescription, float pDefaultValue, float pMinValue, float pMaxValue );
    void defneDouble ( ResourceLocation pSerializerId, String pKey, String pDescription, double pDefaultValue, double pMinValue, double pMaxValue );

    <T extends Enum<T>> void defineEnum ( T pType, String pKey, String pDescription, T pDefaultValue );
    <T> void defineList (ResourceLocation pSerializerId, ResourceLocation pValueSerializerId, String pKey, String pDescription, List<T> pDefaultValue);
    <T> void define ( ResourceLocation pSerializerId, String pKey, String pDescription, T pDefaultValue );

    Collection<ConfigSpec<?>> getSpecifications ();
    Set<String> getKeys ();
}
