package com.google.firebase.firestore.local;

import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.FieldMask;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.model.mutation.Overlay;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.util.Assert;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

class LocalDocumentsView {
    private final DocumentOverlayCache documentOverlayCache;
    private final IndexManager indexManager;
    private final MutationQueue mutationQueue;
    private final RemoteDocumentCache remoteDocumentCache;

    LocalDocumentsView(RemoteDocumentCache remoteDocumentCache2, MutationQueue mutationQueue2, DocumentOverlayCache documentOverlayCache2, IndexManager indexManager2) {
        this.remoteDocumentCache = remoteDocumentCache2;
        this.mutationQueue = mutationQueue2;
        this.documentOverlayCache = documentOverlayCache2;
        this.indexManager = indexManager2;
    }

    /* access modifiers changed from: package-private */
    public RemoteDocumentCache getRemoteDocumentCache() {
        return this.remoteDocumentCache;
    }

    /* access modifiers changed from: package-private */
    public MutationQueue getMutationQueue() {
        return this.mutationQueue;
    }

    /* access modifiers changed from: package-private */
    public DocumentOverlayCache getDocumentOverlayCache() {
        return this.documentOverlayCache;
    }

    /* access modifiers changed from: package-private */
    public Document getDocument(DocumentKey key) {
        Overlay overlay = this.documentOverlayCache.getOverlay(key);
        MutableDocument document = getBaseDocument(key, overlay);
        if (overlay != null) {
            overlay.getMutation().applyToLocalView(document, (FieldMask) null, Timestamp.now());
        }
        return document;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedMap<DocumentKey, Document> getDocuments(Iterable<DocumentKey> keys) {
        return getLocalViewOfDocuments(this.remoteDocumentCache.getAll(keys), new HashSet());
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedMap<DocumentKey, Document> getLocalViewOfDocuments(Map<DocumentKey, MutableDocument> docs, Set<DocumentKey> existenceStateChanged) {
        Map<DocumentKey, Overlay> overlays = new HashMap<>();
        populateOverlays(overlays, docs.keySet());
        return computeViews(docs, overlays, existenceStateChanged);
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedMap<DocumentKey, Document> getLocalViewOfDocuments(Map<DocumentKey, MutableDocument> docs) {
        Map<DocumentKey, Overlay> overlays = new HashMap<>();
        populateOverlays(overlays, docs.keySet());
        return computeViews(docs, overlays, new HashSet());
    }

    private ImmutableSortedMap<DocumentKey, Document> computeViews(Map<DocumentKey, MutableDocument> docs, Map<DocumentKey, Overlay> overlays, Set<DocumentKey> existenceStateChanged) {
        ImmutableSortedMap<DocumentKey, Document> results = DocumentCollections.emptyDocumentMap();
        Map<DocumentKey, MutableDocument> recalculateDocuments = new HashMap<>();
        for (MutableDocument doc : docs.values()) {
            Overlay overlay = overlays.get(doc.getKey());
            if (existenceStateChanged.contains(doc.getKey()) && (overlay == null || (overlay.getMutation() instanceof PatchMutation))) {
                recalculateDocuments.put(doc.getKey(), doc);
            } else if (overlay != null) {
                overlay.getMutation().applyToLocalView(doc, (FieldMask) null, Timestamp.now());
            }
        }
        recalculateAndSaveOverlays(recalculateDocuments);
        for (Map.Entry<DocumentKey, MutableDocument> entry : docs.entrySet()) {
            results = results.insert(entry.getKey(), entry.getValue());
        }
        return results;
    }

    private void recalculateAndSaveOverlays(Map<DocumentKey, MutableDocument> docs) {
        List<MutationBatch> batches = this.mutationQueue.getAllMutationBatchesAffectingDocumentKeys(docs.keySet());
        Map<DocumentKey, FieldMask> masks = new HashMap<>();
        TreeMap<Integer, Set<DocumentKey>> documentsByBatchId = new TreeMap<>();
        for (MutationBatch batch : batches) {
            for (DocumentKey key : batch.getKeys()) {
                MutableDocument baseDoc = docs.get(key);
                if (baseDoc != null) {
                    masks.put(key, batch.applyToLocalView(baseDoc, masks.containsKey(key) ? masks.get(key) : FieldMask.EMPTY));
                    int batchId = batch.getBatchId();
                    if (!documentsByBatchId.containsKey(Integer.valueOf(batchId))) {
                        documentsByBatchId.put(Integer.valueOf(batchId), new HashSet());
                    }
                    documentsByBatchId.get(Integer.valueOf(batchId)).add(key);
                }
            }
        }
        Set<DocumentKey> processed = new HashSet<>();
        for (Map.Entry<Integer, Set<DocumentKey>> entry : documentsByBatchId.descendingMap().entrySet()) {
            Map<DocumentKey, Mutation> overlays = new HashMap<>();
            for (DocumentKey key2 : entry.getValue()) {
                if (!processed.contains(key2)) {
                    Mutation mutation = Mutation.calculateOverlayMutation(docs.get(key2), masks.get(key2));
                    if (mutation != null) {
                        overlays.put(key2, mutation);
                    }
                    processed.add(key2);
                }
            }
            this.documentOverlayCache.saveOverlays(entry.getKey().intValue(), overlays);
        }
    }

    /* access modifiers changed from: package-private */
    public void recalculateAndSaveOverlays(Set<DocumentKey> documentKeys) {
        recalculateAndSaveOverlays(this.remoteDocumentCache.getAll(documentKeys));
    }

    /* access modifiers changed from: package-private */
    public ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingQuery(Query query, FieldIndex.IndexOffset offset) {
        ResourcePath path = query.getPath();
        if (query.isDocumentQuery()) {
            return getDocumentsMatchingDocumentQuery(path);
        }
        if (query.isCollectionGroupQuery()) {
            return getDocumentsMatchingCollectionGroupQuery(query, offset);
        }
        return getDocumentsMatchingCollectionQuery(query, offset);
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingDocumentQuery(ResourcePath path) {
        ImmutableSortedMap<DocumentKey, Document> result = DocumentCollections.emptyDocumentMap();
        Document doc = getDocument(DocumentKey.fromPath(path));
        if (doc.isFoundDocument()) {
            return result.insert(doc.getKey(), doc);
        }
        return result;
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingCollectionGroupQuery(Query query, FieldIndex.IndexOffset offset) {
        Assert.hardAssert(query.getPath().isEmpty(), "Currently we only support collection group queries at the root.", new Object[0]);
        String collectionId = query.getCollectionGroup();
        ImmutableSortedMap<DocumentKey, Document> results = DocumentCollections.emptyDocumentMap();
        for (ResourcePath parent : this.indexManager.getCollectionParents(collectionId)) {
            Iterator<Map.Entry<DocumentKey, Document>> it = getDocumentsMatchingCollectionQuery(query.asCollectionQueryAtPath((ResourcePath) parent.append(collectionId)), offset).iterator();
            while (it.hasNext()) {
                Map.Entry<DocumentKey, Document> docEntry = it.next();
                results = results.insert(docEntry.getKey(), docEntry.getValue());
            }
        }
        return results;
    }

    /* access modifiers changed from: package-private */
    public LocalDocumentsResult getNextDocuments(String collectionGroup, FieldIndex.IndexOffset offset, int count) {
        Map<DocumentKey, Overlay> overlays;
        Map<DocumentKey, MutableDocument> docs = this.remoteDocumentCache.getAll(collectionGroup, offset, count);
        if (count - docs.size() > 0) {
            overlays = this.documentOverlayCache.getOverlays(collectionGroup, offset.getLargestBatchId(), count - docs.size());
        } else {
            overlays = Collections.emptyMap();
        }
        int largestBatchId = -1;
        for (Overlay overlay : overlays.values()) {
            if (!docs.containsKey(overlay.getKey())) {
                docs.put(overlay.getKey(), getBaseDocument(overlay.getKey(), overlay));
            }
            largestBatchId = Math.max(largestBatchId, overlay.getLargestBatchId());
        }
        populateOverlays(overlays, docs.keySet());
        return new LocalDocumentsResult(largestBatchId, computeViews(docs, overlays, Collections.emptySet()));
    }

    private void populateOverlays(Map<DocumentKey, Overlay> overlays, Set<DocumentKey> keys) {
        SortedSet<DocumentKey> missingOverlays = new TreeSet<>();
        for (DocumentKey key : keys) {
            if (!overlays.containsKey(key)) {
                missingOverlays.add(key);
            }
        }
        overlays.putAll(this.documentOverlayCache.getOverlays(missingOverlays));
    }

    private ImmutableSortedMap<DocumentKey, Document> getDocumentsMatchingCollectionQuery(Query query, FieldIndex.IndexOffset offset) {
        Map<DocumentKey, MutableDocument> remoteDocuments = this.remoteDocumentCache.getAll(query.getPath(), offset);
        Map<DocumentKey, Overlay> overlays = this.documentOverlayCache.getOverlays(query.getPath(), offset.getLargestBatchId());
        for (Map.Entry<DocumentKey, Overlay> entry : overlays.entrySet()) {
            if (!remoteDocuments.containsKey(entry.getKey())) {
                remoteDocuments.put(entry.getKey(), MutableDocument.newInvalidDocument(entry.getKey()));
            }
        }
        ImmutableSortedMap<DocumentKey, Document> results = DocumentCollections.emptyDocumentMap();
        for (Map.Entry<DocumentKey, MutableDocument> docEntry : remoteDocuments.entrySet()) {
            Overlay overlay = overlays.get(docEntry.getKey());
            if (overlay != null) {
                overlay.getMutation().applyToLocalView(docEntry.getValue(), (FieldMask) null, Timestamp.now());
            }
            if (query.matches(docEntry.getValue())) {
                results = results.insert(docEntry.getKey(), docEntry.getValue());
            }
        }
        return results;
    }

    private MutableDocument getBaseDocument(DocumentKey key, Overlay overlay) {
        if (overlay == null || (overlay.getMutation() instanceof PatchMutation)) {
            return this.remoteDocumentCache.get(key);
        }
        return MutableDocument.newInvalidDocument(key);
    }
}
