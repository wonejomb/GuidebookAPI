package de.wonejo.wuidebook.impl.config;

import de.wonejo.wuidebook.api.config.serialization.ConfigValueSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class DeserializeResultImpl<T> implements ConfigValueSerializer.DeserializeResult<T> {

    @NotNull
    public static <B> ConfigValueSerializer.DeserializeResult<B> success ( B pResult ) {
        return new DeserializeResultImpl<>(pResult);
    }

    @NotNull
    public static <B> ConfigValueSerializer.DeserializeResult<B> haveBoth ( B pResult, List<String> pErrors ) {
        return new DeserializeResultImpl<>(pResult, pErrors);
    }

    @NotNull
    public static <B> ConfigValueSerializer.DeserializeResult<B> fail ( List<String> pErrors ) {
        return new DeserializeResultImpl<>(pErrors);
    }

    @Nullable
    private final T result;
    private final List<String> errors;

    private DeserializeResultImpl ( T pResult ) {
        this ( pResult, List.of() );
    }

    private DeserializeResultImpl ( List<String> pErrors ) {
        this (null, pErrors);
    }

    private DeserializeResultImpl ( @Nullable T pResult, List<String> pErrors ) {
        this.result = pResult;
        this.errors = pErrors;
    }

    public Optional<T> getResult() {
        return Optional.ofNullable(this.result);
    }

    public List<String> getErrors() {
        return this.errors;
    }

}
