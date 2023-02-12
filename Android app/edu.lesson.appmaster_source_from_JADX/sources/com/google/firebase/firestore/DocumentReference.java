package com.google.firebase.firestore;

import android.app.Activity;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.core.ActivityScope;
import com.google.firebase.firestore.core.AsyncEventListener;
import com.google.firebase.firestore.core.EventManager;
import com.google.firebase.firestore.core.ListenerRegistrationImpl;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.UserData;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.DeleteMutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.firestore.util.Util;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class DocumentReference {
    private final FirebaseFirestore firestore;
    private final DocumentKey key;

    DocumentReference(DocumentKey key2, FirebaseFirestore firestore2) {
        this.key = (DocumentKey) Preconditions.checkNotNull(key2);
        this.firestore = firestore2;
    }

    static DocumentReference forPath(ResourcePath path, FirebaseFirestore firestore2) {
        if (path.length() % 2 == 0) {
            return new DocumentReference(DocumentKey.fromPath(path), firestore2);
        }
        throw new IllegalArgumentException("Invalid document reference. Document references must have an even number of segments, but " + path.canonicalString() + " has " + path.length());
    }

    /* access modifiers changed from: package-private */
    public DocumentKey getKey() {
        return this.key;
    }

    public FirebaseFirestore getFirestore() {
        return this.firestore;
    }

    public String getId() {
        return this.key.getDocumentId();
    }

    public CollectionReference getParent() {
        return new CollectionReference(this.key.getCollectionPath(), this.firestore);
    }

    public String getPath() {
        return this.key.getPath().canonicalString();
    }

    public CollectionReference collection(String collectionPath) {
        Preconditions.checkNotNull(collectionPath, "Provided collection path must not be null.");
        return new CollectionReference((ResourcePath) this.key.getPath().append(ResourcePath.fromString(collectionPath)), this.firestore);
    }

    public Task<Void> set(Object data) {
        return set(data, SetOptions.OVERWRITE);
    }

    public Task<Void> set(Object data, SetOptions options) {
        UserData.ParsedSetData parsed;
        Preconditions.checkNotNull(data, "Provided data must not be null.");
        Preconditions.checkNotNull(options, "Provided options must not be null.");
        if (options.isMerge()) {
            parsed = this.firestore.getUserDataReader().parseMergeData(data, options.getFieldMask());
        } else {
            parsed = this.firestore.getUserDataReader().parseSetData(data);
        }
        return this.firestore.getClient().write(Collections.singletonList(parsed.toMutation(this.key, Precondition.NONE))).continueWith(Executors.DIRECT_EXECUTOR, Util.voidErrorTransformer());
    }

    public Task<Void> update(Map<String, Object> data) {
        return update(this.firestore.getUserDataReader().parseUpdateData(data));
    }

    public Task<Void> update(String field, Object value, Object... moreFieldsAndValues) {
        return update(this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, field, value, moreFieldsAndValues)));
    }

    public Task<Void> update(FieldPath fieldPath, Object value, Object... moreFieldsAndValues) {
        return update(this.firestore.getUserDataReader().parseUpdateData(Util.collectUpdateArguments(1, fieldPath, value, moreFieldsAndValues)));
    }

    private Task<Void> update(UserData.ParsedUpdateData parsedData) {
        return this.firestore.getClient().write(Collections.singletonList(parsedData.toMutation(this.key, Precondition.exists(true)))).continueWith(Executors.DIRECT_EXECUTOR, Util.voidErrorTransformer());
    }

    public Task<Void> delete() {
        return this.firestore.getClient().write(Collections.singletonList(new DeleteMutation(this.key, Precondition.NONE))).continueWith(Executors.DIRECT_EXECUTOR, Util.voidErrorTransformer());
    }

    public Task<DocumentSnapshot> get() {
        return get(Source.DEFAULT);
    }

    public Task<DocumentSnapshot> get(Source source) {
        if (source == Source.CACHE) {
            return this.firestore.getClient().getDocumentFromLocalCache(this.key).continueWith(Executors.DIRECT_EXECUTOR, new DocumentReference$$ExternalSyntheticLambda0(this));
        }
        return getViaSnapshotListener(source);
    }

    /* renamed from: lambda$get$0$com-google-firebase-firestore-DocumentReference  reason: not valid java name */
    public /* synthetic */ DocumentSnapshot m443lambda$get$0$comgooglefirebasefirestoreDocumentReference(Task task) throws Exception {
        Document doc = (Document) task.getResult();
        return new DocumentSnapshot(this.firestore, this.key, doc, true, doc != null && doc.hasLocalMutations());
    }

    private Task<DocumentSnapshot> getViaSnapshotListener(Source source) {
        TaskCompletionSource<DocumentSnapshot> res = new TaskCompletionSource<>();
        TaskCompletionSource<ListenerRegistration> registration = new TaskCompletionSource<>();
        EventManager.ListenOptions options = new EventManager.ListenOptions();
        options.includeDocumentMetadataChanges = true;
        options.includeQueryMetadataChanges = true;
        options.waitForSyncWhenOnline = true;
        registration.setResult(addSnapshotListenerInternal(Executors.DIRECT_EXECUTOR, options, (Activity) null, new DocumentReference$$ExternalSyntheticLambda1(res, registration, source)));
        return res.getTask();
    }

    static /* synthetic */ void lambda$getViaSnapshotListener$1(TaskCompletionSource res, TaskCompletionSource registration, Source source, DocumentSnapshot snapshot, FirebaseFirestoreException error) {
        if (error != null) {
            res.setException(error);
            return;
        }
        try {
            ((ListenerRegistration) Tasks.await(registration.getTask())).remove();
            if (!snapshot.exists() && snapshot.getMetadata().isFromCache()) {
                res.setException(new FirebaseFirestoreException("Failed to get document because the client is offline.", FirebaseFirestoreException.Code.UNAVAILABLE));
            } else if (!snapshot.exists() || !snapshot.getMetadata().isFromCache() || source != Source.SERVER) {
                res.setResult(snapshot);
            } else {
                res.setException(new FirebaseFirestoreException("Failed to get document from server. (However, this document does exist in the local cache. Run again without setting source to SERVER to retrieve the cached document.)", FirebaseFirestoreException.Code.UNAVAILABLE));
            }
        } catch (ExecutionException e) {
            throw Assert.fail(e, "Failed to register a listener for a single document", new Object[0]);
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            throw Assert.fail(e2, "Failed to register a listener for a single document", new Object[0]);
        }
    }

    public ListenerRegistration addSnapshotListener(EventListener<DocumentSnapshot> listener) {
        return addSnapshotListener(MetadataChanges.EXCLUDE, listener);
    }

    public ListenerRegistration addSnapshotListener(Executor executor, EventListener<DocumentSnapshot> listener) {
        return addSnapshotListener(executor, MetadataChanges.EXCLUDE, listener);
    }

    public ListenerRegistration addSnapshotListener(Activity activity, EventListener<DocumentSnapshot> listener) {
        return addSnapshotListener(activity, MetadataChanges.EXCLUDE, listener);
    }

    public ListenerRegistration addSnapshotListener(MetadataChanges metadataChanges, EventListener<DocumentSnapshot> listener) {
        return addSnapshotListener(Executors.DEFAULT_CALLBACK_EXECUTOR, metadataChanges, listener);
    }

    public ListenerRegistration addSnapshotListener(Executor executor, MetadataChanges metadataChanges, EventListener<DocumentSnapshot> listener) {
        Preconditions.checkNotNull(executor, "Provided executor must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(listener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(executor, internalOptions(metadataChanges), (Activity) null, listener);
    }

    public ListenerRegistration addSnapshotListener(Activity activity, MetadataChanges metadataChanges, EventListener<DocumentSnapshot> listener) {
        Preconditions.checkNotNull(activity, "Provided activity must not be null.");
        Preconditions.checkNotNull(metadataChanges, "Provided MetadataChanges value must not be null.");
        Preconditions.checkNotNull(listener, "Provided EventListener must not be null.");
        return addSnapshotListenerInternal(Executors.DEFAULT_CALLBACK_EXECUTOR, internalOptions(metadataChanges), activity, listener);
    }

    private ListenerRegistration addSnapshotListenerInternal(Executor userExecutor, EventManager.ListenOptions options, Activity activity, EventListener<DocumentSnapshot> userListener) {
        AsyncEventListener<ViewSnapshot> asyncListener = new AsyncEventListener<>(userExecutor, new DocumentReference$$ExternalSyntheticLambda2(this, userListener));
        return ActivityScope.bind(activity, new ListenerRegistrationImpl(this.firestore.getClient(), this.firestore.getClient().listen(asQuery(), options, asyncListener), asyncListener));
    }

    /* renamed from: lambda$addSnapshotListenerInternal$2$com-google-firebase-firestore-DocumentReference */
    public /* synthetic */ void mo8436xb5c5a0f3(EventListener userListener, ViewSnapshot snapshot, FirebaseFirestoreException error) {
        DocumentSnapshot documentSnapshot;
        if (error != null) {
            userListener.onEvent(null, error);
            return;
        }
        boolean z = true;
        Assert.hardAssert(snapshot != null, "Got event without value or error set", new Object[0]);
        if (snapshot.getDocuments().size() > 1) {
            z = false;
        }
        Assert.hardAssert(z, "Too many documents returned on a document query", new Object[0]);
        Document document = snapshot.getDocuments().getDocument(this.key);
        if (document != null) {
            documentSnapshot = DocumentSnapshot.fromDocument(this.firestore, document, snapshot.isFromCache(), snapshot.getMutatedKeys().contains(document.getKey()));
        } else {
            documentSnapshot = DocumentSnapshot.fromNoDocument(this.firestore, this.key, snapshot.isFromCache());
        }
        userListener.onEvent(documentSnapshot, (FirebaseFirestoreException) null);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DocumentReference)) {
            return false;
        }
        DocumentReference that = (DocumentReference) o;
        if (!this.key.equals(that.key) || !this.firestore.equals(that.firestore)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.key.hashCode() * 31) + this.firestore.hashCode();
    }

    private Query asQuery() {
        return Query.atPath(this.key.getPath());
    }

    private static EventManager.ListenOptions internalOptions(MetadataChanges metadataChanges) {
        EventManager.ListenOptions internalOptions = new EventManager.ListenOptions();
        boolean z = true;
        internalOptions.includeDocumentMetadataChanges = metadataChanges == MetadataChanges.INCLUDE;
        if (metadataChanges != MetadataChanges.INCLUDE) {
            z = false;
        }
        internalOptions.includeQueryMetadataChanges = z;
        internalOptions.waitForSyncWhenOnline = false;
        return internalOptions;
    }
}
