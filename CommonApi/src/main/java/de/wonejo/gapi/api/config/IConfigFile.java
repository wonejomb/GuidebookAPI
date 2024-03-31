package de.wonejo.gapi.api.config;

import de.wonejo.gapi.api.impl.config.ConfigFile;

import java.io.IOException;
import java.nio.file.Path;

public interface IConfigFile {

    static IConfigFile of ( Path pConfigPath, String pFilename ) { return ConfigFile.of(pConfigPath, pFilename); }

    void init ();

    void save ();
    void load () throws IOException;

    IConfigFile provider ( IConfigProvider pProvider );

    boolean isBroken ();

}
