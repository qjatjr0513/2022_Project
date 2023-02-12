package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.util.Assert;

public final class DeleteMutation extends Mutation {
    public DeleteMutation(DocumentKey key, Precondition precondition) {
        super(key, precondition);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return hasSameKeyAndPrecondition((DeleteMutation) o);
    }

    public int hashCode() {
        return keyAndPreconditionHashCode();
    }

    public String toString() {
        return "DeleteMutation{" + keyAndPreconditionToString() + "}";
    }

    public void applyToRemoteDocument(MutableDocument document, MutationResult mutationResult) {
        verifyKeyMatches(document);
        Assert.hardAssert(mutationResult.getTransformResults().isEmpty(), "Transform results received by DeleteMutation.", new Object[0]);
        document.convertToNoDocument(mutationResult.getVersion()).setHasCommittedMutations();
    }

    public FieldMask applyToLocalView(MutableDocument document, FieldMask previousMask, Timestamp localWriteTime) {
        verifyKeyMatches(document);
        if (!getPrecondition().isValidFor(document)) {
            return previousMask;
        }
        document.convertToNoDocument(document.getVersion()).setHasLocalMutations();
        return null;
    }
}
