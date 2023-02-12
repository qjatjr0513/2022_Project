package com.google.firebase.firestore;

import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentSet;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.List;

public class DocumentChange {
    private final QueryDocumentSnapshot document;
    private final int newIndex;
    private final int oldIndex;
    private final Type type;

    public enum Type {
        ADDED,
        MODIFIED,
        REMOVED
    }

    DocumentChange(QueryDocumentSnapshot document2, Type type2, int oldIndex2, int newIndex2) {
        this.type = type2;
        this.document = document2;
        this.oldIndex = oldIndex2;
        this.newIndex = newIndex2;
    }

    public boolean equals(Object object) {
        if (!(object instanceof DocumentChange)) {
            return false;
        }
        DocumentChange that = (DocumentChange) object;
        if (!this.type.equals(that.type) || !this.document.equals(that.document) || this.oldIndex != that.oldIndex || this.newIndex != that.newIndex) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((this.type.hashCode() * 31) + this.document.hashCode()) * 31) + this.oldIndex) * 31) + this.newIndex;
    }

    public Type getType() {
        return this.type;
    }

    public QueryDocumentSnapshot getDocument() {
        return this.document;
    }

    public int getOldIndex() {
        return this.oldIndex;
    }

    public int getNewIndex() {
        return this.newIndex;
    }

    static List<DocumentChange> changesFromSnapshot(FirebaseFirestore firestore, MetadataChanges metadataChanges, ViewSnapshot snapshot) {
        int oldIndex2;
        int newIndex2;
        FirebaseFirestore firebaseFirestore = firestore;
        List<DocumentChange> documentChanges = new ArrayList<>();
        if (snapshot.getOldDocuments().isEmpty()) {
            int index = 0;
            Document lastDoc = null;
            for (DocumentViewChange change : snapshot.getChanges()) {
                Document document2 = change.getDocument();
                QueryDocumentSnapshot documentSnapshot = QueryDocumentSnapshot.fromDocument(firebaseFirestore, document2, snapshot.isFromCache(), snapshot.getMutatedKeys().contains(document2.getKey()));
                Assert.hardAssert(change.getType() == DocumentViewChange.Type.ADDED, "Invalid added event for first snapshot", new Object[0]);
                Assert.hardAssert(lastDoc == null || snapshot.getQuery().comparator().compare(lastDoc, document2) < 0, "Got added events in wrong order", new Object[0]);
                documentChanges.add(new DocumentChange(documentSnapshot, Type.ADDED, -1, index));
                lastDoc = document2;
                index++;
            }
            MetadataChanges metadataChanges2 = metadataChanges;
        } else {
            DocumentSet indexTracker = snapshot.getOldDocuments();
            for (DocumentViewChange change2 : snapshot.getChanges()) {
                if (metadataChanges != MetadataChanges.EXCLUDE || change2.getType() != DocumentViewChange.Type.METADATA) {
                    Document document3 = change2.getDocument();
                    QueryDocumentSnapshot documentSnapshot2 = QueryDocumentSnapshot.fromDocument(firebaseFirestore, document3, snapshot.isFromCache(), snapshot.getMutatedKeys().contains(document3.getKey()));
                    Type type2 = getType(change2);
                    if (type2 != Type.ADDED) {
                        oldIndex2 = indexTracker.indexOf(document3.getKey());
                        Assert.hardAssert(oldIndex2 >= 0, "Index for document not found", new Object[0]);
                        indexTracker = indexTracker.remove(document3.getKey());
                    } else {
                        oldIndex2 = -1;
                    }
                    if (type2 != Type.REMOVED) {
                        indexTracker = indexTracker.add(document3);
                        newIndex2 = indexTracker.indexOf(document3.getKey());
                        Assert.hardAssert(newIndex2 >= 0, "Index for document not found", new Object[0]);
                    } else {
                        newIndex2 = -1;
                    }
                    documentChanges.add(new DocumentChange(documentSnapshot2, type2, oldIndex2, newIndex2));
                }
            }
            MetadataChanges metadataChanges3 = metadataChanges;
        }
        return documentChanges;
    }

    /* renamed from: com.google.firebase.firestore.DocumentChange$1 */
    static /* synthetic */ class C07551 {

        /* renamed from: $SwitchMap$com$google$firebase$firestore$core$DocumentViewChange$Type */
        static final /* synthetic */ int[] f148x33862af7;

        static {
            int[] iArr = new int[DocumentViewChange.Type.values().length];
            f148x33862af7 = iArr;
            try {
                iArr[DocumentViewChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f148x33862af7[DocumentViewChange.Type.METADATA.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f148x33862af7[DocumentViewChange.Type.MODIFIED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f148x33862af7[DocumentViewChange.Type.REMOVED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    private static Type getType(DocumentViewChange change) {
        switch (C07551.f148x33862af7[change.getType().ordinal()]) {
            case 1:
                return Type.ADDED;
            case 2:
            case 3:
                return Type.MODIFIED;
            case 4:
                return Type.REMOVED;
            default:
                throw new IllegalArgumentException("Unknown view change type: " + change.getType());
        }
    }
}
