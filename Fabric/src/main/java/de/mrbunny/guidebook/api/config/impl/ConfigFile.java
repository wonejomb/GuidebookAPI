package de.mrbunny.guidebook.api.config.impl;

import de.mrbunny.guidebook.api.config.IConfigFile;
import de.mrbunny.guidebook.api.config.IConfigProvider;
import de.mrbunny.guidebook.api.config.IConfigValue;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

public class ConfigFile implements IConfigFile {

    private static final Logger LOGGER = LoggerFactory.getLogger("GuidebookAPI/ConfigFile");

    public static IConfigFile of(String pFileName) {
        File configFileDir = FabricLoader.getInstance().getConfigDir().toFile();
        File configFile = new File(configFileDir, pFileName + ".properties");

        return new ConfigFile(pFileName, configFile);
    }

    private final String fileName;
    private final File configFile;

    private IConfigProvider provider;
    private boolean broken = false;

    private ConfigFile ( String pFileName, File pFile ) {
        this.fileName = pFileName;
        this.configFile = pFile;
    }

    public void startConfigFile () {
        this.save();

        if (!this.configFile.exists()) {
            try {
                LOGGER.warn("Creating configuration file {}", this.fileName);
                this.createFile();
            } catch (IOException pException) {
                LOGGER.warn("Fail creating {} config file", this.fileName);
                LOGGER.trace(pException.toString());
                this.broken = true;
            }
        }

        if ( !this.broken ) {
            try {
                this.load();
            } catch (IOException pException) {
                LOGGER.error("Error loading config in {}", this.fileName);
                LOGGER.trace(pException.toString());
                broken = true;
            }
        }
    }

    public ConfigFile provider (IConfigProvider pProvider) {
        this.provider = pProvider;
        return this;
    }

    private void createFile ( ) throws IOException {
        String configFile = getFileContent();

        FileWriter writer = new FileWriter(this.configFile, StandardCharsets.UTF_8);
        writer.write(configFile);
        writer.close();
    }

    private String getFileContent () {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, IConfigValue<?>> configEntry : this.provider.configurations().entrySet()) {
            String comment = configEntry.getValue().getComment();
            String key = configEntry.getKey();
            IConfigValue<?> value = configEntry.getValue();

            builder.append("#").append(comment).append(" || Default Value: ").append(value.getDefaultValue()).append("\n");
            builder.append(key).append("=").append(value.get()).append("\n");
        }

        return builder.toString();
    }

    public void save() {
        if ( !this.isBroken() )
            this.provider.createConfigurations();
    }

    public void load() throws IOException {
        if ( !this.isBroken() ) {

            Scanner reader = new Scanner(this.configFile);

            for ( int lineId = 1 ; reader.hasNextLine(); lineId++ ) {
                String line = reader.nextLine();

                if (!line.isEmpty() && !line.startsWith("#")) {
                    String[] parts = line.split("=", 2);

                    if ( parts.length == 2 ) {

                        provider.loadedConfigurations().put(parts[0], new ConfigValue<>(parts[0], parts[1]));

                    } else {
                        LoggerFactory.getLogger("GuidebookAPI/Config").error("Error in line: {} of file '{}'", lineId, this.fileName);
                    }
                }
            }

            this.provider.loadConfigurations();
            this.provider.configurations().clear();
        }
    }

    public boolean isBroken() {
        return broken;
    }

    public IConfigProvider getProvider() {
        return this.provider;
    }
}
