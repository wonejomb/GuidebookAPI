package de.wonejo.wuidebook.api.wgc;

import de.wonejo.wuidebook.impl.wgc.WgcTemplateImpl;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public interface WgcTemplate {

    @NotNull
    static Builder builder () {
        return WgcTemplateImpl.builderImpl();
    }

    default boolean isTagValid ( WgcTag pTag ) {
        for ( ResourceLocation property : this.requiredProperties() )
            if ( !pTag.haveProperty(property) ) return false;

        return true;
    }

    Set<ResourceLocation> requiredProperties ();
    Set<ResourceLocation> optionalProperties ();

    ResourceLocation templateId ();

    interface Builder {
        Builder id ( ResourceLocation pId );

        Builder required ( ResourceLocation pPropertyKey );
        Builder optional ( ResourceLocation pPropertyKey );

        Builder requires ( ResourceLocation... pPropertiesKeys );
        Builder optionals ( ResourceLocation... pPropertiesKeys );

        WgcTemplate build ();
    }
}
