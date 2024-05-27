package de.wonejo.gapi.service;

import de.wonejo.gapi.api.service.IDataComponentHelper;
import de.wonejo.gapi.core.ModDataComponents;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceLocation;

public class FabricDataComponentHelperImpl implements IDataComponentHelper {

    public DataComponentType<Integer> getPageDataComponent() {
        return ModDataComponents.PAGE;
    }

    public DataComponentType<Integer> getCategoryDataComponent() {
        return ModDataComponents.CATEGORY;
    }

    public DataComponentType<ResourceLocation> getEntryDataComponent() {
        return ModDataComponents.ENTRY;
    }

}
