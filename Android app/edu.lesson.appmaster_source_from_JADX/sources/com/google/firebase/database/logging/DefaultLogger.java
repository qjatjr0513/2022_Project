package com.google.firebase.database.logging;

import com.google.firebase.database.logging.Logger;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DefaultLogger implements Logger {
    private final Set<String> enabledComponents;
    private final Logger.Level minLevel;

    public DefaultLogger(Logger.Level level, List<String> enabledComponents2) {
        if (enabledComponents2 != null) {
            this.enabledComponents = new HashSet(enabledComponents2);
        } else {
            this.enabledComponents = null;
        }
        this.minLevel = level;
    }

    public Logger.Level getLogLevel() {
        return this.minLevel;
    }

    public void onLogMessage(Logger.Level level, String tag, String message, long msTimestamp) {
        if (shouldLog(level, tag)) {
            String toLog = buildLogMessage(level, tag, message, msTimestamp);
            switch (C07391.$SwitchMap$com$google$firebase$database$logging$Logger$Level[level.ordinal()]) {
                case 1:
                    error(tag, toLog);
                    return;
                case 2:
                    warn(tag, toLog);
                    return;
                case 3:
                    info(tag, toLog);
                    return;
                case 4:
                    debug(tag, toLog);
                    return;
                default:
                    throw new RuntimeException("Should not reach here!");
            }
        }
    }

    /* renamed from: com.google.firebase.database.logging.DefaultLogger$1 */
    static /* synthetic */ class C07391 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$database$logging$Logger$Level;

        static {
            int[] iArr = new int[Logger.Level.values().length];
            $SwitchMap$com$google$firebase$database$logging$Logger$Level = iArr;
            try {
                iArr[Logger.Level.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$google$firebase$database$logging$Logger$Level[Logger.Level.WARN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$google$firebase$database$logging$Logger$Level[Logger.Level.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$google$firebase$database$logging$Logger$Level[Logger.Level.DEBUG.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    /* access modifiers changed from: protected */
    public String buildLogMessage(Logger.Level level, String tag, String message, long msTimestamp) {
        return new Date(msTimestamp).toString() + " [" + level + "] " + tag + ": " + message;
    }

    /* access modifiers changed from: protected */
    public void error(String tag, String toLog) {
        System.err.println(toLog);
    }

    /* access modifiers changed from: protected */
    public void warn(String tag, String toLog) {
        System.out.println(toLog);
    }

    /* access modifiers changed from: protected */
    public void info(String tag, String toLog) {
        System.out.println(toLog);
    }

    /* access modifiers changed from: protected */
    public void debug(String tag, String toLog) {
        System.out.println(toLog);
    }

    /* access modifiers changed from: protected */
    public boolean shouldLog(Logger.Level level, String tag) {
        return level.ordinal() >= this.minLevel.ordinal() && (this.enabledComponents == null || level.ordinal() > Logger.Level.DEBUG.ordinal() || this.enabledComponents.contains(tag));
    }
}
