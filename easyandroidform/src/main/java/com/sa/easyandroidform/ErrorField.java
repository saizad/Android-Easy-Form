package com.sa.easyandroidform;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import io.reactivex.exceptions.CompositeException;

public class ErrorField {

    private @NonNull
    final CompositeException exception;
    private @NonNull
    final String field;

    public ErrorField(@NotNull String field, @NotNull CompositeException exception) {
        this.exception = exception;
        this.field = field;
    }

    @NonNull
    public CompositeException getException() {
        return exception;
    }

    public String getField() {
        return field;
    }


}
