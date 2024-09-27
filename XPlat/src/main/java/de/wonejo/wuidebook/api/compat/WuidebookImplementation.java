package de.wonejo.wuidebook.api.compat;

import de.wonejo.wuidebook.api.config.ConfigManager;
import de.wonejo.wuidebook.api.config.ConfigProviderBuilder;
import de.wonejo.wuidebook.api.wgc.WgcValueTypeRegistry;

public interface WuidebookImplementation {

    /**
     * Register custom serialization for custom configurations.
     * <p/>
     * Note: Do not use this method to register config files, only serializers.
     * @since 4.0.0-dev1
     */
    default void onRegisterConfigSerializer ( ConfigManager pManager ) {}

    /**
     * PLEASE, if you use any of these three methods, specify in your config the modId, sommething like: 'exampleModId.exampleConfig' PLEASE.
     * <p/>
     * These three methods are for the main-config files of mod, if you want to add a custom config definitions use some of this methods.
     * @since 4.0.0-dev1
     */
    default void onRegisterCustomClientConfig ( ConfigProviderBuilder pBuilder ) {}
    default void onRegisterCustomServerConfig ( ConfigProviderBuilder pBuilder ) {}
    default void onRegisterCustomCommonConfig ( ConfigProviderBuilder pBuilder ) {}

    /**
     * If add custom WGC content templates and need to add other value type use this, might help.
     * @since 4.0.0-dev2
     */
    default void onRegisterCustomWGCValueType ( WgcValueTypeRegistry pRegistry ) {}

}
