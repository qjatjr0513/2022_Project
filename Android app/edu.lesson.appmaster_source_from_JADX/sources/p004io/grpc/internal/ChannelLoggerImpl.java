package p004io.grpc.internal;

import com.google.common.base.Preconditions;
import java.text.MessageFormat;
import java.util.logging.Level;
import p004io.grpc.ChannelLogger;
import p004io.grpc.InternalChannelz;
import p004io.grpc.InternalLogId;

/* renamed from: io.grpc.internal.ChannelLoggerImpl */
final class ChannelLoggerImpl extends ChannelLogger {
    private final TimeProvider time;
    private final ChannelTracer tracer;

    ChannelLoggerImpl(ChannelTracer tracer2, TimeProvider time2) {
        this.tracer = (ChannelTracer) Preconditions.checkNotNull(tracer2, "tracer");
        this.time = (TimeProvider) Preconditions.checkNotNull(time2, "time");
    }

    public void log(ChannelLogger.ChannelLogLevel level, String msg) {
        logOnly(this.tracer.getLogId(), level, msg);
        if (isTraceable(level)) {
            trace(level, msg);
        }
    }

    public void log(ChannelLogger.ChannelLogLevel level, String messageFormat, Object... args) {
        String msg = null;
        Level javaLogLevel = toJavaLogLevel(level);
        if (isTraceable(level) || ChannelTracer.logger.isLoggable(javaLogLevel)) {
            msg = MessageFormat.format(messageFormat, args);
        }
        log(level, msg);
    }

    static void logOnly(InternalLogId logId, ChannelLogger.ChannelLogLevel level, String msg) {
        Level javaLogLevel = toJavaLogLevel(level);
        if (ChannelTracer.logger.isLoggable(javaLogLevel)) {
            ChannelTracer.logOnly(logId, javaLogLevel, msg);
        }
    }

    static void logOnly(InternalLogId logId, ChannelLogger.ChannelLogLevel level, String messageFormat, Object... args) {
        Level javaLogLevel = toJavaLogLevel(level);
        if (ChannelTracer.logger.isLoggable(javaLogLevel)) {
            ChannelTracer.logOnly(logId, javaLogLevel, MessageFormat.format(messageFormat, args));
        }
    }

    private boolean isTraceable(ChannelLogger.ChannelLogLevel level) {
        return level != ChannelLogger.ChannelLogLevel.DEBUG && this.tracer.isTraceEnabled();
    }

    private void trace(ChannelLogger.ChannelLogLevel level, String msg) {
        if (level != ChannelLogger.ChannelLogLevel.DEBUG) {
            this.tracer.traceOnly(new InternalChannelz.ChannelTrace.Event.Builder().setDescription(msg).setSeverity(toTracerSeverity(level)).setTimestampNanos(this.time.currentTimeNanos()).build());
        }
    }

    /* renamed from: io.grpc.internal.ChannelLoggerImpl$1 */
    static /* synthetic */ class C11901 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel;

        static {
            int[] iArr = new int[ChannelLogger.ChannelLogLevel.values().length];
            $SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel = iArr;
            try {
                iArr[ChannelLogger.ChannelLogLevel.ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel[ChannelLogger.ChannelLogLevel.WARNING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel[ChannelLogger.ChannelLogLevel.INFO.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private static InternalChannelz.ChannelTrace.Event.Severity toTracerSeverity(ChannelLogger.ChannelLogLevel level) {
        switch (C11901.$SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel[level.ordinal()]) {
            case 1:
                return InternalChannelz.ChannelTrace.Event.Severity.CT_ERROR;
            case 2:
                return InternalChannelz.ChannelTrace.Event.Severity.CT_WARNING;
            default:
                return InternalChannelz.ChannelTrace.Event.Severity.CT_INFO;
        }
    }

    private static Level toJavaLogLevel(ChannelLogger.ChannelLogLevel level) {
        switch (C11901.$SwitchMap$io$grpc$ChannelLogger$ChannelLogLevel[level.ordinal()]) {
            case 1:
            case 2:
                return Level.FINE;
            case 3:
                return Level.FINER;
            default:
                return Level.FINEST;
        }
    }
}
