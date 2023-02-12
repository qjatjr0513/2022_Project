package com.google.firebase.firestore.util;

import android.util.Log;
import com.google.firebase.firestore.BuildConfig;

public class Logger {
    private static Level logLevel = Level.WARN;

    public enum Level {
        DEBUG,
        WARN,
        NONE
    }

    public static void setLogLevel(Level level) {
        logLevel = level;
    }

    public static boolean isDebugEnabled() {
        return logLevel.ordinal() >= Level.DEBUG.ordinal();
    }

    private static void doLog(Level level, String tag, String toLog, Object... values) {
        if (level.ordinal() >= logLevel.ordinal()) {
            String value = String.format("(%s) [%s]: ", new Object[]{BuildConfig.VERSION_NAME, tag}) + String.format(toLog, values);
            switch (C08021.$SwitchMap$com$google$firebase$firestore$util$Logger$Level[level.ordinal()]) {
                case 1:
                    Log.i("Firestore", value);
                    return;
                case 2:
                    Log.w("Firestore", value);
                    return;
                case 3:
                    throw new IllegalStateException("Trying to log something on level NONE");
                default:
                    return;
            }
        }
    }

    /* renamed from: com.google.firebase.firestore.util.Logger$1 */
    static /* synthetic */ class C08021 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$firestore$util$Logger$Level;

        static {
            int[] iArr = new int[Level.values().length];
            $SwitchMap$com$google$firebase$firestore$util$Logger$Level = iArr;
            try {
                iArr[Level.DEBUG.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$util$Logger$Level[Level.WARN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$firebase$firestore$util$Logger$Level[Level.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public static void warn(String tag, String toLog, Object... values) {
        doLog(Level.WARN, tag, toLog, values);
    }

    public static void debug(String tag, String toLog, Object... values) {
        doLog(Level.DEBUG, tag, toLog, values);
    }
}
