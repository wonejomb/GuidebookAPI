package de.wonejo.wuidebook.wcontent;

import com.google.common.collect.Maps;
import de.wonejo.wuidebook.api.wcontent.WContentTag;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class WContentTagImpl<T> implements WContentTag<T> {

    public static <B> @NotNull WContentTag<B> createTag (Builder pBuilder ) {
        return new WContentTagImpl<>(pBuilder);
    }

    private final String name;
    private final Map<String, Property<?>> properties;
    private final Map<String, WContentTag<?>> subTags;

    WContentTagImpl (@NotNull Builder pBuilder ) {
        this.name = pBuilder.getName();
        this.properties = pBuilder.getProperties();
        this.subTags = pBuilder.getSubTags();
    }

    public String getName() {
        return this.name;
    }

    @SuppressWarnings("unchecked")
    public Property<T> getProperty(String pName) {
        return (Property<T>) this.properties.get(pName);
    }

    public WContentTag<?> getSubTag(String pName) {
        return this.subTags.get(pName);
    }

    public boolean hasEnded() {
        return false;
    }

    public static class Builder {

        @NotNull
        public static Builder of () { return new Builder(); }

        private String name;
        private final Map<String, Property<?>> properties = Maps.newHashMap();
        private final Map<String, WContentTag<?>> subTags = Maps.newHashMap();

        private Builder () {}

        public Builder name ( String pName ) {
            this.name = pName;
            return this;
        }

        public Builder addProperty ( Property<?> pProperty ) {
            this.properties.put(pProperty.getPropertyName(), pProperty);
            return this;
        }

        public Builder addSubTag ( WContentTag<?> pContentTag ) {
            this.subTags.put(pContentTag.getName(), pContentTag);
            return this;
        }

        public String getName() {
            return name;
        }

        public Map<String, Property<?>> getProperties() {
            return properties;
        }

        public Map<String, WContentTag<?>> getSubTags() {
            return subTags;
        }
    }
}
