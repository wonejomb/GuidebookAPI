package de.wonejo.wuidebook.impl.config.serializer;

import de.wonejo.wuidebook.api.config.serialization.ConfigValueSerializer;
import de.wonejo.wuidebook.api.util.TriState;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BooleanCfgSerializer implements ConfigValueSerializer<TriState> {

    public String serialize( @NotNull TriState pValue ) {
        return "bool(" + pValue + ")";
    }

    public DeserializeResult<TriState> deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("bool(") ) {
            if ( !pValue.endsWith(")") ) return DeserializeResult.fail(List.of("Boolean can not be deserialized! It seems to be open but it doesn't seem to be closed."));

            pValue = pValue.substring(5, pValue.length() - 1);
        }

        return DeserializeResult.success(TriState.fromBooleanText(pValue));
    }

    public boolean isValid(TriState pValue) {
        return true;
    }

    public String getValidValuesDescription() {
        return "Can be: 'true', 'false', ['undefined', 'unset', 'unknown']";
    }

}
