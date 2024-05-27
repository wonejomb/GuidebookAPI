package de.wonejo.gapi.api.service;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceLocation;

public interface IDataComponentHelper {

    DataComponentType<Integer> getPageDataComponent ();
    DataComponentType<Integer> getCategoryDataComponent ();
    DataComponentType<ResourceLocation> getEntryDataComponent ();

}
