package com.google.firebase.firestore.bundle;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.LoadBundleTaskProgress;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.util.Preconditions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BundleLoader {
    private final BundleCallback bundleCallback;
    private final BundleMetadata bundleMetadata;
    private long bytesLoaded;
    private BundledDocumentMetadata currentMetadata;
    private ImmutableSortedMap<DocumentKey, MutableDocument> documents = DocumentCollections.emptyMutableDocumentMap();
    private final Map<DocumentKey, BundledDocumentMetadata> documentsMetadata = new HashMap();
    private final List<NamedQuery> queries = new ArrayList();

    public BundleLoader(BundleCallback bundleCallback2, BundleMetadata bundleMetadata2) {
        this.bundleCallback = bundleCallback2;
        this.bundleMetadata = bundleMetadata2;
    }

    public LoadBundleTaskProgress addElement(BundleElement bundleElement, long byteSize) {
        Preconditions.checkArgument(!(bundleElement instanceof BundleMetadata), "Unexpected bundle metadata element.", new Object[0]);
        int beforeDocumentCount = this.documents.size();
        if (bundleElement instanceof NamedQuery) {
            this.queries.add((NamedQuery) bundleElement);
        } else if (bundleElement instanceof BundledDocumentMetadata) {
            BundledDocumentMetadata bundledDocumentMetadata = (BundledDocumentMetadata) bundleElement;
            this.documentsMetadata.put(bundledDocumentMetadata.getKey(), bundledDocumentMetadata);
            this.currentMetadata = bundledDocumentMetadata;
            if (!((BundledDocumentMetadata) bundleElement).exists()) {
                this.documents = this.documents.insert(bundledDocumentMetadata.getKey(), MutableDocument.newNoDocument(bundledDocumentMetadata.getKey(), bundledDocumentMetadata.getReadTime()).setReadTime(bundledDocumentMetadata.getReadTime()));
                this.currentMetadata = null;
            }
        } else if (bundleElement instanceof BundleDocument) {
            BundleDocument bundleDocument = (BundleDocument) bundleElement;
            if (this.currentMetadata == null || !bundleDocument.getKey().equals(this.currentMetadata.getKey())) {
                throw new IllegalArgumentException("The document being added does not match the stored metadata.");
            }
            this.documents = this.documents.insert(bundleDocument.getKey(), bundleDocument.getDocument().setReadTime(this.currentMetadata.getReadTime()));
            this.currentMetadata = null;
        }
        this.bytesLoaded += byteSize;
        if (beforeDocumentCount != this.documents.size()) {
            return new LoadBundleTaskProgress(this.documents.size(), this.bundleMetadata.getTotalDocuments(), this.bytesLoaded, this.bundleMetadata.getTotalBytes(), (Exception) null, LoadBundleTaskProgress.TaskState.RUNNING);
        }
        return null;
    }

    public ImmutableSortedMap<DocumentKey, Document> applyChanges() {
        Preconditions.checkArgument(this.currentMetadata == null, "Bundled documents end with a document metadata element instead of a document.", new Object[0]);
        Preconditions.checkArgument(this.bundleMetadata.getBundleId() != null, "Bundle ID must be set", new Object[0]);
        Preconditions.checkArgument(this.documents.size() == this.bundleMetadata.getTotalDocuments(), "Expected %s documents, but loaded %s.", Integer.valueOf(this.bundleMetadata.getTotalDocuments()), Integer.valueOf(this.documents.size()));
        ImmutableSortedMap<DocumentKey, Document> changes = this.bundleCallback.applyBundledDocuments(this.documents, this.bundleMetadata.getBundleId());
        Map<String, ImmutableSortedSet<DocumentKey>> queryDocumentMap = getQueryDocumentMapping();
        for (NamedQuery namedQuery : this.queries) {
            this.bundleCallback.saveNamedQuery(namedQuery, queryDocumentMap.get(namedQuery.getName()));
        }
        this.bundleCallback.saveBundle(this.bundleMetadata);
        return changes;
    }

    private Map<String, ImmutableSortedSet<DocumentKey>> getQueryDocumentMapping() {
        Map<String, ImmutableSortedSet<DocumentKey>> queryDocumentMap = new HashMap<>();
        for (NamedQuery namedQuery : this.queries) {
            queryDocumentMap.put(namedQuery.getName(), DocumentKey.emptyKeySet());
        }
        for (BundledDocumentMetadata metadata : this.documentsMetadata.values()) {
            for (String query : metadata.getQueries()) {
                queryDocumentMap.put(query, queryDocumentMap.get(query).insert(metadata.getKey()));
            }
        }
        return queryDocumentMap;
    }
}
