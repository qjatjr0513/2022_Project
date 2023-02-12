package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.model.DocumentKey;
import java.util.Collections;
import java.util.Iterator;

public class ReferenceSet {
    private ImmutableSortedSet<DocumentReference> referencesByKey = new ImmutableSortedSet<>(Collections.emptyList(), DocumentReference.BY_KEY);
    private ImmutableSortedSet<DocumentReference> referencesByTarget = new ImmutableSortedSet<>(Collections.emptyList(), DocumentReference.BY_TARGET);

    public boolean isEmpty() {
        return this.referencesByKey.isEmpty();
    }

    public void addReference(DocumentKey key, int targetOrBatchId) {
        DocumentReference ref = new DocumentReference(key, targetOrBatchId);
        this.referencesByKey = this.referencesByKey.insert(ref);
        this.referencesByTarget = this.referencesByTarget.insert(ref);
    }

    public void addReferences(ImmutableSortedSet<DocumentKey> keys, int targetOrBatchId) {
        Iterator<DocumentKey> it = keys.iterator();
        while (it.hasNext()) {
            addReference(it.next(), targetOrBatchId);
        }
    }

    public void removeReference(DocumentKey key, int targetOrBatchId) {
        removeReference(new DocumentReference(key, targetOrBatchId));
    }

    public void removeReferences(ImmutableSortedSet<DocumentKey> keys, int targetOrBatchId) {
        Iterator<DocumentKey> it = keys.iterator();
        while (it.hasNext()) {
            removeReference(it.next(), targetOrBatchId);
        }
    }

    public ImmutableSortedSet<DocumentKey> removeReferencesForId(int targetId) {
        Iterator<DocumentReference> it = this.referencesByTarget.iteratorFrom(new DocumentReference(DocumentKey.empty(), targetId));
        ImmutableSortedSet<DocumentKey> keys = DocumentKey.emptyKeySet();
        while (it.hasNext()) {
            DocumentReference ref = it.next();
            if (ref.getId() != targetId) {
                break;
            }
            keys = keys.insert(ref.getKey());
            removeReference(ref);
        }
        return keys;
    }

    public void removeAllReferences() {
        Iterator<DocumentReference> it = this.referencesByKey.iterator();
        while (it.hasNext()) {
            removeReference(it.next());
        }
    }

    private void removeReference(DocumentReference ref) {
        this.referencesByKey = this.referencesByKey.remove(ref);
        this.referencesByTarget = this.referencesByTarget.remove(ref);
    }

    public ImmutableSortedSet<DocumentKey> referencesForId(int target) {
        Iterator<DocumentReference> iterator = this.referencesByTarget.iteratorFrom(new DocumentReference(DocumentKey.empty(), target));
        ImmutableSortedSet<DocumentKey> keys = DocumentKey.emptyKeySet();
        while (iterator.hasNext()) {
            DocumentReference reference = iterator.next();
            if (reference.getId() != target) {
                break;
            }
            keys = keys.insert(reference.getKey());
        }
        return keys;
    }

    public boolean containsKey(DocumentKey key) {
        Iterator<DocumentReference> iterator = this.referencesByKey.iteratorFrom(new DocumentReference(key, 0));
        if (!iterator.hasNext()) {
            return false;
        }
        return iterator.next().getKey().equals(key);
    }
}
