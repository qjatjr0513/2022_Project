package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Util;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;

public class QueryEngine {
    private static final String LOG_TAG = "QueryEngine";
    private IndexManager indexManager;
    private boolean initialized;
    private LocalDocumentsView localDocumentsView;

    public void initialize(LocalDocumentsView localDocumentsView2, IndexManager indexManager2) {
        this.localDocumentsView = localDocumentsView2;
        this.indexManager = indexManager2;
        this.initialized = true;
    }

    public ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(Query query, SnapshotVersion lastLimboFreeSnapshotVersion, ImmutableSortedSet<DocumentKey> remoteKeys) {
        Assert.hardAssert(this.initialized, "initialize() not called", new Object[0]);
        ImmutableSortedMap<DocumentKey, Document> result = performQueryUsingIndex(query, query.toTarget());
        if (result != null) {
            return result;
        }
        ImmutableSortedMap<DocumentKey, Document> result2 = performQueryUsingRemoteKeys(query, remoteKeys, lastLimboFreeSnapshotVersion);
        if (result2 != null) {
            return result2;
        }
        return executeFullCollectionScan(query);
    }

    @Nullable
    private ImmutableSortedMap<DocumentKey, Document> performQueryUsingIndex(Query query, Target target) {
        List<DocumentKey> keys;
        if (query.matchesAllDocuments() || (keys = this.indexManager.getDocumentsMatchingTarget(target)) == null) {
            return null;
        }
        ImmutableSortedMap<DocumentKey, Document> indexedDocuments = this.localDocumentsView.getDocuments(keys);
        FieldIndex.IndexOffset offset = this.indexManager.getMinOffset(target);
        ImmutableSortedSet<Document> previousResults = applyQuery(query, indexedDocuments);
        if ((query.hasLimitToFirst() || query.hasLimitToLast()) && needsRefill(query.getLimitType(), keys.size(), previousResults, offset.getReadTime())) {
            return null;
        }
        return appendRemainingResults(Util.values(indexedDocuments), query, offset);
    }

    @Nullable
    private ImmutableSortedMap<DocumentKey, Document> performQueryUsingRemoteKeys(Query query, ImmutableSortedSet<DocumentKey> remoteKeys, SnapshotVersion lastLimboFreeSnapshotVersion) {
        if (query.matchesAllDocuments() || lastLimboFreeSnapshotVersion.equals(SnapshotVersion.NONE)) {
            return null;
        }
        ImmutableSortedSet<Document> previousResults = applyQuery(query, this.localDocumentsView.getDocuments(remoteKeys));
        if ((query.hasLimitToFirst() || query.hasLimitToLast()) && needsRefill(query.getLimitType(), remoteKeys.size(), previousResults, lastLimboFreeSnapshotVersion)) {
            return null;
        }
        if (Logger.isDebugEnabled()) {
            Logger.debug(LOG_TAG, "Re-using previous result from %s to execute query: %s", lastLimboFreeSnapshotVersion.toString(), query.toString());
        }
        return appendRemainingResults(previousResults, query, FieldIndex.IndexOffset.createSuccessor(lastLimboFreeSnapshotVersion, -1));
    }

    private ImmutableSortedSet<Document> applyQuery(Query query, ImmutableSortedMap<DocumentKey, Document> documents) {
        ImmutableSortedSet<Document> queryResults = new ImmutableSortedSet<>(Collections.emptyList(), query.comparator());
        Iterator<Map.Entry<DocumentKey, Document>> it = documents.iterator();
        while (it.hasNext()) {
            Document document = it.next().getValue();
            if (query.matches(document)) {
                queryResults = queryResults.insert(document);
            }
        }
        return queryResults;
    }

    private boolean needsRefill(Query.LimitType limitType, int expectedDocumentCount, ImmutableSortedSet<Document> sortedPreviousResults, SnapshotVersion limboFreeSnapshotVersion) {
        Document documentAtLimitEdge;
        if (expectedDocumentCount != sortedPreviousResults.size()) {
            return true;
        }
        if (limitType == Query.LimitType.LIMIT_TO_FIRST) {
            documentAtLimitEdge = sortedPreviousResults.getMaxEntry();
        } else {
            documentAtLimitEdge = sortedPreviousResults.getMinEntry();
        }
        if (documentAtLimitEdge == null) {
            return false;
        }
        if (documentAtLimitEdge.hasPendingWrites() || documentAtLimitEdge.getVersion().compareTo(limboFreeSnapshotVersion) > 0) {
            return true;
        }
        return false;
    }

    private ImmutableSortedMap<DocumentKey, Document> executeFullCollectionScan(Query query) {
        if (Logger.isDebugEnabled()) {
            Logger.debug(LOG_TAG, "Using full collection scan to execute query: %s", query.toString());
        }
        return this.localDocumentsView.getDocumentsMatchingQuery(query, FieldIndex.IndexOffset.NONE);
    }

    private ImmutableSortedMap<DocumentKey, Document> appendRemainingResults(Iterable<Document> indexedResults, Query query, FieldIndex.IndexOffset offset) {
        ImmutableSortedMap<DocumentKey, Document> remainingResults = this.localDocumentsView.getDocumentsMatchingQuery(query, offset);
        for (Document entry : indexedResults) {
            remainingResults = remainingResults.insert(entry.getKey(), entry);
        }
        return remainingResults;
    }
}
