package de.wonejo.gapi.api.cfg;

import de.wonejo.gapi.impl.cfg.ConfigFile;

import java.io.IOException;
import java.nio.file.Path;

public interface IConfigFile {

    static IConfigFile of ( Path pConfigPath, String pFileName ) { return ConfigFile.of(pConfigPath, pFileName); };

    IConfigFile provider ( IConfigProvider pProvider );

    void init();
    void load () throws IOException;
    void save ();

    boolean isBroken ();


}
