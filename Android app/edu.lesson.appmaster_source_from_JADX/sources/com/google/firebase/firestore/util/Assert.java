package com.google.firebase.firestore.util;

public class Assert {
    public static void hardAssert(boolean condition, String messageFormat, Object... args) {
        if (!condition) {
            throw fail(messageFormat, args);
        }
    }

    public static AssertionError fail(String messageFormat, Object... args) {
        throw new AssertionError(format(messageFormat, args));
    }

    public static AssertionError fail(Throwable cause, String messageFormat, Object... args) {
        throw ApiUtil.newAssertionError(format(messageFormat, args), cause);
    }

    private static String format(String messageFormat, Object... args) {
        return "INTERNAL ASSERTION FAILED: " + String.format(messageFormat, args);
    }
}
