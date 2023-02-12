package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class MutationBatch {
    public static final int UNKNOWN = -1;
    private final List<Mutation> baseMutations;
    private final int batchId;
    private final Timestamp localWriteTime;
    private final List<Mutation> mutations;

    public MutationBatch(int batchId2, Timestamp localWriteTime2, List<Mutation> baseMutations2, List<Mutation> mutations2) {
        Assert.hardAssert(!mutations2.isEmpty(), "Cannot create an empty mutation batch", new Object[0]);
        this.batchId = batchId2;
        this.localWriteTime = localWriteTime2;
        this.baseMutations = baseMutations2;
        this.mutations = mutations2;
    }

    public void applyToRemoteDocument(MutableDocument document, MutationBatchResult batchResult) {
        int size = this.mutations.size();
        List<MutationResult> mutationResults = batchResult.getMutationResults();
        Assert.hardAssert(mutationResults.size() == size, "Mismatch between mutations length (%d) and results length (%d)", Integer.valueOf(size), Integer.valueOf(mutationResults.size()));
        for (int i = 0; i < size; i++) {
            Mutation mutation = this.mutations.get(i);
            if (mutation.getKey().equals(document.getKey())) {
                mutation.applyToRemoteDocument(document, mutationResults.get(i));
            }
        }
    }

    public FieldMask applyToLocalView(MutableDocument document) {
        return applyToLocalView(document, FieldMask.fromSet(new HashSet()));
    }

    public FieldMask applyToLocalView(MutableDocument document, FieldMask mutatedFields) {
        for (int i = 0; i < this.baseMutations.size(); i++) {
            Mutation mutation = this.baseMutations.get(i);
            if (mutation.getKey().equals(document.getKey())) {
                mutatedFields = mutation.applyToLocalView(document, mutatedFields, this.localWriteTime);
            }
        }
        for (int i2 = 0; i2 < this.mutations.size(); i2++) {
            Mutation mutation2 = this.mutations.get(i2);
            if (mutation2.getKey().equals(document.getKey())) {
                mutatedFields = mutation2.applyToLocalView(document, mutatedFields, this.localWriteTime);
            }
        }
        return mutatedFields;
    }

    public Map<DocumentKey, Mutation> applyToLocalDocumentSet(ImmutableSortedMap<DocumentKey, Document> documentMap, Set<DocumentKey> documentsWithoutRemoteVersion) {
        Map<DocumentKey, Mutation> overlays = new HashMap<>();
        for (DocumentKey key : getKeys()) {
            MutableDocument document = (MutableDocument) documentMap.get(key);
            Mutation overlay = Mutation.calculateOverlayMutation(document, documentsWithoutRemoteVersion.contains(key) ? null : applyToLocalView(document));
            if (overlay != null) {
                overlays.put(key, overlay);
            }
            if (!document.isValidDocument()) {
                document.convertToNoDocument(SnapshotVersion.NONE);
            }
        }
        return overlays;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MutationBatch that = (MutationBatch) o;
        if (this.batchId != that.batchId || !this.localWriteTime.equals(that.localWriteTime) || !this.baseMutations.equals(that.baseMutations) || !this.mutations.equals(that.mutations)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((this.batchId * 31) + this.localWriteTime.hashCode()) * 31) + this.baseMutations.hashCode()) * 31) + this.mutations.hashCode();
    }

    public String toString() {
        return "MutationBatch(batchId=" + this.batchId + ", localWriteTime=" + this.localWriteTime + ", baseMutations=" + this.baseMutations + ", mutations=" + this.mutations + ')';
    }

    public Set<DocumentKey> getKeys() {
        HashSet<DocumentKey> set = new HashSet<>();
        for (Mutation mutation : this.mutations) {
            set.add(mutation.getKey());
        }
        return set;
    }

    public int getBatchId() {
        return this.batchId;
    }

    public Timestamp getLocalWriteTime() {
        return this.localWriteTime;
    }

    public List<Mutation> getMutations() {
        return this.mutations;
    }

    public List<Mutation> getBaseMutations() {
        return this.baseMutations;
    }
}
