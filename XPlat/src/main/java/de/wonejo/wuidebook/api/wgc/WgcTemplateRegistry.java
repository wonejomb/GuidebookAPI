package de.wonejo.wuidebook.api.wgc;

import com.google.common.collect.Maps;
import de.wonejo.wuidebook.api.util.WuidebookRegistryException;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class WgcTemplateRegistry {

    private static WgcTemplateRegistry INSTANCE;

    public static WgcTemplateRegistry get () {
        if ( WgcTemplateRegistry.INSTANCE == null ) WgcTemplateRegistry.INSTANCE = new WgcTemplateRegistry();
        return WgcTemplateRegistry.INSTANCE;
    }

    private final Map<ResourceLocation, WgcTemplate> templates = Maps.newHashMap();

    private WgcTemplateRegistry () {}

    public void registerTemplate (@NotNull Supplier<WgcTemplate> pTemplate) {
        WgcTemplate template = pTemplate.get();
        if  ( this.templates.containsKey(template.templateId()) ) throw new WuidebookRegistryException("Can not register Wgc Template because it already exist: " + template.templateId());
        this.templates.put(template.templateId(), template);
    }

    public boolean checkProperties ( ResourceLocation pTemplateId, WgcTag pTag ) {
        WgcTemplate template = this.templates.get(pTemplateId);
        if ( template == null ) throw new WuidebookRegistryException("Wgc template: '" + pTemplateId + "' seems to not be present in registry, can not check properties.");
        return template.isTagValid(pTag);
    }

    public boolean checkPropertiesWithOptTag ( ResourceLocation pTemplateId, WgcTag pTag ) {
        Optional<WgcTemplate> template = this.getOptTemplate(pTemplateId);
        return template.map(wgcTemplate -> wgcTemplate.isTagValid(pTag)).orElse(false);
    }

    public WgcTemplate getTemplate ( ResourceLocation pTemplateId ) {
        WgcTemplate template = this.templates.get(pTemplateId);
        if ( template == null ) throw new WuidebookRegistryException("Wgc template: '" + pTemplateId + "' seems to not be present in registry.");
        return template;
    }

    public Optional<WgcTemplate> getOptTemplate ( ResourceLocation pTemplateId ) {
        WgcTemplate template = this.templates.get(pTemplateId);
        return Optional.ofNullable(template);
    }

}
