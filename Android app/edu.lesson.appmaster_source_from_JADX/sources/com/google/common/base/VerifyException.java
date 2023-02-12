package com.google.common.base;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class VerifyException extends RuntimeException {
    public VerifyException() {
    }

    public VerifyException(@NullableDecl String message) {
        super(message);
    }

    public VerifyException(@NullableDecl Throwable cause) {
        super(cause);
    }

    public VerifyException(@NullableDecl String message, @NullableDecl Throwable cause) {
        super(message, cause);
    }
}
