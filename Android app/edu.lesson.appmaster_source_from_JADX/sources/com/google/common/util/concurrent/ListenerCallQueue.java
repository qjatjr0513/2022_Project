package com.google.common.util.concurrent;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import com.google.firebase.messaging.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

final class ListenerCallQueue<L> {
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(ListenerCallQueue.class.getName());
    private final List<PerListenerQueue<L>> listeners = Collections.synchronizedList(new ArrayList());

    interface Event<L> {
        void call(L l);
    }

    ListenerCallQueue() {
    }

    public void addListener(L listener, Executor executor) {
        Preconditions.checkNotNull(listener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Preconditions.checkNotNull(executor, "executor");
        this.listeners.add(new PerListenerQueue(listener, executor));
    }

    public void enqueue(Event<L> event) {
        enqueueHelper(event, event);
    }

    public void enqueue(Event<L> event, String label) {
        enqueueHelper(event, label);
    }

    private void enqueueHelper(Event<L> event, Object label) {
        Preconditions.checkNotNull(event, NotificationCompat.CATEGORY_EVENT);
        Preconditions.checkNotNull(label, Constants.ScionAnalytics.PARAM_LABEL);
        synchronized (this.listeners) {
            for (PerListenerQueue<L> queue : this.listeners) {
                queue.add(event, label);
            }
        }
    }

    public void dispatch() {
        for (int i = 0; i < this.listeners.size(); i++) {
            this.listeners.get(i).dispatch();
        }
    }

    private static final class PerListenerQueue<L> implements Runnable {
        final Executor executor;
        boolean isThreadScheduled;
        final Queue<Object> labelQueue = Queues.newArrayDeque();
        final L listener;
        final Queue<Event<L>> waitQueue = Queues.newArrayDeque();

        PerListenerQueue(L listener2, Executor executor2) {
            this.listener = Preconditions.checkNotNull(listener2);
            this.executor = (Executor) Preconditions.checkNotNull(executor2);
        }

        /* access modifiers changed from: package-private */
        public synchronized void add(Event<L> event, Object label) {
            this.waitQueue.add(event);
            this.labelQueue.add(label);
        }

        /* access modifiers changed from: package-private */
        public void dispatch() {
            boolean scheduleEventRunner = false;
            synchronized (this) {
                if (!this.isThreadScheduled) {
                    this.isThreadScheduled = true;
                    scheduleEventRunner = true;
                }
            }
            if (scheduleEventRunner) {
                try {
                    this.executor.execute(this);
                } catch (RuntimeException e) {
                    synchronized (this) {
                        this.isThreadScheduled = false;
                        Logger access$000 = ListenerCallQueue.logger;
                        Level level = Level.SEVERE;
                        String valueOf = String.valueOf(this.listener);
                        String valueOf2 = String.valueOf(this.executor);
                        access$000.log(level, new StringBuilder(String.valueOf(valueOf).length() + 42 + String.valueOf(valueOf2).length()).append("Exception while running callbacks for ").append(valueOf).append(" on ").append(valueOf2).toString(), e);
                        throw e;
                    }
                }
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
            monitor-enter(r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
            r11.isThreadScheduled = false;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:13:0x0021, code lost:
            monitor-exit(r11);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            r2.call(r11.listener);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
            if (0 == 0) goto L_?;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r11 = this;
                r0 = 1
            L_0x0001:
                r1 = 0
                monitor-enter(r11)     // Catch:{ all -> 0x0076 }
                boolean r2 = r11.isThreadScheduled     // Catch:{ all -> 0x0073 }
                com.google.common.base.Preconditions.checkState(r2)     // Catch:{ all -> 0x0073 }
                java.util.Queue<com.google.common.util.concurrent.ListenerCallQueue$Event<L>> r2 = r11.waitQueue     // Catch:{ all -> 0x0073 }
                java.lang.Object r2 = r2.poll()     // Catch:{ all -> 0x0073 }
                com.google.common.util.concurrent.ListenerCallQueue$Event r2 = (com.google.common.util.concurrent.ListenerCallQueue.Event) r2     // Catch:{ all -> 0x0073 }
                java.util.Queue<java.lang.Object> r3 = r11.labelQueue     // Catch:{ all -> 0x0073 }
                java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x0073 }
                if (r2 != 0) goto L_0x0027
                r11.isThreadScheduled = r1     // Catch:{ all -> 0x0073 }
                r0 = 0
                monitor-exit(r11)     // Catch:{ all -> 0x0073 }
                if (r0 == 0) goto L_0x0026
                monitor-enter(r11)
                r11.isThreadScheduled = r1     // Catch:{ all -> 0x0023 }
                monitor-exit(r11)     // Catch:{ all -> 0x0023 }
                goto L_0x0026
            L_0x0023:
                r1 = move-exception
                monitor-exit(r11)     // Catch:{ all -> 0x0023 }
                throw r1
            L_0x0026:
                return
            L_0x0027:
                monitor-exit(r11)     // Catch:{ all -> 0x0073 }
                L r4 = r11.listener     // Catch:{ RuntimeException -> 0x002e }
                r2.call(r4)     // Catch:{ RuntimeException -> 0x002e }
                goto L_0x0072
            L_0x002e:
                r4 = move-exception
                java.util.logging.Logger r5 = com.google.common.util.concurrent.ListenerCallQueue.logger     // Catch:{ all -> 0x0076 }
                java.util.logging.Level r6 = java.util.logging.Level.SEVERE     // Catch:{ all -> 0x0076 }
                L r7 = r11.listener     // Catch:{ all -> 0x0076 }
                java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x0076 }
                java.lang.String r8 = java.lang.String.valueOf(r3)     // Catch:{ all -> 0x0076 }
                java.lang.String r9 = java.lang.String.valueOf(r7)     // Catch:{ all -> 0x0076 }
                int r9 = r9.length()     // Catch:{ all -> 0x0076 }
                int r9 = r9 + 37
                java.lang.String r10 = java.lang.String.valueOf(r8)     // Catch:{ all -> 0x0076 }
                int r10 = r10.length()     // Catch:{ all -> 0x0076 }
                int r9 = r9 + r10
                java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x0076 }
                r10.<init>(r9)     // Catch:{ all -> 0x0076 }
                java.lang.String r9 = "Exception while executing callback: "
                java.lang.StringBuilder r9 = r10.append(r9)     // Catch:{ all -> 0x0076 }
                java.lang.StringBuilder r7 = r9.append(r7)     // Catch:{ all -> 0x0076 }
                java.lang.String r9 = " "
                java.lang.StringBuilder r7 = r7.append(r9)     // Catch:{ all -> 0x0076 }
                java.lang.StringBuilder r7 = r7.append(r8)     // Catch:{ all -> 0x0076 }
                java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0076 }
                r5.log(r6, r7, r4)     // Catch:{ all -> 0x0076 }
            L_0x0072:
                goto L_0x0001
            L_0x0073:
                r2 = move-exception
                monitor-exit(r11)     // Catch:{ all -> 0x0073 }
                throw r2     // Catch:{ all -> 0x0076 }
            L_0x0076:
                r2 = move-exception
                if (r0 == 0) goto L_0x0081
                monitor-enter(r11)
                r11.isThreadScheduled = r1     // Catch:{ all -> 0x007e }
                monitor-exit(r11)     // Catch:{ all -> 0x007e }
                goto L_0x0081
            L_0x007e:
                r1 = move-exception
                monitor-exit(r11)     // Catch:{ all -> 0x007e }
                throw r1
            L_0x0081:
                throw r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ListenerCallQueue.PerListenerQueue.run():void");
        }
    }
}
