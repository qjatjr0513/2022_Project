package com.google.android.datatransport.runtime.logging;

import android.util.Log;

public final class Logging {
    private Logging() {
    }

    private static String getTag(String tag) {
        return "TransportRuntime." + tag;
    }

    /* renamed from: d */
    public static void m397d(String tag, String message) {
        Log.d(getTag(tag), message);
    }

    /* renamed from: d */
    public static void m398d(String tag, String message, Object arg1) {
        Log.d(getTag(tag), String.format(message, new Object[]{arg1}));
    }

    /* renamed from: d */
    public static void m399d(String tag, String message, Object arg1, Object arg2) {
        Log.d(getTag(tag), String.format(message, new Object[]{arg1, arg2}));
    }

    /* renamed from: d */
    public static void m400d(String tag, String message, Object... args) {
        Log.d(getTag(tag), String.format(message, args));
    }

    /* renamed from: i */
    public static void m402i(String tag, String message) {
        Log.i(getTag(tag), message);
    }

    /* renamed from: e */
    public static void m401e(String tag, String message, Throwable e) {
        Log.e(getTag(tag), message, e);
    }

    /* renamed from: w */
    public static void m403w(String tag, String message, Object arg1) {
        Log.w(getTag(tag), String.format(message, new Object[]{arg1}));
    }
}
