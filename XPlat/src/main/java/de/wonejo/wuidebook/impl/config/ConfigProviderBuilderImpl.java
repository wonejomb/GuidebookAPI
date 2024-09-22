package de.wonejo.wuidebook.impl.config;

import com.google.common.collect.Maps;
import de.wonejo.wuidebook.api.config.ConfigManager;
import de.wonejo.wuidebook.api.config.ConfigProviderBuilder;
import de.wonejo.wuidebook.api.config.ConfigSpec;
import de.wonejo.wuidebook.api.config.serialization.ConfigValueSerializer;
import de.wonejo.wuidebook.api.config.serialization.ListConfigValueSerializer;
import de.wonejo.wuidebook.api.util.TriState;
import de.wonejo.wuidebook.impl.config.serializer.EnumSerializer;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

class ConfigProviderBuilderImpl implements ConfigProviderBuilder {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Map<String, ConfigSpec<?>> specs = Maps.newHashMap();

    public void defineString(ResourceLocation pSerializerId, String pKey, String pDescription, String pDefaultValue) {
        ConfigValueSerializer<String> serializer = ConfigManager.get().getSerializer(pSerializerId);
        ConfigSpec<String> spec = ConfigSpec.create(serializer, pKey, pDescription, pDefaultValue);

        if ( this.specs.putIfAbsent(pKey, spec) != null ) this.sendWarnLog(spec);
    }

    public void defineBoolean(ResourceLocation pSerializerId, String pKey, String pDescription, TriState pDefaultValue) {
        ConfigValueSerializer<TriState> serializer = ConfigManager.get().getSerializer(pSerializerId);
        ConfigSpec<TriState> spec = ConfigSpec.create(serializer, pKey, pDescription, pDefaultValue);

        if ( this.specs.putIfAbsent(pKey, spec) != null ) this.sendWarnLog(spec);
    }

    public void defineInt(ResourceLocation pSerializerId, String pKey, String pDescription, int pDefaultValue, int pMinValue, int pMaxValue) {
        ConfigValueSerializer<Integer> serializer = ConfigManager.get().getSerializer(pSerializerId);
        serializer.onRange(pMinValue, pMaxValue);
        ConfigSpec<Integer> spec = ConfigSpec.create(serializer, pKey, pDescription, pDefaultValue);

        if ( this.specs.putIfAbsent(pKey, spec) != null ) this.sendWarnLog(spec);
    }

    public void defneFloat(ResourceLocation pSerializerId, String pKey, String pDescription, float pDefaultValue, float pMinValue, float pMaxValue) {
        ConfigValueSerializer<Float> serializer = ConfigManager.get().getSerializer(pSerializerId);
        serializer.onRange(pMinValue, pMaxValue);
        ConfigSpec<Float> spec = ConfigSpec.create(serializer, pKey, pDescription, pDefaultValue);

        if ( this.specs.putIfAbsent(pKey, spec) != null ) this.sendWarnLog(spec);
    }

    public void defneDouble(ResourceLocation pSerializerId, String pKey, String pDescription, double pDefaultValue, double pMinValue, double pMaxValue) {
        ConfigValueSerializer<Double> serializer = ConfigManager.get().getSerializer(pSerializerId);
        serializer.onRange(pMinValue, pMaxValue);
        ConfigSpec<Double> spec = ConfigSpec.create(serializer, pKey, pDescription, pDefaultValue);

        if ( this.specs.putIfAbsent(pKey, spec) != null ) this.sendWarnLog(spec);
    }

    public <T extends Enum<T>> void defineEnum(T pType, String pKey, String pDescription, T pDefaultValue) {
        EnumSerializer<T> serializer = new EnumSerializer<>(pType.getDeclaringClass());
        ConfigSpec<T> spec = ConfigSpec.create(serializer, pKey, pDescription, pDefaultValue);

        if ( this.specs.putIfAbsent(pKey, spec) != null ) this.sendWarnLog(spec);
    }

    public <T> void defineList(ResourceLocation pSerializerId, ResourceLocation pValueSerializerId, String pKey, String pDescription, List<T> pDefaultValue) {
        ConfigValueSerializer<List<T>> serializer = ConfigManager.get().getSerializer(pSerializerId);
        ((ListConfigValueSerializer<T>) serializer).withValueSerializer(pValueSerializerId);
        ConfigSpec<List<T>> spec = ConfigSpec.create(serializer, pKey, pDescription, pDefaultValue);

        if ( this.specs.putIfAbsent(pKey, spec) != null ) this.sendWarnLog(spec);
    }

    public <T> void define(ResourceLocation pSerializerId, String pKey, String pDescription, T pDefaultValue) {
        ConfigValueSerializer<T> serializer = ConfigManager.get().getSerializer(pSerializerId);
        ConfigSpec<T> spec = ConfigSpec.create(serializer, pKey, pDescription, pDefaultValue);

        if ( this.specs.putIfAbsent(pKey, spec) != null ) this.sendWarnLog(spec);
    }

    private void sendWarnLog ( ConfigSpec<?> pSpec ) {
        LOGGER.error("Can not register spec with id: {}, there is already other spec with that id. ConfigSpec: {}", pSpec.key(), pSpec);
    }

    public Set<String> getKeys() {
        return this.specs.keySet();
    }

    public Collection<ConfigSpec<?>> getSpecifications() {
        return this.specs.values();
    }
}
