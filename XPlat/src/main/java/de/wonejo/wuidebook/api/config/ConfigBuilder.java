package de.wonejo.wuidebook.api.config;

import de.wonejo.wuidebook.api.util.Constants;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Map;

public interface ConfigBuilder {

    /* ---------- CONFIG BASE : START ---------- */

    <T> void defineConfig ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, T pDefaultValue );

    void defineColor ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, Color pDefaultValue );
    void defineInteger ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, int pMinValue, int pMaxValue, int pDefaultValue );
    void defineFloat ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, float pMinValue, float pMaxValue, float pDefaultValue );
    void defineDouble ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, double pMinValue, double pMaxValue, double pDefaultValue );
    void defineBoolean ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, boolean pDefaultValue );
    void defineString ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, String pDefaultValue );

    Map<String, ConfigSpec<?>> getConfigurations ();

    /* ---------- CONFIG BASE : END ---------- */

    default void defineColor ( ResourceLocation pSerializerId, String pKey, Color pDefaultValue ) {
        this.defineColor(pSerializerId, pKey, null, pDefaultValue);
    }

    default void defineColor ( String pKey, String pComment, Color pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_color");
        this.defineColor(serializer, pKey, pComment, pDefaultValue);
    }

    default void defineInteger ( ResourceLocation pSerializerId, String pKey, int pMinValue, int pMaxValue, int pDefaultValue ) {
        this.defineInteger(pSerializerId, pKey, null, pMinValue, pMaxValue, pDefaultValue);
    }

    default void defineInteger ( String pKey, String pComment, int pMinValue, int pMaxValue, int pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_int");
        this.defineInteger(serializer, pKey, pComment, pMinValue, pMaxValue, pDefaultValue);
    }

    default void defineFloat ( ResourceLocation pSerializerId, String pKey, float pMinValue, float pMaxValue, float pDefaultValue ) {
        this.defineFloat(pSerializerId, pKey, null, pMinValue, pMaxValue, pDefaultValue);
    }

    default void defineFloat ( String pKey, String pComment, float pMinValue, float pMaxValue, float pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_float");
        this.defineFloat(serializer, pKey, pComment, pMinValue, pMaxValue, pDefaultValue);
    }

    default void defineDouble ( ResourceLocation pSerializerId, String pKey, double pMinValue, double pMaxValue, double pDefaultValue ) {
        this.defineDouble(pSerializerId, pKey, null, pMinValue, pMaxValue, pDefaultValue);
    }

    default void defineDouble ( String pKey, String pComment, double pMinValue, double pMaxValue, double pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_double");
        this.defineDouble(serializer, pKey, pComment, pMinValue, pMaxValue, pDefaultValue);
    }

    default void defineBoolean ( ResourceLocation pSerializerId, String pKey, boolean pDefaultValue ) {
        this.defineBoolean(pSerializerId, pKey, null, pDefaultValue);
    }

    default void defineBoolean ( String pKey, String pComment, boolean pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_bool");
        this.defineBoolean(serializer, pKey, pComment, pDefaultValue);
    }

    default void defineString ( ResourceLocation pSerializerId, String pKey, String pDefaultValue ) {
        this.defineString(pSerializerId, pKey, null, pDefaultValue);
    }

    default void defineString ( String pKey, String pComment, String pDefaultValue ) {
        ResourceLocation serializer = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "cfg_bool");
        this.defineString(serializer, pKey, pComment, pDefaultValue);
    }

}
