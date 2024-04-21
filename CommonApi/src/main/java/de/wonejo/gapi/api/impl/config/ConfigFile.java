package de.wonejo.gapi.api.impl.config;

import com.google.common.collect.Maps;
import de.wonejo.gapi.api.config.IConfigFile;
import de.wonejo.gapi.api.config.IConfigProvider;
import de.wonejo.gapi.api.config.IConfigValue;
import de.wonejo.gapi.api.config.serializer.IConfigValueSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

public class ConfigFile implements IConfigFile {

    private static final Logger LOGGER = LoggerFactory.getLogger("Config");

    public static ConfigFile of (Path pConfigPath, String pFilename ) {
        File configFile = new File(pConfigPath.toFile(), pFilename + ".properties");
        return new ConfigFile(configFile, pFilename);
    }

    private final File configFile;
    private final String fileName;

    private IConfigProvider provider;
    private boolean broken = false;

    private ConfigFile ( File pConfigFile, String pFileName ) {
        this.configFile = pConfigFile;
        this.fileName = pFileName;
    }

    public void init() {
        this.save();

        String content = this.getFileContent();

        if (!this.configFile.exists()) {
            try {
                LOGGER.warn("Creating configuration file: {}", this.fileName);
                this.createFile(content);
            } catch (IOException pException) {
                LOGGER.warn("Fail creating {} configuration file", this.fileName);
                LOGGER.trace(pException.toString());
                this.broken = true;
            }
        }

        if ( !this.isBroken() ) {
            try {
                this.updateFileWithMissingKeys();

                LOGGER.debug("Loading configurations from: {}", this.configFile);

                this.load();
            } catch (IOException pException) {
                LOGGER.error("Error loading config in: {}", this.fileName);
                LOGGER.trace(pException.toString());
                this.broken = true;
            }
        }
    }

    public void load() throws IOException {
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

    public void save() {
        if ( !this.isBroken() )
            this.provider.buildConfigurations();
    }

    private void createFile (String pConfigContent) throws IOException {
        FileWriter writer = new FileWriter(this.configFile, StandardCharsets.UTF_8);
        writer.write(pConfigContent);
        writer.close();
    }

    private String getFileContent() {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, IConfigValue<?>> configEntry : this.provider.configurations().entrySet()) {
            String comment = configEntry.getValue().comment();
            String key = configEntry.getKey();
            IConfigValue<?> value = configEntry.getValue();

            builder.append("#").append(comment).append("  ||  DEFAULT: ").append(value.defaultValue()).append("\n");
            builder.append(key).append("=").append(serializeValue(value)).append("\n");
        }

        return builder.toString();
    }

    private <T> String serializeValue(IConfigValue<T> value) {
        IConfigValueSerializer<T> serializer = value.serializer();
        T actualValue = value.get();
        return serializer.serialize(actualValue);
    }

    private void updateFileWithMissingKeys () throws IOException {
        LOGGER.warn("Config file needs update the configurations");
        Set<String> existingKeys = readExistingKeys();
        Set<String> providerKeys = this.provider.configurations().keySet();

        try ( BufferedWriter writer = new BufferedWriter(new FileWriter(this.configFile, true)) )  {
            for ( String key : providerKeys ) {
                if (!existingKeys.contains(key)) {
                    IConfigValue<?> config = this.provider.configurations().get(key);
                    String comment = config.comment();
                    Object value = config.get();
                    writer.write("#" + comment + "  ||  DEFAULT: " + config.defaultValue());
                    writer.newLine();
                    writer.write(key + "=" + serializeValue(config));
                    writer.newLine();
                }
            }
        }
    }

    private Set<String> readExistingKeys() throws IOException {
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
