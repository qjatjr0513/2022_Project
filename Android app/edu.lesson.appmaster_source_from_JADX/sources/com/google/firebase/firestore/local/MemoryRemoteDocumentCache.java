package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

final class MemoryRemoteDocumentCache implements RemoteDocumentCache {
    /* access modifiers changed from: private */
    public ImmutableSortedMap<DocumentKey, Document> docs = DocumentCollections.emptyDocumentMap();
    private IndexManager indexManager;

    MemoryRemoteDocumentCache() {
    }

    public void setIndexManager(IndexManager indexManager2) {
        this.indexManager = indexManager2;
    }

    public void add(MutableDocument document, SnapshotVersion readTime) {
        Assert.hardAssert(this.indexManager != null, "setIndexManager() not called", new Object[0]);
        Assert.hardAssert(!readTime.equals(SnapshotVersion.NONE), "Cannot add document to the RemoteDocumentCache with a read time of zero", new Object[0]);
        this.docs = this.docs.insert(document.getKey(), document.mutableCopy().setReadTime(readTime));
        this.indexManager.addToCollectionParentIndex(document.getKey().getCollectionPath());
    }

    public void removeAll(Collection<DocumentKey> keys) {
        Assert.hardAssert(this.indexManager != null, "setIndexManager() not called", new Object[0]);
        ImmutableSortedMap<DocumentKey, Document> deletedDocs = DocumentCollections.emptyDocumentMap();
        for (DocumentKey key : keys) {
            this.docs = this.docs.remove(key);
            deletedDocs = deletedDocs.insert(key, MutableDocument.newNoDocument(key, SnapshotVersion.NONE));
        }
        this.indexManager.updateIndexEntries(deletedDocs);
    }

    public MutableDocument get(DocumentKey key) {
        Document doc = this.docs.get(key);
        return doc != null ? doc.mutableCopy() : MutableDocument.newInvalidDocument(key);
    }

    public Map<DocumentKey, MutableDocument> getAll(Iterable<DocumentKey> keys) {
        Map<DocumentKey, MutableDocument> result = new HashMap<>();
        for (DocumentKey key : keys) {
            result.put(key, get(key));
        }
        return result;
    }

    public Map<DocumentKey, MutableDocument> getAll(String collectionGroup, FieldIndex.IndexOffset offset, int limit) {
        throw new UnsupportedOperationException("getAll(String, IndexOffset, int) is not supported.");
    }

    public Map<DocumentKey, MutableDocument> getAll(ResourcePath collection, FieldIndex.IndexOffset offset) {
        Map<DocumentKey, MutableDocument> result = new HashMap<>();
        Iterator<Map.Entry<DocumentKey, Document>> iterator = this.docs.iteratorFrom(DocumentKey.fromPath((ResourcePath) collection.append("")));
        while (iterator.hasNext()) {
            Map.Entry<DocumentKey, Document> entry = iterator.next();
            Document doc = entry.getValue();
            DocumentKey key = entry.getKey();
            if (!collection.isPrefixOf(key.getPath())) {
                break;
            } else if (key.getPath().length() <= collection.length() + 1 && FieldIndex.IndexOffset.fromDocument(doc).compareTo(offset) > 0) {
                result.put(doc.getKey(), doc.mutableCopy());
            }
        }
        return result;
    }

    /* access modifiers changed from: package-private */
    public Iterable<Document> getDocuments() {
        return new DocumentIterable();
    }

    /* access modifiers changed from: package-private */
    public long getByteSize(LocalSerializer serializer) {
        long count = 0;
        Iterator<Document> it = new DocumentIterable().iterator();
        while (it.hasNext()) {
            count += (long) serializer.encodeMaybeDocument(it.next()).getSerializedSize();
        }
        return count;
    }

    private class DocumentIterable implements Iterable<Document> {
        private DocumentIterable() {
        }

        public Iterator<Document> iterator() {
            final Iterator<Map.Entry<DocumentKey, Document>> iterator = MemoryRemoteDocumentCache.this.docs.iterator();
            return new Iterator<Document>() {
                public boolean hasNext() {
                    return iterator.hasNext();
                }

                public Document next() {
                    return (Document) ((Map.Entry) iterator.next()).getValue();
                }
            };
        }
    }
}
