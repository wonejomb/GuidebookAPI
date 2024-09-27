package de.wonejo.wuidebook.api.wgc.value;

import net.minecraft.resources.ResourceLocation;

public record WgcValueTagType<T>(ResourceLocation id, Class<T> classType) {
}
