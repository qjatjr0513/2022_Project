package com.google.firebase.firestore.model.mutation;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.firestore.model.DocumentCollections;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.util.Assert;
import com.google.protobuf.ByteString;
import java.util.List;

public final class MutationBatchResult {
    private final MutationBatch batch;
    private final SnapshotVersion commitVersion;
    private final ImmutableSortedMap<DocumentKey, SnapshotVersion> docVersions;
    private final List<MutationResult> mutationResults;
    private final ByteString streamToken;

    private MutationBatchResult(MutationBatch batch2, SnapshotVersion commitVersion2, List<MutationResult> mutationResults2, ByteString streamToken2, ImmutableSortedMap<DocumentKey, SnapshotVersion> docVersions2) {
        this.batch = batch2;
        this.commitVersion = commitVersion2;
        this.mutationResults = mutationResults2;
        this.streamToken = streamToken2;
        this.docVersions = docVersions2;
    }

    public static MutationBatchResult create(MutationBatch batch2, SnapshotVersion commitVersion2, List<MutationResult> mutationResults2, ByteString streamToken2) {
        Assert.hardAssert(batch2.getMutations().size() == mutationResults2.size(), "Mutations sent %d must equal results received %d", Integer.valueOf(batch2.getMutations().size()), Integer.valueOf(mutationResults2.size()));
        ImmutableSortedMap<DocumentKey, SnapshotVersion> docVersions2 = DocumentCollections.emptyVersionMap();
        List<Mutation> mutations = batch2.getMutations();
        for (int i = 0; i < mutations.size(); i++) {
            docVersions2 = docVersions2.insert(mutations.get(i).getKey(), mutationResults2.get(i).getVersion());
        }
        return new MutationBatchResult(batch2, commitVersion2, mutationResults2, streamToken2, docVersions2);
    }

    public MutationBatch getBatch() {
        return this.batch;
    }

    public SnapshotVersion getCommitVersion() {
        return this.commitVersion;
    }

    public List<MutationResult> getMutationResults() {
        return this.mutationResults;
    }

    public ByteString getStreamToken() {
        return this.streamToken;
    }

    public ImmutableSortedMap<DocumentKey, SnapshotVersion> getDocVersions() {
        return this.docVersions;
    }
}
