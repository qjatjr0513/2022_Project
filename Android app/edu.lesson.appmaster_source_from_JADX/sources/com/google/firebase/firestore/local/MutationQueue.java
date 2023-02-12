package com.google.firebase.firestore.local;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.protobuf.ByteString;
import java.util.List;

interface MutationQueue {
    void acknowledgeBatch(MutationBatch mutationBatch, ByteString byteString);

    MutationBatch addMutationBatch(Timestamp timestamp, List<Mutation> list, List<Mutation> list2);

    List<MutationBatch> getAllMutationBatches();

    List<MutationBatch> getAllMutationBatchesAffectingDocumentKey(DocumentKey documentKey);

    List<MutationBatch> getAllMutationBatchesAffectingDocumentKeys(Iterable<DocumentKey> iterable);

    List<MutationBatch> getAllMutationBatchesAffectingQuery(Query query);

    int getHighestUnacknowledgedBatchId();

    ByteString getLastStreamToken();

    MutationBatch getNextMutationBatchAfterBatchId(int i);

    boolean isEmpty();

    MutationBatch lookupMutationBatch(int i);

    void performConsistencyCheck();

    void removeMutationBatch(MutationBatch mutationBatch);

    void setLastStreamToken(ByteString byteString);

    void start();
}
