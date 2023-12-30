package de.mrbunny.guidebook.api;

import de.mrbunny.guidebook.api.book.IBookBuilder;
import de.mrbunny.guidebook.api.util.References;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public interface IGuidebook {

    IBookBuilder buildBook ();

    @Nullable
    @OnlyIn(Dist.CLIENT)
    default ResourceLocation getModel ( ) {
        return new ResourceLocation(References.GUIDEBOOKAPI_ID, "guidebook");
    }

}
