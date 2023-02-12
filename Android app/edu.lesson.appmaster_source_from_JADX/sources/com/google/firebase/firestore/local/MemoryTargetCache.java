package com.google.firebase.firestore.local;

import android.util.SparseArray;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Consumer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

final class MemoryTargetCache implements TargetCache {
    private long highestSequenceNumber = 0;
    private int highestTargetId;
    private SnapshotVersion lastRemoteSnapshotVersion = SnapshotVersion.NONE;
    private final MemoryPersistence persistence;
    private final ReferenceSet references = new ReferenceSet();
    private final Map<Target, TargetData> targets = new HashMap();

    MemoryTargetCache(MemoryPersistence persistence2) {
        this.persistence = persistence2;
    }

    public int getHighestTargetId() {
        return this.highestTargetId;
    }

    public long getTargetCount() {
        return (long) this.targets.size();
    }

    public void forEachTarget(Consumer<TargetData> consumer) {
        for (TargetData targetData : this.targets.values()) {
            consumer.accept(targetData);
        }
    }

    public long getHighestListenSequenceNumber() {
        return this.highestSequenceNumber;
    }

    public SnapshotVersion getLastRemoteSnapshotVersion() {
        return this.lastRemoteSnapshotVersion;
    }

    public void setLastRemoteSnapshotVersion(SnapshotVersion snapshotVersion) {
        this.lastRemoteSnapshotVersion = snapshotVersion;
    }

    public void addTargetData(TargetData targetData) {
        this.targets.put(targetData.getTarget(), targetData);
        int targetId = targetData.getTargetId();
        if (targetId > this.highestTargetId) {
            this.highestTargetId = targetId;
        }
        if (targetData.getSequenceNumber() > this.highestSequenceNumber) {
            this.highestSequenceNumber = targetData.getSequenceNumber();
        }
    }

    public void updateTargetData(TargetData targetData) {
        addTargetData(targetData);
    }

    public void removeTargetData(TargetData targetData) {
        this.targets.remove(targetData.getTarget());
        this.references.removeReferencesForId(targetData.getTargetId());
    }

    /* access modifiers changed from: package-private */
    public int removeQueries(long upperBound, SparseArray<?> activeTargetIds) {
        int removed = 0;
        Iterator<Map.Entry<Target, TargetData>> it = this.targets.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Target, TargetData> entry = it.next();
            int targetId = entry.getValue().getTargetId();
            if (entry.getValue().getSequenceNumber() <= upperBound && activeTargetIds.get(targetId) == null) {
                it.remove();
                removeMatchingKeysForTargetId(targetId);
                removed++;
            }
        }
        return removed;
    }

    public TargetData getTargetData(Target target) {
        return this.targets.get(target);
    }

    public void addMatchingKeys(ImmutableSortedSet<DocumentKey> keys, int targetId) {
        this.references.addReferences(keys, targetId);
        ReferenceDelegate referenceDelegate = this.persistence.getReferenceDelegate();
        Iterator<DocumentKey> it = keys.iterator();
        while (it.hasNext()) {
            referenceDelegate.addReference(it.next());
        }
    }

    public void removeMatchingKeys(ImmutableSortedSet<DocumentKey> keys, int targetId) {
        this.references.removeReferences(keys, targetId);
        ReferenceDelegate referenceDelegate = this.persistence.getReferenceDelegate();
        Iterator<DocumentKey> it = keys.iterator();
        while (it.hasNext()) {
            referenceDelegate.removeReference(it.next());
        }
    }

    public void removeMatchingKeysForTargetId(int targetId) {
        this.references.removeReferencesForId(targetId);
    }

    public ImmutableSortedSet<DocumentKey> getMatchingKeysForTargetId(int targetId) {
        return this.references.referencesForId(targetId);
    }

    public boolean containsKey(DocumentKey key) {
        return this.references.containsKey(key);
    }

    /* access modifiers changed from: package-private */
    public long getByteSize(LocalSerializer serializer) {
        long count = 0;
        for (Map.Entry<Target, TargetData> entry : this.targets.entrySet()) {
            count += (long) serializer.encodeTargetData(entry.getValue()).getSerializedSize();
        }
        return count;
    }
}
