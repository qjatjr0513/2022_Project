package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.DocumentKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

class MemoryEagerReferenceDelegate implements ReferenceDelegate {
    private ReferenceSet inMemoryPins;
    private Set<DocumentKey> orphanedDocuments;
    private final MemoryPersistence persistence;

    MemoryEagerReferenceDelegate(MemoryPersistence persistence2) {
        this.persistence = persistence2;
    }

    public long getCurrentSequenceNumber() {
        return -1;
    }

    public void setInMemoryPins(ReferenceSet inMemoryPins2) {
        this.inMemoryPins = inMemoryPins2;
    }

    public void addReference(DocumentKey key) {
        this.orphanedDocuments.remove(key);
    }

    public void removeReference(DocumentKey key) {
        this.orphanedDocuments.add(key);
    }

    public void removeMutationReference(DocumentKey key) {
        this.orphanedDocuments.add(key);
    }

    public void removeTarget(TargetData targetData) {
        MemoryTargetCache targetCache = this.persistence.getTargetCache();
        Iterator<DocumentKey> it = targetCache.getMatchingKeysForTargetId(targetData.getTargetId()).iterator();
        while (it.hasNext()) {
            this.orphanedDocuments.add(it.next());
        }
        targetCache.removeTargetData(targetData);
    }

    public void onTransactionStarted() {
        this.orphanedDocuments = new HashSet();
    }

    public void onTransactionCommitted() {
        MemoryRemoteDocumentCache remoteDocuments = this.persistence.getRemoteDocumentCache();
        List<DocumentKey> docsToRemove = new ArrayList<>();
        for (DocumentKey key : this.orphanedDocuments) {
            if (!isReferenced(key)) {
                docsToRemove.add(key);
            }
        }
        remoteDocuments.removeAll(docsToRemove);
        this.orphanedDocuments = null;
    }

    public void updateLimboDocument(DocumentKey key) {
        if (isReferenced(key)) {
            this.orphanedDocuments.remove(key);
        } else {
            this.orphanedDocuments.add(key);
        }
    }

    private boolean mutationQueuesContainKey(DocumentKey key) {
        for (MemoryMutationQueue queue : this.persistence.getMutationQueues()) {
            if (queue.containsKey(key)) {
                return true;
            }
        }
        return false;
    }

    private boolean isReferenced(DocumentKey key) {
        if (this.persistence.getTargetCache().containsKey(key) || mutationQueuesContainKey(key)) {
            return true;
        }
        ReferenceSet referenceSet = this.inMemoryPins;
        if (referenceSet == null || !referenceSet.containsKey(key)) {
            return false;
        }
        return true;
    }
}
