package com.google.firebase.database.logging;

import com.google.firebase.database.logging.Logger;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LogWrapper {
    private final String component;
    private final Logger logger;
    private final String prefix;

    private static String exceptionStacktrace(Throwable e) {
        StringWriter writer = new StringWriter();
        e.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }

    public LogWrapper(Logger logger2, String component2) {
        this(logger2, component2, (String) null);
    }

    public LogWrapper(Logger logger2, String component2, String prefix2) {
        this.logger = logger2;
        this.component = component2;
        this.prefix = prefix2;
    }

    public void error(String message, Throwable e) {
        this.logger.onLogMessage(Logger.Level.ERROR, this.component, toLog(message, new Object[0]) + "\n" + exceptionStacktrace(e), now());
    }

    public void warn(String message) {
        warn(message, (Throwable) null);
    }

    public void warn(String message, Throwable e) {
        String logMsg = toLog(message, new Object[0]);
        if (e != null) {
            logMsg = logMsg + "\n" + exceptionStacktrace(e);
        }
        this.logger.onLogMessage(Logger.Level.WARN, this.component, logMsg, now());
    }

    public void info(String message) {
        this.logger.onLogMessage(Logger.Level.INFO, this.component, toLog(message, new Object[0]), now());
    }

    public void debug(String message, Object... args) {
        debug(message, (Throwable) null, args);
    }

    public boolean logsDebug() {
        return this.logger.getLogLevel().ordinal() <= Logger.Level.DEBUG.ordinal();
    }

    public void debug(String message, Throwable e, Object... args) {
        if (logsDebug()) {
            String logMsg = toLog(message, args);
            if (e != null) {
                logMsg = logMsg + "\n" + exceptionStacktrace(e);
            }
            this.logger.onLogMessage(Logger.Level.DEBUG, this.component, logMsg, now());
        }
    }

    private long now() {
        return System.currentTimeMillis();
    }

    private String toLog(String message, Object... args) {
        String formatted = args.length > 0 ? String.format(message, args) : message;
        return this.prefix == null ? formatted : this.prefix + " - " + formatted;
    }
}
