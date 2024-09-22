package de.wonejo.wuidebook.impl.config;

import com.google.common.collect.Maps;
import de.wonejo.wuidebook.WuidebookCommonMod;
import de.wonejo.wuidebook.api.config.ConfigFile;
import de.wonejo.wuidebook.api.config.ConfigProviderBuilder;
import de.wonejo.wuidebook.api.config.ConfigSpec;
import de.wonejo.wuidebook.api.util.McEnvironment;
import de.wonejo.wuidebook.api.util.TriState;
import org.apache.commons.compress.utils.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ConfigFileImpl implements ConfigFile {

    @NotNull
    public static Builder createBuilderImpl () {
        return new BuilderImpl();
    }

    private static final Logger LOGGER = LogManager.getLogger();
    private final Map<String, ConfigSpec<?>> loadedSpecifications = Maps.newHashMap();
    private final String name;
    private final Path filePath;
    private final McEnvironment mcEnvironment;
    private final ConfigFileExceptionFactory exceptionFactory;
    private final ConfigProviderBuilder providerBuilder = new ConfigProviderBuilderImpl();
    private FilePhase filePhase = FilePhase.UNKNOWN;
    private TriState fileLoaded = TriState.UNDEFINED;
    private TriState broken = TriState.UNDEFINED;

    private ConfigFileImpl (String pName, McEnvironment pEnvironment, ConfigFileExceptionFactory pExceptionFactory, @NotNull Consumer<ConfigProviderBuilder> pProviderBuilder ) {
        this.name = pName;
        this.mcEnvironment = pEnvironment;
        this.exceptionFactory = pExceptionFactory;
        this.filePath = WuidebookCommonMod.getConfigDirectory().resolve(pName + "-" + pEnvironment + ".wcfg");

        pProviderBuilder.accept(this.providerBuilder);
    }

    public void loadFile() {
        if ( this.fileLoaded.isTrue() ) return;

        this.createFileIfNeeded();
        this.tryToAdditionFile();
        this.tryToLoadFile();

        if ( this.broken.isTrue() ) {
            this.unloadFile();
            LOGGER.error("An error occurred in some phase of the file! The logs with that errors should appear in the latest log file so check it! The file must be unloaded :(");
        }
    }

    public void unloadFile() {
        if ( !this.fileLoaded.isTrue() ) return;

        this.filePhase = FilePhase.UNKNOWN;
        this.fileLoaded = TriState.FALSE;
        this.broken = TriState.UNDEFINED;
        this.loadedSpecifications.clear();
    }

    private void tryToLoadFile () {
        if ( this.broken.isTrue() ) return;
        this.filePhase = FilePhase.READING;

        try ( BufferedReader reader = Files.newBufferedReader(this.filePath) ) {
            ConfigSpecGetter getter = new ConfigSpecGetter(reader, this.providerBuilder.getSpecifications());

            for ( String key : this.providerBuilder.getKeys() ) {
                ConfigSpec<?> spec = getter.getSpec(key);
                this.loadedSpecifications.putIfAbsent(key, spec);
            }
        } catch ( IOException pException ) {
            LOGGER.error(this.exceptionFactory.createException(this, "Reading the file send an error.. why?",pException));
            this.broken = this.broken.turnTrue();
        }

        this.fileLoaded = this.fileLoaded.turnTrue();
    }

    private void tryToAdditionFile () {
        if (this.broken.isTrue()) return;
        this.filePhase = FilePhase.READING;

        Set<String> existingKeys = new HashSet<>();

        try (BufferedReader reader = Files.newBufferedReader(this.filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty() && !line.startsWith("//")) {
                    String key = line.split("=", 2)[0].trim();
                    existingKeys.add(key);
                }
            }
        } catch (IOException e) {
            LOGGER.error(this.exceptionFactory.createException(this, "Reading the keys of the file seems to show an error, weird.",e));
            this.broken = this.broken.turnTrue();
        }

        if (this.broken.isTrue()) return;
        this.filePhase = FilePhase.ADDITION;

        try (BufferedWriter writer = Files.newBufferedWriter(this.filePath, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            for (String providerKey : this.providerBuilder.getKeys()) {
                if (!existingKeys.contains(providerKey)) {
                    ConfigSpec<?> spec = this.providerBuilder.getSpecifications().stream()
                            .filter(spec2 -> spec2.key().equals(providerKey))
                            .findFirst()
                            .orElse(null);

                    if (spec != null) {
                        writer.write("// Default Value: " + spec.getSerializedValue());
                        writer.newLine();

                        if ( spec.description().isPresent() ) {
                            writer.write(spec.description().get());
                            writer.newLine();
                        }

                        writer.write(providerKey + "=" + spec.getSerializedValue());
                        writer.newLine();
                    }
                }
            }
        } catch (IOException pException) {
            LOGGER.error(this.exceptionFactory.createException(this, "Addition of missing keys just send a error? What.", pException));
            this.broken = this.broken.turnTrue();
        }
    }

    private void createFileIfNeeded () {
        this.filePhase = FilePhase.CREATION;

        try ( BufferedWriter writer = Files.newBufferedWriter(this.filePath) ) {
            for ( String key : this.providerBuilder.getKeys() ) {
                ConfigSpec<?> spec = this.providerBuilder.getSpecifications().stream().filter((spec2) -> spec2.key().equals(key)).findFirst().orElse(null);
                if ( spec == null ) continue;

                writer.write("// Default Value: " + spec.getSerializedValue());
                writer.newLine();

                if ( spec.description().isPresent() ) {
                    writer.write(spec.description().get());
                    writer.newLine();
                }

                writer.write(spec.key() + "=" + spec.getSerializedValue());
                writer.newLine();
            }
        } catch (IOException pException) {
            LOGGER.error(this.exceptionFactory.createException(this, "Some error must happened when creating file! What could it be D:? ", pException));
            this.broken = this.broken.turnTrue();
        }
    }

    @SuppressWarnings("unchecked")
    public <T> Optional<ConfigSpec<T>> getSpec(String pKey) {
        return Optional.ofNullable((ConfigSpec<T>) this.loadedSpecifications.get(pKey));
    }

    public Map<String, ConfigSpec<?>> getSpecsByPrefix(String pPrefix) {
        return this.loadedSpecifications.entrySet().stream()
                .filter(entrySpec -> entrySpec.getKey().startsWith(pPrefix))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public TriState isBroken() {
        return this.broken;
    }

    public TriState isFileLoaded() {
        return this.fileLoaded;
    }

    public String getName() {
        return this.name;
    }

    public Path getPath() {
        return this.filePath;
    }

    public McEnvironment getEnvironment() {
        return this.mcEnvironment;
    }

    public FilePhase getFilePhase() {
        return this.filePhase;
    }

    public static final class BuilderImpl implements Builder {

        private String name;
        private Consumer<ConfigProviderBuilder> providerBuilder;
        private McEnvironment environment = McEnvironment.COMMON;
        private ConfigFileExceptionFactory exceptionFactory = new DefaultExceptionFactory();

        public Builder setName(String pName) {
            this.name = pName;
            return this;
        }

        public Builder onEnv(McEnvironment pEnvironment) {
            this.environment = pEnvironment;
            return this;
        }

        public Builder withConfigProvider(Consumer<ConfigProviderBuilder> pProvider) {
            this.providerBuilder = pProvider;
            return this;
        }

        public Builder defineExceptionFactory(ConfigFileExceptionFactory pFactory) {
            this.exceptionFactory = pFactory;
            return this;
        }

        public ConfigFile build() {
            if ( this.name == null ) throw new IllegalArgumentException("Can not build file if name is null.");
            if ( this.providerBuilder == null ) throw new IllegalArgumentException("Can not build file if provider is null.");

            return new ConfigFileImpl(this.name, this.environment, this.exceptionFactory, this.providerBuilder);
        }

    }

    public static final class DefaultExceptionFactory implements ConfigFileExceptionFactory {

        public @NotNull ConfigFileException createException (@NotNull ConfigFile pFile, String pMessage, Throwable pOtherException) {
            String builder = "########## WUIDEBOOK / CONFIG ##########\n" +
                    "Dev Message: " + pMessage + "\n" +
                    "File Phase: " + pFile.getFilePhase() + "\n" +
                    "File Name: " + pFile.getName() +
                    "\nFile Path: '" + pFile.getPath() + "'\n" +
                    "File Environment: " + pFile.getEnvironment() + "\n" +
                    "Was Broken: " + pFile.isBroken() + "\n" +
                    "Discord: https://discord.gg/vpkYUrB2RB \n" +
                    "Github: https://github.com/wonejomb/GuidebookAPI\n" +
                    "\nException: " + pOtherException;

            return new ConfigFileException(builder, pOtherException);
        }

    }

    private static class ConfigSpecGetter {

        private static final Pattern SPEC_PATTERN = Pattern.compile("(\\w+)=([\\w\\s]+)");

        private final List<String> fileLines = Lists.newArrayList();
        private final Collection<ConfigSpec<?>> providerSpecs;

        public ConfigSpecGetter ( @NotNull BufferedReader pReader, Collection<ConfigSpec<?>> pProviderSpecs ) {
            this.providerSpecs = pProviderSpecs;

            try ( pReader ) {
                String line;
                while ( (line = pReader.readLine()) != null && !line.startsWith("//") )
                    fileLines.add(line);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @SuppressWarnings("unchecked")
        @Nullable
        public <T> ConfigSpec<T> getSpec(String pKey) {
            ConfigSpec<T> providerSpec = (ConfigSpec<T>) this.providerSpecs.stream().filter((spec2) -> spec2.key().equals(pKey)).findFirst().orElse(null);
            if (providerSpec == null) return null;

            for (String line : this.fileLines) {
                Matcher matcher = SPEC_PATTERN.matcher(line);
                if (matcher.find() && matcher.group(1).equals(pKey)) {
                    String value = matcher.group(2);
                    providerSpec.setFromSerialized(value);
                    return providerSpec;
                }
            }
            return null;
        }
    }

}
