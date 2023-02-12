package com.google.common.eventbus;

import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

abstract class Dispatcher {
    /* access modifiers changed from: package-private */
    public abstract void dispatch(Object obj, Iterator<Subscriber> it);

    Dispatcher() {
    }

    static Dispatcher perThreadDispatchQueue() {
        return new PerThreadQueuedDispatcher();
    }

    static Dispatcher legacyAsync() {
        return new LegacyAsyncDispatcher();
    }

    static Dispatcher immediate() {
        return ImmediateDispatcher.INSTANCE;
    }

    private static final class PerThreadQueuedDispatcher extends Dispatcher {
        private final ThreadLocal<Boolean> dispatching;
        private final ThreadLocal<Queue<Event>> queue;

        private PerThreadQueuedDispatcher() {
            this.queue = new ThreadLocal<Queue<Event>>(this) {
                /* access modifiers changed from: protected */
                public Queue<Event> initialValue() {
                    return Queues.newArrayDeque();
                }
            };
            this.dispatching = new ThreadLocal<Boolean>(this) {
                /* access modifiers changed from: protected */
                public Boolean initialValue() {
                    return false;
                }
            };
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:13:0x0054 A[SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:6:0x0038 A[Catch:{ all -> 0x005f }, LOOP:1: B:6:0x0038->B:8:0x0042, LOOP_START] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void dispatch(java.lang.Object r5, java.util.Iterator<com.google.common.eventbus.Subscriber> r6) {
            /*
                r4 = this;
                com.google.common.base.Preconditions.checkNotNull(r5)
                com.google.common.base.Preconditions.checkNotNull(r6)
                java.lang.ThreadLocal<java.util.Queue<com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event>> r0 = r4.queue
                java.lang.Object r0 = r0.get()
                java.util.Queue r0 = (java.util.Queue) r0
                com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event r1 = new com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event
                r2 = 0
                r1.<init>(r5, r6)
                r0.offer(r1)
                java.lang.ThreadLocal<java.lang.Boolean> r1 = r4.dispatching
                java.lang.Object r1 = r1.get()
                java.lang.Boolean r1 = (java.lang.Boolean) r1
                boolean r1 = r1.booleanValue()
                if (r1 != 0) goto L_0x006b
                java.lang.ThreadLocal<java.lang.Boolean> r1 = r4.dispatching
                r2 = 1
                java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)
                r1.set(r2)
            L_0x002f:
                java.lang.Object r1 = r0.poll()     // Catch:{ all -> 0x005f }
                com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event r1 = (com.google.common.eventbus.Dispatcher.PerThreadQueuedDispatcher.Event) r1     // Catch:{ all -> 0x005f }
                r2 = r1
                if (r1 == 0) goto L_0x0054
            L_0x0038:
                java.util.Iterator r1 = r2.subscribers     // Catch:{ all -> 0x005f }
                boolean r1 = r1.hasNext()     // Catch:{ all -> 0x005f }
                if (r1 == 0) goto L_0x002f
                java.util.Iterator r1 = r2.subscribers     // Catch:{ all -> 0x005f }
                java.lang.Object r1 = r1.next()     // Catch:{ all -> 0x005f }
                com.google.common.eventbus.Subscriber r1 = (com.google.common.eventbus.Subscriber) r1     // Catch:{ all -> 0x005f }
                java.lang.Object r3 = r2.event     // Catch:{ all -> 0x005f }
                r1.dispatchEvent(r3)     // Catch:{ all -> 0x005f }
                goto L_0x0038
            L_0x0054:
                java.lang.ThreadLocal<java.lang.Boolean> r1 = r4.dispatching
                r1.remove()
                java.lang.ThreadLocal<java.util.Queue<com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event>> r1 = r4.queue
                r1.remove()
                goto L_0x006b
            L_0x005f:
                r1 = move-exception
                java.lang.ThreadLocal<java.lang.Boolean> r2 = r4.dispatching
                r2.remove()
                java.lang.ThreadLocal<java.util.Queue<com.google.common.eventbus.Dispatcher$PerThreadQueuedDispatcher$Event>> r2 = r4.queue
                r2.remove()
                throw r1
            L_0x006b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.eventbus.Dispatcher.PerThreadQueuedDispatcher.dispatch(java.lang.Object, java.util.Iterator):void");
        }

        private static final class Event {
            /* access modifiers changed from: private */
            public final Object event;
            /* access modifiers changed from: private */
            public final Iterator<Subscriber> subscribers;

            private Event(Object event2, Iterator<Subscriber> subscribers2) {
                this.event = event2;
                this.subscribers = subscribers2;
            }
        }
    }

    private static final class LegacyAsyncDispatcher extends Dispatcher {
        private final ConcurrentLinkedQueue<EventWithSubscriber> queue;

        private LegacyAsyncDispatcher() {
            this.queue = Queues.newConcurrentLinkedQueue();
        }

        /* access modifiers changed from: package-private */
        public void dispatch(Object event, Iterator<Subscriber> subscribers) {
            Preconditions.checkNotNull(event);
            while (subscribers.hasNext()) {
                this.queue.add(new EventWithSubscriber(event, subscribers.next()));
            }
            while (true) {
                EventWithSubscriber poll = this.queue.poll();
                EventWithSubscriber e = poll;
                if (poll != null) {
                    e.subscriber.dispatchEvent(e.event);
                } else {
                    return;
                }
            }
        }

        private static final class EventWithSubscriber {
            /* access modifiers changed from: private */
            public final Object event;
            /* access modifiers changed from: private */
            public final Subscriber subscriber;

            private EventWithSubscriber(Object event2, Subscriber subscriber2) {
                this.event = event2;
                this.subscriber = subscriber2;
            }
        }
    }

    private static final class ImmediateDispatcher extends Dispatcher {
        /* access modifiers changed from: private */
        public static final ImmediateDispatcher INSTANCE = new ImmediateDispatcher();

        private ImmediateDispatcher() {
        }

        /* access modifiers changed from: package-private */
        public void dispatch(Object event, Iterator<Subscriber> subscribers) {
            Preconditions.checkNotNull(event);
            while (subscribers.hasNext()) {
                subscribers.next().dispatchEvent(event);
            }
        }
    }
}
