package com.smilej.bestmile.common.validator;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;


public class Validator<T> {

    private final T subjectObject;

    private final List<Throwable> exceptions = new ArrayList<>();

    private Validator(T subjectObject) {
        this.subjectObject = subjectObject;
    }

    public static <T> Validator<T> of(@NonNull T subjectObject) {
        return new Validator<>(subjectObject);
    }

    public Validator<T> check(Predicate<T> validation, String message) {
        if (!validation.test(subjectObject)) {
            exceptions.add(new IllegalStateException(message));
        }
        return this;
    }

    public <U> Validator<T> checkNotNull(Function<T, U> projection, String message) {
        return check(projection, Objects::nonNull, message);
    }

    public <U> Validator<T> check(Function<T, U> projection, Predicate<U> validation, String message) {
        return check(projection.andThen(validation::test)::apply, message);
    }

    public void validate() throws IllegalArgumentException {
        if (exceptions.isEmpty()) {
            return;
        }
        IllegalArgumentException e = new IllegalArgumentException();
        exceptions.forEach(e::addSuppressed);
        throw e;
    }
}