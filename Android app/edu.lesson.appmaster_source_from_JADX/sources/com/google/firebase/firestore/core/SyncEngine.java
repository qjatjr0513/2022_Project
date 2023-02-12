package com.google.firebase.firestore.core;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.LoadBundleTask;
import com.google.firebase.firestore.LoadBundleTaskProgress;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.bundle.BundleElement;
import com.google.firebase.firestore.bundle.BundleLoader;
import com.google.firebase.firestore.bundle.BundleMetadata;
import com.google.firebase.firestore.bundle.BundleReader;
import com.google.firebase.firestore.core.LimboDocumentChange;
import com.google.firebase.firestore.core.View;
import com.google.firebase.firestore.core.ViewSnapshot;
import com.google.firebase.firestore.local.LocalDocumentsResult;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.LocalViewChanges;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.QueryResult;
import com.google.firebase.firestore.local.ReferenceSet;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatchResult;
import com.google.firebase.firestore.remote.RemoteEvent;
import com.google.firebase.firestore.remote.RemoteStore;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Function;
import com.google.firebase.firestore.util.Logger;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import p004io.grpc.Status;

public class SyncEngine implements RemoteStore.RemoteStoreCallback {
    private static final String TAG = SyncEngine.class.getSimpleName();
    private final Map<Integer, LimboResolution> activeLimboResolutionsByTarget = new HashMap();
    private final Map<DocumentKey, Integer> activeLimboTargetsByKey = new HashMap();
    private User currentUser;
    private final LinkedHashSet<DocumentKey> enqueuedLimboResolutions = new LinkedHashSet<>();
    private final ReferenceSet limboDocumentRefs = new ReferenceSet();
    private final LocalStore localStore;
    private final int maxConcurrentLimboResolutions;
    private final Map<User, Map<Integer, TaskCompletionSource<Void>>> mutationUserCallbacks = new HashMap();
    private final Map<Integer, List<TaskCompletionSource<Void>>> pendingWritesCallbacks;
    private final Map<Integer, List<Query>> queriesByTarget = new HashMap();
    private final Map<Query, QueryView> queryViewsByQuery = new HashMap();
    private final RemoteStore remoteStore;
    private SyncEngineCallback syncEngineListener;
    private final TargetIdGenerator targetIdGenerator = TargetIdGenerator.forSyncEngine();

    interface SyncEngineCallback {
        void handleOnlineStateChange(OnlineState onlineState);

        void onError(Query query, Status status);

        void onViewSnapshots(List<ViewSnapshot> list);
    }

    private static class LimboResolution {
        /* access modifiers changed from: private */
        public final DocumentKey key;
        /* access modifiers changed from: private */
        public boolean receivedDocument;

        LimboResolution(DocumentKey key2) {
            this.key = key2;
        }
    }

    public SyncEngine(LocalStore localStore2, RemoteStore remoteStore2, User initialUser, int maxConcurrentLimboResolutions2) {
        this.localStore = localStore2;
        this.remoteStore = remoteStore2;
        this.maxConcurrentLimboResolutions = maxConcurrentLimboResolutions2;
        this.currentUser = initialUser;
        this.pendingWritesCallbacks = new HashMap();
    }

    public void setCallback(SyncEngineCallback callback) {
        this.syncEngineListener = callback;
    }

    private void assertCallback(String method) {
        Assert.hardAssert(this.syncEngineListener != null, "Trying to call %s before setting callback", method);
    }

    public int listen(Query query) {
        assertCallback("listen");
        Assert.hardAssert(!this.queryViewsByQuery.containsKey(query), "We already listen to query: %s", query);
        TargetData targetData = this.localStore.allocateTarget(query.toTarget());
        this.remoteStore.listen(targetData);
        this.syncEngineListener.onViewSnapshots(Collections.singletonList(initializeViewAndComputeSnapshot(query, targetData.getTargetId())));
        return targetData.getTargetId();
    }

    private ViewSnapshot initializeViewAndComputeSnapshot(Query query, int targetId) {
        QueryResult queryResult = this.localStore.executeQuery(query, true);
        ViewSnapshot.SyncState syncState = ViewSnapshot.SyncState.NONE;
        TargetChange synthesizedCurrentChange = null;
        if (this.queriesByTarget.get(Integer.valueOf(targetId)) != null) {
            boolean z = false;
            if (this.queryViewsByQuery.get((Query) this.queriesByTarget.get(Integer.valueOf(targetId)).get(0)).getView().getSyncState() == ViewSnapshot.SyncState.SYNCED) {
                z = true;
            }
            synthesizedCurrentChange = TargetChange.createSynthesizedTargetChangeForCurrentChange(z);
        }
        View view = new View(query, queryResult.getRemoteKeys());
        ViewChange viewChange = view.applyChanges(view.computeDocChanges(queryResult.getDocuments()), synthesizedCurrentChange);
        updateTrackedLimboDocuments(viewChange.getLimboChanges(), targetId);
        this.queryViewsByQuery.put(query, new QueryView(query, targetId, view));
        if (!this.queriesByTarget.containsKey(Integer.valueOf(targetId))) {
            this.queriesByTarget.put(Integer.valueOf(targetId), new ArrayList(1));
        }
        this.queriesByTarget.get(Integer.valueOf(targetId)).add(query);
        return viewChange.getSnapshot();
    }

    /* access modifiers changed from: package-private */
    public void stopListening(Query query) {
        assertCallback("stopListening");
        QueryView queryView = this.queryViewsByQuery.get(query);
        Assert.hardAssert(queryView != null, "Trying to stop listening to a query not found", new Object[0]);
        this.queryViewsByQuery.remove(query);
        int targetId = queryView.getTargetId();
        List<Query> targetQueries = this.queriesByTarget.get(Integer.valueOf(targetId));
        targetQueries.remove(query);
        if (targetQueries.isEmpty()) {
            this.localStore.releaseTarget(targetId);
            this.remoteStore.stopListening(targetId);
            removeAndCleanupTarget(targetId, Status.f313OK);
        }
    }

    public void writeMutations(List<Mutation> mutations, TaskCompletionSource<Void> userTask) {
        assertCallback("writeMutations");
        LocalDocumentsResult result = this.localStore.writeLocally(mutations);
        addUserCallback(result.getBatchId(), userTask);
        emitNewSnapsAndNotifyLocalStore(result.getDocuments(), (RemoteEvent) null);
        this.remoteStore.fillWritePipeline();
    }

    private void addUserCallback(int batchId, TaskCompletionSource<Void> userTask) {
        Map<Integer, TaskCompletionSource<Void>> userTasks = this.mutationUserCallbacks.get(this.currentUser);
        if (userTasks == null) {
            userTasks = new HashMap<>();
            this.mutationUserCallbacks.put(this.currentUser, userTasks);
        }
        userTasks.put(Integer.valueOf(batchId), userTask);
    }

    public <TResult> Task<TResult> transaction(AsyncQueue asyncQueue, Function<Transaction, Task<TResult>> updateFunction) {
        return new TransactionRunner(asyncQueue, this.remoteStore, updateFunction).run();
    }

    public void handleRemoteEvent(RemoteEvent event) {
        assertCallback("handleRemoteEvent");
        for (Map.Entry<Integer, TargetChange> entry : event.getTargetChanges().entrySet()) {
            TargetChange targetChange = entry.getValue();
            LimboResolution limboResolution = this.activeLimboResolutionsByTarget.get(entry.getKey());
            if (limboResolution != null) {
                Assert.hardAssert((targetChange.getAddedDocuments().size() + targetChange.getModifiedDocuments().size()) + targetChange.getRemovedDocuments().size() <= 1, "Limbo resolution for single document contains multiple changes.", new Object[0]);
                if (targetChange.getAddedDocuments().size() > 0) {
                    boolean unused = limboResolution.receivedDocument = true;
                } else if (targetChange.getModifiedDocuments().size() > 0) {
                    Assert.hardAssert(limboResolution.receivedDocument, "Received change for limbo target document without add.", new Object[0]);
                } else if (targetChange.getRemovedDocuments().size() > 0) {
                    Assert.hardAssert(limboResolution.receivedDocument, "Received remove for limbo target document without add.", new Object[0]);
                    boolean unused2 = limboResolution.receivedDocument = false;
                }
            }
        }
        emitNewSnapsAndNotifyLocalStore(this.localStore.applyRemoteEvent(event), event);
    }

    public void handleOnlineStateChange(OnlineState onlineState) {
        assertCallback("handleOnlineStateChange");
        ArrayList<ViewSnapshot> newViewSnapshots = new ArrayList<>();
        for (Map.Entry<Query, QueryView> entry : this.queryViewsByQuery.entrySet()) {
            ViewChange viewChange = entry.getValue().getView().applyOnlineStateChange(onlineState);
            Assert.hardAssert(viewChange.getLimboChanges().isEmpty(), "OnlineState should not affect limbo documents.", new Object[0]);
            if (viewChange.getSnapshot() != null) {
                newViewSnapshots.add(viewChange.getSnapshot());
            }
        }
        this.syncEngineListener.onViewSnapshots(newViewSnapshots);
        this.syncEngineListener.handleOnlineStateChange(onlineState);
    }

    public ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int targetId) {
        LimboResolution limboResolution = this.activeLimboResolutionsByTarget.get(Integer.valueOf(targetId));
        if (limboResolution != null && limboResolution.receivedDocument) {
            return DocumentKey.emptyKeySet().insert(limboResolution.key);
        }
        ImmutableSortedSet<DocumentKey> remoteKeys = DocumentKey.emptyKeySet();
        if (this.queriesByTarget.containsKey(Integer.valueOf(targetId))) {
            for (Query query : this.queriesByTarget.get(Integer.valueOf(targetId))) {
                if (this.queryViewsByQuery.containsKey(query)) {
                    remoteKeys = remoteKeys.unionWith(this.queryViewsByQuery.get(query).getView().getSyncedDocuments());
                }
            }
        }
        return remoteKeys;
    }

    public void handleRejectedListen(int targetId, Status error) {
        assertCallback("handleRejectedListen");
        LimboResolution limboResolution = this.activeLimboResolutionsByTarget.get(Integer.valueOf(targetId));
        DocumentKey limboKey = limboResolution != null ? limboResolution.key : null;
        if (limboKey != null) {
            this.activeLimboTargetsByKey.remove(limboKey);
            this.activeLimboResolutionsByTarget.remove(Integer.valueOf(targetId));
            pumpEnqueuedLimboResolutions();
            handleRemoteEvent(new RemoteEvent(SnapshotVersion.NONE, Collections.emptyMap(), Collections.emptySet(), Collections.singletonMap(limboKey, MutableDocument.newNoDocument(limboKey, SnapshotVersion.NONE)), Collections.singleton(limboKey)));
            return;
        }
        this.localStore.releaseTarget(targetId);
        removeAndCleanupTarget(targetId, error);
    }

    public void handleSuccessfulWrite(MutationBatchResult mutationBatchResult) {
        assertCallback("handleSuccessfulWrite");
        notifyUser(mutationBatchResult.getBatch().getBatchId(), (Status) null);
        resolvePendingWriteTasks(mutationBatchResult.getBatch().getBatchId());
        emitNewSnapsAndNotifyLocalStore(this.localStore.acknowledgeBatch(mutationBatchResult), (RemoteEvent) null);
    }

    public void handleRejectedWrite(int batchId, Status status) {
        assertCallback("handleRejectedWrite");
        ImmutableSortedMap<DocumentKey, Document> changes = this.localStore.rejectBatch(batchId);
        if (!changes.isEmpty()) {
            logErrorIfInteresting(status, "Write failed at %s", changes.getMinKey().getPath());
        }
        notifyUser(batchId, status);
        resolvePendingWriteTasks(batchId);
        emitNewSnapsAndNotifyLocalStore(changes, (RemoteEvent) null);
    }

    public void registerPendingWritesTask(TaskCompletionSource<Void> userTask) {
        if (!this.remoteStore.canUseNetwork()) {
            Logger.debug(TAG, "The network is disabled. The task returned by 'awaitPendingWrites()' will not complete until the network is enabled.", new Object[0]);
        }
        int largestPendingBatchId = this.localStore.getHighestUnacknowledgedBatchId();
        if (largestPendingBatchId == -1) {
            userTask.setResult(null);
            return;
        }
        if (!this.pendingWritesCallbacks.containsKey(Integer.valueOf(largestPendingBatchId))) {
            this.pendingWritesCallbacks.put(Integer.valueOf(largestPendingBatchId), new ArrayList());
        }
        this.pendingWritesCallbacks.get(Integer.valueOf(largestPendingBatchId)).add(userTask);
    }

    private void resolvePendingWriteTasks(int batchId) {
        if (this.pendingWritesCallbacks.containsKey(Integer.valueOf(batchId))) {
            for (TaskCompletionSource<Void> task : this.pendingWritesCallbacks.get(Integer.valueOf(batchId))) {
                task.setResult(null);
            }
            this.pendingWritesCallbacks.remove(Integer.valueOf(batchId));
        }
    }

    private void failOutstandingPendingWritesAwaitingTasks() {
        for (Map.Entry<Integer, List<TaskCompletionSource<Void>>> entry : this.pendingWritesCallbacks.entrySet()) {
            for (TaskCompletionSource<Void> task : entry.getValue()) {
                task.setException(new FirebaseFirestoreException("'waitForPendingWrites' task is cancelled due to User change.", FirebaseFirestoreException.Code.CANCELLED));
            }
        }
        this.pendingWritesCallbacks.clear();
    }

    public void loadBundle(BundleReader bundleReader, LoadBundleTask resultTask) {
        LoadBundleTask loadBundleTask = resultTask;
        try {
            BundleMetadata bundleMetadata = bundleReader.getBundleMetadata();
            if (this.localStore.hasNewerBundle(bundleMetadata)) {
                loadBundleTask.setResult(LoadBundleTaskProgress.forSuccess(bundleMetadata));
                try {
                    bundleReader.close();
                } catch (IOException e) {
                    Logger.warn("SyncEngine", "Exception while closing bundle", e);
                }
            } else {
                loadBundleTask.updateProgress(LoadBundleTaskProgress.forInitial(bundleMetadata));
                BundleLoader bundleLoader = new BundleLoader(this.localStore, bundleMetadata);
                long currentBytesRead = 0;
                while (true) {
                    BundleElement nextElement = bundleReader.getNextElement();
                    BundleElement bundleElement = nextElement;
                    if (nextElement != null) {
                        long oldBytesRead = currentBytesRead;
                        currentBytesRead = bundleReader.getBytesRead();
                        LoadBundleTaskProgress progress = bundleLoader.addElement(bundleElement, currentBytesRead - oldBytesRead);
                        if (progress != null) {
                            loadBundleTask.updateProgress(progress);
                        }
                    } else {
                        emitNewSnapsAndNotifyLocalStore(bundleLoader.applyChanges(), (RemoteEvent) null);
                        this.localStore.saveBundle(bundleMetadata);
                        loadBundleTask.setResult(LoadBundleTaskProgress.forSuccess(bundleMetadata));
                        try {
                            bundleReader.close();
                            return;
                        } catch (IOException e2) {
                            Logger.warn("SyncEngine", "Exception while closing bundle", e2);
                            return;
                        }
                    }
                }
            }
        } catch (Exception e3) {
            Logger.warn("Firestore", "Loading bundle failed : %s", e3);
            loadBundleTask.setException(new FirebaseFirestoreException("Bundle failed to load", FirebaseFirestoreException.Code.INVALID_ARGUMENT, e3));
            try {
                bundleReader.close();
            } catch (IOException e4) {
                Logger.warn("SyncEngine", "Exception while closing bundle", e4);
            }
        } catch (Throwable e5) {
            IOException iOException = e5;
            try {
                bundleReader.close();
            } catch (IOException e6) {
                Logger.warn("SyncEngine", "Exception while closing bundle", e6);
            }
            throw iOException;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r1 = java.lang.Integer.valueOf(r5);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void notifyUser(int r5, p004io.grpc.Status r6) {
        /*
            r4 = this;
            java.util.Map<com.google.firebase.firestore.auth.User, java.util.Map<java.lang.Integer, com.google.android.gms.tasks.TaskCompletionSource<java.lang.Void>>> r0 = r4.mutationUserCallbacks
            com.google.firebase.firestore.auth.User r1 = r4.currentUser
            java.lang.Object r0 = r0.get(r1)
            java.util.Map r0 = (java.util.Map) r0
            if (r0 == 0) goto L_0x0029
            java.lang.Integer r1 = java.lang.Integer.valueOf(r5)
            java.lang.Object r2 = r0.get(r1)
            com.google.android.gms.tasks.TaskCompletionSource r2 = (com.google.android.gms.tasks.TaskCompletionSource) r2
            if (r2 == 0) goto L_0x0029
            if (r6 == 0) goto L_0x0022
            com.google.firebase.firestore.FirebaseFirestoreException r3 = com.google.firebase.firestore.util.Util.exceptionFromStatus(r6)
            r2.setException(r3)
            goto L_0x0026
        L_0x0022:
            r3 = 0
            r2.setResult(r3)
        L_0x0026:
            r0.remove(r1)
        L_0x0029:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.firestore.core.SyncEngine.notifyUser(int, io.grpc.Status):void");
    }

    private void removeAndCleanupTarget(int targetId, Status status) {
        for (Query query : this.queriesByTarget.get(Integer.valueOf(targetId))) {
            this.queryViewsByQuery.remove(query);
            if (!status.isOk()) {
                this.syncEngineListener.onError(query, status);
                logErrorIfInteresting(status, "Listen for %s failed", query);
            }
        }
        this.queriesByTarget.remove(Integer.valueOf(targetId));
        ImmutableSortedSet<DocumentKey> limboKeys = this.limboDocumentRefs.referencesForId(targetId);
        this.limboDocumentRefs.removeReferencesForId(targetId);
        Iterator<DocumentKey> it = limboKeys.iterator();
        while (it.hasNext()) {
            DocumentKey key = it.next();
            if (!this.limboDocumentRefs.containsKey(key)) {
                removeLimboTarget(key);
            }
        }
    }

    private void removeLimboTarget(DocumentKey key) {
        this.enqueuedLimboResolutions.remove(key);
        Integer targetId = this.activeLimboTargetsByKey.get(key);
        if (targetId != null) {
            this.remoteStore.stopListening(targetId.intValue());
            this.activeLimboTargetsByKey.remove(key);
            this.activeLimboResolutionsByTarget.remove(targetId);
            pumpEnqueuedLimboResolutions();
        }
    }

    private void emitNewSnapsAndNotifyLocalStore(ImmutableSortedMap<DocumentKey, Document> changes, RemoteEvent remoteEvent) {
        List<ViewSnapshot> newSnapshots = new ArrayList<>();
        List<LocalViewChanges> documentChangesInAllViews = new ArrayList<>();
        for (Map.Entry<Query, QueryView> entry : this.queryViewsByQuery.entrySet()) {
            QueryView queryView = entry.getValue();
            View view = queryView.getView();
            View.DocumentChanges viewDocChanges = view.computeDocChanges(changes);
            if (viewDocChanges.needsRefill()) {
                viewDocChanges = view.computeDocChanges(this.localStore.executeQuery(queryView.getQuery(), false).getDocuments(), viewDocChanges);
            }
            ViewChange viewChange = queryView.getView().applyChanges(viewDocChanges, remoteEvent == null ? null : remoteEvent.getTargetChanges().get(Integer.valueOf(queryView.getTargetId())));
            updateTrackedLimboDocuments(viewChange.getLimboChanges(), queryView.getTargetId());
            if (viewChange.getSnapshot() != null) {
                newSnapshots.add(viewChange.getSnapshot());
                documentChangesInAllViews.add(LocalViewChanges.fromViewSnapshot(queryView.getTargetId(), viewChange.getSnapshot()));
            }
        }
        this.syncEngineListener.onViewSnapshots(newSnapshots);
        this.localStore.notifyLocalViewChanges(documentChangesInAllViews);
    }

    /* renamed from: com.google.firebase.firestore.core.SyncEngine$1 */
    static /* synthetic */ class C07621 {

        /* renamed from: $SwitchMap$com$google$firebase$firestore$core$LimboDocumentChange$Type */
        static final /* synthetic */ int[] f155x84ba890d;

        static {
            int[] iArr = new int[LimboDocumentChange.Type.values().length];
            f155x84ba890d = iArr;
            try {
                iArr[LimboDocumentChange.Type.ADDED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f155x84ba890d[LimboDocumentChange.Type.REMOVED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    private void updateTrackedLimboDocuments(List<LimboDocumentChange> limboChanges, int targetId) {
        for (LimboDocumentChange limboChange : limboChanges) {
            switch (C07621.f155x84ba890d[limboChange.getType().ordinal()]) {
                case 1:
                    this.limboDocumentRefs.addReference(limboChange.getKey(), targetId);
                    trackLimboChange(limboChange);
                    break;
                case 2:
                    Logger.debug(TAG, "Document no longer in limbo: %s", limboChange.getKey());
                    DocumentKey limboDocKey = limboChange.getKey();
                    this.limboDocumentRefs.removeReference(limboDocKey, targetId);
                    if (this.limboDocumentRefs.containsKey(limboDocKey)) {
                        break;
                    } else {
                        removeLimboTarget(limboDocKey);
                        break;
                    }
                default:
                    throw Assert.fail("Unknown limbo change type: %s", limboChange.getType());
            }
        }
    }

    private void trackLimboChange(LimboDocumentChange change) {
        DocumentKey key = change.getKey();
        if (!this.activeLimboTargetsByKey.containsKey(key) && !this.enqueuedLimboResolutions.contains(key)) {
            Logger.debug(TAG, "New document in limbo: %s", key);
            this.enqueuedLimboResolutions.add(key);
            pumpEnqueuedLimboResolutions();
        }
    }

    private void pumpEnqueuedLimboResolutions() {
        while (!this.enqueuedLimboResolutions.isEmpty() && this.activeLimboTargetsByKey.size() < this.maxConcurrentLimboResolutions) {
            Iterator<DocumentKey> it = this.enqueuedLimboResolutions.iterator();
            DocumentKey key = it.next();
            it.remove();
            int limboTargetId = this.targetIdGenerator.nextId();
            this.activeLimboResolutionsByTarget.put(Integer.valueOf(limboTargetId), new LimboResolution(key));
            this.activeLimboTargetsByKey.put(key, Integer.valueOf(limboTargetId));
            this.remoteStore.listen(new TargetData(Query.atPath(key.getPath()).toTarget(), limboTargetId, -1, QueryPurpose.LIMBO_RESOLUTION));
        }
    }

    public Map<DocumentKey, Integer> getActiveLimboDocumentResolutions() {
        return new HashMap(this.activeLimboTargetsByKey);
    }

    public List<DocumentKey> getEnqueuedLimboDocumentResolutions() {
        return new ArrayList(this.enqueuedLimboResolutions);
    }

    public void handleCredentialChange(User user) {
        boolean userChanged = !this.currentUser.equals(user);
        this.currentUser = user;
        if (userChanged) {
            failOutstandingPendingWritesAwaitingTasks();
            emitNewSnapsAndNotifyLocalStore(this.localStore.handleUserChange(user), (RemoteEvent) null);
        }
        this.remoteStore.handleCredentialChange();
    }

    private void logErrorIfInteresting(Status error, String contextString, Object... contextArgs) {
        if (errorIsInteresting(error)) {
            Logger.warn("Firestore", "%s: %s", String.format(contextString, contextArgs), error);
        }
    }

    private boolean errorIsInteresting(Status error) {
        Status.Code code = error.getCode();
        String description = error.getDescription() != null ? error.getDescription() : "";
        if ((code != Status.Code.FAILED_PRECONDITION || !description.contains("requires an index")) && code != Status.Code.PERMISSION_DENIED) {
            return false;
        }
        return true;
    }
}
