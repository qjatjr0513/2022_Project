package com.google.android.datatransport.runtime.time;

import java.util.concurrent.atomic.AtomicLong;

public class TestClock implements Clock {
    private final AtomicLong timestamp;

    public TestClock(long initialTimestamp) {
        this.timestamp = new AtomicLong(initialTimestamp);
    }

    public long getTime() {
        return this.timestamp.get();
    }

    public void tick() {
        advance(1);
    }

    public void advance(long value) {
        if (value >= 0) {
            this.timestamp.addAndGet(value);
            return;
        }
        throw new IllegalArgumentException("cannot advance time backwards.");
    }
}
