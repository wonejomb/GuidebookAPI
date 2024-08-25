package de.wonejo.wuidebook.api.config;

import de.wonejo.wuidebook.api.util.McEnvironment;
import de.wonejo.wuidebook.impl.config.ConfigFileImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public interface ConfigFile {

    void initializeFile ();

    String getFilename ();
    McEnvironment getEnvironment ();

    <T> T getValue ( String pKey );
    <T> Optional<T> getOptValue ( String pKey );

    <T> Map<String, ConfigSpec<T>> getConfigurationsByPrefix ( String pPrefix );
    <T> Set<ConfigSpec<T>> getConfigurationsSetByPrefix ( String pPrefix );

    interface Builder {
        @NotNull
        static Builder createBuilder () {
            return ConfigFileImpl.BuilderImpl.createBuilder();
        }

        Builder filename ( String pFilename );
        Builder env ( McEnvironment pEnvironment );
        Builder provider (Consumer<ConfigurationBuilder> pConfigProvider);

        ConfigFile build ();
    }

}
