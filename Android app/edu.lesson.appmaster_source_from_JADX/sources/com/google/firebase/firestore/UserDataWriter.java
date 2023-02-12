package com.google.firebase.firestore;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ServerTimestamps;
import com.google.firebase.firestore.model.Values;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import com.google.firestore.p002v1.ArrayValue;
import com.google.firestore.p002v1.Value;
import com.google.protobuf.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDataWriter {
    private final FirebaseFirestore firestore;
    private final DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior;

    public UserDataWriter(FirebaseFirestore firestore2, DocumentSnapshot.ServerTimestampBehavior serverTimestampBehavior2) {
        this.firestore = firestore2;
        this.serverTimestampBehavior = serverTimestampBehavior2;
    }

    public Object convertValue(Value value) {
        switch (Values.typeOrder(value)) {
            case 0:
                return null;
            case 1:
                return Boolean.valueOf(value.getBooleanValue());
            case 2:
                if (value.getValueTypeCase().equals(Value.ValueTypeCase.INTEGER_VALUE)) {
                    return Long.valueOf(value.getIntegerValue());
                }
                return Double.valueOf(value.getDoubleValue());
            case 3:
                return convertTimestamp(value.getTimestampValue());
            case 4:
                return convertServerTimestamp(value);
            case 5:
                return value.getStringValue();
            case 6:
                return Blob.fromByteString(value.getBytesValue());
            case 7:
                return convertReference(value);
            case 8:
                return new GeoPoint(value.getGeoPointValue().getLatitude(), value.getGeoPointValue().getLongitude());
            case 9:
                return convertArray(value.getArrayValue());
            case 10:
                return convertObject(value.getMapValue().getFieldsMap());
            default:
                throw Assert.fail("Unknown value type: " + value.getValueTypeCase(), new Object[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public Map<String, Object> convertObject(Map<String, Value> mapValue) {
        Map<String, Object> result = new HashMap<>();
        for (Map.Entry<String, Value> entry : mapValue.entrySet()) {
            result.put(entry.getKey(), convertValue(entry.getValue()));
        }
        return result;
    }

    /* renamed from: com.google.firebase.firestore.UserDataWriter$1 */
    static /* synthetic */ class C07581 {

        /* renamed from: $SwitchMap$com$google$firebase$firestore$DocumentSnapshot$ServerTimestampBehavior */
        static final /* synthetic */ int[] f152x1f31a5ae;

        static {
            int[] iArr = new int[DocumentSnapshot.ServerTimestampBehavior.values().length];
            f152x1f31a5ae = iArr;
            try {
                iArr[DocumentSnapshot.ServerTimestampBehavior.PREVIOUS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f152x1f31a5ae[DocumentSnapshot.ServerTimestampBehavior.ESTIMATE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    private Object convertServerTimestamp(Value serverTimestampValue) {
        switch (C07581.f152x1f31a5ae[this.serverTimestampBehavior.ordinal()]) {
            case 1:
                Value previousValue = ServerTimestamps.getPreviousValue(serverTimestampValue);
                if (previousValue == null) {
                    return null;
                }
                return convertValue(previousValue);
            case 2:
                return convertTimestamp(ServerTimestamps.getLocalWriteTime(serverTimestampValue));
            default:
                return null;
        }
    }

    private Object convertTimestamp(Timestamp value) {
        return new com.google.firebase.Timestamp(value.getSeconds(), value.getNanos());
    }

    private List<Object> convertArray(ArrayValue arrayValue) {
        ArrayList<Object> result = new ArrayList<>(arrayValue.getValuesCount());
        for (Value v : arrayValue.getValuesList()) {
            result.add(convertValue(v));
        }
        return result;
    }

    private Object convertReference(Value value) {
        DatabaseId refDatabase = DatabaseId.fromName(value.getReferenceValue());
        DocumentKey key = DocumentKey.fromName(value.getReferenceValue());
        DatabaseId database = this.firestore.getDatabaseId();
        if (!refDatabase.equals(database)) {
            Logger.warn("DocumentSnapshot", "Document %s contains a document reference within a different database (%s/%s) which is not supported. It will be treated as a reference in the current database (%s/%s) instead.", key.getPath(), refDatabase.getProjectId(), refDatabase.getDatabaseId(), database.getProjectId(), database.getDatabaseId());
        }
        return new DocumentReference(key, this.firestore);
    }
}
