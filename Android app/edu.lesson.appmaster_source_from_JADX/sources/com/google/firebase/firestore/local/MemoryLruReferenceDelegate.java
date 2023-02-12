package com.google.firebase.firestore.local;

import android.util.SparseArray;
import com.google.firebase.firestore.core.ListenSequence;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MemoryLruReferenceDelegate implements ReferenceDelegate, LruDelegate {
    private long currentSequenceNumber;
    private final LruGarbageCollector garbageCollector;
    private ReferenceSet inMemoryPins;
    private final ListenSequence listenSequence;
    private final Map<DocumentKey, Long> orphanedSequenceNumbers = new HashMap();
    private final MemoryPersistence persistence;
    private final LocalSerializer serializer;

    MemoryLruReferenceDelegate(MemoryPersistence persistence2, LruGarbageCollector.Params params, LocalSerializer serializer2) {
        this.persistence = persistence2;
        this.serializer = serializer2;
        this.listenSequence = new ListenSequence(persistence2.getTargetCache().getHighestListenSequenceNumber());
        this.currentSequenceNumber = -1;
        this.garbageCollector = new LruGarbageCollector(this, params);
    }

    public LruGarbageCollector getGarbageCollector() {
        return this.garbageCollector;
    }

    public void onTransactionStarted() {
        Assert.hardAssert(this.currentSequenceNumber == -1, "Starting a transaction without committing the previous one", new Object[0]);
        this.currentSequenceNumber = this.listenSequence.next();
    }

    public void onTransactionCommitted() {
        Assert.hardAssert(this.currentSequenceNumber != -1, "Committing a transaction without having started one", new Object[0]);
        this.currentSequenceNumber = -1;
    }

    public long getCurrentSequenceNumber() {
        Assert.hardAssert(this.currentSequenceNumber != -1, "Attempting to get a sequence number outside of a transaction", new Object[0]);
        return this.currentSequenceNumber;
    }

    public void forEachTarget(Consumer<TargetData> consumer) {
        this.persistence.getTargetCache().forEachTarget(consumer);
    }

    public long getSequenceNumberCount() {
        long targetCount = this.persistence.getTargetCache().getTargetCount();
        long[] orphanedCount = new long[1];
        forEachOrphanedDocumentSequenceNumber(new MemoryLruReferenceDelegate$$ExternalSyntheticLambda0(orphanedCount));
        return orphanedCount[0] + targetCount;
    }

    static /* synthetic */ void lambda$getSequenceNumberCount$0(long[] orphanedCount, Long sequenceNumber) {
        orphanedCount[0] = orphanedCount[0] + 1;
    }

    public void forEachOrphanedDocumentSequenceNumber(Consumer<Long> consumer) {
        for (Map.Entry<DocumentKey, Long> entry : this.orphanedSequenceNumbers.entrySet()) {
            if (!isPinned(entry.getKey(), entry.getValue().longValue())) {
                consumer.accept(entry.getValue());
            }
        }
    }

    public void setInMemoryPins(ReferenceSet inMemoryPins2) {
        this.inMemoryPins = inMemoryPins2;
    }

    public int removeTargets(long upperBound, SparseArray<?> activeTargetIds) {
        return this.persistence.getTargetCache().removeQueries(upperBound, activeTargetIds);
    }

    public int removeOrphanedDocuments(long upperBound) {
        MemoryRemoteDocumentCache cache = this.persistence.getRemoteDocumentCache();
        List<DocumentKey> docsToRemove = new ArrayList<>();
        for (Document doc : cache.getDocuments()) {
            DocumentKey key = doc.getKey();
            if (!isPinned(key, upperBound)) {
                docsToRemove.add(key);
                this.orphanedSequenceNumbers.remove(key);
            }
        }
        cache.removeAll(docsToRemove);
        return docsToRemove.size();
    }

    public void removeMutationReference(DocumentKey key) {
        this.orphanedSequenceNumbers.put(key, Long.valueOf(getCurrentSequenceNumber()));
    }

    public void removeTarget(TargetData targetData) {
        this.persistence.getTargetCache().updateTargetData(targetData.withSequenceNumber(getCurrentSequenceNumber()));
    }

    public void addReference(DocumentKey key) {
        this.orphanedSequenceNumbers.put(key, Long.valueOf(getCurrentSequenceNumber()));
    }

    public void removeReference(DocumentKey key) {
        this.orphanedSequenceNumbers.put(key, Long.valueOf(getCurrentSequenceNumber()));
    }

    public void updateLimboDocument(DocumentKey key) {
        this.orphanedSequenceNumbers.put(key, Long.valueOf(getCurrentSequenceNumber()));
    }

    private boolean mutationQueuesContainsKey(DocumentKey key) {
        for (MemoryMutationQueue mutationQueue : this.persistence.getMutationQueues()) {
            if (mutationQueue.containsKey(key)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPinned(DocumentKey key, long upperBound) {
        if (mutationQueuesContainsKey(key) || this.inMemoryPins.containsKey(key) || this.persistence.getTargetCache().containsKey(key)) {
            return true;
        }
        Long sequenceNumber = this.orphanedSequenceNumbers.get(key);
        if (sequenceNumber == null || sequenceNumber.longValue() <= upperBound) {
            return false;
        }
        return true;
    }

    public long getByteSize() {
        long count = 0 + this.persistence.getTargetCache().getByteSize(this.serializer) + this.persistence.getRemoteDocumentCache().getByteSize(this.serializer);
        for (MemoryMutationQueue queue : this.persistence.getMutationQueues()) {
            count += queue.getByteSize(this.serializer);
        }
        return count;
    }
}
