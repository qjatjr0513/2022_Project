package com.google.firebase.firestore;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.emulators.EmulatedServiceSettings;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.firestore.auth.CredentialsProvider;
import com.google.firebase.firestore.auth.FirebaseAppCheckTokenProvider;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.core.ActivityScope;
import com.google.firebase.firestore.core.AsyncEventListener;
import com.google.firebase.firestore.core.DatabaseInfo;
import com.google.firebase.firestore.core.FirestoreClient;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.local.SQLitePersistence;
import com.google.firebase.firestore.model.DatabaseId;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.FieldPath;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.remote.FirestoreChannel;
import com.google.firebase.firestore.remote.GrpcMetadataProvider;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.ByteBufferInputStream;
import com.google.firebase.firestore.util.Executors;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Preconditions;
import com.google.firebase.inject.Deferred;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FirebaseFirestore {
    private static final String TAG = "FirebaseFirestore";
    private final CredentialsProvider<String> appCheckProvider;
    private final AsyncQueue asyncQueue;
    private final CredentialsProvider<User> authProvider;
    private volatile FirestoreClient client;
    private final Context context;
    private final DatabaseId databaseId;
    private EmulatedServiceSettings emulatorSettings;
    private final FirebaseApp firebaseApp;
    private final InstanceRegistry instanceRegistry;
    private final GrpcMetadataProvider metadataProvider;
    private final String persistenceKey;
    private FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().build();
    private final UserDataReader userDataReader;

    public interface InstanceRegistry {
        void remove(String str);
    }

    public static FirebaseFirestore getInstance() {
        FirebaseApp app2 = FirebaseApp.getInstance();
        if (app2 != null) {
            return getInstance(app2, DatabaseId.DEFAULT_DATABASE_ID);
        }
        throw new IllegalStateException("You must call FirebaseApp.initializeApp first.");
    }

    public static FirebaseFirestore getInstance(FirebaseApp app2) {
        return getInstance(app2, DatabaseId.DEFAULT_DATABASE_ID);
    }

    private static FirebaseFirestore getInstance(FirebaseApp app2, String database) {
        Preconditions.checkNotNull(app2, "Provided FirebaseApp must not be null.");
        FirestoreMultiDbComponent component = (FirestoreMultiDbComponent) app2.get(FirestoreMultiDbComponent.class);
        Preconditions.checkNotNull(component, "Firestore component is not present.");
        return component.get(database);
    }

    static FirebaseFirestore newInstance(Context context2, FirebaseApp app2, Deferred<InternalAuthProvider> deferredAuthProvider, Deferred<InternalAppCheckTokenProvider> deferredAppCheckTokenProvider, String database, InstanceRegistry instanceRegistry2, GrpcMetadataProvider metadataProvider2) {
        String projectId = app2.getOptions().getProjectId();
        if (projectId != null) {
            DatabaseId databaseId2 = DatabaseId.forDatabase(projectId, database);
            AsyncQueue queue = new AsyncQueue();
            return new FirebaseFirestore(context2, databaseId2, app2.getName(), new FirebaseAuthCredentialsProvider(deferredAuthProvider), new FirebaseAppCheckTokenProvider(deferredAppCheckTokenProvider), queue, app2, instanceRegistry2, metadataProvider2);
        }
        Deferred<InternalAuthProvider> deferred = deferredAuthProvider;
        Deferred<InternalAppCheckTokenProvider> deferred2 = deferredAppCheckTokenProvider;
        String str = database;
        throw new IllegalArgumentException("FirebaseOptions.getProjectId() cannot be null");
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Object, com.google.firebase.firestore.auth.CredentialsProvider<com.google.firebase.firestore.auth.User>] */
    /* JADX WARNING: type inference failed for: r6v0, types: [com.google.firebase.firestore.auth.CredentialsProvider<java.lang.String>, java.lang.Object] */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    FirebaseFirestore(android.content.Context r2, com.google.firebase.firestore.model.DatabaseId r3, java.lang.String r4, com.google.firebase.firestore.auth.CredentialsProvider<com.google.firebase.firestore.auth.User> r5, com.google.firebase.firestore.auth.CredentialsProvider<java.lang.String> r6, com.google.firebase.firestore.util.AsyncQueue r7, com.google.firebase.FirebaseApp r8, com.google.firebase.firestore.FirebaseFirestore.InstanceRegistry r9, com.google.firebase.firestore.remote.GrpcMetadataProvider r10) {
        /*
            r1 = this;
            r1.<init>()
            java.lang.Object r0 = com.google.firebase.firestore.util.Preconditions.checkNotNull(r2)
            android.content.Context r0 = (android.content.Context) r0
            r1.context = r0
            java.lang.Object r0 = com.google.firebase.firestore.util.Preconditions.checkNotNull(r3)
            com.google.firebase.firestore.model.DatabaseId r0 = (com.google.firebase.firestore.model.DatabaseId) r0
            java.lang.Object r0 = com.google.firebase.firestore.util.Preconditions.checkNotNull(r0)
            com.google.firebase.firestore.model.DatabaseId r0 = (com.google.firebase.firestore.model.DatabaseId) r0
            r1.databaseId = r0
            com.google.firebase.firestore.UserDataReader r0 = new com.google.firebase.firestore.UserDataReader
            r0.<init>(r3)
            r1.userDataReader = r0
            java.lang.Object r0 = com.google.firebase.firestore.util.Preconditions.checkNotNull(r4)
            java.lang.String r0 = (java.lang.String) r0
            r1.persistenceKey = r0
            java.lang.Object r0 = com.google.firebase.firestore.util.Preconditions.checkNotNull(r5)
            com.google.firebase.firestore.auth.CredentialsProvider r0 = (com.google.firebase.firestore.auth.CredentialsProvider) r0
            r1.authProvider = r0
            java.lang.Object r0 = com.google.firebase.firestore.util.Preconditions.checkNotNull(r6)
            com.google.firebase.firestore.auth.CredentialsProvider r0 = (com.google.firebase.firestore.auth.CredentialsProvider) r0
            r1.appCheckProvider = r0
            java.lang.Object r0 = com.google.firebase.firestore.util.Preconditions.checkNotNull(r7)
            com.google.firebase.firestore.util.AsyncQueue r0 = (com.google.firebase.firestore.util.AsyncQueue) r0
            r1.asyncQueue = r0
            r1.firebaseApp = r8
            r1.instanceRegistry = r9
            r1.metadataProvider = r10
            com.google.firebase.firestore.FirebaseFirestoreSettings$Builder r0 = new com.google.firebase.firestore.FirebaseFirestoreSettings$Builder
            r0.<init>()
            com.google.firebase.firestore.FirebaseFirestoreSettings r0 = r0.build()
            r1.settings = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.FirebaseFirestore.<init>(android.content.Context, com.google.firebase.firestore.model.DatabaseId, java.lang.String, com.google.firebase.firestore.auth.CredentialsProvider, com.google.firebase.firestore.auth.CredentialsProvider, com.google.firebase.firestore.util.AsyncQueue, com.google.firebase.FirebaseApp, com.google.firebase.firestore.FirebaseFirestore$InstanceRegistry, com.google.firebase.firestore.remote.GrpcMetadataProvider):void");
    }

    public FirebaseFirestoreSettings getFirestoreSettings() {
        return this.settings;
    }

    public void setFirestoreSettings(FirebaseFirestoreSettings settings2) {
        FirebaseFirestoreSettings settings3 = mergeEmulatorSettings(settings2, this.emulatorSettings);
        synchronized (this.databaseId) {
            Preconditions.checkNotNull(settings3, "Provided settings must not be null.");
            if (this.client != null) {
                if (!this.settings.equals(settings3)) {
                    throw new IllegalStateException("FirebaseFirestore has already been started and its settings can no longer be changed. You can only call setFirestoreSettings() before calling any other methods on a FirebaseFirestore object.");
                }
            }
            this.settings = settings3;
        }
    }

    public void useEmulator(String host, int port) {
        if (this.client == null) {
            EmulatedServiceSettings emulatedServiceSettings = new EmulatedServiceSettings(host, port);
            this.emulatorSettings = emulatedServiceSettings;
            this.settings = mergeEmulatorSettings(this.settings, emulatedServiceSettings);
            return;
        }
        throw new IllegalStateException("Cannot call useEmulator() after instance has already been initialized.");
    }

    private void ensureClientConfigured() {
        if (this.client == null) {
            synchronized (this.databaseId) {
                if (this.client == null) {
                    this.client = new FirestoreClient(this.context, new DatabaseInfo(this.databaseId, this.persistenceKey, this.settings.getHost(), this.settings.isSslEnabled()), this.settings, this.authProvider, this.appCheckProvider, this.asyncQueue, this.metadataProvider);
                }
            }
        }
    }

    private FirebaseFirestoreSettings mergeEmulatorSettings(FirebaseFirestoreSettings settings2, EmulatedServiceSettings emulatorSettings2) {
        if (emulatorSettings2 == null) {
            return settings2;
        }
        if (!FirebaseFirestoreSettings.DEFAULT_HOST.equals(settings2.getHost())) {
            Logger.warn(TAG, "Host has been set in FirebaseFirestoreSettings and useEmulator, emulator host will be used.", new Object[0]);
        }
        return new FirebaseFirestoreSettings.Builder(settings2).setHost(emulatorSettings2.getHost() + ":" + emulatorSettings2.getPort()).setSslEnabled(false).build();
    }

    public FirebaseApp getApp() {
        return this.firebaseApp;
    }

    public Task<Void> setIndexConfiguration(String json) {
        ensureClientConfigured();
        Preconditions.checkState(this.settings.isPersistenceEnabled(), "Cannot enable indexes when persistence is disabled");
        List<FieldIndex> parsedIndexes = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            if (jsonObject.has("indexes")) {
                JSONArray indexes = jsonObject.getJSONArray("indexes");
                for (int i = 0; i < indexes.length(); i++) {
                    JSONObject definition = indexes.getJSONObject(i);
                    String collectionGroup = definition.getString("collectionGroup");
                    List<FieldIndex.Segment> segments = new ArrayList<>();
                    JSONArray fields = definition.optJSONArray("fields");
                    int f = 0;
                    while (fields != null && f < fields.length()) {
                        JSONObject field = fields.getJSONObject(f);
                        FieldPath fieldPath = FieldPath.fromServerFormat(field.getString("fieldPath"));
                        if ("CONTAINS".equals(field.optString("arrayConfig"))) {
                            segments.add(FieldIndex.Segment.create(fieldPath, FieldIndex.Segment.Kind.CONTAINS));
                        } else if ("ASCENDING".equals(field.optString("order"))) {
                            segments.add(FieldIndex.Segment.create(fieldPath, FieldIndex.Segment.Kind.ASCENDING));
                        } else {
                            segments.add(FieldIndex.Segment.create(fieldPath, FieldIndex.Segment.Kind.DESCENDING));
                        }
                        f++;
                    }
                    parsedIndexes.add(FieldIndex.create(-1, collectionGroup, segments, FieldIndex.INITIAL_STATE));
                }
            }
            return this.client.configureFieldIndexes(parsedIndexes);
        } catch (JSONException e) {
            throw new IllegalArgumentException("Failed to parse index configuration", e);
        }
    }

    public CollectionReference collection(String collectionPath) {
        Preconditions.checkNotNull(collectionPath, "Provided collection path must not be null.");
        ensureClientConfigured();
        return new CollectionReference(ResourcePath.fromString(collectionPath), this);
    }

    public DocumentReference document(String documentPath) {
        Preconditions.checkNotNull(documentPath, "Provided document path must not be null.");
        ensureClientConfigured();
        return DocumentReference.forPath(ResourcePath.fromString(documentPath), this);
    }

    public Query collectionGroup(String collectionId) {
        Preconditions.checkNotNull(collectionId, "Provided collection ID must not be null.");
        if (!collectionId.contains("/")) {
            ensureClientConfigured();
            return new Query(new Query(ResourcePath.EMPTY, collectionId), this);
        }
        throw new IllegalArgumentException(String.format("Invalid collectionId '%s'. Collection IDs must not contain '/'.", new Object[]{collectionId}));
    }

    private <ResultT> Task<ResultT> runTransaction(Transaction.Function<ResultT> updateFunction, Executor executor) {
        ensureClientConfigured();
        return this.client.transaction(new FirebaseFirestore$$ExternalSyntheticLambda3(this, executor, updateFunction));
    }

    /* renamed from: lambda$runTransaction$1$com-google-firebase-firestore-FirebaseFirestore */
    public /* synthetic */ Task mo8511x911046dd(Executor executor, Transaction.Function updateFunction, com.google.firebase.firestore.core.Transaction internalTransaction) {
        return Tasks.call(executor, new FirebaseFirestore$$ExternalSyntheticLambda5(this, updateFunction, internalTransaction));
    }

    /* renamed from: lambda$runTransaction$0$com-google-firebase-firestore-FirebaseFirestore */
    public /* synthetic */ Object mo8510x9186acdc(Transaction.Function updateFunction, com.google.firebase.firestore.core.Transaction internalTransaction) throws Exception {
        return updateFunction.apply(new Transaction(internalTransaction, this));
    }

    public <TResult> Task<TResult> runTransaction(Transaction.Function<TResult> updateFunction) {
        Preconditions.checkNotNull(updateFunction, "Provided transaction update function must not be null.");
        return runTransaction(updateFunction, com.google.firebase.firestore.core.Transaction.getDefaultExecutor());
    }

    public WriteBatch batch() {
        ensureClientConfigured();
        return new WriteBatch(this);
    }

    public Task<Void> runBatch(WriteBatch.Function batchFunction) {
        WriteBatch batch = batch();
        batchFunction.apply(batch);
        return batch.commit();
    }

    public Task<Void> terminate() {
        this.instanceRegistry.remove(getDatabaseId().getDatabaseId());
        ensureClientConfigured();
        return this.client.terminate();
    }

    public Task<Void> waitForPendingWrites() {
        return this.client.waitForPendingWrites();
    }

    /* access modifiers changed from: package-private */
    public AsyncQueue getAsyncQueue() {
        return this.asyncQueue;
    }

    public Task<Void> enableNetwork() {
        ensureClientConfigured();
        return this.client.enableNetwork();
    }

    public Task<Void> disableNetwork() {
        ensureClientConfigured();
        return this.client.disableNetwork();
    }

    public static void setLoggingEnabled(boolean loggingEnabled) {
        if (loggingEnabled) {
            Logger.setLogLevel(Logger.Level.DEBUG);
        } else {
            Logger.setLogLevel(Logger.Level.WARN);
        }
    }

    public Task<Void> clearPersistence() {
        TaskCompletionSource<Void> source = new TaskCompletionSource<>();
        this.asyncQueue.enqueueAndForgetEvenAfterShutdown(new FirebaseFirestore$$ExternalSyntheticLambda4(this, source));
        return source.getTask();
    }

    /* renamed from: lambda$clearPersistence$2$com-google-firebase-firestore-FirebaseFirestore */
    public /* synthetic */ void mo8508x422b1e9d(TaskCompletionSource source) {
        try {
            if (this.client != null) {
                if (!this.client.isTerminated()) {
                    throw new FirebaseFirestoreException("Persistence cannot be cleared while the firestore instance is running.", FirebaseFirestoreException.Code.FAILED_PRECONDITION);
                }
            }
            SQLitePersistence.clearPersistence(this.context, this.databaseId, this.persistenceKey);
            source.setResult(null);
        } catch (FirebaseFirestoreException e) {
            source.setException(e);
        }
    }

    public ListenerRegistration addSnapshotsInSyncListener(Runnable runnable) {
        return addSnapshotsInSyncListener(Executors.DEFAULT_CALLBACK_EXECUTOR, runnable);
    }

    public ListenerRegistration addSnapshotsInSyncListener(Activity activity, Runnable runnable) {
        return addSnapshotsInSyncListener(Executors.DEFAULT_CALLBACK_EXECUTOR, activity, runnable);
    }

    public ListenerRegistration addSnapshotsInSyncListener(Executor executor, Runnable runnable) {
        return addSnapshotsInSyncListener(executor, (Activity) null, runnable);
    }

    public LoadBundleTask loadBundle(InputStream bundleData) {
        ensureClientConfigured();
        LoadBundleTask resultTask = new LoadBundleTask();
        this.client.loadBundle(bundleData, resultTask);
        return resultTask;
    }

    public LoadBundleTask loadBundle(byte[] bundleData) {
        return loadBundle((InputStream) new ByteArrayInputStream(bundleData));
    }

    public LoadBundleTask loadBundle(ByteBuffer bundleData) {
        return loadBundle((InputStream) new ByteBufferInputStream(bundleData));
    }

    public Task<Query> getNamedQuery(String name) {
        ensureClientConfigured();
        return this.client.getNamedQuery(name).continueWith(new FirebaseFirestore$$ExternalSyntheticLambda0(this));
    }

    /* renamed from: lambda$getNamedQuery$3$com-google-firebase-firestore-FirebaseFirestore */
    public /* synthetic */ Query mo8509xe4dfadf7(Task task) throws Exception {
        Query query = (Query) task.getResult();
        if (query != null) {
            return new Query(query, this);
        }
        return null;
    }

    private ListenerRegistration addSnapshotsInSyncListener(Executor userExecutor, Activity activity, Runnable runnable) {
        ensureClientConfigured();
        AsyncEventListener<Void> asyncListener = new AsyncEventListener<>(userExecutor, new FirebaseFirestore$$ExternalSyntheticLambda1(runnable));
        this.client.addSnapshotsInSyncListener(asyncListener);
        return ActivityScope.bind(activity, new FirebaseFirestore$$ExternalSyntheticLambda2(this, asyncListener));
    }

    static /* synthetic */ void lambda$addSnapshotsInSyncListener$4(Runnable runnable, Void v, FirebaseFirestoreException error) {
        Assert.hardAssert(error == null, "snapshots-in-sync listeners should never get errors.", new Object[0]);
        runnable.run();
    }

    /* renamed from: lambda$addSnapshotsInSyncListener$5$com-google-firebase-firestore-FirebaseFirestore */
    public /* synthetic */ void mo8507xb65623b0(AsyncEventListener asyncListener) {
        asyncListener.mute();
        this.client.removeSnapshotsInSyncListener(asyncListener);
    }

    /* access modifiers changed from: package-private */
    public FirestoreClient getClient() {
        return this.client;
    }

    /* access modifiers changed from: package-private */
    public DatabaseId getDatabaseId() {
        return this.databaseId;
    }

    /* access modifiers changed from: package-private */
    public UserDataReader getUserDataReader() {
        return this.userDataReader;
    }

    /* access modifiers changed from: package-private */
    public void validateReference(DocumentReference docRef) {
        Preconditions.checkNotNull(docRef, "Provided DocumentReference must not be null.");
        if (docRef.getFirestore() != this) {
            throw new IllegalArgumentException("Provided document reference is from a different Cloud Firestore instance.");
        }
    }

    static void setClientLanguage(String languageToken) {
        FirestoreChannel.setClientLanguage(languageToken);
    }
}
