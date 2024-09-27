package de.wonejo.wuidebook.api.wgc;

import de.wonejo.wuidebook.api.wgc.value.WgcTagValue;
import de.wonejo.wuidebook.api.wgc.value.WgcValueTagType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class WgcValueTypeRegistry {
    private static WgcValueTypeRegistry INSTANCE;

    public static WgcValueTypeRegistry get () {
        if ( WgcValueTypeRegistry.INSTANCE == null ) WgcValueTypeRegistry.INSTANCE = new WgcValueTypeRegistry();
        return WgcValueTypeRegistry.INSTANCE;
    }

    private final Map<ResourceLocation, Function<String, WgcTagValue<?>>> tagValues = new HashMap<>();

    private WgcValueTypeRegistry () {}

    public <T> void registerType( ResourceLocation typeName, Function<String, WgcTagValue<?>> valueCreator ) {
        tagValues.put(typeName, valueCreator);
    }

    @SuppressWarnings("unchecked")
    public <T> WgcTagValue<T> createTagValue ( ResourceLocation pInputType, String pValue ) {
        Function<String, WgcTagValue<?>> valueCreator = this.tagValues.get(pInputType);
        if ( valueCreator == null ) throw new RuntimeException("No matching tag value creator found for type: " + pInputType);
        return (WgcTagValue<T>) valueCreator.apply(pValue);
    }

    public <T> WgcTagValue<T> createTagValue (@NotNull WgcValueTagType<T> pInputType, String pValue ) {
        return this.createTagValue(pInputType.id(), pValue);
    }

}
