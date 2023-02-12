package com.google.common.util.concurrent;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class UncheckedTimeoutException extends RuntimeException {
    private static final long serialVersionUID = 0;

    public UncheckedTimeoutException() {
    }

    public UncheckedTimeoutException(@NullableDecl String message) {
        super(message);
    }

    public UncheckedTimeoutException(@NullableDecl Throwable cause) {
        super(cause);
    }

    public UncheckedTimeoutException(@NullableDecl String message, @NullableDecl Throwable cause) {
        super(message, cause);
    }
}
