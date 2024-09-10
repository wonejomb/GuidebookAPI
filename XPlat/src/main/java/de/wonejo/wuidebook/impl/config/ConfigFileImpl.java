package de.wonejo.wuidebook.impl.config;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import de.wonejo.wuidebook.api.config.ConfigBuilder;
import de.wonejo.wuidebook.api.config.ConfigFile;
import de.wonejo.wuidebook.api.config.ConfigSpec;
import de.wonejo.wuidebook.api.util.ConfigSerializationHelper;
import de.wonejo.wuidebook.api.util.McEnvironment;
import de.wonejo.wuidebook.impl.service.ModServices;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

@ApiStatus.Internal
public final class ConfigFileImpl implements ConfigFile {
    private static final ConfigSpec<?> NULL_SPEC = new ConfigSpecImpl<>("", null, "", null);

    private boolean loaded = false;
    private final String filename;
    private final McEnvironment environment;
    private final Path filePath;

    private ConfigBuilder builder = new ConfigBuilderImpl();
    private ConfigFile.Phase phase = Phase.UNKNOWN;
    private boolean broken = false;

    private ConfigFileImpl(String pFileName, McEnvironment pEnvironment, @NotNull Consumer<ConfigBuilder> pConfigProvider ) {
        this.filename = pFileName;
        this.environment = pEnvironment;
        this.filePath = ModServices.ABSTRACTION.getConfigPath().resolve(pFileName + "-" + pEnvironment + ".wcfg");

        pConfigProvider.accept(this.builder);
    }

    public void initializeFile() {
        this.tryToSaveDefaultFileIfNeeded();
        this.tryToUpdateFileIfNeeded();
        this.tryToReadFile();

        this.loaded = true;
    }

    public void unloadFile() {
        this.phase = Phase.UNKNOWN;
        this.builder = new ConfigBuilderImpl();
        this.loaded = false;
    }

    private void tryToSaveDefaultFileIfNeeded () {
        this.phase = Phase.SAVE;

        try {
            if ( !Files.exists(this.filePath) )
                this.saveDefaultFile();
        } catch ( IOException pException ) {
            this.broken = true;
            this.loaded = false;
        }
    }

    private void tryToUpdateFileIfNeeded () {
        this.phase = Phase.CHECK;
        this.checkIfBroken();

        try {
            Set<String> providerEntries = this.builder.getConfigurations().keySet();
            Set<String> fileEntries = this.readExistingKeys();

            this.updateFile(fileEntries, providerEntries);
        } catch ( IOException pException ) {
            this.broken = true;
            this.loaded = false;
        }
    }

    private void tryToReadFile ( ) {
        this.phase = Phase.READ;
        this.checkIfBroken();

        try {
            this.readFile();
        } catch (IOException pException) {
            this.broken = true;
            this.loaded = false;
            // I'm lazy, i do not wanna re-write all the exception x,d
            this.checkIfBroken();
        }
    }

    private void readFile () throws IOException {
        try ( BufferedReader reader = Files.newBufferedReader(this.filePath) ) {
            String line;
            while ( (line = reader.readLine()) != null ) {
                if ( line.isEmpty() || line.startsWith("//") ) continue;
                String[] split = line.split("=", 2);

                if ( !this.builder.getConfigurations().containsKey(split[0]) ) continue;

                ConfigSerializationHelper.setValue(this.builder.getConfigurations().get(split[0]), split[1]);
            }
        }
    }

    private void updateFile (Set<String> pExistingEntries, @NotNull Set<String> pProviderEntries) throws IOException {
        try ( BufferedWriter writer = Files.newBufferedWriter(this.filePath, StandardOpenOption.APPEND) ) {
            for ( String providerEntry : pProviderEntries ) {
                if ( pExistingEntries.contains(providerEntry) ) continue;
                ConfigSpec<?> spec = this.builder.getConfigurations().get(providerEntry);

                if ( spec.comment().isPresent() ) {
                    writer.write( "// " + spec.comment().get());
                    writer.newLine();
                }

                writer.write("// Default Value: " + ConfigSerializationHelper.serialize(spec));
                writer.newLine();
                writer.write(spec.key() + "=" + ConfigSerializationHelper.serialize(spec));
                writer.newLine();
            }
        }
    }

    private void saveDefaultFile () throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(this.filePath, StandardCharsets.UTF_8)) {
            for ( Map.Entry<String, ConfigSpec<?>> entry : this.builder.getConfigurations().entrySet() ) {
                ConfigSpec<?> spec = entry.getValue();

                if ( spec.comment().isPresent() ) {
                    writer.write( "// " + spec.comment().get());
                    writer.newLine();
                }

                writer.write("// Default Value: " + ConfigSerializationHelper.serialize(spec));
                writer.newLine();
                writer.write(spec.key() + "=" + ConfigSerializationHelper.serialize(spec));
                writer.newLine();
            }
        }
    }

    private @NotNull Set<String> readExistingKeys () throws IOException {
        Set<String> existingKeys = Sets.newHashSet();

        try (BufferedReader reader = Files.newBufferedReader(this.filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if ( line.isEmpty() || line.startsWith("//") ) continue;
                String[] parts = line.split("=", 2);
                if ( parts.length == 2 ) existingKeys.add(parts[0].trim());
            }
        }

        return existingKeys;
    }

    private void checkIfBroken () {
        if ( this.broken )
            throw new RuntimeException("Config file: '%s' is broken!\n -- Data -- \n  - File Phase: %s\n  - File Path: '%s'\n  - File Name: '%s'.\n\n - Note: Go to server support or open a github issue.\n\n -- Links -- \nGithub: https://github.com/wonejomb/GuidebookApi\nDiscord: https://discord.gg/vpkYUrB2RB"
                    .formatted(this.filename, this.phase, this.filePath, this.filename));
    }

    @SuppressWarnings("unchecked")
    public <T> ConfigSpec<T> getNullable (String pKey) {
        this.checkIfLoaded();

        return this.builder.getConfigurations().get(pKey) == null ? (ConfigSpec<T>) NULL_SPEC : (ConfigSpec<T>) builder.getConfigurations().get(pKey);
    }

    @SuppressWarnings("unchecked")
    public <T> ConfigSpec<T> getConfig(String pKey) {
        this.checkIfLoaded();

        ConfigSpec<T> spec = (ConfigSpec<T>) builder.getConfigurations().get(pKey);
        if ( spec == null ) throw new NullPointerException("Config with key: " + pKey + " is not present in file. ('%s')".formatted(this.filePath));
        return spec;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String pKey) {
        this.checkIfLoaded();

        return (T) this.getConfig(pKey).getValue();
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<T> getOpt(String pKey) {
        this.checkIfLoaded();

        ConfigSpec<T> spec = (ConfigSpec<T>) builder.getConfigurations().get(pKey);
        return (Optional<T>) Optional.of(spec);
    }

    private void checkIfLoaded () {
        if (!this.loaded)
            throw new RuntimeException("Config file '%s' is not loaded, the file can not perform actions if not loaded! \n  - File Path: %s".formatted(this.filename, this.filePath));
    }

    public String getFilename() {
        return this.filename;
    }

    public McEnvironment getEnvironment() {
        return this.environment;
    }

    @ApiStatus.Internal
    public static class BuilderImpl implements ConfigFile.Builder {

        @NotNull
        public static ConfigFile.Builder createBuilder () { return new BuilderImpl(); }

        private BuilderImpl () {}

        private String filename;
        private McEnvironment mcEnvironment;
        private Consumer<ConfigBuilder> configProvider;

        public ConfigFile.Builder withName(String pFileName) {
            this.filename = pFileName;
            return this;
        }

        public ConfigFile.Builder onSide(McEnvironment pEnvironment) {
            this.mcEnvironment = pEnvironment;
            return this;
        }

        public ConfigFile.Builder configProvider(Consumer<ConfigBuilder> pConfigurationProvider) {
            this.configProvider = pConfigurationProvider;
            return this;
        }

        public ConfigFileImpl build() {
            if ( filename == null ) throw new IllegalStateException("Can not create config file if filename is null.");
            if ( this.mcEnvironment == null ) mcEnvironment = McEnvironment.COMMON;
            if ( this.configProvider == null ) throw new IllegalStateException("Can not create config file if not provider is provided. ( Long live redundancy! )");

            return new ConfigFileImpl(this.filename, this.mcEnvironment, this.configProvider);
        }
    }


}

