package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.Overlay;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.BackgroundQueue;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.p002v1.Write;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

public class SQLiteDocumentOverlayCache implements DocumentOverlayCache {

    /* renamed from: db */
    private final SQLitePersistence f162db;
    private final LocalSerializer serializer;
    private final String uid;

    public SQLiteDocumentOverlayCache(SQLitePersistence db, LocalSerializer serializer2, User user) {
        this.f162db = db;
        this.serializer = serializer2;
        this.uid = user.isAuthenticated() ? user.getUid() : "";
    }

    public Overlay getOverlay(DocumentKey key) {
        String collectionPath = EncodedPath.encode((ResourcePath) key.getPath().popLast());
        String documentId = key.getPath().getLastSegment();
        return (Overlay) this.f162db.query("SELECT overlay_mutation, largest_batch_id FROM document_overlays WHERE uid = ? AND collection_path = ? AND document_id = ?").binding(this.uid, collectionPath, documentId).firstValue(new SQLiteDocumentOverlayCache$$ExternalSyntheticLambda4(this));
    }

    /* renamed from: lambda$getOverlay$0$com-google-firebase-firestore-local-SQLiteDocumentOverlayCache */
    public /* synthetic */ Overlay mo9300xe0a2a77c(Cursor row) {
        return decodeOverlay(row.getBlob(0), row.getInt(1));
    }

    public Map<DocumentKey, Overlay> getOverlays(SortedSet<DocumentKey> keys) {
        Assert.hardAssert(keys.comparator() == null, "getOverlays() requires natural order", new Object[0]);
        Map<DocumentKey, Overlay> result = new HashMap<>();
        BackgroundQueue backgroundQueue = new BackgroundQueue();
        ResourcePath currentCollection = ResourcePath.EMPTY;
        List<Object> accumulatedDocumentIds = new ArrayList<>();
        for (DocumentKey key : keys) {
            if (!currentCollection.equals(key.getCollectionPath())) {
                processSingleCollection(result, backgroundQueue, currentCollection, accumulatedDocumentIds);
                currentCollection = key.getCollectionPath();
                accumulatedDocumentIds.clear();
            }
            accumulatedDocumentIds.add(key.getDocumentId());
        }
        processSingleCollection(result, backgroundQueue, currentCollection, accumulatedDocumentIds);
        backgroundQueue.drain();
        return result;
    }

    private void processSingleCollection(Map<DocumentKey, Overlay> result, BackgroundQueue backgroundQueue, ResourcePath collectionPath, List<Object> documentIds) {
        if (!documentIds.isEmpty()) {
            SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.f162db, "SELECT overlay_mutation, largest_batch_id FROM document_overlays WHERE uid = ? AND collection_path = ? AND document_id IN (", Arrays.asList(new Object[]{this.uid, EncodedPath.encode(collectionPath)}), documentIds, ")");
            while (longQuery.hasMoreSubqueries()) {
                longQuery.performNextSubquery().forEach(new SQLiteDocumentOverlayCache$$ExternalSyntheticLambda2(this, backgroundQueue, result));
            }
        }
    }

    private void saveOverlay(int largestBatchId, DocumentKey key, Mutation mutation) {
        String group = key.getCollectionGroup();
        String collectionPath = EncodedPath.encode((ResourcePath) key.getPath().popLast());
        String documentId = key.getPath().getLastSegment();
        this.f162db.execute("INSERT OR REPLACE INTO document_overlays (uid, collection_group, collection_path, document_id, largest_batch_id, overlay_mutation) VALUES (?, ?, ?, ?, ?, ?)", this.uid, group, collectionPath, documentId, Integer.valueOf(largestBatchId), this.serializer.encodeMutation(mutation).toByteArray());
    }

    public void saveOverlays(int largestBatchId, Map<DocumentKey, Mutation> overlays) {
        for (Map.Entry<DocumentKey, Mutation> entry : overlays.entrySet()) {
            DocumentKey key = entry.getKey();
            saveOverlay(largestBatchId, key, (Mutation) Preconditions.checkNotNull(entry.getValue(), "null value for key: %s", key));
        }
    }

    public void removeOverlaysForBatchId(int batchId) {
        this.f162db.execute("DELETE FROM document_overlays WHERE uid = ? AND largest_batch_id = ?", this.uid, Integer.valueOf(batchId));
    }

    public Map<DocumentKey, Overlay> getOverlays(ResourcePath collection, int sinceBatchId) {
        Map<DocumentKey, Overlay> result = new HashMap<>();
        BackgroundQueue backgroundQueue = new BackgroundQueue();
        this.f162db.query("SELECT overlay_mutation, largest_batch_id FROM document_overlays WHERE uid = ? AND collection_path = ? AND largest_batch_id > ?").binding(this.uid, EncodedPath.encode(collection), Integer.valueOf(sinceBatchId)).forEach(new SQLiteDocumentOverlayCache$$ExternalSyntheticLambda0(this, backgroundQueue, result));
        backgroundQueue.drain();
        return result;
    }

    public Map<DocumentKey, Overlay> getOverlays(String collectionGroup, int sinceBatchId, int count) {
        Map<DocumentKey, Overlay> result = new HashMap<>();
        String[] lastCollectionPath = new String[1];
        String[] lastDocumentPath = new String[1];
        int[] lastLargestBatchId = new int[1];
        BackgroundQueue backgroundQueue = new BackgroundQueue();
        SQLiteDocumentOverlayCache$$ExternalSyntheticLambda3 sQLiteDocumentOverlayCache$$ExternalSyntheticLambda3 = r0;
        SQLiteDocumentOverlayCache$$ExternalSyntheticLambda3 sQLiteDocumentOverlayCache$$ExternalSyntheticLambda32 = new SQLiteDocumentOverlayCache$$ExternalSyntheticLambda3(this, lastLargestBatchId, lastCollectionPath, lastDocumentPath, backgroundQueue, result);
        this.f162db.query("SELECT overlay_mutation, largest_batch_id, collection_path, document_id  FROM document_overlays WHERE uid = ? AND collection_group = ? AND largest_batch_id > ? ORDER BY largest_batch_id, collection_path, document_id LIMIT ?").binding(this.uid, collectionGroup, Integer.valueOf(sinceBatchId), Integer.valueOf(count)).forEach(sQLiteDocumentOverlayCache$$ExternalSyntheticLambda3);
        if (lastCollectionPath[0] == null) {
            return result;
        }
        this.f162db.query("SELECT overlay_mutation, largest_batch_id FROM document_overlays WHERE uid = ? AND collection_group = ? AND (collection_path > ? OR (collection_path = ? AND document_id > ?)) AND largest_batch_id = ?").binding(this.uid, collectionGroup, lastCollectionPath[0], lastCollectionPath[0], lastDocumentPath[0], Integer.valueOf(lastLargestBatchId[0])).forEach(new SQLiteDocumentOverlayCache$$ExternalSyntheticLambda1(this, backgroundQueue, result));
        backgroundQueue.drain();
        return result;
    }

    /* renamed from: lambda$getOverlays$3$com-google-firebase-firestore-local-SQLiteDocumentOverlayCache */
    public /* synthetic */ void mo9302xa097d694(int[] lastLargestBatchId, String[] lastCollectionPath, String[] lastDocumentPath, BackgroundQueue backgroundQueue, Map result, Cursor row) {
        lastLargestBatchId[0] = row.getInt(1);
        lastCollectionPath[0] = row.getString(2);
        lastDocumentPath[0] = row.getString(3);
        mo9305x634144da(backgroundQueue, result, row);
    }

    /* access modifiers changed from: private */
    /* renamed from: processOverlaysInBackground */
    public void mo9305x634144da(BackgroundQueue backgroundQueue, Map<DocumentKey, Overlay> results, Cursor row) {
        (row.isLast() ? Executors.DIRECT_EXECUTOR : backgroundQueue).execute(new SQLiteDocumentOverlayCache$$ExternalSyntheticLambda5(this, row.getBlob(0), row.getInt(1), results));
    }

    /* renamed from: lambda$processOverlaysInBackground$5$com-google-firebase-firestore-local-SQLiteDocumentOverlayCache */
    public /* synthetic */ void mo9304x1d10fe66(byte[] rawMutation, int largestBatchId, Map results) {
        Overlay overlay = decodeOverlay(rawMutation, largestBatchId);
        synchronized (results) {
            results.put(overlay.getKey(), overlay);
        }
    }

    private Overlay decodeOverlay(byte[] rawMutation, int largestBatchId) {
        try {
            return Overlay.create(largestBatchId, this.serializer.decodeMutation(Write.parseFrom(rawMutation)));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("Overlay failed to parse: %s", e);
        }
    }
}
