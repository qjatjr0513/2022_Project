package com.google.firebase.firestore.core;

import java.util.List;

public class ViewChange {
    private final List<LimboDocumentChange> limboChanges;
    private final ViewSnapshot snapshot;

    public ViewChange(ViewSnapshot snapshot2, List<LimboDocumentChange> limboChanges2) {
        this.snapshot = snapshot2;
        this.limboChanges = limboChanges2;
    }

    public ViewSnapshot getSnapshot() {
        return this.snapshot;
    }

    public List<LimboDocumentChange> getLimboChanges() {
        return this.limboChanges;
    }
}
