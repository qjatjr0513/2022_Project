package com.google.common.util.concurrent;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public class ExecutionError extends Error {
    private static final long serialVersionUID = 0;

    protected ExecutionError() {
    }

    protected ExecutionError(@NullableDecl String message) {
        super(message);
    }

    public ExecutionError(@NullableDecl String message, @NullableDecl Error cause) {
        super(message, cause);
    }

    public ExecutionError(@NullableDecl Error cause) {
        super(cause);
    }
}
