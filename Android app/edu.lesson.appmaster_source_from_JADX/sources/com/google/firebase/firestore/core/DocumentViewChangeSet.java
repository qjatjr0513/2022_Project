package com.google.firebase.firestore.core;

import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DocumentViewChangeSet {
    private final TreeMap<DocumentKey, DocumentViewChange> changes = new TreeMap<>();

    public void addChange(DocumentViewChange change) {
        DocumentKey key = change.getDocument().getKey();
        DocumentViewChange old = this.changes.get(key);
        if (old == null) {
            this.changes.put(key, change);
            return;
        }
        DocumentViewChange.Type oldType = old.getType();
        DocumentViewChange.Type newType = change.getType();
        if (newType != DocumentViewChange.Type.ADDED && oldType == DocumentViewChange.Type.METADATA) {
            this.changes.put(key, change);
        } else if (newType == DocumentViewChange.Type.METADATA && oldType != DocumentViewChange.Type.REMOVED) {
            this.changes.put(key, DocumentViewChange.create(oldType, change.getDocument()));
        } else if (newType == DocumentViewChange.Type.MODIFIED && oldType == DocumentViewChange.Type.MODIFIED) {
            this.changes.put(key, DocumentViewChange.create(DocumentViewChange.Type.MODIFIED, change.getDocument()));
        } else if (newType == DocumentViewChange.Type.MODIFIED && oldType == DocumentViewChange.Type.ADDED) {
            this.changes.put(key, DocumentViewChange.create(DocumentViewChange.Type.ADDED, change.getDocument()));
        } else if (newType == DocumentViewChange.Type.REMOVED && oldType == DocumentViewChange.Type.ADDED) {
            this.changes.remove(key);
        } else if (newType == DocumentViewChange.Type.REMOVED && oldType == DocumentViewChange.Type.MODIFIED) {
            this.changes.put(key, DocumentViewChange.create(DocumentViewChange.Type.REMOVED, old.getDocument()));
        } else if (newType == DocumentViewChange.Type.ADDED && oldType == DocumentViewChange.Type.REMOVED) {
            this.changes.put(key, DocumentViewChange.create(DocumentViewChange.Type.MODIFIED, change.getDocument()));
        } else {
            throw Assert.fail("Unsupported combination of changes %s after %s", newType, oldType);
        }
    }

    /* access modifiers changed from: package-private */
    public List<DocumentViewChange> getChanges() {
        return new ArrayList(this.changes.values());
    }
}
