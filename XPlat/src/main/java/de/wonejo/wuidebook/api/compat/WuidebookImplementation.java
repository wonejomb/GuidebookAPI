package de.wonejo.wuidebook.api.compat;

import de.wonejo.wuidebook.api.config.ConfigBuilder;
import de.wonejo.wuidebook.api.config.ConfigManager;
import de.wonejo.wuidebook.api.config.ConfigSerializerRegistry;

public interface WuidebookImplementation {

    default void onRegisterConfigSerializer ( ConfigSerializerRegistry pRegistry ) {}

    default void onRegisterCustomConfigFiles ( ConfigManager pManager ) {}

    /**
     * PLEASE, if you use any of these three methods, specify in your config the modId, sommething like: 'exampleModId.exampleConfig' PLEASE
     */
    default void onRegisterCustomClientConfig ( ConfigBuilder pBuilder ) {}
    default void onRegisterCustomServerConfig ( ConfigBuilder pBuilder ) {}
    default void onRegisterCustomCommonConfig ( ConfigBuilder pBuilder ) {}

}
