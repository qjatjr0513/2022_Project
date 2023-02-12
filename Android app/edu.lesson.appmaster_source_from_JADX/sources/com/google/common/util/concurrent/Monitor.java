package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class Monitor {
    private Guard activeGuards;
    private final boolean fair;
    /* access modifiers changed from: private */
    public final ReentrantLock lock;

    public static abstract class Guard {
        final Condition condition;
        final Monitor monitor;
        @NullableDecl
        Guard next;
        int waiterCount = 0;

        public abstract boolean isSatisfied();

        protected Guard(Monitor monitor2) {
            this.monitor = (Monitor) Preconditions.checkNotNull(monitor2, "monitor");
            this.condition = monitor2.lock.newCondition();
        }
    }

    public Monitor() {
        this(false);
    }

    public Monitor(boolean fair2) {
        this.activeGuards = null;
        this.fair = fair2;
        this.lock = new ReentrantLock(fair2);
    }

    public void enter() {
        this.lock.lock();
    }

    public boolean enter(long time, TimeUnit unit) {
        long remainingNanos;
        boolean tryLock;
        long timeoutNanos = toSafeNanos(time, unit);
        ReentrantLock lock2 = this.lock;
        if (!this.fair && lock2.tryLock()) {
            return true;
        }
        boolean interrupted = Thread.interrupted();
        try {
            remainingNanos = timeoutNanos;
            while (true) {
                tryLock = lock2.tryLock(remainingNanos, TimeUnit.NANOSECONDS);
                break;
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
            return tryLock;
        } catch (InterruptedException e) {
            interrupted = true;
            remainingNanos = remainingNanos(System.nanoTime(), timeoutNanos);
        } catch (Throwable th) {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
            throw th;
        }
    }

    public void enterInterruptibly() throws InterruptedException {
        this.lock.lockInterruptibly();
    }

    public boolean enterInterruptibly(long time, TimeUnit unit) throws InterruptedException {
        return this.lock.tryLock(time, unit);
    }

    public boolean tryEnter() {
        return this.lock.tryLock();
    }

    public void enterWhen(Guard guard) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock lock2 = this.lock;
            boolean signalBeforeWaiting = lock2.isHeldByCurrentThread();
            lock2.lockInterruptibly();
            boolean satisfied = false;
            try {
                if (!guard.isSatisfied()) {
                    await(guard, signalBeforeWaiting);
                }
                satisfied = true;
            } finally {
                if (!satisfied) {
                    leave();
                }
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004c, code lost:
        if (awaitNanos(r12, r4 == 0 ? r0 : remainingNanos(r4, r0), r3) != false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001f, code lost:
        if (r2.tryLock() != false) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean enterWhen(com.google.common.util.concurrent.Monitor.Guard r12, long r13, java.util.concurrent.TimeUnit r15) throws java.lang.InterruptedException {
        /*
            r11 = this;
            long r0 = toSafeNanos(r13, r15)
            com.google.common.util.concurrent.Monitor r2 = r12.monitor
            if (r2 != r11) goto L_0x0079
            java.util.concurrent.locks.ReentrantLock r2 = r11.lock
            boolean r3 = r2.isHeldByCurrentThread()
            r4 = 0
            boolean r6 = r11.fair
            r7 = 0
            if (r6 != 0) goto L_0x0028
            boolean r6 = java.lang.Thread.interrupted()
            if (r6 != 0) goto L_0x0022
            boolean r6 = r2.tryLock()
            if (r6 == 0) goto L_0x0028
            goto L_0x0033
        L_0x0022:
            java.lang.InterruptedException r6 = new java.lang.InterruptedException
            r6.<init>()
            throw r6
        L_0x0028:
            long r4 = initNanoTime(r0)
            boolean r6 = r2.tryLock(r13, r15)
            if (r6 != 0) goto L_0x0033
            return r7
        L_0x0033:
            r6 = 0
            r8 = 1
            boolean r9 = r12.isSatisfied()     // Catch:{ all -> 0x0065 }
            if (r9 != 0) goto L_0x004e
            r9 = 0
            int r9 = (r4 > r9 ? 1 : (r4 == r9 ? 0 : -1))
            if (r9 != 0) goto L_0x0044
            r9 = r0
            goto L_0x0048
        L_0x0044:
            long r9 = remainingNanos(r4, r0)     // Catch:{ all -> 0x0065 }
        L_0x0048:
            boolean r9 = r11.awaitNanos(r12, r9, r3)     // Catch:{ all -> 0x0065 }
            if (r9 == 0) goto L_0x004f
        L_0x004e:
            r7 = 1
        L_0x004f:
            r6 = r7
            r7 = 0
            if (r6 != 0) goto L_0x0064
            if (r7 == 0) goto L_0x0061
            if (r3 != 0) goto L_0x0061
            r11.signalNextWaiter()     // Catch:{ all -> 0x005c }
            goto L_0x0061
        L_0x005c:
            r8 = move-exception
            r2.unlock()
            throw r8
        L_0x0061:
            r2.unlock()
        L_0x0064:
            return r6
        L_0x0065:
            r7 = move-exception
            if (r6 != 0) goto L_0x0078
            if (r8 == 0) goto L_0x0075
            if (r3 != 0) goto L_0x0075
            r11.signalNextWaiter()     // Catch:{ all -> 0x0070 }
            goto L_0x0075
        L_0x0070:
            r7 = move-exception
            r2.unlock()
            throw r7
        L_0x0075:
            r2.unlock()
        L_0x0078:
            throw r7
        L_0x0079:
            java.lang.IllegalMonitorStateException r2 = new java.lang.IllegalMonitorStateException
            r2.<init>()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.enterWhen(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }

    public void enterWhenUninterruptibly(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock lock2 = this.lock;
            boolean signalBeforeWaiting = lock2.isHeldByCurrentThread();
            lock2.lock();
            boolean satisfied = false;
            try {
                if (!guard.isSatisfied()) {
                    awaitUninterruptibly(guard, signalBeforeWaiting);
                }
                satisfied = true;
            } finally {
                if (!satisfied) {
                    leave();
                }
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean enterWhenUninterruptibly(Guard guard, long time, TimeUnit unit) {
        boolean satisfied;
        long remainingNanos;
        long timeoutNanos = toSafeNanos(time, unit);
        if (guard.monitor == this) {
            ReentrantLock lock2 = this.lock;
            long startTime = 0;
            boolean signalBeforeWaiting = lock2.isHeldByCurrentThread();
            boolean interrupted = Thread.interrupted();
            try {
                if (this.fair || !lock2.tryLock()) {
                    startTime = initNanoTime(timeoutNanos);
                    long remainingNanos2 = timeoutNanos;
                    while (true) {
                        try {
                            break;
                        } catch (InterruptedException e) {
                            interrupted = true;
                            remainingNanos2 = remainingNanos(startTime, timeoutNanos);
                        }
                    }
                    if (!lock2.tryLock(remainingNanos2, TimeUnit.NANOSECONDS)) {
                        if (interrupted) {
                            Thread.currentThread().interrupt();
                        }
                        return false;
                    }
                }
                while (true) {
                    break;
                }
                if (guard.isSatisfied()) {
                    satisfied = true;
                } else {
                    if (startTime == 0) {
                        long startTime2 = initNanoTime(timeoutNanos);
                        remainingNanos = timeoutNanos;
                    } else {
                        remainingNanos = remainingNanos(startTime, timeoutNanos);
                    }
                    satisfied = awaitNanos(guard, remainingNanos, signalBeforeWaiting);
                }
                if (!satisfied) {
                    lock2.unlock();
                }
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
                return satisfied;
            } catch (InterruptedException e2) {
                interrupted = true;
                signalBeforeWaiting = false;
            } catch (Throwable th) {
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean enterIf(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock lock2 = this.lock;
            lock2.lock();
            boolean satisfied = false;
            try {
                boolean isSatisfied = guard.isSatisfied();
                satisfied = isSatisfied;
                return isSatisfied;
            } finally {
                if (!satisfied) {
                    lock2.unlock();
                }
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean enterIf(Guard guard, long time, TimeUnit unit) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        } else if (!enter(time, unit)) {
            return false;
        } else {
            boolean satisfied = false;
            try {
                boolean isSatisfied = guard.isSatisfied();
                satisfied = isSatisfied;
                return isSatisfied;
            } finally {
                if (!satisfied) {
                    this.lock.unlock();
                }
            }
        }
    }

    public boolean enterIfInterruptibly(Guard guard) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock lock2 = this.lock;
            lock2.lockInterruptibly();
            boolean satisfied = false;
            try {
                boolean isSatisfied = guard.isSatisfied();
                satisfied = isSatisfied;
                return isSatisfied;
            } finally {
                if (!satisfied) {
                    lock2.unlock();
                }
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean enterIfInterruptibly(Guard guard, long time, TimeUnit unit) throws InterruptedException {
        if (guard.monitor == this) {
            ReentrantLock lock2 = this.lock;
            if (!lock2.tryLock(time, unit)) {
                return false;
            }
            boolean satisfied = false;
            try {
                boolean isSatisfied = guard.isSatisfied();
                satisfied = isSatisfied;
                return isSatisfied;
            } finally {
                if (!satisfied) {
                    lock2.unlock();
                }
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public boolean tryEnterIf(Guard guard) {
        if (guard.monitor == this) {
            ReentrantLock lock2 = this.lock;
            if (!lock2.tryLock()) {
                return false;
            }
            boolean satisfied = false;
            try {
                boolean isSatisfied = guard.isSatisfied();
                satisfied = isSatisfied;
                return isSatisfied;
            } finally {
                if (!satisfied) {
                    lock2.unlock();
                }
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    public void waitFor(Guard guard) throws InterruptedException {
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (!guard.isSatisfied()) {
            await(guard, true);
        }
    }

    public boolean waitFor(Guard guard, long time, TimeUnit unit) throws InterruptedException {
        long timeoutNanos = toSafeNanos(time, unit);
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (guard.isSatisfied()) {
            return true;
        } else {
            if (!Thread.interrupted()) {
                return awaitNanos(guard, timeoutNanos, true);
            }
            throw new InterruptedException();
        }
    }

    public void waitForUninterruptibly(Guard guard) {
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (!guard.isSatisfied()) {
            awaitUninterruptibly(guard, true);
        }
    }

    public boolean waitForUninterruptibly(Guard guard, long time, TimeUnit unit) {
        long timeoutNanos = toSafeNanos(time, unit);
        if (!(guard.monitor == this) || !this.lock.isHeldByCurrentThread()) {
            throw new IllegalMonitorStateException();
        } else if (guard.isSatisfied()) {
            return true;
        } else {
            long startTime = initNanoTime(timeoutNanos);
            long remainingNanos = timeoutNanos;
            boolean interrupted = Thread.interrupted();
            boolean signalBeforeWaiting = true;
            while (true) {
                try {
                    boolean signalBeforeWaiting2 = awaitNanos(guard, remainingNanos, signalBeforeWaiting);
                    if (interrupted) {
                        Thread.currentThread().interrupt();
                    }
                    return signalBeforeWaiting2;
                } catch (InterruptedException e) {
                    InterruptedException interruptedException = e;
                    interrupted = true;
                    if (guard.isSatisfied()) {
                        if (1 != 0) {
                            Thread.currentThread().interrupt();
                        }
                        return true;
                    }
                    signalBeforeWaiting = false;
                    remainingNanos = remainingNanos(startTime, timeoutNanos);
                } catch (Throwable th) {
                    if (1 != 0) {
                        Thread.currentThread().interrupt();
                    }
                    throw th;
                }
            }
        }
    }

    public void leave() {
        ReentrantLock lock2 = this.lock;
        try {
            if (lock2.getHoldCount() == 1) {
                signalNextWaiter();
            }
        } finally {
            lock2.unlock();
        }
    }

    public boolean isFair() {
        return this.fair;
    }

    public boolean isOccupied() {
        return this.lock.isLocked();
    }

    public boolean isOccupiedByCurrentThread() {
        return this.lock.isHeldByCurrentThread();
    }

    public int getOccupiedDepth() {
        return this.lock.getHoldCount();
    }

    public int getQueueLength() {
        return this.lock.getQueueLength();
    }

    public boolean hasQueuedThreads() {
        return this.lock.hasQueuedThreads();
    }

    public boolean hasQueuedThread(Thread thread) {
        return this.lock.hasQueuedThread(thread);
    }

    public boolean hasWaiters(Guard guard) {
        return getWaitQueueLength(guard) > 0;
    }

    public int getWaitQueueLength(Guard guard) {
        if (guard.monitor == this) {
            this.lock.lock();
            try {
                return guard.waiterCount;
            } finally {
                this.lock.unlock();
            }
        } else {
            throw new IllegalMonitorStateException();
        }
    }

    private static long toSafeNanos(long time, TimeUnit unit) {
        return Longs.constrainToRange(unit.toNanos(time), 0, 6917529027641081853L);
    }

    private static long initNanoTime(long timeoutNanos) {
        if (timeoutNanos <= 0) {
            return 0;
        }
        long startTime = System.nanoTime();
        if (startTime == 0) {
            return 1;
        }
        return startTime;
    }

    private static long remainingNanos(long startTime, long timeoutNanos) {
        if (timeoutNanos <= 0) {
            return 0;
        }
        return timeoutNanos - (System.nanoTime() - startTime);
    }

    private void signalNextWaiter() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            if (isSatisfied(guard)) {
                guard.condition.signal();
                return;
            }
        }
    }

    private boolean isSatisfied(Guard guard) {
        try {
            return guard.isSatisfied();
        } catch (Throwable throwable) {
            signalAllWaiters();
            throw throwable;
        }
    }

    private void signalAllWaiters() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            guard.condition.signalAll();
        }
    }

    private void beginWaitingFor(Guard guard) {
        int waiters = guard.waiterCount;
        guard.waiterCount = waiters + 1;
        if (waiters == 0) {
            guard.next = this.activeGuards;
            this.activeGuards = guard;
        }
    }

    private void endWaitingFor(Guard guard) {
        int waiters = guard.waiterCount - 1;
        guard.waiterCount = waiters;
        if (waiters == 0) {
            Guard p = this.activeGuards;
            Guard pred = null;
            while (p != guard) {
                pred = p;
                p = p.next;
            }
            if (pred == null) {
                this.activeGuards = p.next;
            } else {
                pred.next = p.next;
            }
            p.next = null;
        }
    }

    private void await(Guard guard, boolean signalBeforeWaiting) throws InterruptedException {
        if (signalBeforeWaiting) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.await();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    private void awaitUninterruptibly(Guard guard, boolean signalBeforeWaiting) {
        if (signalBeforeWaiting) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        do {
            try {
                guard.condition.awaitUninterruptibly();
            } finally {
                endWaitingFor(guard);
            }
        } while (!guard.isSatisfied());
    }

    private boolean awaitNanos(Guard guard, long nanos, boolean signalBeforeWaiting) throws InterruptedException {
        boolean firstTime = true;
        while (nanos > 0) {
            if (firstTime) {
                if (signalBeforeWaiting) {
                    try {
                        signalNextWaiter();
                    } catch (Throwable th) {
                        if (!firstTime) {
                            endWaitingFor(guard);
                        }
                        throw th;
                    }
                }
                beginWaitingFor(guard);
                firstTime = false;
            }
            nanos = guard.condition.awaitNanos(nanos);
            if (guard.isSatisfied()) {
                if (!firstTime) {
                    endWaitingFor(guard);
                }
                return true;
            }
        }
        if (!firstTime) {
            endWaitingFor(guard);
        }
        return false;
    }
}
