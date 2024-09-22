package de.wonejo.wuidebook.api.service;

import de.wonejo.wuidebook.api.compat.WuidebookImplementation;

import java.nio.file.Path;
import java.util.List;

public interface XPlatAbstraction {

    List<WuidebookImplementation> gatherImplementations ();

    Path getConfigPath ();
    boolean isDevWorkspace ();

}
