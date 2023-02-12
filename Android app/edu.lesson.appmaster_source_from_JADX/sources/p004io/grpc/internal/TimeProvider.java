package p004io.grpc.internal;

import java.util.concurrent.TimeUnit;

/* renamed from: io.grpc.internal.TimeProvider */
public interface TimeProvider {
    public static final TimeProvider SYSTEM_TIME_PROVIDER = new TimeProvider() {
        public long currentTimeNanos() {
            return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis());
        }
    };

    long currentTimeNanos();
}
