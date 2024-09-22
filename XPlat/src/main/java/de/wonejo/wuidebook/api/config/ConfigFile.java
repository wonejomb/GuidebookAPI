package de.wonejo.wuidebook.api.config;

import de.wonejo.wuidebook.api.util.McEnvironment;
import de.wonejo.wuidebook.api.util.TriState;
import de.wonejo.wuidebook.impl.config.ConfigFileImpl;
import org.apache.commons.lang3.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public interface ConfigFile {

    /**
     * Create a {@link Builder}
     * @since 4.0.0-dev2
     */
    @NotNull
    static Builder createBuilder () {
        return ConfigFileImpl.createBuilderImpl();
    }

    /**
     * Get a config spec from loaded ones.
     * @since 4.0.0-dev1
     */
    <T> Optional<ConfigSpec<T>> getSpec ( String pKey );

    /**
     * Get a set of config specs from loaded by a prefix.
     * @since 4.0.0-dev1
     */
    Map<String, ConfigSpec<?>> getSpecsByPrefix ( String pPrefix );

    /**
     * Load the config specs from config file.
     * @since 4.0.0-dev2
     */
    void loadFile ();
    /**
     * Unload the config specs, returning the loaded specs to a clear state.
     * @since 4.0.0-dev2
     */
    void unloadFile ();

    /**
     * Get if config file is broken
     * @since 4.0.0-dev2
     */
    TriState isBroken ();
    /**
     * Get if file is loaded.
     * @since 4.0.0-dev2
     */
    TriState isFileLoaded ();

    /**
     * Get the filename
     * @since 4.0.0-dev2
     */
    String getName ();
    /**
     * Get the file path.
     * @since 4.0.0-dev2
     */
    Path getPath ();
    /**
     * Get the file environment.
     * @since 4.0.0-dev2
     */
    McEnvironment getEnvironment ();

    /**
     * Get the {@link FilePhase} on file.
     * @since 4.0.0-dev2
     */
    FilePhase getFilePhase ();

    /**
     * Define a config file builder.
     * @since 4.0.0-dev2
     */
    interface Builder {
        /**
         * Set the filename
         * @since 4.0.0-dev1
         */
        ConfigFile.Builder setName ( String pName );

        /**
         * Set the file minecraft environment ( if server, it'll be processed on server start, if in client well, when client starts and if common when the mod load. )
         * @since 4.0.0-dev1
         */
        ConfigFile.Builder onEnv ( McEnvironment pEnvironment );

        /**
         * Set the configurations provider with a {@link ConfigProviderBuilder}, where all the {@link ConfigSpec} are defined.
         * @since 4.0.0-dev2
         */
        ConfigFile.Builder withConfigProvider ( Consumer<ConfigProviderBuilder> pProvider );

        /**
         * Set an exception factory for errors, it have one by default but if you are adding a custom config file you can put your own exception factory.
         * @since 4.0.0-dev2
         */
        ConfigFile.Builder defineExceptionFactory ( ConfigFileExceptionFactory pFactory );

        /**
         * Build the file.
         * @since 4.0.0-dev2
         */
        ConfigFile build ();
    }

    /**
     * Provides a method to make the exceptions to config file.
     * @since 4.0.0-dev2
     */
    interface ConfigFileExceptionFactory {
        /**
         * Create a exception when a error appears
         * @since 4.0.0-dev2
         */
        ConfigFileException createException ( ConfigFile pFile, String pMessage, Throwable pOtherException );
    }

    /**
     * Represents a exception in file when something bad happens.
     * @since 4.0.0-dev2
     */
    @SuppressWarnings("deprecation")
    class ConfigFileException extends RuntimeException {

        public ConfigFileException ( String pMessage, Throwable pThrowable ) {
            super(StringEscapeUtils.escapeJava(pMessage), pThrowable);
        }

    }

    /**
     * Define a file phase, creation of this, addition, reading, by default it is unknown.
     * @since 4.0.0-dev2
     */
    enum FilePhase {
        // When creating the file ( Only present if file doesn't exist )
        CREATION,
        // When missing provider keys in file.
        ADDITION,
        // When reading file
        READING,
        // Unknown ( It hasn't been in any of the previous phases )
        UNKNOWN
    }

}
