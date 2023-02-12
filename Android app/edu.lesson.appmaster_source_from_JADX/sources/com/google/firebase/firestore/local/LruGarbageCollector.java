package com.google.firebase.firestore.local;

import android.util.SparseArray;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Logger;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.concurrent.TimeUnit;

public class LruGarbageCollector {
    /* access modifiers changed from: private */
    public static final long INITIAL_GC_DELAY_MS = TimeUnit.MINUTES.toMillis(1);
    /* access modifiers changed from: private */
    public static final long REGULAR_GC_DELAY_MS = TimeUnit.MINUTES.toMillis(5);
    private final LruDelegate delegate;
    /* access modifiers changed from: private */
    public final Params params;

    public static class Params {
        private static final long COLLECTION_DISABLED = -1;
        private static final long DEFAULT_CACHE_SIZE_BYTES = 104857600;
        private static final int DEFAULT_COLLECTION_PERCENTILE = 10;
        private static final int DEFAULT_MAX_SEQUENCE_NUMBERS_TO_COLLECT = 1000;
        final int maximumSequenceNumbersToCollect;
        final long minBytesThreshold;
        final int percentileToCollect;

        public static Params Default() {
            return new Params(DEFAULT_CACHE_SIZE_BYTES, 10, 1000);
        }

        public static Params Disabled() {
            return new Params(-1, 0, 0);
        }

        public static Params WithCacheSizeBytes(long cacheSizeBytes) {
            return new Params(cacheSizeBytes, 10, 1000);
        }

        Params(long minBytesThreshold2, int percentileToCollect2, int maximumSequenceNumbersToCollect2) {
            this.minBytesThreshold = minBytesThreshold2;
            this.percentileToCollect = percentileToCollect2;
            this.maximumSequenceNumbersToCollect = maximumSequenceNumbersToCollect2;
        }
    }

    public static class Results {
        private final int documentsRemoved;
        private final boolean hasRun;
        private final int sequenceNumbersCollected;
        private final int targetsRemoved;

        static Results DidNotRun() {
            return new Results(false, 0, 0, 0);
        }

        Results(boolean hasRun2, int sequenceNumbersCollected2, int targetsRemoved2, int documentsRemoved2) {
            this.hasRun = hasRun2;
            this.sequenceNumbersCollected = sequenceNumbersCollected2;
            this.targetsRemoved = targetsRemoved2;
            this.documentsRemoved = documentsRemoved2;
        }

        public boolean hasRun() {
            return this.hasRun;
        }

        public int getSequenceNumbersCollected() {
            return this.sequenceNumbersCollected;
        }

        public int getTargetsRemoved() {
            return this.targetsRemoved;
        }

        public int getDocumentsRemoved() {
            return this.documentsRemoved;
        }
    }

    public class GCScheduler implements Scheduler {
        private final AsyncQueue asyncQueue;
        private AsyncQueue.DelayedTask gcTask;
        private boolean hasRun = false;
        private final LocalStore localStore;

        public GCScheduler(AsyncQueue asyncQueue2, LocalStore localStore2) {
            this.asyncQueue = asyncQueue2;
            this.localStore = localStore2;
        }

        public void start() {
            if (LruGarbageCollector.this.params.minBytesThreshold != -1) {
                scheduleGC();
            }
        }

        public void stop() {
            AsyncQueue.DelayedTask delayedTask = this.gcTask;
            if (delayedTask != null) {
                delayedTask.cancel();
            }
        }

        private void scheduleGC() {
            this.gcTask = this.asyncQueue.enqueueAfterDelay(AsyncQueue.TimerId.GARBAGE_COLLECTION, this.hasRun ? LruGarbageCollector.REGULAR_GC_DELAY_MS : LruGarbageCollector.INITIAL_GC_DELAY_MS, new LruGarbageCollector$GCScheduler$$ExternalSyntheticLambda0(this));
        }

        /* renamed from: lambda$scheduleGC$0$com-google-firebase-firestore-local-LruGarbageCollector$GCScheduler */
        public /* synthetic */ void mo9205xb32188f8() {
            this.localStore.collectGarbage(LruGarbageCollector.this);
            this.hasRun = true;
            scheduleGC();
        }
    }

    LruGarbageCollector(LruDelegate delegate2, Params params2) {
        this.delegate = delegate2;
        this.params = params2;
    }

    public GCScheduler newScheduler(AsyncQueue asyncQueue, LocalStore localStore) {
        return new GCScheduler(asyncQueue, localStore);
    }

    /* access modifiers changed from: package-private */
    public int calculateQueryCount(int percentile) {
        return (int) ((((float) percentile) / 100.0f) * ((float) this.delegate.getSequenceNumberCount()));
    }

    private static class RollingSequenceNumberBuffer {
        private static final Comparator<Long> COMPARATOR = C0771xc382edc1.INSTANCE;
        private final int maxElements;
        private final PriorityQueue<Long> queue;

        RollingSequenceNumberBuffer(int count) {
            this.maxElements = count;
            this.queue = new PriorityQueue<>(count, COMPARATOR);
        }

        /* access modifiers changed from: package-private */
        public void addElement(Long sequenceNumber) {
            if (this.queue.size() < this.maxElements) {
                this.queue.add(sequenceNumber);
            } else if (sequenceNumber.longValue() < this.queue.peek().longValue()) {
                this.queue.poll();
                this.queue.add(sequenceNumber);
            }
        }

        /* access modifiers changed from: package-private */
        public long getMaxValue() {
            return this.queue.peek().longValue();
        }
    }

    /* access modifiers changed from: package-private */
    public long getNthSequenceNumber(int count) {
        if (count == 0) {
            return -1;
        }
        RollingSequenceNumberBuffer buffer = new RollingSequenceNumberBuffer(count);
        this.delegate.forEachTarget(new LruGarbageCollector$$ExternalSyntheticLambda0(buffer));
        LruDelegate lruDelegate = this.delegate;
        Objects.requireNonNull(buffer);
        lruDelegate.forEachOrphanedDocumentSequenceNumber(new LruGarbageCollector$$ExternalSyntheticLambda1(buffer));
        return buffer.getMaxValue();
    }

    /* access modifiers changed from: package-private */
    public int removeTargets(long upperBound, SparseArray<?> activeTargetIds) {
        return this.delegate.removeTargets(upperBound, activeTargetIds);
    }

    /* access modifiers changed from: package-private */
    public int removeOrphanedDocuments(long upperBound) {
        return this.delegate.removeOrphanedDocuments(upperBound);
    }

    /* access modifiers changed from: package-private */
    public Results collect(SparseArray<?> activeTargetIds) {
        if (this.params.minBytesThreshold == -1) {
            Logger.debug("LruGarbageCollector", "Garbage collection skipped; disabled", new Object[0]);
            return Results.DidNotRun();
        }
        long cacheSize = getByteSize();
        if (cacheSize >= this.params.minBytesThreshold) {
            return runGarbageCollection(activeTargetIds);
        }
        Logger.debug("LruGarbageCollector", "Garbage collection skipped; Cache size " + cacheSize + " is lower than threshold " + this.params.minBytesThreshold, new Object[0]);
        return Results.DidNotRun();
    }

    private Results runGarbageCollection(SparseArray<?> liveTargetIds) {
        long startTs = System.currentTimeMillis();
        int sequenceNumbers = calculateQueryCount(this.params.percentileToCollect);
        if (sequenceNumbers > this.params.maximumSequenceNumbersToCollect) {
            Logger.debug("LruGarbageCollector", "Capping sequence numbers to collect down to the maximum of " + this.params.maximumSequenceNumbersToCollect + " from " + sequenceNumbers, new Object[0]);
            sequenceNumbers = this.params.maximumSequenceNumbersToCollect;
        }
        long countedTargetsTs = System.currentTimeMillis();
        long upperBound = getNthSequenceNumber(sequenceNumbers);
        long foundUpperBoundTs = System.currentTimeMillis();
        int numTargetsRemoved = removeTargets(upperBound, liveTargetIds);
        long removedTargetsTs = System.currentTimeMillis();
        int numDocumentsRemoved = removeOrphanedDocuments(upperBound);
        long removedDocumentsTs = System.currentTimeMillis();
        if (Logger.isDebugEnabled()) {
            Object obj = "LRU Garbage Collection:\n";
            long j = upperBound;
            String desc = "LRU Garbage Collection:\n" + "\tCounted targets in " + (countedTargetsTs - startTs) + "ms\n";
            String str = desc;
            String desc2 = desc + String.format(Locale.ROOT, "\tDetermined least recently used %d sequence numbers in %dms\n", new Object[]{Integer.valueOf(sequenceNumbers), Long.valueOf(foundUpperBoundTs - countedTargetsTs)});
            String str2 = desc2;
            String desc3 = desc2 + String.format(Locale.ROOT, "\tRemoved %d targets in %dms\n", new Object[]{Integer.valueOf(numTargetsRemoved), Long.valueOf(removedTargetsTs - foundUpperBoundTs)});
            String str3 = desc3;
            String desc4 = desc3 + String.format(Locale.ROOT, "\tRemoved %d documents in %dms\n", new Object[]{Integer.valueOf(numDocumentsRemoved), Long.valueOf(removedDocumentsTs - removedTargetsTs)});
            String str4 = desc4;
            long j2 = startTs;
            Logger.debug("LruGarbageCollector", desc4 + String.format(Locale.ROOT, "Total Duration: %dms", new Object[]{Long.valueOf(removedDocumentsTs - startTs)}), new Object[0]);
        } else {
            long j3 = upperBound;
        }
        return new Results(true, sequenceNumbers, numTargetsRemoved, numDocumentsRemoved);
    }

    /* access modifiers changed from: package-private */
    public long getByteSize() {
        return this.delegate.getByteSize();
    }
}
