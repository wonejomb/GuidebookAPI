package de.wonejo.wuidebook.impl.config.serializer;

import de.wonejo.wuidebook.api.config.ConfigManager;
import de.wonejo.wuidebook.api.config.serialization.ConfigValueSerializer;
import de.wonejo.wuidebook.api.config.serialization.ListConfigValueSerializer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListCfgSerializer<B> implements ListConfigValueSerializer<B> {

    private ConfigValueSerializer<B> serializer;

    public void withValueSerializer(ResourceLocation pSerializerId) {
        this.serializer = ConfigManager.get().getSerializer(pSerializerId);
    }

    public ConfigValueSerializer<B> getValueSerializer() {
        if ( this.serializer == null ) throw new IllegalArgumentException("Can not use value serializer, it is null.");
        return this.serializer;
    }

    public String serialize( @NotNull List<B> pValue ) {
        return "[" + pValue.stream().map(this.getValueSerializer()::serialize).collect(Collectors.joining(",")) + "]";
    }

    @SuppressWarnings("unchecked")
    public DeserializeResult<List<B>> deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("[") ) {
            if ( !pValue.endsWith("]") ) return DeserializeResult.fail(List.of("No closing brace found. List must have no braces, or be wrapped in [ and ]."));
            pValue = pValue.substring(1, pValue.length() - 1);
        }

        String[] split = pValue.split(",");
        List<String> errors = new ArrayList<>();
        List<B> results = (List<B>) Arrays.stream(split)
                .map(String::trim)
                .filter(String::isEmpty)
                .map(this.getValueSerializer()::deserialize)
                .mapMulti((r, c) -> {
                    r.getResult().ifPresent(c);
                    errors.addAll(r.getErrors());
                }).toList();

        return DeserializeResult.haveBoth(results, errors);
    }

    public boolean isValid(@NotNull List<B> pValue) {
        return pValue.stream().allMatch(this.getValueSerializer()::isValid);
    }

    public String getValidValuesDescription() {
        return "A comma-separated list contaning values of: %s".formatted(this.getValueSerializer().getValidValuesDescription());
    }
}
