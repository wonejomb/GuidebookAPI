package de.wonejo.wuidebook.impl.config.serializer;

import de.wonejo.wuidebook.api.config.serialization.ConfigValueSerializer;

import java.util.List;

public class StringCfgSerializer implements ConfigValueSerializer<String> {
    public String serialize(String pValue) {
        return "str(\"" + pValue + "\")";
    }

    public DeserializeResult<String> deserialize(String pValue) {
        if ( pValue.startsWith("str(\"") ) {
            if ( !pValue.endsWith("\")") ) return DeserializeResult.fail(List.of("String can not be serialized! It is open but doesn't seem to be closed."));
            pValue = pValue.substring(5, pValue.length() - 2);
        }

        return DeserializeResult.success(pValue);
    }

    public boolean isValid(String pValue) {
        return true;
    }

    public String getValidValuesDescription() {
        return "Any text inside the str(\"\").";
    }
}
