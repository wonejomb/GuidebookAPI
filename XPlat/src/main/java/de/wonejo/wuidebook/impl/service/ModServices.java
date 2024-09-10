package de.wonejo.wuidebook.impl.service;

import de.wonejo.wuidebook.api.service.XPlatAbstraction;

import java.util.ServiceLoader;

public class ModServices {

    public static final XPlatAbstraction ABSTRACTION = load(XPlatAbstraction.class);

    private static <T> T load ( Class<T> pClassType ) {
        return ServiceLoader.load(pClassType).findFirst().orElseThrow(() -> new RuntimeException("Wuidebook platform abstraction does not seem to be present."));
    }

}
