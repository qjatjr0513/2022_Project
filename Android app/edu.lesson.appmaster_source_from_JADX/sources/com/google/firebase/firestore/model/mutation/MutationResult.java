package com.google.firebase.firestore.model.mutation;

import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.p002v1.Value;
import java.util.List;

public final class MutationResult {
    private final List<Value> transformResults;
    private final SnapshotVersion version;

    public MutationResult(SnapshotVersion version2, List<Value> transformResults2) {
        this.version = (SnapshotVersion) Preconditions.checkNotNull(version2);
        this.transformResults = transformResults2;
    }

    public SnapshotVersion getVersion() {
        return this.version;
    }

    public List<Value> getTransformResults() {
        return this.transformResults;
    }
}
