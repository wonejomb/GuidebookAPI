package de.wonejo.wuidebook.api.wcontent.serializer;

import net.minecraft.resources.ResourceLocation;

public interface WContentDataTypeSerializer<T> {

    String serialize ( T pValue );

    T deserialize ( String pValue );

    record Type<T> ( ResourceLocation pId, Class<T> pClassType ) {}
}
