package de.mrbunny.guidebook.api.config;

import java.io.IOException;

public interface IConfigFile {

    static IConfigFile of ( String pFileName ) { return null;};

    IConfigFile provider ( IConfigProvider pProvider );
    IConfigProvider getProvider ();

    void startConfigFile();
    void load () throws IOException;
    void save ();

    boolean isBroken ();

}
