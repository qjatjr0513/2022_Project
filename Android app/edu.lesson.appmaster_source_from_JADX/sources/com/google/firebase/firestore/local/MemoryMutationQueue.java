package com.google.firebase.firestore.local;

import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.remote.WriteStream;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

final class MemoryMutationQueue implements MutationQueue {
    private ImmutableSortedSet<DocumentReference> batchesByDocumentKey = new ImmutableSortedSet<>(Collections.emptyList(), DocumentReference.BY_KEY);
    private final MemoryIndexManager indexManager;
    private ByteString lastStreamToken = WriteStream.EMPTY_STREAM_TOKEN;
    private int nextBatchId = 1;
    private final MemoryPersistence persistence;
    private final List<MutationBatch> queue = new ArrayList();

    MemoryMutationQueue(MemoryPersistence persistence2, User user) {
        this.persistence = persistence2;
        this.indexManager = persistence2.getIndexManager(user);
    }

    public void start() {
        if (isEmpty()) {
            this.nextBatchId = 1;
        }
    }

    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    public void acknowledgeBatch(MutationBatch batch, ByteString streamToken) {
        int batchId = batch.getBatchId();
        int batchIndex = indexOfExistingBatchId(batchId, "acknowledged");
        Assert.hardAssert(batchIndex == 0, "Can only acknowledge the first batch in the mutation queue", new Object[0]);
        MutationBatch check = this.queue.get(batchIndex);
        Assert.hardAssert(batchId == check.getBatchId(), "Queue ordering failure: expected batch %d, got batch %d", Integer.valueOf(batchId), Integer.valueOf(check.getBatchId()));
        this.lastStreamToken = (ByteString) Preconditions.checkNotNull(streamToken);
    }

    public ByteString getLastStreamToken() {
        return this.lastStreamToken;
    }

    public void setLastStreamToken(ByteString streamToken) {
        this.lastStreamToken = (ByteString) Preconditions.checkNotNull(streamToken);
    }

    public MutationBatch addMutationBatch(Timestamp localWriteTime, List<Mutation> baseMutations, List<Mutation> mutations) {
        boolean z = true;
        Assert.hardAssert(!mutations.isEmpty(), "Mutation batches should not be empty", new Object[0]);
        int batchId = this.nextBatchId;
        this.nextBatchId++;
        int size = this.queue.size();
        if (size > 0) {
            if (this.queue.get(size - 1).getBatchId() >= batchId) {
                z = false;
            }
            Assert.hardAssert(z, "Mutation batchIds must be monotonically increasing order", new Object[0]);
        }
        MutationBatch batch = new MutationBatch(batchId, localWriteTime, baseMutations, mutations);
        this.queue.add(batch);
        for (Mutation mutation : mutations) {
            this.batchesByDocumentKey = this.batchesByDocumentKey.insert(new DocumentReference(mutation.getKey(), batchId));
            this.indexManager.addToCollectionParentIndex(mutation.getKey().getCollectionPath());
        }
        return batch;
    }

    public MutationBatch lookupMutationBatch(int batchId) {
        int index = indexOfBatchId(batchId);
        if (index < 0 || index >= this.queue.size()) {
            return null;
        }
        MutationBatch batch = this.queue.get(index);
        Assert.hardAssert(batch.getBatchId() == batchId, "If found batch must match", new Object[0]);
        return batch;
    }

    public MutationBatch getNextMutationBatchAfterBatchId(int batchId) {
        int rawIndex = indexOfBatchId(batchId + 1);
        int index = rawIndex < 0 ? 0 : rawIndex;
        if (this.queue.size() > index) {
            return this.queue.get(index);
        }
        return null;
    }

    public int getHighestUnacknowledgedBatchId() {
        if (this.queue.isEmpty()) {
            return -1;
        }
        return this.nextBatchId - 1;
    }

    public List<MutationBatch> getAllMutationBatches() {
        return Collections.unmodifiableList(this.queue);
    }

    public List<MutationBatch> getAllMutationBatchesAffectingDocumentKey(DocumentKey documentKey) {
        DocumentReference start = new DocumentReference(documentKey, 0);
        List<MutationBatch> result = new ArrayList<>();
        Iterator<DocumentReference> iterator = this.batchesByDocumentKey.iteratorFrom(start);
        while (iterator.hasNext()) {
            DocumentReference reference = iterator.next();
            if (!documentKey.equals(reference.getKey())) {
                break;
            }
            MutationBatch batch = lookupMutationBatch(reference.getId());
            Assert.hardAssert(batch != null, "Batches in the index must exist in the main table", new Object[0]);
            result.add(batch);
        }
        return result;
    }

    public List<MutationBatch> getAllMutationBatchesAffectingDocumentKeys(Iterable<DocumentKey> documentKeys) {
        ImmutableSortedSet<Integer> uniqueBatchIDs = new ImmutableSortedSet<>(Collections.emptyList(), Util.comparator());
        for (DocumentKey key : documentKeys) {
            Iterator<DocumentReference> batchesIter = this.batchesByDocumentKey.iteratorFrom(new DocumentReference(key, 0));
            while (batchesIter.hasNext()) {
                DocumentReference reference = batchesIter.next();
                if (!key.equals(reference.getKey())) {
                    break;
                }
                uniqueBatchIDs = uniqueBatchIDs.insert(Integer.valueOf(reference.getId()));
            }
        }
        return lookupMutationBatches(uniqueBatchIDs);
    }

    /* JADX WARNING: type inference failed for: r4v7, types: [com.google.firebase.firestore.model.BasePath] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.firebase.firestore.model.mutation.MutationBatch> getAllMutationBatchesAffectingQuery(com.google.firebase.firestore.core.Query r10) {
        /*
            r9 = this;
            boolean r0 = r10.isCollectionGroupQuery()
            r0 = r0 ^ 1
            r1 = 0
            java.lang.Object[] r2 = new java.lang.Object[r1]
            java.lang.String r3 = "CollectionGroup queries should be handled in LocalDocumentsView"
            com.google.firebase.firestore.util.Assert.hardAssert(r0, r3, r2)
            com.google.firebase.firestore.model.ResourcePath r0 = r10.getPath()
            int r2 = r0.length()
            int r2 = r2 + 1
            r3 = r0
            boolean r4 = com.google.firebase.firestore.model.DocumentKey.isDocumentKey(r3)
            if (r4 != 0) goto L_0x0029
            java.lang.String r4 = ""
            com.google.firebase.firestore.model.BasePath r4 = r3.append((java.lang.String) r4)
            r3 = r4
            com.google.firebase.firestore.model.ResourcePath r3 = (com.google.firebase.firestore.model.ResourcePath) r3
        L_0x0029:
            com.google.firebase.firestore.local.DocumentReference r4 = new com.google.firebase.firestore.local.DocumentReference
            com.google.firebase.firestore.model.DocumentKey r5 = com.google.firebase.firestore.model.DocumentKey.fromPath(r3)
            r4.<init>(r5, r1)
            r1 = r4
            com.google.firebase.database.collection.ImmutableSortedSet r4 = new com.google.firebase.database.collection.ImmutableSortedSet
            java.util.List r5 = java.util.Collections.emptyList()
            java.util.Comparator r6 = com.google.firebase.firestore.util.Util.comparator()
            r4.<init>(r5, r6)
            com.google.firebase.database.collection.ImmutableSortedSet<com.google.firebase.firestore.local.DocumentReference> r5 = r9.batchesByDocumentKey
            java.util.Iterator r5 = r5.iteratorFrom(r1)
        L_0x0046:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x0074
            java.lang.Object r6 = r5.next()
            com.google.firebase.firestore.local.DocumentReference r6 = (com.google.firebase.firestore.local.DocumentReference) r6
            com.google.firebase.firestore.model.DocumentKey r7 = r6.getKey()
            com.google.firebase.firestore.model.ResourcePath r7 = r7.getPath()
            boolean r8 = r0.isPrefixOf(r7)
            if (r8 != 0) goto L_0x0061
            goto L_0x0074
        L_0x0061:
            int r8 = r7.length()
            if (r8 != r2) goto L_0x0073
            int r8 = r6.getId()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            com.google.firebase.database.collection.ImmutableSortedSet r4 = r4.insert(r8)
        L_0x0073:
            goto L_0x0046
        L_0x0074:
            java.util.List r6 = r9.lookupMutationBatches(r4)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.local.MemoryMutationQueue.getAllMutationBatchesAffectingQuery(com.google.firebase.firestore.core.Query):java.util.List");
    }

    private List<MutationBatch> lookupMutationBatches(ImmutableSortedSet<Integer> batchIds) {
        List<MutationBatch> result = new ArrayList<>();
        Iterator<Integer> it = batchIds.iterator();
        while (it.hasNext()) {
            MutationBatch batch = lookupMutationBatch(it.next().intValue());
            if (batch != null) {
                result.add(batch);
            }
        }
        return result;
    }

    public void removeMutationBatch(MutationBatch batch) {
        Assert.hardAssert(indexOfExistingBatchId(batch.getBatchId(), "removed") == 0, "Can only remove the first entry of the mutation queue", new Object[0]);
        this.queue.remove(0);
        ImmutableSortedSet<DocumentReference> references = this.batchesByDocumentKey;
        for (Mutation mutation : batch.getMutations()) {
            DocumentKey key = mutation.getKey();
            this.persistence.getReferenceDelegate().removeMutationReference(key);
            references = references.remove(new DocumentReference(key, batch.getBatchId()));
        }
        this.batchesByDocumentKey = references;
    }

    public void performConsistencyCheck() {
        if (this.queue.isEmpty()) {
            Assert.hardAssert(this.batchesByDocumentKey.isEmpty(), "Document leak -- detected dangling mutation references when queue is empty.", new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean containsKey(DocumentKey key) {
        Iterator<DocumentReference> iterator = this.batchesByDocumentKey.iteratorFrom(new DocumentReference(key, 0));
        if (!iterator.hasNext()) {
            return false;
        }
        return iterator.next().getKey().equals(key);
    }

    private int indexOfBatchId(int batchId) {
        if (this.queue.isEmpty()) {
            return 0;
        }
        return batchId - this.queue.get(0).getBatchId();
    }

    private int indexOfExistingBatchId(int batchId, String action) {
        int index = indexOfBatchId(batchId);
        Assert.hardAssert(index >= 0 && index < this.queue.size(), "Batches must exist to be %s", action);
        return index;
    }

    /* access modifiers changed from: package-private */
    public long getByteSize(LocalSerializer serializer) {
        long count = 0;
        for (MutationBatch batch : this.queue) {
            count += (long) serializer.encodeMutationBatch(batch).getSerializedSize();
        }
        return count;
    }
}
