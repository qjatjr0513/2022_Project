package p004io.grpc.internal;

import com.google.common.base.Stopwatch;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import p004io.grpc.internal.ClientTransport;

/* renamed from: io.grpc.internal.Http2Ping */
public class Http2Ping {
    private static final Logger log = Logger.getLogger(Http2Ping.class.getName());
    private Map<ClientTransport.PingCallback, Executor> callbacks = new LinkedHashMap();
    private boolean completed;
    private final long data;
    private Throwable failureCause;
    private long roundTripTimeNanos;
    private final Stopwatch stopwatch;

    public Http2Ping(long data2, Stopwatch stopwatch2) {
        this.data = data2;
        this.stopwatch = stopwatch2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001d, code lost:
        doExecute(r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addCallback(p004io.grpc.internal.ClientTransport.PingCallback r3, java.util.concurrent.Executor r4) {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.completed     // Catch:{ all -> 0x0021 }
            if (r0 != 0) goto L_0x000c
            java.util.Map<io.grpc.internal.ClientTransport$PingCallback, java.util.concurrent.Executor> r0 = r2.callbacks     // Catch:{ all -> 0x0021 }
            r0.put(r3, r4)     // Catch:{ all -> 0x0021 }
            monitor-exit(r2)     // Catch:{ all -> 0x0021 }
            return
        L_0x000c:
            java.lang.Throwable r0 = r2.failureCause     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x0015
            java.lang.Runnable r0 = asRunnable((p004io.grpc.internal.ClientTransport.PingCallback) r3, (java.lang.Throwable) r0)     // Catch:{ all -> 0x0021 }
            goto L_0x001b
        L_0x0015:
            long r0 = r2.roundTripTimeNanos     // Catch:{ all -> 0x0021 }
            java.lang.Runnable r0 = asRunnable((p004io.grpc.internal.ClientTransport.PingCallback) r3, (long) r0)     // Catch:{ all -> 0x0021 }
        L_0x001b:
            monitor-exit(r2)     // Catch:{ all -> 0x0021 }
            doExecute(r4, r0)
            return
        L_0x0021:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0021 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.Http2Ping.addCallback(io.grpc.internal.ClientTransport$PingCallback, java.util.concurrent.Executor):void");
    }

    public long payload() {
        return this.data;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
        if (r4.hasNext() == false) goto L_0x0043;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        r5 = r4.next();
        doExecute(r5.getValue(), asRunnable(r5.getKey(), r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0043, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
        r4 = r3.entrySet().iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean complete() {
        /*
            r8 = this;
            monitor-enter(r8)
            boolean r0 = r8.completed     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x0008
            r0 = 0
            monitor-exit(r8)     // Catch:{ all -> 0x0044 }
            return r0
        L_0x0008:
            r0 = 1
            r8.completed = r0     // Catch:{ all -> 0x0044 }
            com.google.common.base.Stopwatch r1 = r8.stopwatch     // Catch:{ all -> 0x0044 }
            java.util.concurrent.TimeUnit r2 = java.util.concurrent.TimeUnit.NANOSECONDS     // Catch:{ all -> 0x0044 }
            long r1 = r1.elapsed(r2)     // Catch:{ all -> 0x0044 }
            r8.roundTripTimeNanos = r1     // Catch:{ all -> 0x0044 }
            java.util.Map<io.grpc.internal.ClientTransport$PingCallback, java.util.concurrent.Executor> r3 = r8.callbacks     // Catch:{ all -> 0x0044 }
            r4 = 0
            r8.callbacks = r4     // Catch:{ all -> 0x0044 }
            monitor-exit(r8)     // Catch:{ all -> 0x0044 }
            java.util.Set r4 = r3.entrySet()
            java.util.Iterator r4 = r4.iterator()
        L_0x0023:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x0043
            java.lang.Object r5 = r4.next()
            java.util.Map$Entry r5 = (java.util.Map.Entry) r5
            java.lang.Object r6 = r5.getValue()
            java.util.concurrent.Executor r6 = (java.util.concurrent.Executor) r6
            java.lang.Object r7 = r5.getKey()
            io.grpc.internal.ClientTransport$PingCallback r7 = (p004io.grpc.internal.ClientTransport.PingCallback) r7
            java.lang.Runnable r7 = asRunnable((p004io.grpc.internal.ClientTransport.PingCallback) r7, (long) r1)
            doExecute(r6, r7)
            goto L_0x0023
        L_0x0043:
            return r0
        L_0x0044:
            r0 = move-exception
            monitor-exit(r8)     // Catch:{ all -> 0x0044 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.Http2Ping.complete():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
        if (r1.hasNext() == false) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0020, code lost:
        r2 = r1.next();
        notifyFailed(r2.getKey(), r2.getValue(), r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0036, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        r1 = r0.entrySet().iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void failed(java.lang.Throwable r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            boolean r0 = r5.completed     // Catch:{ all -> 0x0037 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r5)     // Catch:{ all -> 0x0037 }
            return
        L_0x0007:
            r0 = 1
            r5.completed = r0     // Catch:{ all -> 0x0037 }
            r5.failureCause = r6     // Catch:{ all -> 0x0037 }
            java.util.Map<io.grpc.internal.ClientTransport$PingCallback, java.util.concurrent.Executor> r0 = r5.callbacks     // Catch:{ all -> 0x0037 }
            r1 = 0
            r5.callbacks = r1     // Catch:{ all -> 0x0037 }
            monitor-exit(r5)     // Catch:{ all -> 0x0037 }
            java.util.Set r1 = r0.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x001a:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0036
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            io.grpc.internal.ClientTransport$PingCallback r3 = (p004io.grpc.internal.ClientTransport.PingCallback) r3
            java.lang.Object r4 = r2.getValue()
            java.util.concurrent.Executor r4 = (java.util.concurrent.Executor) r4
            notifyFailed(r3, r4, r6)
            goto L_0x001a
        L_0x0036:
            return
        L_0x0037:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0037 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p004io.grpc.internal.Http2Ping.failed(java.lang.Throwable):void");
    }

    public static void notifyFailed(ClientTransport.PingCallback callback, Executor executor, Throwable cause) {
        doExecute(executor, asRunnable(callback, cause));
    }

    private static void doExecute(Executor executor, Runnable runnable) {
        try {
            executor.execute(runnable);
        } catch (Throwable th) {
            log.log(Level.SEVERE, "Failed to execute PingCallback", th);
        }
    }

    private static Runnable asRunnable(final ClientTransport.PingCallback callback, final long roundTripTimeNanos2) {
        return new Runnable() {
            public void run() {
                ClientTransport.PingCallback.this.onSuccess(roundTripTimeNanos2);
            }
        };
    }

    private static Runnable asRunnable(final ClientTransport.PingCallback callback, final Throwable failureCause2) {
        return new Runnable() {
            public void run() {
                ClientTransport.PingCallback.this.onFailure(failureCause2);
            }
        };
    }
}
