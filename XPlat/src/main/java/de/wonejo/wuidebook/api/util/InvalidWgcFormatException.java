package de.wonejo.wuidebook.api.util;

public class InvalidWgcFormatException extends RuntimeException {

    public InvalidWgcFormatException ( String pMessage, int pCodeLine, Throwable pThrowable ) {
        super("An error occurred on line: " + pCodeLine + " of a Wgc file: " + pMessage, pThrowable);
    }

    public InvalidWgcFormatException ( String pMessage, int pCodeLine ) {
        super("An error occurred on line: " + pCodeLine + " of a Wgc file: " + pMessage);
    }

    public InvalidWgcFormatException ( String pMessage, Throwable pThrowable ) {
        super(pMessage, pThrowable);
    }

    public InvalidWgcFormatException( String pMessage ) {
        super(pMessage);
    }

}
