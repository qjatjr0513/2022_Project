package com.google.common.util.concurrent;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

abstract class InterruptibleTask<T> extends AtomicReference<Runnable> implements Runnable {
    private static final Runnable DONE = new DoNothingRunnable();
    private static final Runnable INTERRUPTING = new DoNothingRunnable();
    private static final int MAX_BUSY_WAIT_SPINS = 1000;
    private static final Runnable PARKED = new DoNothingRunnable();

    /* access modifiers changed from: package-private */
    public abstract void afterRanInterruptibly(@NullableDecl T t, @NullableDecl Throwable th);

    /* access modifiers changed from: package-private */
    public abstract boolean isDone();

    /* access modifiers changed from: package-private */
    public abstract T runInterruptibly() throws Exception;

    /* access modifiers changed from: package-private */
    public abstract String toPendingString();

    InterruptibleTask() {
    }

    static {
        Class<LockSupport> cls = LockSupport.class;
    }

    private static final class DoNothingRunnable implements Runnable {
        private DoNothingRunnable() {
        }

        public void run() {
        }
    }

    public final void run() {
        Thread currentThread = Thread.currentThread();
        if (compareAndSet((Object) null, currentThread)) {
            boolean run = !isDone();
            T result = null;
            Throwable error = null;
            if (run) {
                try {
                    result = runInterruptibly();
                } catch (Throwable t) {
                    error = t;
                    if (!compareAndSet(currentThread, DONE)) {
                        boolean restoreInterruptedBit = false;
                        int spinCount = 0;
                        Object obj = get();
                        while (true) {
                            Runnable state = (Runnable) obj;
                            Runnable runnable = INTERRUPTING;
                            if (state != runnable && state != PARKED) {
                                break;
                            }
                            spinCount++;
                            if (spinCount > 1000) {
                                Runnable runnable2 = PARKED;
                                if (state == runnable2 || compareAndSet(runnable, runnable2)) {
                                    restoreInterruptedBit = Thread.interrupted() || restoreInterruptedBit;
                                    LockSupport.park(this);
                                }
                            } else {
                                Thread.yield();
                            }
                            obj = get();
                        }
                        if (restoreInterruptedBit) {
                            currentThread.interrupt();
                        }
                    }
                    if (!run) {
                        return;
                    }
                }
            }
            if (!compareAndSet(currentThread, DONE)) {
                boolean restoreInterruptedBit2 = false;
                int spinCount2 = 0;
                Object obj2 = get();
                while (true) {
                    Runnable state2 = (Runnable) obj2;
                    Runnable runnable3 = INTERRUPTING;
                    if (state2 != runnable3 && state2 != PARKED) {
                        break;
                    }
                    spinCount2++;
                    if (spinCount2 > 1000) {
                        Runnable runnable4 = PARKED;
                        if (state2 == runnable4 || compareAndSet(runnable3, runnable4)) {
                            restoreInterruptedBit2 = Thread.interrupted() || restoreInterruptedBit2;
                            LockSupport.park(this);
                        }
                    } else {
                        Thread.yield();
                    }
                    obj2 = get();
                }
                if (restoreInterruptedBit2) {
                    currentThread.interrupt();
                }
            }
            if (!run) {
                return;
            }
            afterRanInterruptibly(result, error);
        }
    }

    /* access modifiers changed from: package-private */
    public final void interruptTask() {
        Runnable currentRunner = (Runnable) get();
        if ((currentRunner instanceof Thread) && compareAndSet(currentRunner, INTERRUPTING)) {
            try {
                ((Thread) currentRunner).interrupt();
            } finally {
                if (((Runnable) getAndSet(DONE)) == PARKED) {
                    LockSupport.unpark((Thread) currentRunner);
                }
            }
        }
    }

    public final String toString() {
        String result;
        Runnable state = (Runnable) get();
        if (state == DONE) {
            result = "running=[DONE]";
        } else if (state == INTERRUPTING) {
            result = "running=[INTERRUPTED]";
        } else if (state instanceof Thread) {
            String name = ((Thread) state).getName();
            result = new StringBuilder(String.valueOf(name).length() + 21).append("running=[RUNNING ON ").append(name).append("]").toString();
        } else {
            result = "running=[NOT STARTED YET]";
        }
        String pendingString = toPendingString();
        return new StringBuilder(String.valueOf(result).length() + 2 + String.valueOf(pendingString).length()).append(result).append(", ").append(pendingString).toString();
    }
}
