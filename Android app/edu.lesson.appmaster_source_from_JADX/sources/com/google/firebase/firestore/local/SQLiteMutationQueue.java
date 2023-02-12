package com.google.firebase.firestore.local;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.proto.WriteBatch;
import com.google.firebase.firestore.remote.WriteStream;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Consumer;
import com.google.firebase.firestore.util.Preconditions;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

final class SQLiteMutationQueue implements MutationQueue {
    private static final int BLOB_MAX_INLINE_LENGTH = 1000000;

    /* renamed from: db */
    private final SQLitePersistence f164db;
    private final IndexManager indexManager;
    private ByteString lastStreamToken;
    private int nextBatchId;
    private final LocalSerializer serializer;
    private final String uid;

    SQLiteMutationQueue(SQLitePersistence persistence, LocalSerializer serializer2, User user, IndexManager indexManager2) {
        this.f164db = persistence;
        this.serializer = serializer2;
        this.uid = user.isAuthenticated() ? user.getUid() : "";
        this.lastStreamToken = WriteStream.EMPTY_STREAM_TOKEN;
        this.indexManager = indexManager2;
    }

    public void start() {
        loadNextBatchIdAcrossAllUsers();
        if (this.f164db.query("SELECT last_stream_token FROM mutation_queues WHERE uid = ?").binding(this.uid).first(new SQLiteMutationQueue$$ExternalSyntheticLambda3(this)) == 0) {
            writeMutationQueueMetadata();
        }
    }

    /* renamed from: lambda$start$0$com-google-firebase-firestore-local-SQLiteMutationQueue */
    public /* synthetic */ void mo9321xf87fd3c7(Cursor row) {
        this.lastStreamToken = ByteString.copyFrom(row.getBlob(0));
    }

    private void loadNextBatchIdAcrossAllUsers() {
        List<String> uids = new ArrayList<>();
        this.f164db.query("SELECT uid FROM mutation_queues").forEach(new SQLiteMutationQueue$$ExternalSyntheticLambda8(uids));
        this.nextBatchId = 0;
        for (String uid2 : uids) {
            this.f164db.query("SELECT MAX(batch_id) FROM mutations WHERE uid = ?").binding(uid2).forEach(new SQLiteMutationQueue$$ExternalSyntheticLambda0(this));
        }
        this.nextBatchId++;
    }

    /* renamed from: lambda$loadNextBatchIdAcrossAllUsers$2$com-google-firebase-firestore-local-SQLiteMutationQueue */
    public /* synthetic */ void mo9319xa3a174c9(Cursor row) {
        this.nextBatchId = Math.max(this.nextBatchId, row.getInt(0));
    }

    public boolean isEmpty() {
        return this.f164db.query("SELECT batch_id FROM mutations WHERE uid = ? LIMIT 1").binding(this.uid).isEmpty();
    }

    public void acknowledgeBatch(MutationBatch batch, ByteString streamToken) {
        this.lastStreamToken = (ByteString) Preconditions.checkNotNull(streamToken);
        writeMutationQueueMetadata();
    }

    public ByteString getLastStreamToken() {
        return this.lastStreamToken;
    }

    public void setLastStreamToken(ByteString streamToken) {
        this.lastStreamToken = (ByteString) Preconditions.checkNotNull(streamToken);
        writeMutationQueueMetadata();
    }

    private void writeMutationQueueMetadata() {
        this.f164db.execute("INSERT OR REPLACE INTO mutation_queues (uid, last_acknowledged_batch_id, last_stream_token) VALUES (?, ?, ?)", this.uid, -1, this.lastStreamToken.toByteArray());
    }

    public MutationBatch addMutationBatch(Timestamp localWriteTime, List<Mutation> baseMutations, List<Mutation> mutations) {
        int batchId = this.nextBatchId;
        this.nextBatchId++;
        MutationBatch batch = new MutationBatch(batchId, localWriteTime, baseMutations, mutations);
        int i = 3;
        this.f164db.execute("INSERT INTO mutations (uid, batch_id, mutations) VALUES (?, ?, ?)", this.uid, Integer.valueOf(batchId), this.serializer.encodeMutationBatch(batch).toByteArray());
        Set<DocumentKey> inserted = new HashSet<>();
        SQLiteStatement indexInserter = this.f164db.prepare("INSERT INTO document_mutations (uid, path, batch_id) VALUES (?, ?, ?)");
        for (Mutation mutation : mutations) {
            DocumentKey key = mutation.getKey();
            if (inserted.add(key)) {
                String path = EncodedPath.encode(key.getPath());
                SQLitePersistence sQLitePersistence = this.f164db;
                Object[] objArr = new Object[i];
                objArr[0] = this.uid;
                objArr[1] = path;
                objArr[2] = Integer.valueOf(batchId);
                sQLitePersistence.execute(indexInserter, objArr);
                this.indexManager.addToCollectionParentIndex(key.getCollectionPath());
                i = 3;
            }
        }
        return batch;
    }

    public MutationBatch lookupMutationBatch(int batchId) {
        return (MutationBatch) this.f164db.query("SELECT SUBSTR(mutations, 1, ?) FROM mutations WHERE uid = ? AND batch_id = ?").binding(Integer.valueOf(BLOB_MAX_INLINE_LENGTH), this.uid, Integer.valueOf(batchId)).firstValue(new SQLiteMutationQueue$$ExternalSyntheticLambda11(this, batchId));
    }

    /* renamed from: lambda$lookupMutationBatch$3$com-google-firebase-firestore-local-SQLiteMutationQueue */
    public /* synthetic */ MutationBatch mo9320xb685bf9f(int batchId, Cursor row) {
        return decodeInlineMutationBatch(batchId, row.getBlob(0));
    }

    public MutationBatch getNextMutationBatchAfterBatchId(int batchId) {
        return (MutationBatch) this.f164db.query("SELECT batch_id, SUBSTR(mutations, 1, ?) FROM mutations WHERE uid = ? AND batch_id >= ? ORDER BY batch_id ASC LIMIT 1").binding(Integer.valueOf(BLOB_MAX_INLINE_LENGTH), this.uid, Integer.valueOf(batchId + 1)).firstValue(new SQLiteMutationQueue$$ExternalSyntheticLambda10(this));
    }

    /* renamed from: lambda$getNextMutationBatchAfterBatchId$4$com-google-firebase-firestore-local-SQLiteMutationQueue */
    public /* synthetic */ MutationBatch mo9318x6512fc44(Cursor row) {
        return decodeInlineMutationBatch(row.getInt(0), row.getBlob(1));
    }

    public int getHighestUnacknowledgedBatchId() {
        return ((Integer) this.f164db.query("SELECT IFNULL(MAX(batch_id), ?) FROM mutations WHERE uid = ?").binding(-1, this.uid).firstValue(SQLiteMutationQueue$$ExternalSyntheticLambda1.INSTANCE)).intValue();
    }

    public List<MutationBatch> getAllMutationBatches() {
        List<MutationBatch> result = new ArrayList<>();
        this.f164db.query("SELECT batch_id, SUBSTR(mutations, 1, ?) FROM mutations WHERE uid = ? ORDER BY batch_id ASC").binding(Integer.valueOf(BLOB_MAX_INLINE_LENGTH), this.uid).forEach(new SQLiteMutationQueue$$ExternalSyntheticLambda4(this, result));
        return result;
    }

    /* renamed from: lambda$getAllMutationBatches$6$com-google-firebase-firestore-local-SQLiteMutationQueue */
    public /* synthetic */ void mo9314x425a097f(List result, Cursor row) {
        result.add(decodeInlineMutationBatch(row.getInt(0), row.getBlob(1)));
    }

    public List<MutationBatch> getAllMutationBatchesAffectingDocumentKey(DocumentKey documentKey) {
        String path = EncodedPath.encode(documentKey.getPath());
        List<MutationBatch> result = new ArrayList<>();
        this.f164db.query("SELECT m.batch_id, SUBSTR(m.mutations, 1, ?) FROM document_mutations dm, mutations m WHERE dm.uid = ? AND dm.path = ? AND dm.uid = m.uid AND dm.batch_id = m.batch_id ORDER BY dm.batch_id").binding(Integer.valueOf(BLOB_MAX_INLINE_LENGTH), this.uid, path).forEach(new SQLiteMutationQueue$$ExternalSyntheticLambda5(this, result));
        return result;
    }

    /* renamed from: lambda$getAllMutationBatchesAffectingDocumentKey$7$com-google-firebase-firestore-local-SQLiteMutationQueue */
    public /* synthetic */ void mo9315xb3096ab7(List result, Cursor row) {
        result.add(decodeInlineMutationBatch(row.getInt(0), row.getBlob(1)));
    }

    public List<MutationBatch> getAllMutationBatchesAffectingDocumentKeys(Iterable<DocumentKey> documentKeys) {
        List<Object> args = new ArrayList<>();
        for (DocumentKey key : documentKeys) {
            args.add(EncodedPath.encode(key.getPath()));
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.f164db, "SELECT DISTINCT dm.batch_id, SUBSTR(m.mutations, 1, ?) FROM document_mutations dm, mutations m WHERE dm.uid = ? AND dm.path IN (", Arrays.asList(new Object[]{Integer.valueOf(BLOB_MAX_INLINE_LENGTH), this.uid}), args, ") AND dm.uid = m.uid AND dm.batch_id = m.batch_id ORDER BY dm.batch_id");
        List<MutationBatch> result = new ArrayList<>();
        Set<Integer> uniqueBatchIds = new HashSet<>();
        while (longQuery.hasMoreSubqueries()) {
            longQuery.performNextSubquery().forEach(new SQLiteMutationQueue$$ExternalSyntheticLambda7(this, uniqueBatchIds, result));
        }
        if (longQuery.getSubqueriesPerformed() > 1) {
            Collections.sort(result, SQLiteMutationQueue$$ExternalSyntheticLambda2.INSTANCE);
        }
        return result;
    }

    /* renamed from: lambda$getAllMutationBatchesAffectingDocumentKeys$8$com-google-firebase-firestore-local-SQLiteMutationQueue */
    public /* synthetic */ void mo9316x1f85c24f(Set uniqueBatchIds, List result, Cursor row) {
        int batchId = row.getInt(0);
        if (!uniqueBatchIds.contains(Integer.valueOf(batchId))) {
            uniqueBatchIds.add(Integer.valueOf(batchId));
            result.add(decodeInlineMutationBatch(batchId, row.getBlob(1)));
        }
    }

    public List<MutationBatch> getAllMutationBatchesAffectingQuery(Query query) {
        Assert.hardAssert(!query.isCollectionGroupQuery(), "CollectionGroup queries should be handled in LocalDocumentsView", new Object[0]);
        ResourcePath prefix = query.getPath();
        String prefixPath = EncodedPath.encode(prefix);
        String prefixSuccessorPath = EncodedPath.prefixSuccessor(prefixPath);
        List<MutationBatch> result = new ArrayList<>();
        this.f164db.query("SELECT dm.batch_id, dm.path, SUBSTR(m.mutations, 1, ?) FROM document_mutations dm, mutations m WHERE dm.uid = ? AND dm.path >= ? AND dm.path < ? AND dm.uid = m.uid AND dm.batch_id = m.batch_id ORDER BY dm.batch_id").binding(Integer.valueOf(BLOB_MAX_INLINE_LENGTH), this.uid, prefixPath, prefixSuccessorPath).forEach(new SQLiteMutationQueue$$ExternalSyntheticLambda6(this, result, prefix.length() + 1));
        return result;
    }

    /* renamed from: lambda$getAllMutationBatchesAffectingQuery$10$com-google-firebase-firestore-local-SQLiteMutationQueue */
    public /* synthetic */ void mo9317xb8a43fc7(List result, int immediateChildrenPathLength, Cursor row) {
        int batchId = row.getInt(0);
        int size = result.size();
        if ((size <= 0 || batchId != ((MutationBatch) result.get(size - 1)).getBatchId()) && EncodedPath.decodeResourcePath(row.getString(1)).length() == immediateChildrenPathLength) {
            result.add(decodeInlineMutationBatch(batchId, row.getBlob(2)));
        }
    }

    public void removeMutationBatch(MutationBatch batch) {
        SQLiteStatement mutationDeleter = this.f164db.prepare("DELETE FROM mutations WHERE uid = ? AND batch_id = ?");
        SQLiteStatement indexDeleter = this.f164db.prepare("DELETE FROM document_mutations WHERE uid = ? AND path = ? AND batch_id = ?");
        int batchId = batch.getBatchId();
        Assert.hardAssert(this.f164db.execute(mutationDeleter, this.uid, Integer.valueOf(batchId)) != 0, "Mutation batch (%s, %d) did not exist", this.uid, Integer.valueOf(batch.getBatchId()));
        for (Mutation mutation : batch.getMutations()) {
            DocumentKey key = mutation.getKey();
            this.f164db.execute(indexDeleter, this.uid, EncodedPath.encode(key.getPath()), Integer.valueOf(batchId));
            this.f164db.getReferenceDelegate().removeMutationReference(key);
        }
    }

    public void performConsistencyCheck() {
        if (isEmpty()) {
            List<ResourcePath> danglingMutationReferences = new ArrayList<>();
            this.f164db.query("SELECT path FROM document_mutations WHERE uid = ?").binding(this.uid).forEach(new SQLiteMutationQueue$$ExternalSyntheticLambda9(danglingMutationReferences));
            Assert.hardAssert(danglingMutationReferences.isEmpty(), "Document leak -- detected dangling mutation references when queue is empty. Dangling keys: %s", danglingMutationReferences);
        }
    }

    private MutationBatch decodeInlineMutationBatch(int batchId, byte[] bytes) {
        try {
            if (bytes.length < BLOB_MAX_INLINE_LENGTH) {
                return this.serializer.decodeMutationBatch(WriteBatch.parseFrom(bytes));
            }
            BlobAccumulator accumulator = new BlobAccumulator(bytes);
            while (accumulator.more) {
                this.f164db.query("SELECT SUBSTR(mutations, ?, ?) FROM mutations WHERE uid = ? AND batch_id = ?").binding(Integer.valueOf((accumulator.numChunks() * BLOB_MAX_INLINE_LENGTH) + 1), Integer.valueOf(BLOB_MAX_INLINE_LENGTH), this.uid, Integer.valueOf(batchId)).first(accumulator);
            }
            return this.serializer.decodeMutationBatch(WriteBatch.parseFrom(accumulator.result()));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("MutationBatch failed to parse: %s", e);
        }
    }

    private static class BlobAccumulator implements Consumer<Cursor> {
        private final ArrayList<ByteString> chunks = new ArrayList<>();
        /* access modifiers changed from: private */
        public boolean more = true;

        BlobAccumulator(byte[] firstChunk) {
            addChunk(firstChunk);
        }

        /* access modifiers changed from: package-private */
        public int numChunks() {
            return this.chunks.size();
        }

        /* access modifiers changed from: package-private */
        public ByteString result() {
            return ByteString.copyFrom((Iterable<ByteString>) this.chunks);
        }

        public void accept(Cursor row) {
            byte[] bytes = row.getBlob(0);
            addChunk(bytes);
            if (bytes.length < SQLiteMutationQueue.BLOB_MAX_INLINE_LENGTH) {
                this.more = false;
            }
        }

        private void addChunk(byte[] bytes) {
            this.chunks.add(ByteString.copyFrom(bytes));
        }
    }
}
