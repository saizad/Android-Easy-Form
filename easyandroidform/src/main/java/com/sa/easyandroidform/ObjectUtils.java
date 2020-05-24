package com.sa.easyandroidform;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class ObjectUtils {
    private ObjectUtils() {
    }

    public static boolean isNull(final @Nullable Object object) {
        return object == null;
    }

    public static boolean isNotNull(final @Nullable Object object) {
        return object != null;
    }

    /**
     * Returns the first non-`null` value of its arguments.
     */
    @NonNull
    public static <T> T coalesce(final @Nullable T value, final @NonNull T theDefault) {
        if (value != null) {
            return value;
        }
        return theDefault;
    }

    public static @NonNull
    <T> T requireNonNull(final @Nullable T value) throws NullPointerException {
        return requireNonNull(value, "Value should not be null.");
    }

    public static @NonNull
    <T> T requireNonNull(final @Nullable T value, final @NonNull String message)
            throws NullPointerException {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }

}
