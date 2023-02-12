package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.util.SparseArray;
import com.google.firebase.firestore.core.ListenSequence;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import java.util.ArrayList;
import java.util.List;

class SQLiteLruReferenceDelegate implements ReferenceDelegate, LruDelegate {
    static final int REMOVE_ORPHANED_DOCUMENTS_BATCH_SIZE = 100;
    private long currentSequenceNumber = -1;
    private final LruGarbageCollector garbageCollector;
    private ReferenceSet inMemoryPins;
    private ListenSequence listenSequence;
    private final SQLitePersistence persistence;

    SQLiteLruReferenceDelegate(SQLitePersistence persistence2, LruGarbageCollector.Params params) {
        this.persistence = persistence2;
        this.garbageCollector = new LruGarbageCollector(this, params);
    }

    /* access modifiers changed from: package-private */
    public void start(long highestSequenceNumber) {
        this.listenSequence = new ListenSequence(highestSequenceNumber);
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

    public LruGarbageCollector getGarbageCollector() {
        return this.garbageCollector;
    }

    public long getSequenceNumberCount() {
        return this.persistence.getTargetCache().getTargetCount() + ((Long) this.persistence.query("SELECT COUNT(*) FROM (SELECT sequence_number FROM target_documents GROUP BY path HAVING COUNT(*) = 1 AND target_id = 0)").firstValue(SQLiteLruReferenceDelegate$$ExternalSyntheticLambda2.INSTANCE)).longValue();
    }

    public void forEachTarget(Consumer<TargetData> consumer) {
        this.persistence.getTargetCache().forEachTarget(consumer);
    }

    public void forEachOrphanedDocumentSequenceNumber(Consumer<Long> consumer) {
        this.persistence.query("select sequence_number from target_documents group by path having COUNT(*) = 1 AND target_id = 0").forEach(new SQLiteLruReferenceDelegate$$ExternalSyntheticLambda1(consumer));
    }

    public void setInMemoryPins(ReferenceSet inMemoryPins2) {
        this.inMemoryPins = inMemoryPins2;
    }

    public void addReference(DocumentKey key) {
        writeSentinel(key);
    }

    public void removeReference(DocumentKey key) {
        writeSentinel(key);
    }

    public int removeTargets(long upperBound, SparseArray<?> activeTargetIds) {
        return this.persistence.getTargetCache().removeQueries(upperBound, activeTargetIds);
    }

    public void removeMutationReference(DocumentKey key) {
        writeSentinel(key);
    }

    private boolean mutationQueuesContainKey(DocumentKey key) {
        return !this.persistence.query("SELECT 1 FROM document_mutations WHERE path = ?").binding(EncodedPath.encode(key.getPath())).isEmpty();
    }

    private boolean isPinned(DocumentKey key) {
        if (this.inMemoryPins.containsKey(key)) {
            return true;
        }
        return mutationQueuesContainKey(key);
    }

    private void removeSentinel(DocumentKey key) {
        this.persistence.execute("DELETE FROM target_documents WHERE path = ? AND target_id = 0", EncodedPath.encode(key.getPath()));
    }

    public int removeOrphanedDocuments(long upperBound) {
        int[] count = new int[1];
        boolean resultsRemaining = true;
        List<DocumentKey> docsToRemove = new ArrayList<>();
        while (true) {
            boolean z = false;
            if (resultsRemaining) {
                if (this.persistence.query("select path from target_documents group by path having COUNT(*) = 1 AND target_id = 0 AND sequence_number <= ? LIMIT ?").binding(Long.valueOf(upperBound), 100).forEach(new SQLiteLruReferenceDelegate$$ExternalSyntheticLambda0(this, count, docsToRemove)) == 100) {
                    z = true;
                }
                resultsRemaining = z;
            } else {
                this.persistence.getRemoteDocumentCache().removeAll(docsToRemove);
                return count[0];
            }
        }
    }

    /* renamed from: lambda$removeOrphanedDocuments$2$com-google-firebase-firestore-local-SQLiteLruReferenceDelegate */
    public /* synthetic */ void mo9312x5fd3c655(int[] count, List docsToRemove, Cursor row) {
        DocumentKey key = DocumentKey.fromPath(EncodedPath.decodeResourcePath(row.getString(0)));
        if (!isPinned(key)) {
            count[0] = count[0] + 1;
            docsToRemove.add(key);
            removeSentinel(key);
        }
    }

    public void removeTarget(TargetData targetData) {
        this.persistence.getTargetCache().updateTargetData(targetData.withSequenceNumber(getCurrentSequenceNumber()));
    }

    public void updateLimboDocument(DocumentKey key) {
        writeSentinel(key);
    }

    private void writeSentinel(DocumentKey key) {
        String path = EncodedPath.encode(key.getPath());
        this.persistence.execute("INSERT OR REPLACE INTO target_documents (target_id, path, sequence_number) VALUES (0, ?, ?)", path, Long.valueOf(getCurrentSequenceNumber()));
    }

    public long getByteSize() {
        return this.persistence.getByteSize();
    }
}
