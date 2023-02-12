package com.google.firebase.firestore.model.mutation;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.util.Assert;
import com.google.firestore.p002v1.Value;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Mutation {
    private final List<FieldTransform> fieldTransforms;
    private final DocumentKey key;
    private final Precondition precondition;

    public abstract FieldMask applyToLocalView(MutableDocument mutableDocument, FieldMask fieldMask, Timestamp timestamp);

    public abstract void applyToRemoteDocument(MutableDocument mutableDocument, MutationResult mutationResult);

    Mutation(DocumentKey key2, Precondition precondition2) {
        this(key2, precondition2, new ArrayList());
    }

    Mutation(DocumentKey key2, Precondition precondition2, List<FieldTransform> fieldTransforms2) {
        this.key = key2;
        this.precondition = precondition2;
        this.fieldTransforms = fieldTransforms2;
    }

    /* JADX WARNING: type inference failed for: r6v3, types: [com.google.firebase.firestore.model.BasePath] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.firebase.firestore.model.mutation.Mutation calculateOverlayMutation(com.google.firebase.firestore.model.MutableDocument r8, com.google.firebase.firestore.model.mutation.FieldMask r9) {
        /*
            boolean r0 = r8.hasLocalMutations()
            if (r0 == 0) goto L_0x008f
            if (r9 == 0) goto L_0x0014
            java.util.Set r0 = r9.getMask()
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0014
            goto L_0x008f
        L_0x0014:
            if (r9 != 0) goto L_0x0038
            boolean r0 = r8.isNoDocument()
            if (r0 == 0) goto L_0x0028
            com.google.firebase.firestore.model.mutation.DeleteMutation r0 = new com.google.firebase.firestore.model.mutation.DeleteMutation
            com.google.firebase.firestore.model.DocumentKey r1 = r8.getKey()
            com.google.firebase.firestore.model.mutation.Precondition r2 = com.google.firebase.firestore.model.mutation.Precondition.NONE
            r0.<init>(r1, r2)
            return r0
        L_0x0028:
            com.google.firebase.firestore.model.mutation.SetMutation r0 = new com.google.firebase.firestore.model.mutation.SetMutation
            com.google.firebase.firestore.model.DocumentKey r1 = r8.getKey()
            com.google.firebase.firestore.model.ObjectValue r2 = r8.getData()
            com.google.firebase.firestore.model.mutation.Precondition r3 = com.google.firebase.firestore.model.mutation.Precondition.NONE
            r0.<init>(r1, r2, r3)
            return r0
        L_0x0038:
            com.google.firebase.firestore.model.ObjectValue r0 = r8.getData()
            com.google.firebase.firestore.model.ObjectValue r1 = new com.google.firebase.firestore.model.ObjectValue
            r1.<init>()
            java.util.HashSet r2 = new java.util.HashSet
            r2.<init>()
            java.util.Set r3 = r9.getMask()
            java.util.Iterator r3 = r3.iterator()
        L_0x004e:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x007f
            java.lang.Object r4 = r3.next()
            com.google.firebase.firestore.model.FieldPath r4 = (com.google.firebase.firestore.model.FieldPath) r4
            boolean r5 = r2.contains(r4)
            if (r5 != 0) goto L_0x007e
            com.google.firestore.v1.Value r5 = r0.get(r4)
            if (r5 != 0) goto L_0x0074
            int r6 = r4.length()
            r7 = 1
            if (r6 <= r7) goto L_0x0074
            com.google.firebase.firestore.model.BasePath r6 = r4.popLast()
            r4 = r6
            com.google.firebase.firestore.model.FieldPath r4 = (com.google.firebase.firestore.model.FieldPath) r4
        L_0x0074:
            com.google.firestore.v1.Value r6 = r0.get(r4)
            r1.set(r4, r6)
            r2.add(r4)
        L_0x007e:
            goto L_0x004e
        L_0x007f:
            com.google.firebase.firestore.model.mutation.PatchMutation r3 = new com.google.firebase.firestore.model.mutation.PatchMutation
            com.google.firebase.firestore.model.DocumentKey r4 = r8.getKey()
            com.google.firebase.firestore.model.mutation.FieldMask r5 = com.google.firebase.firestore.model.mutation.FieldMask.fromSet(r2)
            com.google.firebase.firestore.model.mutation.Precondition r6 = com.google.firebase.firestore.model.mutation.Precondition.NONE
            r3.<init>(r4, r1, r5, r6)
            return r3
        L_0x008f:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.model.mutation.Mutation.calculateOverlayMutation(com.google.firebase.firestore.model.MutableDocument, com.google.firebase.firestore.model.mutation.FieldMask):com.google.firebase.firestore.model.mutation.Mutation");
    }

    public DocumentKey getKey() {
        return this.key;
    }

    public Precondition getPrecondition() {
        return this.precondition;
    }

    public List<FieldTransform> getFieldTransforms() {
        return this.fieldTransforms;
    }

    /* access modifiers changed from: package-private */
    public boolean hasSameKeyAndPrecondition(Mutation other) {
        return this.key.equals(other.key) && this.precondition.equals(other.precondition);
    }

    /* access modifiers changed from: package-private */
    public int keyAndPreconditionHashCode() {
        return (getKey().hashCode() * 31) + this.precondition.hashCode();
    }

    /* access modifiers changed from: package-private */
    public String keyAndPreconditionToString() {
        return "key=" + this.key + ", precondition=" + this.precondition;
    }

    /* access modifiers changed from: package-private */
    public void verifyKeyMatches(MutableDocument document) {
        Assert.hardAssert(document.getKey().equals(getKey()), "Can only apply a mutation to a document with the same key", new Object[0]);
    }

    /* access modifiers changed from: protected */
    public Map<FieldPath, Value> serverTransformResults(MutableDocument mutableDocument, List<Value> serverTransformResults) {
        Map<FieldPath, Value> transformResults = new HashMap<>(this.fieldTransforms.size());
        Assert.hardAssert(this.fieldTransforms.size() == serverTransformResults.size(), "server transform count (%d) should match field transform count (%d)", Integer.valueOf(serverTransformResults.size()), Integer.valueOf(this.fieldTransforms.size()));
        for (int i = 0; i < serverTransformResults.size(); i++) {
            FieldTransform fieldTransform = this.fieldTransforms.get(i);
            transformResults.put(fieldTransform.getFieldPath(), fieldTransform.getOperation().applyToRemoteDocument(mutableDocument.getField(fieldTransform.getFieldPath()), serverTransformResults.get(i)));
        }
        return transformResults;
    }

    /* access modifiers changed from: protected */
    public Map<FieldPath, Value> localTransformResults(Timestamp localWriteTime, MutableDocument mutableDocument) {
        Map<FieldPath, Value> transformResults = new HashMap<>(this.fieldTransforms.size());
        for (FieldTransform fieldTransform : this.fieldTransforms) {
            transformResults.put(fieldTransform.getFieldPath(), fieldTransform.getOperation().applyToLocalView(mutableDocument.getField(fieldTransform.getFieldPath()), localWriteTime));
        }
        return transformResults;
    }

    public ObjectValue extractTransformBaseValue(Document document) {
        ObjectValue baseObject = null;
        for (FieldTransform transform : this.fieldTransforms) {
            Value coercedValue = transform.getOperation().computeBaseValue(document.getField(transform.getFieldPath()));
            if (coercedValue != null) {
                if (baseObject == null) {
                    baseObject = new ObjectValue();
                }
                baseObject.set(transform.getFieldPath(), coercedValue);
            }
        }
        return baseObject;
    }
}
