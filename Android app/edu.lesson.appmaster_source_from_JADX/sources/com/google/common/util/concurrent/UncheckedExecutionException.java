package com.google.common.util.concurrent;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class UncheckedExecutionException extends RuntimeException {
    private static final long serialVersionUID = 0;

    protected UncheckedExecutionException() {
    }

    protected UncheckedExecutionException(@NullableDecl String message) {
        super(message);
    }

    public UncheckedExecutionException(@NullableDecl String message, @NullableDecl Throwable cause) {
        super(message, cause);
    }

    public UncheckedExecutionException(@NullableDecl Throwable cause) {
        super(cause);
    }
}
