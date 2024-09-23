package de.wonejo.wuidebook.api.book;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public interface RegistrableBookInfo {

    ResourceLocation bookId ();

    ResourceLocation modelLocation ();
    ResourceLocation mainBookGUITexture ();
    ResourceLocation pageBookGUITexture ();

    Component title ();
    Component header ();
    Component itemName ();
    Component author ();

    Color bookColor ();

    boolean shouldSpawnWithBook ();

}
