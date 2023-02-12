package com.google.firebase.encoders;

public final class EncodingException extends RuntimeException {
    public EncodingException(String message) {
        super(message);
    }

    public EncodingException(String message, Exception cause) {
        super(message, cause);
    }
}
