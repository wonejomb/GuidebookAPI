package de.mrbunny.guidebook.api.util;

import java.io.IOException;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public final class PathUtil {
    private static final String unsafeFileChars = "[^\\w-]";
    private static boolean atomicMoveSupported = true;

    public static String sanitizePathName ( String pFilename ) {
        return String.join("_", pFilename.split(unsafeFileChars));
    }

    public static void writeUsingTempFile (Path pPath, Iterable<? extends CharSequence> pLines) throws IOException {
        Files.createDirectories(pPath.getParent());
        Path tempFile = Files.createTempFile(pPath.getParent(), null, null);
        try {
            Files.write(tempFile, pLines);
            moveAtomicReplace ( tempFile, pPath );
        } finally {
            if ( Files.exists(tempFile) ) {
                Files.delete(tempFile);
            }
        }
    }

    private static void moveAtomicReplace ( Path pSource, Path pTarget ) throws IOException {
        if ( atomicMoveSupported ) {
            try {
                Files.move(pSource, pTarget, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
                return;
            } catch (AtomicMoveNotSupportedException ignored) {
                atomicMoveSupported = false;
            }
        }
        Files.move(pSource, pTarget, StandardCopyOption.REPLACE_EXISTING);
    }
}
