package com.google.firebase.components;

import com.google.firebase.events.Event;
import com.google.firebase.events.EventHandler;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;

class EventBus implements Subscriber, Publisher {
    private final Executor defaultExecutor;
    private final Map<Class<?>, ConcurrentHashMap<EventHandler<Object>, Executor>> handlerMap = new HashMap();
    private Queue<Event<?>> pendingEvents = new ArrayDeque();

    EventBus(Executor defaultExecutor2) {
        this.defaultExecutor = defaultExecutor2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001a, code lost:
        if (r0.hasNext() == false) goto L_0x0032;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        r1 = r0.next();
        r1.getValue().execute(new com.google.firebase.components.EventBus$$ExternalSyntheticLambda0(r1, r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0032, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000e, code lost:
        r0 = getHandlers(r6).iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void publish(com.google.firebase.events.Event<?> r6) {
        /*
            r5 = this;
            com.google.firebase.components.Preconditions.checkNotNull(r6)
            monitor-enter(r5)
            java.util.Queue<com.google.firebase.events.Event<?>> r0 = r5.pendingEvents     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x000d
            r0.add(r6)     // Catch:{ all -> 0x0033 }
            monitor-exit(r5)     // Catch:{ all -> 0x0033 }
            return
        L_0x000d:
            monitor-exit(r5)     // Catch:{ all -> 0x0033 }
            java.util.Set r0 = r5.getHandlers(r6)
            java.util.Iterator r0 = r0.iterator()
        L_0x0016:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0032
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            r2 = r6
            java.lang.Object r3 = r1.getValue()
            java.util.concurrent.Executor r3 = (java.util.concurrent.Executor) r3
            com.google.firebase.components.EventBus$$ExternalSyntheticLambda0 r4 = new com.google.firebase.components.EventBus$$ExternalSyntheticLambda0
            r4.<init>(r1, r2)
            r3.execute(r4)
            goto L_0x0016
        L_0x0032:
            return
        L_0x0033:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0033 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.components.EventBus.publish(com.google.firebase.events.Event):void");
    }

    private synchronized Set<Map.Entry<EventHandler<Object>, Executor>> getHandlers(Event<?> event) {
        Map<EventHandler<Object>, Executor> handlers;
        handlers = this.handlerMap.get(event.getType());
        return handlers == null ? Collections.emptySet() : handlers.entrySet();
    }

    public synchronized <T> void subscribe(Class<T> type, Executor executor, EventHandler<? super T> handler) {
        Preconditions.checkNotNull(type);
        Preconditions.checkNotNull(handler);
        Preconditions.checkNotNull(executor);
        if (!this.handlerMap.containsKey(type)) {
            this.handlerMap.put(type, new ConcurrentHashMap());
        }
        this.handlerMap.get(type).put(handler, executor);
    }

    public <T> void subscribe(Class<T> type, EventHandler<? super T> handler) {
        subscribe(type, this.defaultExecutor, handler);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0029, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized <T> void unsubscribe(java.lang.Class<T> r4, com.google.firebase.events.EventHandler<? super T> r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            com.google.firebase.components.Preconditions.checkNotNull(r4)     // Catch:{ all -> 0x002a }
            com.google.firebase.components.Preconditions.checkNotNull(r5)     // Catch:{ all -> 0x002a }
            java.util.Map<java.lang.Class<?>, java.util.concurrent.ConcurrentHashMap<com.google.firebase.events.EventHandler<java.lang.Object>, java.util.concurrent.Executor>> r0 = r3.handlerMap     // Catch:{ all -> 0x002a }
            boolean r0 = r0.containsKey(r4)     // Catch:{ all -> 0x002a }
            if (r0 != 0) goto L_0x0011
            monitor-exit(r3)
            return
        L_0x0011:
            java.util.Map<java.lang.Class<?>, java.util.concurrent.ConcurrentHashMap<com.google.firebase.events.EventHandler<java.lang.Object>, java.util.concurrent.Executor>> r0 = r3.handlerMap     // Catch:{ all -> 0x002a }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x002a }
            java.util.concurrent.ConcurrentHashMap r0 = (java.util.concurrent.ConcurrentHashMap) r0     // Catch:{ all -> 0x002a }
            r1 = r5
            r0.remove(r1)     // Catch:{ all -> 0x002a }
            boolean r2 = r0.isEmpty()     // Catch:{ all -> 0x002a }
            if (r2 == 0) goto L_0x0028
            java.util.Map<java.lang.Class<?>, java.util.concurrent.ConcurrentHashMap<com.google.firebase.events.EventHandler<java.lang.Object>, java.util.concurrent.Executor>> r2 = r3.handlerMap     // Catch:{ all -> 0x002a }
            r2.remove(r4)     // Catch:{ all -> 0x002a }
        L_0x0028:
            monitor-exit(r3)
            return
        L_0x002a:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.components.EventBus.unsubscribe(java.lang.Class, com.google.firebase.events.EventHandler):void");
    }

    /* access modifiers changed from: package-private */
    public void enablePublishingAndFlushPending() {
        Queue<Event<?>> pending = null;
        synchronized (this) {
            Queue<Event<?>> queue = this.pendingEvents;
            if (queue != null) {
                pending = queue;
                this.pendingEvents = null;
            }
        }
        if (pending != null) {
            for (Event<?> event : pending) {
                publish(event);
            }
        }
    }
}
