package com.google.firebase.firestore;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;

public class CollectionReference extends Query {
    CollectionReference(ResourcePath path, FirebaseFirestore firestore) {
        super(Query.atPath(path), firestore);
        if (path.length() % 2 != 1) {
            throw new IllegalArgumentException("Invalid collection reference. Collection references must have an odd number of segments, but " + path.canonicalString() + " has " + path.length());
        }
    }

    public String getId() {
        return this.query.getPath().getLastSegment();
    }

    public DocumentReference getParent() {
        ResourcePath parentPath = (ResourcePath) this.query.getPath().popLast();
        if (parentPath.isEmpty()) {
            return null;
        }
        return new DocumentReference(DocumentKey.fromPath(parentPath), this.firestore);
    }

    public String getPath() {
        return this.query.getPath().canonicalString();
    }

    public DocumentReference document() {
        return document(Util.autoId());
    }

    public DocumentReference document(String documentPath) {
        Preconditions.checkNotNull(documentPath, "Provided document path must not be null.");
        return DocumentReference.forPath((ResourcePath) this.query.getPath().append(ResourcePath.fromString(documentPath)), this.firestore);
    }

    public Task<DocumentReference> add(Object data) {
        Preconditions.checkNotNull(data, "Provided data must not be null.");
        DocumentReference ref = document();
        return ref.set(data).continueWith(Executors.DIRECT_EXECUTOR, new CollectionReference$$ExternalSyntheticLambda0(ref));
    }
}
