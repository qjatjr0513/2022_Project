package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.util.Assert;

public final class VerifyMutation extends Mutation {
    public VerifyMutation(DocumentKey key, Precondition precondition) {
        super(key, precondition);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return hasSameKeyAndPrecondition((VerifyMutation) o);
    }

    public int hashCode() {
        return keyAndPreconditionHashCode();
    }

    public String toString() {
        return "VerifyMutation{" + keyAndPreconditionToString() + "}";
    }

    public void applyToRemoteDocument(MutableDocument document, MutationResult mutationResult) {
        throw Assert.fail("VerifyMutation should only be used in Transactions.", new Object[0]);
    }

    public FieldMask applyToLocalView(MutableDocument document, FieldMask previousMask, Timestamp localWriteTime) {
        throw Assert.fail("VerifyMutation should only be used in Transactions.", new Object[0]);
    }
}
