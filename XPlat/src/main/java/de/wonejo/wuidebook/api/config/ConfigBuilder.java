package de.wonejo.wuidebook.api.config;

import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;
import de.wonejo.wuidebook.api.util.Constants;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Deque;
import java.util.Map;

public interface ConfigBuilder {

    /* ---------- CONFIG BASE / START ---------- */

    @ApiStatus.Internal
    <T> void defineConfig (ConfigSerializer<T> pSerializer, String pKey, @Nullable String pComment, T pDefaultValue);

    default <T> void defineConfig ( ConfigSerializer<T> pSerializer, String pKey, T pDefaultValue ) {
        this.defineConfig(pSerializer, pKey, null, pDefaultValue);
    }

    default <T> void defineConfig ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, T pDefaultValue ) {
        this.checkIfSerializerExist(pSerializerId);
        ConfigSerializer<T> serializer = ConfigSerializerRegistry.get().getSerializer(pSerializerId);
        this.defineConfig(serializer, pKey, pComment, pDefaultValue);
    }

    default <T> void defineConfig ( ResourceLocation pSerializerId, String pKey, T pDefaultValue ) {
        this.defineConfig(pSerializerId, pKey, null, pDefaultValue);
    }

    Map<String, ConfigSpec<?>> getConfigurations ();

    /* ---------- CONFIG BASE / END ---------- */

    /* ---------- COLOR CONFIG / START ---------- */

    default void defineColor ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, Color pDefaultValue ) {
        this.defineConfig(pSerializerId, pKey, pComment, pDefaultValue);
    }

    default void defineColor ( ResourceLocation pSerializerId, String pKey, Color pDefaultValue ) {
        this.defineConfig(pSerializerId, pKey, pDefaultValue);
    }

    default void defineColor ( String pKey, @Nullable String pComment, Color pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_color");
        this.defineColor(serializer, pKey, pComment, pDefaultValue);
    }

    default void defineColor ( String pKey, Color pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_color");
        this.defineColor(serializer, pKey, pDefaultValue);
    }

    /* ---------- COLOR CONFIG / END ---------- */

    /* ---------- INT CONFIG / START ---------- */

    default void defineInteger ( ResourceLocation pSerializerId, String pKey, int pMinValue, int pMaxValue, int pDefaultValue ) {
        this.checkIfSerializerExist(pSerializerId);
        ConfigSerializer<Integer> serializer = ConfigSerializerRegistry.get().getSerializer(pSerializerId);
        serializer.onRange(pMinValue, pMaxValue);

        this.defineConfig(serializer, pKey, pDefaultValue);
    }

    default void defineInteger ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, int pMinValue, int pMaxValue, int pDefaultValue ) {
        this.checkIfSerializerExist(pSerializerId);
        ConfigSerializer<Integer> serializer = ConfigSerializerRegistry.get().getSerializer(pSerializerId);
        serializer.onRange(pMinValue, pMaxValue);

        this.defineConfig(serializer, pKey, pComment, pDefaultValue);
    }

    default void defineInteger ( String pKey, @Nullable String pComment, int pMinValue, int pMaxValue, int pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_int");
        this.defineInteger(serializer, pKey, pComment, pMinValue, pMaxValue, pDefaultValue);
    }

    default void defineInteger ( String pKey, int pMinValue, int pMaxValue, int pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_int");
        this.defineInteger(serializer, pKey, pMinValue, pMaxValue, pDefaultValue);
    }

    /* ---------- INT CONFIG / END ---------- */

    /* ---------- FLOAT CONFIG / START ---------- */

    default void defineFloat ( ResourceLocation pSerializerId, String pKey, float pMinValue, float pMaxValue, float pDefaultValue ) {
        this.checkIfSerializerExist(pSerializerId);
        ConfigSerializer<Float> serializer = ConfigSerializerRegistry.get().getSerializer(pSerializerId);
        serializer.onRange(pMinValue, pMaxValue);

        this.defineConfig(serializer, pKey, pDefaultValue);
    }

    default void defineFloat ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, float pMinValue, float pMaxValue, float pDefaultValue ) {
        this.checkIfSerializerExist(pSerializerId);
        ConfigSerializer<Float> serializer = ConfigSerializerRegistry.get().getSerializer(pSerializerId);
        serializer.onRange(pMinValue, pMaxValue);

        this.defineConfig(serializer, pKey, pComment, pDefaultValue);
    }

    default void defineFloat ( String pKey, @Nullable String pComment, float pMinValue, float pMaxValue, float pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_float");
        this.defineFloat(serializer, pKey, pComment, pMinValue, pMaxValue, pDefaultValue);
    }

    default void defineFloat ( String pKey, float pMinValue, float pMaxValue, float pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_float");
        this.defineFloat(serializer, pKey, pMinValue, pMaxValue, pDefaultValue);
    }

    /* ---------- FLOAT CONFIG / END ---------- */

    /* ---------- DOUBLE CONFIG / START ---------- */

    default void defineDouble ( ResourceLocation pSerializerId, String pKey, double pMinValue, double pMaxValue, double pDefaultValue ) {
        this.checkIfSerializerExist(pSerializerId);
        ConfigSerializer<Double> serializer = ConfigSerializerRegistry.get().getSerializer(pSerializerId);
        serializer.onRange(pMinValue, pMaxValue);

        this.defineConfig(serializer, pKey, pDefaultValue);
    }

    default void defineDouble ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, double pMinValue, double pMaxValue, double pDefaultValue ) {
        this.checkIfSerializerExist(pSerializerId);
        ConfigSerializer<Double> serializer = ConfigSerializerRegistry.get().getSerializer(pSerializerId);
        serializer.onRange(pMinValue, pMaxValue);

        this.defineConfig(serializer, pKey, pComment, pDefaultValue);
    }

    default void defineDouble ( String pKey, @Nullable String pComment, double pMinValue, double pMaxValue, double pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_double");
        this.defineDouble(serializer, pKey, pComment, pMinValue, pMaxValue, pDefaultValue);
    }

    default void defineDouble ( String pKey, double pMinValue, double pMaxValue, double pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_double");
        this.defineDouble(serializer, pKey, pMinValue, pMaxValue, pDefaultValue);
    }

    /* ---------- DOUBLE CONFIG / END ---------- */

    /* ---------- BOOLEAN CONFIG / START ---------- */

    default void defineBoolean ( ResourceLocation pSerializerId, String pKey, boolean pDefaultValue ) {
        this.checkIfSerializerExist(pSerializerId);
        ConfigSerializer<Boolean> serializer = ConfigSerializerRegistry.get().getSerializer(pSerializerId);
        this.defineConfig(serializer, pKey, pDefaultValue);
    }

    default void defineBoolean ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, boolean pDefaultValue ) {
        this.checkIfSerializerExist(pSerializerId);
        ConfigSerializer<Boolean> serializer = ConfigSerializerRegistry.get().getSerializer(pSerializerId);
        this.defineConfig(serializer, pKey, pComment, pDefaultValue);
    }

    default void defineBoolean ( String pKey, @Nullable String pComment, boolean pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_bool");
        this.defineBoolean(serializer, pKey, pComment, pDefaultValue);
    }

    default void defineBoolean ( String pKey, boolean pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_bool");
        this.defineBoolean(serializer, pKey, pDefaultValue);
    }

    /* ---------- BOOLEAN CONFIG / END ---------- */

    /* ---------- STRING CONFIG / START ---------- */

    default void defineString ( ResourceLocation pSerializerId, String pKey, String pDefaultValue ) {
        this.checkIfSerializerExist(pSerializerId);
        ConfigSerializer<String> serializer = ConfigSerializerRegistry.get().getSerializer(pSerializerId);
        this.defineConfig(serializer, pKey, pDefaultValue);
    }

    default void defineString ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, String pDefaultValue ) {
        this.checkIfSerializerExist(pSerializerId);
        ConfigSerializer<String> serializer = ConfigSerializerRegistry.get().getSerializer(pSerializerId);
        this.defineConfig(serializer, pKey, pComment, pDefaultValue);
    }

    default void defineString ( String pKey, @Nullable String pComment, String pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_str");
        this.defineString(serializer, pKey, pComment, pDefaultValue);
    }

    default void defineString ( String pKey, String pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_str");
        this.defineString(serializer, pKey, pDefaultValue);
    }

    /* ---------- STRING CONFIG / END ---------- */

    private <T> void checkIfSerializerExist ( ResourceLocation pSerializerId ) {
        ConfigSerializer<T> serializer = ConfigSerializerRegistry.get().getSerializer(pSerializerId);
        if ( serializer == null )
            throw new NullPointerException("(WUIDEBOOK) Can not find serializer in registry with id: '%s'".formatted(pSerializerId));
    }

}
