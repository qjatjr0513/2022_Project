package com.google.firebase.firestore.local;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Consumer;

interface TargetCache {
    void addMatchingKeys(ImmutableSortedSet<DocumentKey> immutableSortedSet, int i);

    void addTargetData(TargetData targetData);

    boolean containsKey(DocumentKey documentKey);

    void forEachTarget(Consumer<TargetData> consumer);

    long getHighestListenSequenceNumber();

    int getHighestTargetId();

    SnapshotVersion getLastRemoteSnapshotVersion();

    ImmutableSortedSet<DocumentKey> getMatchingKeysForTargetId(int i);

    long getTargetCount();

    TargetData getTargetData(Target target);

    void removeMatchingKeys(ImmutableSortedSet<DocumentKey> immutableSortedSet, int i);

    void removeMatchingKeysForTargetId(int i);

    void removeTargetData(TargetData targetData);

    void setLastRemoteSnapshotVersion(SnapshotVersion snapshotVersion);

    void updateTargetData(TargetData targetData);
}
