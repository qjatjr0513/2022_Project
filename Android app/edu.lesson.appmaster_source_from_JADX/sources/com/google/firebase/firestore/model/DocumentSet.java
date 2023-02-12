package com.google.firebase.firestore.model;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class DocumentSet implements Iterable<Document> {
    private final ImmutableSortedMap<DocumentKey, Document> keyIndex;
    private final ImmutableSortedSet<Document> sortedSet;

    public static DocumentSet emptySet(Comparator<Document> comparator) {
        return new DocumentSet(DocumentCollections.emptyDocumentMap(), new ImmutableSortedSet(Collections.emptyList(), new DocumentSet$$ExternalSyntheticLambda0(comparator)));
    }

    static /* synthetic */ int lambda$emptySet$0(Comparator comparator, Document left, Document right) {
        int comparison = comparator.compare(left, right);
        if (comparison == 0) {
            return Document.KEY_COMPARATOR.compare(left, right);
        }
        return comparison;
    }

    private DocumentSet(ImmutableSortedMap<DocumentKey, Document> keyIndex2, ImmutableSortedSet<Document> sortedSet2) {
        this.keyIndex = keyIndex2;
        this.sortedSet = sortedSet2;
    }

    public int size() {
        return this.keyIndex.size();
    }

    public boolean isEmpty() {
        return this.keyIndex.isEmpty();
    }

    public boolean contains(DocumentKey key) {
        return this.keyIndex.containsKey(key);
    }

    public Document getDocument(DocumentKey key) {
        return this.keyIndex.get(key);
    }

    public Document getFirstDocument() {
        return this.sortedSet.getMinEntry();
    }

    public Document getLastDocument() {
        return this.sortedSet.getMaxEntry();
    }

    public Document getPredecessor(DocumentKey key) {
        Document document = this.keyIndex.get(key);
        if (document != null) {
            return this.sortedSet.getPredecessorEntry(document);
        }
        throw new IllegalArgumentException("Key not contained in DocumentSet: " + key);
    }

    public int indexOf(DocumentKey key) {
        Document document = this.keyIndex.get(key);
        if (document == null) {
            return -1;
        }
        return this.sortedSet.indexOf(document);
    }

    public DocumentSet add(Document document) {
        DocumentSet removed = remove(document.getKey());
        return new DocumentSet(removed.keyIndex.insert(document.getKey(), document), removed.sortedSet.insert(document));
    }

    public DocumentSet remove(DocumentKey key) {
        Document document = this.keyIndex.get(key);
        if (document == null) {
            return this;
        }
        return new DocumentSet(this.keyIndex.remove(key), this.sortedSet.remove(document));
    }

    public List<Document> toList() {
        List<Document> documents = new ArrayList<>(size());
        Iterator<Document> it = iterator();
        while (it.hasNext()) {
            documents.add(it.next());
        }
        return documents;
    }

    public Iterator<Document> iterator() {
        return this.sortedSet.iterator();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        DocumentSet documentSet = (DocumentSet) other;
        if (size() != documentSet.size()) {
            return false;
        }
        Iterator<Document> thisIterator = iterator();
        Iterator<Document> otherIterator = documentSet.iterator();
        while (thisIterator.hasNext()) {
            if (!thisIterator.next().equals(otherIterator.next())) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int result = 0;
        Iterator<Document> it = iterator();
        while (it.hasNext()) {
            Document document = it.next();
            result = (((result * 31) + document.getKey().hashCode()) * 31) + document.getData().hashCode();
        }
        return result;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        boolean first = true;
        Iterator<Document> it = iterator();
        while (it.hasNext()) {
            Document doc = it.next();
            if (first) {
                first = false;
            } else {
                builder.append(", ");
            }
            builder.append(doc);
        }
        builder.append("]");
        return builder.toString();
    }
}
