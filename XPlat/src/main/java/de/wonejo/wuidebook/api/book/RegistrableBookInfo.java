package de.wonejo.wuidebook.api.book;

import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public interface RegistrableBookInfo {

    ResourceLocation bookId ();
    ResourceLocation modelLocation ();

    Color bookColor ();

    boolean shouldSpawnWithBook ();

}
