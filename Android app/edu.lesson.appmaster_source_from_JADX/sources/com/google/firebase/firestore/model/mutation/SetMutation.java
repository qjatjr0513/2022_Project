package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firestore.p002v1.Value;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class SetMutation extends Mutation {
    private final ObjectValue value;

    public SetMutation(DocumentKey key, ObjectValue value2, Precondition precondition) {
        this(key, value2, precondition, new ArrayList());
    }

    public SetMutation(DocumentKey key, ObjectValue value2, Precondition precondition, List<FieldTransform> fieldTransforms) {
        super(key, precondition, fieldTransforms);
        this.value = value2;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SetMutation that = (SetMutation) o;
        if (!hasSameKeyAndPrecondition(that) || !this.value.equals(that.value) || !getFieldTransforms().equals(that.getFieldTransforms())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (keyAndPreconditionHashCode() * 31) + this.value.hashCode();
    }

    public String toString() {
        return "SetMutation{" + keyAndPreconditionToString() + ", value=" + this.value + "}";
    }

    public void applyToRemoteDocument(MutableDocument document, MutationResult mutationResult) {
        verifyKeyMatches(document);
        ObjectValue newData = this.value.clone();
        newData.setAll(serverTransformResults(document, mutationResult.getTransformResults()));
        document.convertToFoundDocument(mutationResult.getVersion(), newData).setHasCommittedMutations();
    }

    public FieldMask applyToLocalView(MutableDocument document, FieldMask previousMask, Timestamp localWriteTime) {
        verifyKeyMatches(document);
        if (!getPrecondition().isValidFor(document)) {
            return previousMask;
        }
        Map<FieldPath, Value> transformResults = localTransformResults(localWriteTime, document);
        ObjectValue localValue = this.value.clone();
        localValue.setAll(transformResults);
        document.convertToFoundDocument(document.getVersion(), localValue).setHasLocalMutations();
        return null;
    }

    public ObjectValue getValue() {
        return this.value;
    }
}
