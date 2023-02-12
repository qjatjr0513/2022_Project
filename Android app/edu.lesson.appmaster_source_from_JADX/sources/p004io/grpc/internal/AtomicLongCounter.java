package p004io.grpc.internal;

import java.util.concurrent.atomic.AtomicLong;

/* renamed from: io.grpc.internal.AtomicLongCounter */
final class AtomicLongCounter implements LongCounter {
    private final AtomicLong counter = new AtomicLong();

    AtomicLongCounter() {
    }

    public void add(long delta) {
        this.counter.getAndAdd(delta);
    }

    public long value() {
        return this.counter.get();
    }
}
