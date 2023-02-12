package com.google.firebase.firestore.local;

import android.database.Cursor;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.proto.MaybeDocument;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.BackgroundQueue;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class SQLiteRemoteDocumentCache implements RemoteDocumentCache {
    static final int BINDS_PER_STATEMENT = 9;

    /* renamed from: db */
    private final SQLitePersistence f169db;
    private IndexManager indexManager;
    private final LocalSerializer serializer;

    SQLiteRemoteDocumentCache(SQLitePersistence persistence, LocalSerializer serializer2) {
        this.f169db = persistence;
        this.serializer = serializer2;
    }

    public void setIndexManager(IndexManager indexManager2) {
        this.indexManager = indexManager2;
    }

    public void add(MutableDocument document, SnapshotVersion readTime) {
        Assert.hardAssert(!readTime.equals(SnapshotVersion.NONE), "Cannot add document to the RemoteDocumentCache with a read time of zero", new Object[0]);
        DocumentKey documentKey = document.getKey();
        Timestamp timestamp = readTime.getTimestamp();
        MessageLite message = this.serializer.encodeMaybeDocument(document);
        this.f169db.execute("INSERT OR REPLACE INTO remote_documents (path, path_length, read_time_seconds, read_time_nanos, contents) VALUES (?, ?, ?, ?, ?)", EncodedPath.encode(documentKey.getPath()), Integer.valueOf(documentKey.getPath().length()), Long.valueOf(timestamp.getSeconds()), Integer.valueOf(timestamp.getNanoseconds()), message.toByteArray());
        this.indexManager.addToCollectionParentIndex(document.getKey().getCollectionPath());
    }

    public void removeAll(Collection<DocumentKey> keys) {
        if (!keys.isEmpty()) {
            List<Object> encodedPaths = new ArrayList<>();
            ImmutableSortedMap<DocumentKey, Document> deletedDocs = DocumentCollections.emptyDocumentMap();
            for (DocumentKey key : keys) {
                encodedPaths.add(EncodedPath.encode(key.getPath()));
                deletedDocs = deletedDocs.insert(key, MutableDocument.newNoDocument(key, SnapshotVersion.NONE));
            }
            SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.f169db, "DELETE FROM remote_documents WHERE path IN (", encodedPaths, ")");
            while (longQuery.hasMoreSubqueries()) {
                longQuery.executeNextSubquery();
            }
            this.indexManager.updateIndexEntries(deletedDocs);
        }
    }

    public MutableDocument get(DocumentKey documentKey) {
        return getAll(Collections.singletonList(documentKey)).get(documentKey);
    }

    public Map<DocumentKey, MutableDocument> getAll(Iterable<DocumentKey> documentKeys) {
        Map<DocumentKey, MutableDocument> results = new HashMap<>();
        List<Object> bindVars = new ArrayList<>();
        for (DocumentKey key : documentKeys) {
            bindVars.add(EncodedPath.encode(key.getPath()));
            results.put(key, MutableDocument.newInvalidDocument(key));
        }
        SQLitePersistence.LongQuery longQuery = new SQLitePersistence.LongQuery(this.f169db, "SELECT contents, read_time_seconds, read_time_nanos FROM remote_documents WHERE path IN (", bindVars, ") ORDER BY path");
        BackgroundQueue backgroundQueue = new BackgroundQueue();
        while (longQuery.hasMoreSubqueries()) {
            longQuery.performNextSubquery().forEach(new SQLiteRemoteDocumentCache$$ExternalSyntheticLambda0(this, backgroundQueue, results));
        }
        backgroundQueue.drain();
        return results;
    }

    public Map<DocumentKey, MutableDocument> getAll(String collectionGroup, FieldIndex.IndexOffset offset, int limit) {
        List<ResourcePath> collectionParents = this.indexManager.getCollectionParents(collectionGroup);
        List<ResourcePath> collections = new ArrayList<>(collectionParents.size());
        for (ResourcePath collectionParent : collectionParents) {
            collections.add((ResourcePath) collectionParent.append(collectionGroup));
        }
        if (collections.isEmpty()) {
            return Collections.emptyMap();
        }
        if (collections.size() * 9 < 900) {
            return getAll(collections, offset, limit);
        }
        Map<DocumentKey, MutableDocument> results = new HashMap<>();
        for (int i = 0; i < collections.size(); i += 100) {
            results.putAll(getAll(collections.subList(i, Math.min(collections.size(), i + 100)), offset, limit));
        }
        return Util.firstNEntries(results, limit, FieldIndex.IndexOffset.DOCUMENT_COMPARATOR);
    }

    private Map<DocumentKey, MutableDocument> getAll(List<ResourcePath> collections, FieldIndex.IndexOffset offset, int count) {
        Timestamp readTime = offset.getReadTime().getTimestamp();
        DocumentKey documentKey = offset.getDocumentKey();
        StringBuilder sql = Util.repeatSequence("SELECT contents, read_time_seconds, read_time_nanos, path FROM remote_documents WHERE path >= ? AND path < ? AND path_length = ? AND (read_time_seconds > ? OR ( read_time_seconds = ? AND read_time_nanos > ?) OR ( read_time_seconds = ? AND read_time_nanos = ? and path > ?)) ", collections.size(), " UNION ");
        sql.append("ORDER BY read_time_seconds, read_time_nanos, path LIMIT ?");
        Object[] bindVars = new Object[((collections.size() * 9) + 1)];
        int i = 0;
        for (ResourcePath collection : collections) {
            String prefixPath = EncodedPath.encode(collection);
            int i2 = i + 1;
            bindVars[i] = prefixPath;
            int i3 = i2 + 1;
            bindVars[i2] = EncodedPath.prefixSuccessor(prefixPath);
            int i4 = i3 + 1;
            bindVars[i3] = Integer.valueOf(collection.length() + 1);
            int i5 = i4 + 1;
            bindVars[i4] = Long.valueOf(readTime.getSeconds());
            int i6 = i5 + 1;
            bindVars[i5] = Long.valueOf(readTime.getSeconds());
            int i7 = i6 + 1;
            bindVars[i6] = Integer.valueOf(readTime.getNanoseconds());
            int i8 = i7 + 1;
            bindVars[i7] = Long.valueOf(readTime.getSeconds());
            int i9 = i8 + 1;
            bindVars[i8] = Integer.valueOf(readTime.getNanoseconds());
            bindVars[i9] = EncodedPath.encode(documentKey.getPath());
            i = i9 + 1;
        }
        bindVars[i] = Integer.valueOf(count);
        BackgroundQueue backgroundQueue = new BackgroundQueue();
        Map<DocumentKey, MutableDocument> results = new HashMap<>();
        this.f169db.query(sql.toString()).binding(bindVars).forEach(new SQLiteRemoteDocumentCache$$ExternalSyntheticLambda1(this, backgroundQueue, results));
        backgroundQueue.drain();
        return results;
    }

    /* access modifiers changed from: private */
    /* renamed from: processRowInBackground */
    public void mo9353xbda45604(BackgroundQueue backgroundQueue, Map<DocumentKey, MutableDocument> results, Cursor row) {
        (row.isLast() ? Executors.DIRECT_EXECUTOR : backgroundQueue).execute(new SQLiteRemoteDocumentCache$$ExternalSyntheticLambda2(this, row.getBlob(0), row.getInt(1), row.getInt(2), results));
    }

    /* renamed from: lambda$processRowInBackground$2$com-google-firebase-firestore-local-SQLiteRemoteDocumentCache */
    public /* synthetic */ void mo9354x16ef698(byte[] rawDocument, int readTimeSeconds, int readTimeNanos, Map results) {
        MutableDocument document = decodeMaybeDocument(rawDocument, readTimeSeconds, readTimeNanos);
        synchronized (results) {
            results.put(document.getKey(), document);
        }
    }

    public Map<DocumentKey, MutableDocument> getAll(ResourcePath collection, FieldIndex.IndexOffset offset) {
        return getAll((List<ResourcePath>) Collections.singletonList(collection), offset, Integer.MAX_VALUE);
    }

    private MutableDocument decodeMaybeDocument(byte[] bytes, int readTimeSeconds, int readTimeNanos) {
        try {
            return this.serializer.decodeMaybeDocument(MaybeDocument.parseFrom(bytes)).setReadTime(new SnapshotVersion(new Timestamp((long) readTimeSeconds, readTimeNanos)));
        } catch (InvalidProtocolBufferException e) {
            throw Assert.fail("MaybeDocument failed to parse: %s", e);
        }
    }
}
