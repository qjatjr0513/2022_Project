package com.google.firebase.firestore.core;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.DocumentSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ViewSnapshot {
    private final List<DocumentViewChange> changes;
    private final boolean didSyncStateChange;
    private final DocumentSet documents;
    private boolean excludesMetadataChanges;
    private final boolean isFromCache;
    private final ImmutableSortedSet<DocumentKey> mutatedKeys;
    private final DocumentSet oldDocuments;
    private final Query query;

    public enum SyncState {
        NONE,
        LOCAL,
        SYNCED
    }

    public ViewSnapshot(Query query2, DocumentSet documents2, DocumentSet oldDocuments2, List<DocumentViewChange> changes2, boolean isFromCache2, ImmutableSortedSet<DocumentKey> mutatedKeys2, boolean didSyncStateChange2, boolean excludesMetadataChanges2) {
        this.query = query2;
        this.documents = documents2;
        this.oldDocuments = oldDocuments2;
        this.changes = changes2;
        this.isFromCache = isFromCache2;
        this.mutatedKeys = mutatedKeys2;
        this.didSyncStateChange = didSyncStateChange2;
        this.excludesMetadataChanges = excludesMetadataChanges2;
    }

    public static ViewSnapshot fromInitialDocuments(Query query2, DocumentSet documents2, ImmutableSortedSet<DocumentKey> mutatedKeys2, boolean fromCache, boolean excludesMetadataChanges2) {
        List<DocumentViewChange> viewChanges = new ArrayList<>();
        Iterator<Document> it = documents2.iterator();
        while (it.hasNext()) {
            viewChanges.add(DocumentViewChange.create(DocumentViewChange.Type.ADDED, it.next()));
        }
        return new ViewSnapshot(query2, documents2, DocumentSet.emptySet(query2.comparator()), viewChanges, fromCache, mutatedKeys2, true, excludesMetadataChanges2);
    }

    public Query getQuery() {
        return this.query;
    }

    public DocumentSet getDocuments() {
        return this.documents;
    }

    public DocumentSet getOldDocuments() {
        return this.oldDocuments;
    }

    public List<DocumentViewChange> getChanges() {
        return this.changes;
    }

    public boolean isFromCache() {
        return this.isFromCache;
    }

    public boolean hasPendingWrites() {
        return !this.mutatedKeys.isEmpty();
    }

    public ImmutableSortedSet<DocumentKey> getMutatedKeys() {
        return this.mutatedKeys;
    }

    public boolean didSyncStateChange() {
        return this.didSyncStateChange;
    }

    public boolean excludesMetadataChanges() {
        return this.excludesMetadataChanges;
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ViewSnapshot)) {
            return false;
        }
        ViewSnapshot that = (ViewSnapshot) o;
        if (this.isFromCache == that.isFromCache && this.didSyncStateChange == that.didSyncStateChange && this.excludesMetadataChanges == that.excludesMetadataChanges && this.query.equals(that.query) && this.mutatedKeys.equals(that.mutatedKeys) && this.documents.equals(that.documents) && this.oldDocuments.equals(that.oldDocuments)) {
            return this.changes.equals(that.changes);
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((this.query.hashCode() * 31) + this.documents.hashCode()) * 31) + this.oldDocuments.hashCode()) * 31) + this.changes.hashCode()) * 31) + this.mutatedKeys.hashCode()) * 31) + (this.isFromCache ? 1 : 0)) * 31) + (this.didSyncStateChange ? 1 : 0)) * 31) + (this.excludesMetadataChanges ? 1 : 0);
    }

    public String toString() {
        return "ViewSnapshot(" + this.query + ", " + this.documents + ", " + this.oldDocuments + ", " + this.changes + ", isFromCache=" + this.isFromCache + ", mutatedKeys=" + this.mutatedKeys.size() + ", didSyncStateChange=" + this.didSyncStateChange + ", excludesMetadataChanges=" + this.excludesMetadataChanges + ")";
    }
}
