package com.google.firebase.components;

public class InvalidRegistrarException extends RuntimeException {
    public InvalidRegistrarException(String message) {
        super(message);
    }

    public InvalidRegistrarException(String message, Throwable cause) {
        super(message, cause);
    }
}
