package de.wonejo.gapi.api.impl.config;

import de.wonejo.gapi.api.config.IConfigFile;
import de.wonejo.gapi.api.config.IConfigProvider;
import de.wonejo.gapi.api.config.IConfigValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

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
                LOGGER.debug("Loading configurations from: {}", this.configFile);

                Map<String, IConfigValue<?>> fileConfigurations = getFileConfigurations();
                Set<String> providedConfigKeys = this.provider.configurations().keySet();

                if (!fileConfigurations.keySet().containsAll(providedConfigKeys))
                    rewrite();

                this.provider.configurations().clear();
                fileConfigurations.clear();

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

        while ( reader.hasNextLine() ) {
            String line = reader.nextLine();

            if (line.isEmpty() || line.startsWith("#")) continue;

            String[] configParts = line.split("=", 2);
            String key = configParts[0];
            String value = configParts[1];

            Object configValue = parseConfigValue(value);

            if ( configValue != null )  {
                this.provider.configurations().put(key, new ConfigValue<>(key, "", configValue));
            }
        }

        this.provider.defineConfigurations();
        this.provider.configurations().clear();
    }

    private void rewrite ( ) {
        LOGGER.warn("Config file rewrite required. ( All actual configuration will stay there )");

        try (BufferedReader reader = Files.newBufferedReader(this.configFile.toPath());
             BufferedWriter writer = Files.newBufferedWriter(this.configFile.toPath())) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("=")) {
                    writer.write(line);
                    writer.newLine();
                } else {
                    String comment = line.trim();
                    String key = comment.substring(1, comment.indexOf("||") - 1).trim();
                    IConfigValue<?> configValue = this.provider.configurations().get(key);

                    writer.write(comment);
                    writer.newLine();

                    if (configValue != null) {
                        String value = configValue.get().toString();
                        writer.write(key + "=" + value);
                        writer.newLine();
                    }
                }
            }

            for (Map.Entry<String, IConfigValue<?>> entry : this.provider.configurations().entrySet()) {
                String key = entry.getKey();
                if (!isKeyPresent(key, this.configFile)) {
                    String comment = "#" + entry.getValue().comment() + " || DEFAULT: " + entry.getValue().defaultValue();
                    String value = entry.getValue().get().toString();
                    writer.write(comment);
                    writer.newLine();
                    writer.write(key + "=" + value);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            LOGGER.error("Error in rewrite of file");
            throw new IllegalStateException(e);
        }
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

    private String getFileContent () {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, IConfigValue<?>> configEntry : this.provider.configurations().entrySet()) {
            String comment = configEntry.getValue().comment();
            String key = configEntry.getKey();
            IConfigValue<?> value = configEntry.getValue();

            builder.append("#").append(comment).append("  ||  DEFAULT: ").append(value.defaultValue()).append("\n");
            builder.append(key).append("=").append(value.get()).append("\n");
        }

        return builder.toString();
    }

    private Map<String, IConfigValue<?>> getFileConfigurations () throws IOException {
        Map<String, IConfigValue<?>> fileConfigurations = new HashMap<>();

        try ( BufferedReader reader = Files.newBufferedReader(this.configFile.toPath()) ) {
            String line;

            while((line = reader.readLine()) != null) {
                line = line.trim();

                if (!line.isEmpty()) {
                    String comment = "";

                    if (line.startsWith("#")) {
                        comment = line;
                    }

                    String[] parts = line.split("=", 2);

                    if (parts.length == 2) {
                        String key = parts[0].trim();
                        String value = parts[1].trim();

                        Object configValue = parseConfigValue(value);
                        fileConfigurations.put(key, new ConfigValue<>(key, comment, configValue));
                    }
                }
            }
        }

        return fileConfigurations;
    }

    private boolean isKeyPresent(String key, File configFile) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(configFile.toPath())) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#") && line.contains(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Object parseConfigValue (String pValue) {
        if ( pValue.equalsIgnoreCase("true") || pValue.equalsIgnoreCase("false") )
            return Boolean.parseBoolean(pValue);

        try {
            return Integer.parseInt(pValue);
        } catch (NumberFormatException pEx) {
            try {
                return Double.parseDouble(pValue);
            } catch (NumberFormatException pEx2 ) {
                return pValue;
            }
        }
    }

    public ConfigFile provider (IConfigProvider pProvider )  {
        this.provider = pProvider;
        return this;
    }

    public boolean isBroken() {
        return this.broken;
    }
}
