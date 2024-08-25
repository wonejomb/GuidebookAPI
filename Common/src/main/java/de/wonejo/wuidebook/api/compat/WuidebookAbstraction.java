package de.wonejo.wuidebook.api.compat;

import de.wonejo.wuidebook.api.book.BookRegistry;
import de.wonejo.wuidebook.api.config.ConfigSerializerRegistry;

@FunctionalInterface
public interface WuidebookAbstraction {

    void setupGuidesRegistry (BookRegistry pRegistry);

    default void setupCustomSerializers (ConfigSerializerRegistry pRegistry) {}

    default void setupCustomBookContent () {}

}
