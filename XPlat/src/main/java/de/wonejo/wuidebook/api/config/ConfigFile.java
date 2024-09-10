package de.wonejo.wuidebook.api.config;

import de.wonejo.wuidebook.api.util.McEnvironment;
import de.wonejo.wuidebook.impl.config.ConfigFileImpl;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.function.Consumer;

public interface ConfigFile {

    void initializeFile();

    void unloadFile ();

    <T> ConfigSpec<T> getConfig ( String pKey );
    <T> ConfigSpec<T> getNullable  ( String pKey );
    <T> T get ( String pKey );
    <T> Optional<T> getOpt ( String pKey );

    String getFilename ();
    McEnvironment getEnvironment ();

    interface Builder {

        @NotNull
        static Builder createBuilder () {
            return ConfigFileImpl.BuilderImpl.createBuilder();
        }

        Builder withName ( String pFileName );
        Builder onSide ( McEnvironment pEnvironment );
        Builder configProvider (Consumer<ConfigBuilder> pConfigurationProvider );

        ConfigFile build ();
    }

    enum Phase {
        SAVE,
        CHECK,
        READ,
        UNKNOWN
    }


}
