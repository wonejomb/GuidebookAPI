package de.mrbunny.guidebook.api.config.impl;

import com.mojang.blaze3d.vertex.VertexConsumer;
import de.mrbunny.guidebook.api.config.IConfigValue;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public final class ConfigValue<T> implements IConfigValue<T> {

    private final String key;
    private String comment;
    private final T defaultValue;
    public T value;

    public ConfigValue ( String pKey, T pDefaultValue )  {
        this(pKey, pDefaultValue, "");
    }

    public ConfigValue ( String pKey, T pDefaultValue, String pComment ) {
        this.key = pKey;
        this.defaultValue = pDefaultValue;
        this.value = pDefaultValue;
        this.comment = pComment;
    }

    public String getKey() {
        return this.key;
    }

    public String getComment() {
        return this.comment;
    }

    public T get() {
        return this.value == null ? this.defaultValue : this.value;
    }

    public T getDefaultValue() {
        return this.defaultValue;
    }
}
