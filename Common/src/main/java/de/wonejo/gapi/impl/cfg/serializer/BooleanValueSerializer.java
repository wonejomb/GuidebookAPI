package de.wonejo.gapi.impl.cfg.serializer;

import de.wonejo.gapi.api.cfg.serializer.IConfigValueSerializer;

public class BooleanValueSerializer implements IConfigValueSerializer<Boolean> {

    protected BooleanValueSerializer () {}

    public String serialize(Boolean pValue) {
        return pValue.toString();
    }

    public Boolean deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.equalsIgnoreCase("true") )
            return true;
        else if ( pValue.equalsIgnoreCase("false") )
            return false;
        else
            throw new IllegalArgumentException("Boolean config value can't deserialize value: %s. Allowed values are 'true' or 'false'".formatted(pValue));
    }

}
