package com.google.firebase.firestore;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.util.CustomClassMapper;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firestore.p002v1.Value;
import java.util.Date;
import java.util.Map;

public class DocumentSnapshot {
    private final Document doc;
    private final FirebaseFirestore firestore;
    private final DocumentKey key;
    private final SnapshotMetadata metadata;

    public enum ServerTimestampBehavior {
        NONE,
        ESTIMATE,
        PREVIOUS;
        
        static final ServerTimestampBehavior DEFAULT = null;

        static {
            ServerTimestampBehavior serverTimestampBehavior;
            DEFAULT = serverTimestampBehavior;
        }
    }

    DocumentSnapshot(FirebaseFirestore firestore2, DocumentKey key2, Document doc2, boolean isFromCache, boolean hasPendingWrites) {
        this.firestore = (FirebaseFirestore) Preconditions.checkNotNull(firestore2);
        this.key = (DocumentKey) Preconditions.checkNotNull(key2);
        this.doc = doc2;
        this.metadata = new SnapshotMetadata(hasPendingWrites, isFromCache);
    }

    static DocumentSnapshot fromDocument(FirebaseFirestore firestore2, Document doc2, boolean fromCache, boolean hasPendingWrites) {
        return new DocumentSnapshot(firestore2, doc2.getKey(), doc2, fromCache, hasPendingWrites);
    }

    static DocumentSnapshot fromNoDocument(FirebaseFirestore firestore2, DocumentKey key2, boolean fromCache) {
        return new DocumentSnapshot(firestore2, key2, (Document) null, fromCache, false);
    }

    public String getId() {
        return this.key.getDocumentId();
    }

    public SnapshotMetadata getMetadata() {
        return this.metadata;
    }

    public boolean exists() {
        return this.doc != null;
    }

    /* access modifiers changed from: package-private */
    public Document getDocument() {
        return this.doc;
    }

    public Map<String, Object> getData() {
        return getData(ServerTimestampBehavior.DEFAULT);
    }

    public Map<String, Object> getData(ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        UserDataWriter userDataWriter = new UserDataWriter(this.firestore, serverTimestampBehavior);
        Document document = this.doc;
        if (document == null) {
            return null;
        }
        return userDataWriter.convertObject(document.getData().getFieldsMap());
    }

    public <T> T toObject(Class<T> valueType) {
        return toObject(valueType, ServerTimestampBehavior.DEFAULT);
    }

    public <T> T toObject(Class<T> valueType, ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(valueType, "Provided POJO type must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        Map<String, Object> data = getData(serverTimestampBehavior);
        if (data == null) {
            return null;
        }
        return CustomClassMapper.convertToCustomClass(data, valueType, getReference());
    }

    public boolean contains(String field) {
        return contains(FieldPath.fromDotSeparatedPath(field));
    }

    public boolean contains(FieldPath fieldPath) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        Document document = this.doc;
        return (document == null || document.getField(fieldPath.getInternalPath()) == null) ? false : true;
    }

    public Object get(String field) {
        return get(FieldPath.fromDotSeparatedPath(field), ServerTimestampBehavior.DEFAULT);
    }

    public Object get(String field, ServerTimestampBehavior serverTimestampBehavior) {
        return get(FieldPath.fromDotSeparatedPath(field), serverTimestampBehavior);
    }

    public Object get(FieldPath fieldPath) {
        return get(fieldPath, ServerTimestampBehavior.DEFAULT);
    }

    public Object get(FieldPath fieldPath, ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(fieldPath, "Provided field path must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        return getInternal(fieldPath.getInternalPath(), serverTimestampBehavior);
    }

    public <T> T get(String field, Class<T> valueType) {
        return get(FieldPath.fromDotSeparatedPath(field), valueType, ServerTimestampBehavior.DEFAULT);
    }

    public <T> T get(String field, Class<T> valueType, ServerTimestampBehavior serverTimestampBehavior) {
        return get(FieldPath.fromDotSeparatedPath(field), valueType, serverTimestampBehavior);
    }

    public <T> T get(FieldPath fieldPath, Class<T> valueType) {
        return get(fieldPath, valueType, ServerTimestampBehavior.DEFAULT);
    }

    public <T> T get(FieldPath fieldPath, Class<T> valueType, ServerTimestampBehavior serverTimestampBehavior) {
        Object data = get(fieldPath, serverTimestampBehavior);
        if (data == null) {
            return null;
        }
        return CustomClassMapper.convertToCustomClass(data, valueType, getReference());
    }

    public Boolean getBoolean(String field) {
        return (Boolean) getTypedValue(field, Boolean.class);
    }

    public Double getDouble(String field) {
        Number val = (Number) getTypedValue(field, Number.class);
        if (val != null) {
            return Double.valueOf(val.doubleValue());
        }
        return null;
    }

    public String getString(String field) {
        return (String) getTypedValue(field, String.class);
    }

    public Long getLong(String field) {
        Number val = (Number) getTypedValue(field, Number.class);
        if (val != null) {
            return Long.valueOf(val.longValue());
        }
        return null;
    }

    public Date getDate(String field) {
        return getDate(field, ServerTimestampBehavior.DEFAULT);
    }

    public Date getDate(String field, ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(field, "Provided field path must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        Timestamp timestamp = getTimestamp(field, serverTimestampBehavior);
        if (timestamp != null) {
            return timestamp.toDate();
        }
        return null;
    }

    public Timestamp getTimestamp(String field) {
        return getTimestamp(field, ServerTimestampBehavior.DEFAULT);
    }

    public Timestamp getTimestamp(String field, ServerTimestampBehavior serverTimestampBehavior) {
        Preconditions.checkNotNull(field, "Provided field path must not be null.");
        Preconditions.checkNotNull(serverTimestampBehavior, "Provided serverTimestampBehavior value must not be null.");
        return (Timestamp) castTypedValue(getInternal(FieldPath.fromDotSeparatedPath(field).getInternalPath(), serverTimestampBehavior), field, Timestamp.class);
    }

    public Blob getBlob(String field) {
        return (Blob) getTypedValue(field, Blob.class);
    }

    public GeoPoint getGeoPoint(String field) {
        return (GeoPoint) getTypedValue(field, GeoPoint.class);
    }

    public DocumentReference getDocumentReference(String field) {
        return (DocumentReference) getTypedValue(field, DocumentReference.class);
    }

    public DocumentReference getReference() {
        return new DocumentReference(this.key, this.firestore);
    }

    private <T> T getTypedValue(String field, Class<T> clazz) {
        Preconditions.checkNotNull(field, "Provided field must not be null.");
        return castTypedValue(get(field, ServerTimestampBehavior.DEFAULT), field, clazz);
    }

    private <T> T castTypedValue(Object value, String field, Class<T> clazz) {
        if (value == null) {
            return null;
        }
        if (clazz.isInstance(value)) {
            return clazz.cast(value);
        }
        throw new RuntimeException("Field '" + field + "' is not a " + clazz.getName());
    }

    private Object getInternal(FieldPath fieldPath, ServerTimestampBehavior serverTimestampBehavior) {
        Value val;
        Document document = this.doc;
        if (document == null || (val = document.getField(fieldPath)) == null) {
            return null;
        }
        return new UserDataWriter(this.firestore, serverTimestampBehavior).convertValue(val);
    }

    public boolean equals(Object obj) {
        Document document;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DocumentSnapshot)) {
            return false;
        }
        DocumentSnapshot other = (DocumentSnapshot) obj;
        if (!this.firestore.equals(other.firestore) || !this.key.equals(other.key) || ((document = this.doc) != null ? !document.equals(other.doc) : other.doc != null) || !this.metadata.equals(other.metadata)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = ((this.firestore.hashCode() * 31) + this.key.hashCode()) * 31;
        Document document = this.doc;
        int i = 0;
        int hash2 = (hash + (document != null ? document.getKey().hashCode() : 0)) * 31;
        Document document2 = this.doc;
        if (document2 != null) {
            i = document2.getData().hashCode();
        }
        return ((hash2 + i) * 31) + this.metadata.hashCode();
    }

    public String toString() {
        return "DocumentSnapshot{key=" + this.key + ", metadata=" + this.metadata + ", doc=" + this.doc + '}';
    }
}
