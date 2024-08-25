package de.wonejo.wuidebook.api.book;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public interface BookInformation {

    Component title ();
    Component subTitle ();
    Component itemName ();
    Component author ();

    Color bookColor ();
    Color textColor ();
    Color entryColor ();
    Color entryAboveColor ();

    ResourceLocation id ();
    ResourceLocation modelLocation();

    boolean shouldSpawnWithBook ();
    boolean customModel ();

}
