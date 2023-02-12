package androidx.startup;

import android.util.Log;

public final class StartupLogger {
    static final boolean DEBUG = false;
    private static final String TAG = "StartupLogger";

    private StartupLogger() {
    }

    /* renamed from: i */
    public static void m388i(String message) {
        Log.i(TAG, message);
    }

    /* renamed from: e */
    public static void m387e(String message, Throwable throwable) {
        Log.e(TAG, message, throwable);
    }
}
