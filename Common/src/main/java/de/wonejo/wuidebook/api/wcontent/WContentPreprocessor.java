package de.wonejo.wuidebook.api.wcontent;

import net.minecraft.resources.ResourceLocation;

import java.util.List;

public interface WContentPreprocessor {

    String process ( ResourceLocation pFileLocation );

    List<String> processImport (ResourceLocation pImportId);


}
