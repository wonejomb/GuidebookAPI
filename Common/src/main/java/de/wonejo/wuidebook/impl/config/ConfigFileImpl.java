package de.wonejo.wuidebook.impl.config;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import de.wonejo.wuidebook.WuidebookCommon;
import de.wonejo.wuidebook.api.config.ConfigFile;
import de.wonejo.wuidebook.api.config.ConfigSpec;
import de.wonejo.wuidebook.api.config.ConfigurationBuilder;
import de.wonejo.wuidebook.api.logger.DebugLogger;
import de.wonejo.wuidebook.api.util.ConfigSerializationUtil;
import de.wonejo.wuidebook.api.util.McEnvironment;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Consumer;

public class ConfigFileImpl implements ConfigFile {
    private final String filename;
    private final McEnvironment environment;

    private final Path configFolder;
    private final Path filePath;

    private final ConfigurationBuilder configurationBuilder = ConfigurationBuilder.create();
    private boolean broken = false;

    public ConfigFileImpl ( String pFileName, @NotNull McEnvironment pEnvironment, @NotNull Consumer<ConfigurationBuilder> pSource ) {
        this.filename = pFileName;
        this.environment = pEnvironment;
        this.configFolder = WuidebookCommon.get().getAbstraction().getConfigPath().resolve("wuidebookapi");
        this.filePath = this.configFolder.resolve(pFileName + "-" + pEnvironment.toString().toLowerCase() + ".wcfg");
        pSource.accept(configurationBuilder);
    }

    public void initializeFile() {
        this.tryToBuildDefaultContent();
        this.tryToUpdateEntriesIfNeeded();
        this.tryToReadFile();
    }

    private void tryToReadFile () {
        this.checkIfFileIsBroken();
        try {
            this.readFileContent();
        } catch (IOException pException) {
            DebugLogger.log("An error occurred reading configuration from file: '%s'. Expeption: %s".formatted(this.filePath, pException));
        }
    }

    private void tryToUpdateEntriesIfNeeded () {
        this.checkIfFileIsBroken();

        try {
            Set<String> providerEntries = this.configurationBuilder.configurations().keySet();
            Set<String> fileEntries = this.readExistingKeys();

            this.updateFileEntries(fileEntries, providerEntries);
        } catch (IOException pException) {
            DebugLogger.log("The config file: '%s' needed to update entries but something goes wrong. Exception: %s".formatted(this.filePath, pException));
        }
    }

    private void tryToBuildDefaultContent () {
        try {
            if (!Files.exists(this.filePath))
                this.buildDefaultContent();
        } catch ( IOException pException ) {
            this.broken = false;
            DebugLogger.log("An error occurred creating file!. Exception: " + pException);
        }
    }

    private void readFileContent () throws IOException {
        BufferedReader reader = Files.newBufferedReader(this.filePath);
        String line;
        while ( (line = reader.readLine()) != null ) {
            if (line.isEmpty() || line.startsWith("//")) continue;
            String[] splitConfig = line.split("=", 2);
            if ( this.configurationBuilder.configurations().containsKey(splitConfig[0]) ) {
                String newValue = splitConfig[1];
                this.changeValue(this.configurationBuilder.configurations().get(splitConfig[0]), newValue);
            }
        }
    }

    private void updateFileEntries (Set<String> pExistingEntries, @NotNull Set<String> pProviderEntries ) throws IOException {
        DebugLogger.log("Config file: '%s' does not have all the provider entries, adding these.");

        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath.toFile(), true)) ) {
            for (String provEntry : pProviderEntries) {
                if (!pExistingEntries.contains(provEntry)) {
                    ConfigSpec<?> spec = this.configurationBuilder.configurations().get(provEntry);
                    if (spec.getComment().isPresent())
                        writer.write("// " + spec.getComment().get());

                    writer.write("// Default Value: " + ConfigSerializationUtil.serializeComment(spec));
                    writer.newLine();
                    writer.write(provEntry + "=" + ConfigSerializationUtil.serialize(spec));writer.newLine();

                }
            }
        }
    }

    private void buildDefaultContent () throws IOException {
        StringBuilder content = new StringBuilder();

        for ( Map.Entry<String, ConfigSpec<?>> entry : this.configurationBuilder.configurations().entrySet() ) {
            ConfigSpec<?> spec = entry.getValue();
            spec.getComment().ifPresent((msg) -> content.append("// ").append(msg).append("\n"));
            content.append("// Default Value: ").append(ConfigSerializationUtil.serializeComment(spec)).append("\n");
            content.append(spec.getKey()).append("=").append(ConfigSerializationUtil.serialize(spec)).append("\n");
        }

        Files.createDirectories(this.configFolder);
        try ( FileWriter writer = new FileWriter(this.filePath.toFile(), StandardCharsets.UTF_8) ) {
            writer.write(content.toString());
        }
    }


    private @NotNull Set<String> readExistingKeys() throws IOException {
        Set<String> existingKeys = Sets.newHashSet();

        try (BufferedReader reader = Files.newBufferedReader(this.filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty() && !line.startsWith("//")) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        existingKeys.add(parts[0].trim());
                    }
                }
            }
        }

        return existingKeys;
    }

    @SuppressWarnings("unchecked")
    public <T> T getValue(String pKey) {
        return (T) this.configurationBuilder.configurations().get(pKey).getValue();
    }

    public <T> Optional<T> getOptValue(String pKey) {
        T value = this.getValue(pKey);
        if ( value == null ) return Optional.empty();
        return Optional.of(value);
    }

    @SuppressWarnings("unchecked")
    public <T> Map<String, ConfigSpec<T>> getConfigurationsByPrefix(String pPrefix) {
        Map<String, ConfigSpec<T>> byPrefix = Maps.newHashMap();

        for (Map.Entry<String, ConfigSpec<?>> spec : this.configurationBuilder.configurations().entrySet() ) {
            if ( spec.getKey().startsWith(pPrefix) )
                byPrefix.put(spec.getKey(), (ConfigSpec<T>) spec.getValue());
        }

        return byPrefix;
    }

    @SuppressWarnings("unchecked")
    public <T> Set<ConfigSpec<T>> getConfigurationsSetByPrefix(String pPrefix) {
        Set<ConfigSpec<T>> byPrefix = Sets.newHashSet();

        for ( Map.Entry<String, ConfigSpec<?>> spec : this.configurationBuilder.configurations().entrySet() )
            if ( spec.getKey().startsWith(pPrefix)) byPrefix.add((ConfigSpec<T>) spec);

        return byPrefix;
    }

    private <T> void changeValue (@NotNull ConfigSpec<T> pSpecToChange, String pNewValue ) {
        T deserializedValue = pSpecToChange.serializer().deserialize(pNewValue);
        if ( !pSpecToChange.set(deserializedValue) )
            DebugLogger.log("Configuration spec '{}' don't change the value, isn't different from default or current.", pSpecToChange.getKey());
    }

    private void checkIfFileIsBroken () {
        if ( this.broken )
            throw new RuntimeException("Can't read or add config entries to file: '%s' because it is broken.".formatted(this.filePath));
    }

    public String getFilename() {
        return this.filename;
    }

    public McEnvironment getEnvironment() {
        return this.environment;
    }

    public static class BuilderImpl implements Builder {

        @NotNull
        public static BuilderImpl createBuilder () {
            return new BuilderImpl();
        }

        private String filename;
        private McEnvironment environment;
        private Consumer<ConfigurationBuilder> configProvider;

        public Builder filename(String pFilename) {
            this.filename = pFilename;
            return this;
        }

        public Builder env(McEnvironment pEnvironment) {
            this.environment = pEnvironment;
            return this;
        }

        public Builder provider(Consumer<ConfigurationBuilder> pConfigProvider) {
            this.configProvider = pConfigProvider;
            return this;
        }

        public ConfigFile build() {
            return new ConfigFileImpl(this.filename, this.environment, this.configProvider);
        }

    }

}
