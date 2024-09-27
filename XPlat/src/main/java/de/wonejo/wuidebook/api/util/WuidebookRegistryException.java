package de.wonejo.wuidebook.api.util;

public class WuidebookRegistryException extends RuntimeException {

    public WuidebookRegistryException ( String pMessage ) {
        super(pMessage);
    }

    public WuidebookRegistryException ( String pMessage, Throwable pThrowable ) {
        super(pMessage, pThrowable);
    }
}
