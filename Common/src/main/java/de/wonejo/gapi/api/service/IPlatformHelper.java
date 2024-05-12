package de.wonejo.gapi.api.service;

import java.nio.file.Path;

public interface IPlatformHelper {

    String getPlatformName ();
    String getActiveNamespace();
    Path getConfigPath ();
    boolean isDevelopmentEnvironment ();

}
