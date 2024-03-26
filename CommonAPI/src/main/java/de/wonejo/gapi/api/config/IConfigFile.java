package de.wonejo.gapi.api.config;

import java.io.IOException;
import java.nio.file.Path;

public interface IConfigFile {

    static IConfigFile of ( Path pConfigPath, String pFilename ) { return null; }

    void init ();

    void save ();
    void load () throws IOException;

    IConfigFile provider ( IConfigProvider pProvider );

    boolean isBroken ();

}
