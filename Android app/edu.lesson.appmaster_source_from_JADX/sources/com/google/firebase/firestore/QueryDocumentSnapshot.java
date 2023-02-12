package com.google.firebase.firestore;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Preconditions;
import java.util.Map;

public class QueryDocumentSnapshot extends DocumentSnapshot {
    private QueryDocumentSnapshot(FirebaseFirestore firestore, DocumentKey key, Document doc, boolean isFromCache, boolean hasPendingWrites) {
        super(firestore, key, doc, isFromCache, hasPendingWrites);
    }

    static QueryDocumentSnapshot fromDocument(FirebaseFirestore firestore, Document doc, boolean fromCache, boolean hasPendingWrites) {
        return new QueryDocumentSnapshot(firestore, doc.getKey(), doc, fromCache, hasPendingWrites);
    }

    public Map<String, Object> getData() {
        Map<String, Object> result = super.getData();
        Assert.hardAssert(result != null, "Data in a QueryDocumentSnapshot should be non-null", new Object[0]);
        return result;
    }

    public Map<String, Object> getData(DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        Map<String, Object> result = super.getData(serverTimestampBehavior);
        Assert.hardAssert(result != null, "Data in a QueryDocumentSnapshot should be non-null", new Object[0]);
        return result;
    }

    public <T> T toObject(Class<T> valueType) {
        T result = super.toObject(valueType);
        Assert.hardAssert(result != null, "Object in a QueryDocumentSnapshot should be non-null", new Object[0]);
        return result;
    }

    public <T> T toObject(Class<T> valueType, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        T result = super.toObject(valueType, serverTimestampBehavior);
        Assert.hardAssert(result != null, "Object in a QueryDocumentSnapshot should be non-null", new Object[0]);
        return result;
    }
}
