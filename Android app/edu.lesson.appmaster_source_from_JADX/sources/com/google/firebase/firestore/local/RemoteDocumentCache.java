package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import java.util.Collection;
import java.util.Map;

interface RemoteDocumentCache {
    void add(MutableDocument mutableDocument, SnapshotVersion snapshotVersion);

    MutableDocument get(DocumentKey documentKey);

    Map<DocumentKey, MutableDocument> getAll(ResourcePath resourcePath, FieldIndex.IndexOffset indexOffset);

    Map<DocumentKey, MutableDocument> getAll(Iterable<DocumentKey> iterable);

    Map<DocumentKey, MutableDocument> getAll(String str, FieldIndex.IndexOffset indexOffset, int i);

    void removeAll(Collection<DocumentKey> collection);

    void setIndexManager(IndexManager indexManager);
}
