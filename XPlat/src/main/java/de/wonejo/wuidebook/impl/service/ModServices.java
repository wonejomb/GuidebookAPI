package de.wonejo.wuidebook.impl.service;

import de.wonejo.wuidebook.api.service.WuidebookPlatformAbstraction;

import java.util.ServiceLoader;

public class ModServices {

    public static final WuidebookPlatformAbstraction ABSTRACTION = load(WuidebookPlatformAbstraction.class);

    private static <T> T load ( Class<T> pClassType ) {
        return ServiceLoader.load(pClassType).findFirst().orElseThrow(() -> new RuntimeException("Wuidebook platform abstraction does not seem to be present."));
    }

}
