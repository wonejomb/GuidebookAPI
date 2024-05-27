package de.wonejo.gapi.impl.service;

import de.wonejo.gapi.api.service.BookRegistryHelper;
import de.wonejo.gapi.api.service.IPlatformHelper;
import de.wonejo.gapi.api.util.DebugLogger;

import java.util.ServiceLoader;

public final class Services {
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    public static final BookRegistryHelper BOOK_REGISTRY = load(BookRegistryHelper.class);

    private static  <T> T load ( Class<T> pClazz ) {
        final T loadedService = ServiceLoader.load(pClazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for: %s".formatted(pClazz.getName())));
        DebugLogger.debug("Loaded {} for service  {}", loadedService, pClazz);
        return loadedService;
    }
}
