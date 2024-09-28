package de.wonejo.wuidebook.impl.wgc;

import com.google.common.collect.Sets;
import de.wonejo.wuidebook.api.wgc.WgcTemplate;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Set;

public class WgcTemplateImpl implements WgcTemplate {

    @NotNull
    public static WgcTemplate.Builder builderImpl () {
        return new BuilderImpl();
    }

    private final ResourceLocation id;
    private final Set<ResourceLocation> requiredProperties;
    private final Set<ResourceLocation> optionalProperties;

    private WgcTemplateImpl ( ResourceLocation pId, Set<ResourceLocation> pRequiredProperties, Set<ResourceLocation> pOptionalProperties ) {
        this.id = pId;
        this.requiredProperties = pRequiredProperties;
        this.optionalProperties = pOptionalProperties;
    }

    public Set<ResourceLocation> requiredProperties() {
        return this.requiredProperties;
    }

    public Set<ResourceLocation> optionalProperties() {
        return this.optionalProperties;
    }

    public ResourceLocation templateId() {
        return this.id;
    }

    public static class BuilderImpl implements Builder {

        private ResourceLocation id;
        private final Set<ResourceLocation> requiredProperties = Sets.newHashSet();
        private final Set<ResourceLocation> optionalProperties = Sets.newHashSet();

        private BuilderImpl () {}

        public Builder id(ResourceLocation pId) {
            this.id = pId;
            return this;
        }

        public Builder required(ResourceLocation pPropertyKey) {
            this.requiredProperties.add(pPropertyKey);
            return this;
        }

        public Builder optional(ResourceLocation pPropertyKey) {
            this.optionalProperties.add(pPropertyKey);
            return this;
        }

        public Builder requires(ResourceLocation... pPropertiesKeys) {
            this.requiredProperties.addAll(Arrays.asList(pPropertiesKeys));
            return this;
        }

        public Builder optionals(ResourceLocation... pPropertiesKeys) {
            this.optionalProperties.addAll(Arrays.asList(pPropertiesKeys));
            return this;
        }

        public WgcTemplate build() {
            if ( this.id == null ) throw new IllegalArgumentException("Can not create Wgc template if id is null.");
            return new WgcTemplateImpl(this.id, this.requiredProperties, this.optionalProperties);
        }

    }
}
