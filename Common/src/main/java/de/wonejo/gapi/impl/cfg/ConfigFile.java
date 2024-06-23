package de.wonejo.gapi.impl.cfg;

import de.wonejo.gapi.api.cfg.IConfigFile;
import de.wonejo.gapi.api.cfg.IConfigProvider;
import de.wonejo.gapi.api.cfg.IConfigValue;
import de.wonejo.gapi.api.cfg.serializer.IConfigValueSerializer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ConfigFile implements IConfigFile {

    private static final Logger LOGGER = LoggerFactory.getLogger("GuidebookAPI/Configuration");

    public static ConfigFile of (Path pConfigPath, String pFilename ) {
        Path configFile = Paths.get(pConfigPath.toString(), pFilename + ".properties");
        return new ConfigFile(configFile, pFilename);
    }

    private final Path configFile;
    private final String fileName;

    private IConfigProvider provider;
    private boolean broken = false;

    private ConfigFile ( Path pConfigFile, String pFileName ) {
        this.configFile = pConfigFile;
        this.fileName = pFileName;
    }

    public void init() {
        this.provider.buildConfigurations();

        if (!Files.exists(this.configFile)) {
            try {
                LOGGER.warn("GuidebookAPI/Configuration detect configuration files but there isn't any, creating configuration file: {}", this.fileName);
                String content = this.getFileContent();
                this.createFile(content);
            } catch (IOException pException) {
                LOGGER.warn("An error occurred while creating the configuration file '{}'", this.fileName);
                LOGGER.trace(pException.toString());
                this.broken = true;
            }
        }

        if ( !this.isBroken() ) {
            try {
                this.updateFileConfigurationsIfNeeded();
                this.loadConfigurations();
            } catch (IOException pException) {
                LOGGER.error("Error loading configurations in: {}", this.fileName);
                LOGGER.trace(pException.toString());
                this.broken = true;
            }
        }
    }

    public void loadConfigurations () throws IOException {
        Scanner reader = new Scanner(this.configFile);
        Map<String, IConfigValue<?>> configurations = new HashMap<>(this.provider.configurations());
        this.provider.configurations().clear();

        while (reader.hasNextLine()) {
            String line = reader.nextLine();

            if (line.isEmpty() || line.startsWith("#")) continue;

            String[] configParts = line.split("=", 2);
            if (configParts.length != 2) continue;

            String key = configParts[0];
            String value = configParts[1];
            IConfigValue<?> config = configurations.get(key);

            if (config != null) {
                IConfigValueSerializer<?> serializer = config.serializer();
                Object configValue = serializer.deserialize(value);

                if (configValue != null) {
                    this.provider.configurations().put(key, new ConfigValue<>(key, "", configValue));
                }
            }
        }

        this.provider.defineConfigurations();
        reader.close();
    }

    private void createFile (String pConfigContent) throws IOException {
        FileWriter writer = new FileWriter(this.configFile.toFile(), StandardCharsets.UTF_8);
        writer.write(pConfigContent);
        writer.close();
    }

    private @NotNull String getFileContent() {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, IConfigValue<?>> configEntry : this.provider.configurations().entrySet()) {
            Optional<String> comment = configEntry.getValue().comment();
            String key = configEntry.getKey();
            IConfigValue<?> value = configEntry.getValue();

            comment.ifPresent(s -> builder.append("# ").append(s).append(" || DEFAULT: ").append(value.defaultValue()).append("\n"));
            builder.append(key).append("=").append(serializeValue(value)).append("\n");
        }

        return builder.toString();
    }

    private <T> String serializeValue(@NotNull IConfigValue<T> value) {
        IConfigValueSerializer<T> serializer = value.serializer();
        T actualValue = value.get();
        return serializer.serialize(actualValue);
    }

    private void updateFileConfigurationsIfNeeded () throws IOException {
        Set<String> existingKeys = readExistingKeys();
        Set<String> providerKeys = this.provider.configurations().keySet();

        if (!existingKeys.containsAll(providerKeys)) {
            LOGGER.warn("Configuration file '%s' have missing keys, applying all the keys.".formatted(this.configFile.getFileName()));

            try ( BufferedWriter writer = new BufferedWriter(new FileWriter(this.configFile.toFile(), true)) )  {
                for ( String key : providerKeys ) {
                    if (!existingKeys.contains(key)) {
                        IConfigValue<?> config = this.provider.configurations().get(key);
                        Optional<String> comment = config.comment();
                        if ( comment.isPresent() )
                            writer.write("# " + comment.get() + " || DEFAULT: " + config.defaultValue());

                        writer.newLine();
                        writer.write(key + "=" + serializeValue(config));
                        writer.newLine();
                    }
                }
            }
        }
    }

    private @NotNull Set<String> readExistingKeys() throws IOException {
        Set<String> existingKeys = new HashSet<>();

        try (Scanner scanner = new Scanner(configFile)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty() && !line.startsWith("#")) {
                    String[] parts = line.split("=", 2);
                    if (parts.length == 2) {
                        existingKeys.add(parts[0].trim());
                    }
                }
            }
        }

        return existingKeys;
    }

    public ConfigFile provider (IConfigProvider pProvider )  {
        this.provider = pProvider;
        return this;
    }

    public boolean isBroken() {
        return this.broken;
    }
}
