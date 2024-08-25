package de.wonejo.wuidebook.api.config;

import de.wonejo.wuidebook.WuidebookCommon;
import de.wonejo.wuidebook.impl.config.ConfigurationBuilderImpl;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Map;

/**
 * <h1>ConfigurationBuilder</h1>
 * All a set of methods to register configurations
 */
public interface ConfigurationBuilder {

    static ConfigurationBuilder create () {
        return ConfigurationBuilderImpl.create();
    }

    /**
     * Method for config types that are not provided here.
     */
    <T> void createConfig (ResourceLocation pSerializerId, String pKey, @Nullable String pComment, T pDefaultValue);

    void createColorConfig (ResourceLocation pSerializerId, String pKey, @Nullable String pComment, Color pDefaultValue);

    default void createColorConfig ( String pKey, @Nullable String pComment, Color pDefaultRange ) {
        ResourceLocation serializerId = ResourceLocation.fromNamespaceAndPath(WuidebookCommon.MOD_ID, "color_cfg");
        this.createColorConfig(serializerId, pKey, pComment, pDefaultRange);
    }

    default void createColorConfig ( String pKey, Color pDefaultRange ) {
        this.createColorConfig(pKey, null, pDefaultRange);
    }

    /**
     * <h3>Integer config builders</h3>
     * Register a integer config with a min and max value
     */
    void createIntegerConfig ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, int pDefaultValue, int pMinRange, int pMaxRange );
    /**
     * Register a integer config with a min and max value with the default serializer provided by WuidebookAPI
     */
    default void createIntegerConfig ( String pKey, @Nullable String pComment, int pDefaultRange, int pMinRange, int pMaxRange ) {
        ResourceLocation serializerId = ResourceLocation.fromNamespaceAndPath(WuidebookCommon.MOD_ID, "integer_cfg");
        this.createIntegerConfig(serializerId, pKey, pComment, pDefaultRange, pMinRange, pMaxRange);
    }
    /**
     * Register a integer config with a min and max value with the default serializer provided by WuidebookAPI and without comment.
     */
    default void createIntegerConfig ( String pKey, int pDefaultRange, int pMinRange, int pMaxRange ) {
        this.createIntegerConfig(pKey, null, pDefaultRange, pMinRange, pMaxRange);
    }

    /**
     * <h3>Float config builders</h3>
     * Register a float config with a min and max value
     */
    void createFloatConfig ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, float pDefaultValue, float pMinRange, float pMaxRange );
    /**
     * Register a float config with a min and max value with the default serializer provided by WuidebookAPI
     */
    default void createFloatConfig ( String pKey, @Nullable String pComment, float pDefaultRange, float pMinRange, float pMaxRange ) {
        ResourceLocation serializerId = ResourceLocation.fromNamespaceAndPath(WuidebookCommon.MOD_ID, "float_cfg");
        this.createFloatConfig (serializerId, pKey, pComment, pDefaultRange, pMinRange, pMaxRange);
    }
    /**
     * Register a float config with a min and max value with the default serializer provided by WuidebookAPI and without comment.
     */
    default void createFloatConfig ( String pKey, float pDefaultRange, float pMinRange, float pMaxRange ) {
        this.createFloatConfig (pKey, null, pDefaultRange, pMinRange, pMaxRange);
    }

    /**
     * <h3>Double Config Builders</h3>
     * Register a double config with a min and max value
     */
    void createDoubleConfig ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, double pDefaultValue, double pMinRange, double pMaxRange );
    /**
     * Register a double config with a min and max value with the default serializer provided by WuidebookAPI
     */
    default void createDoubleConfig ( String pKey, @Nullable String pComment, double pDefaultRange, double pMinRange, double pMaxRange ) {
        ResourceLocation serializerId = ResourceLocation.fromNamespaceAndPath(WuidebookCommon.MOD_ID, "double_cfg");
        this.createDoubleConfig (serializerId, pKey, pComment, pDefaultRange, pMinRange, pMaxRange);
    }
    /**
     * Register a double config with a min and max value with the default serializer provided by WuidebookAPI and without comment.
     */
    default void createDoubleConfig ( String pKey, double pDefaultRange, double pMinRange, double pMaxRange ) {
        this.createDoubleConfig (pKey, null, pDefaultRange, pMinRange, pMaxRange);
    }

    /**
     * <h3>Boolean Config Builders</h3>
     * Register a boolean config
     */
    void createBooleanConfig ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, boolean pDefaultValue );
    /**
     * Register a boolean config with the default serializer provided by WuidebookAPI
     */
    default void createBooleanConfig ( String pKey, @Nullable String pComment, boolean pDefaultRange ) {
        ResourceLocation serializerId = ResourceLocation.fromNamespaceAndPath(WuidebookCommon.MOD_ID, "boolean_cfg");
        this.createBooleanConfig (serializerId, pKey, pComment, pDefaultRange);
    }
    /**
     * Register a boolean config with the default serializer provided by WuidebookAPI and withtout comment
     */
    default void createStringConfig ( String pKey, boolean pDefaultRange ) {
        this.createBooleanConfig (pKey, null, pDefaultRange);
    }

    /**
     * <h3>String Config Builders</h3>
     * Register a string config
     */
    void createStringConfig ( ResourceLocation pSerializerId, String pKey, @Nullable String pComment, String pDefaultValue );
    /**
     * Register a string config with the default provider provided by WuidebookAPI
     */
    default void createStringConfig ( String pKey, @Nullable String pComment, String pDefaultRange ) {
        ResourceLocation serializerId = ResourceLocation.fromNamespaceAndPath(WuidebookCommon.MOD_ID, "string_cfg");
        this.createStringConfig (serializerId, pKey, pComment, pDefaultRange);
    }
    /**
     * Register a string config with the default provider provided by WuidebookAPI and without comment
     */
    default void createStringConfig ( String pKey, String pDefaultRange ) {
        this.createStringConfig (pKey, null, pDefaultRange);
    }

    /**
     * This SHOULD NOT be used by mod developers, this is only to handle all the registered configs
     */
    Map<String, ConfigSpec<?>> configurations ();

}
