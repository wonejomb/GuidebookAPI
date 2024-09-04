package de.wonejo.wuidebook.api.wcontent;

import de.wonejo.wuidebook.wcontent.WContentTagImpl;
import org.jetbrains.annotations.NotNull;

public interface WContentTag<T> {

    @NotNull
    static <B> WContentTag<B> createTag(WContentTagImpl.Builder pBuilder) {
        return WContentTagImpl.createTag(pBuilder);
    }

    String getName ();

    Property<T> getProperty ( String pName );
    WContentTag<?> getSubTag ( String pName );

    boolean hasEnded ();

    interface Property<T> {
        String getPropertyName ();

        T getValue ();
    }
}
