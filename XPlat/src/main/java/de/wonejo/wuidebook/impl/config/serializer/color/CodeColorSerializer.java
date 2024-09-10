package de.wonejo.wuidebook.impl.config.serializer.color;

import de.wonejo.wuidebook.api.config.serializer.ConfigSerializer;

import java.awt.*;
import java.util.Optional;

final class CodeColorSerializer implements ConfigSerializer<Color> {

    private static CodeColorSerializer INSTANCE;

    public String serialize(Color pValue) {
        return "color(" + pValue + ")";
    }

    public Optional<Color> deserialize(String pValue) {
        pValue = pValue.trim();
        if ( pValue.startsWith("color(") && pValue.endsWith(")") ) pValue = pValue.substring(6, pValue.length() - 1);

        int color = Integer.parseInt(pValue);
        return Optional.of(new Color(color));
    }

    static CodeColorSerializer get () {
        if ( CodeColorSerializer.INSTANCE == null ) CodeColorSerializer.INSTANCE = new CodeColorSerializer();
        return CodeColorSerializer.INSTANCE;
    }

}
