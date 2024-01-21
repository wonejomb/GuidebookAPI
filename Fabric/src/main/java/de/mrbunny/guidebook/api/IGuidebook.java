package de.mrbunny.guidebook.api;

import de.mrbunny.guidebook.api.book.IBookBuilder;
import de.mrbunny.guidebook.api.util.References;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.lang.ref.Reference;

public interface IGuidebook {

    IBookBuilder buildBook ();

    @Environment(EnvType.CLIENT)
    @Nullable
    default ResourceLocation getModelLocation () {
        return new ResourceLocation(References.GUIDEBOOKAPI_ID, "guidebook");
    }
}
