package com.google.firebase.firestore.util;

import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Preconditions {
    public static void checkArgument(boolean expression, @Nullable String errorMessageTemplate, @Nullable Object... errorMessageArgs) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static <T> T checkNotNull(@Nonnull T reference) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException();
    }

    public static <T> T checkNotNull(@Nonnull T reference, @Nullable Object errorMessage) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(String.valueOf(errorMessage));
    }

    public static <T> T checkNotNull(@Nonnull T reference, String errorFormatString, @Nullable Object... formatArgs) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(String.format(Locale.US, errorFormatString, formatArgs));
    }

    public static void checkState(boolean expression) {
        if (!expression) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message);
        }
    }
}
