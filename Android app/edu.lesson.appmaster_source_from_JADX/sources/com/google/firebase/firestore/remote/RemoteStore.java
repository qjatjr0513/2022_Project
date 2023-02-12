package com.google.firebase.firestore.remote;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.OnlineState;
import com.google.firebase.firestore.core.Transaction;
import com.google.firebase.firestore.local.LocalStore;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.model.mutation.MutationBatchResult;
import com.google.firebase.firestore.model.mutation.MutationResult;
import com.google.firebase.firestore.remote.ConnectivityMonitor;
import com.google.firebase.firestore.remote.WatchChange;
import com.google.firebase.firestore.remote.WatchChangeAggregator;
import com.google.firebase.firestore.remote.WatchStream;
import com.google.firebase.firestore.remote.WriteStream;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.AsyncQueue;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.ByteString;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import p004io.grpc.Status;

public final class RemoteStore implements WatchChangeAggregator.TargetMetadataProvider {
    private static final String LOG_TAG = "RemoteStore";
    private static final int MAX_PENDING_WRITES = 10;
    private final ConnectivityMonitor connectivityMonitor;
    private final Datastore datastore;
    private final Map<Integer, TargetData> listenTargets;
    private final LocalStore localStore;
    private boolean networkEnabled = false;
    private final OnlineStateTracker onlineStateTracker;
    private final RemoteStoreCallback remoteStoreCallback;
    private WatchChangeAggregator watchChangeAggregator;
    private final WatchStream watchStream;
    private final Deque<MutationBatch> writePipeline;
    /* access modifiers changed from: private */
    public final WriteStream writeStream;

    public interface RemoteStoreCallback {
        ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int i);

        void handleOnlineStateChange(OnlineState onlineState);

        void handleRejectedListen(int i, Status status);

        void handleRejectedWrite(int i, Status status);

        void handleRemoteEvent(RemoteEvent remoteEvent);

        void handleSuccessfulWrite(MutationBatchResult mutationBatchResult);
    }

    public RemoteStore(RemoteStoreCallback remoteStoreCallback2, LocalStore localStore2, Datastore datastore2, AsyncQueue workerQueue, ConnectivityMonitor connectivityMonitor2) {
        this.remoteStoreCallback = remoteStoreCallback2;
        this.localStore = localStore2;
        this.datastore = datastore2;
        this.connectivityMonitor = connectivityMonitor2;
        this.listenTargets = new HashMap();
        this.writePipeline = new ArrayDeque();
        Objects.requireNonNull(remoteStoreCallback2);
        this.onlineStateTracker = new OnlineStateTracker(workerQueue, new RemoteStore$$ExternalSyntheticLambda0(remoteStoreCallback2));
        this.watchStream = datastore2.createWatchStream(new WatchStream.Callback() {
            public void onOpen() {
                RemoteStore.this.handleWatchStreamOpen();
            }

            public void onWatchChange(SnapshotVersion snapshotVersion, WatchChange watchChange) {
                RemoteStore.this.handleWatchChange(snapshotVersion, watchChange);
            }

            public void onClose(Status status) {
                RemoteStore.this.handleWatchStreamClose(status);
            }
        });
        this.writeStream = datastore2.createWriteStream(new WriteStream.Callback() {
            public void onOpen() {
                RemoteStore.this.writeStream.writeHandshake();
            }

            public void onHandshakeComplete() {
                RemoteStore.this.handleWriteStreamHandshakeComplete();
            }

            public void onWriteResponse(SnapshotVersion commitVersion, List<MutationResult> mutationResults) {
                RemoteStore.this.handleWriteStreamMutationResults(commitVersion, mutationResults);
            }

            public void onClose(Status status) {
                RemoteStore.this.handleWriteStreamClose(status);
            }
        });
        connectivityMonitor2.addCallback(new RemoteStore$$ExternalSyntheticLambda1(this, workerQueue));
    }

    /* renamed from: lambda$new$1$com-google-firebase-firestore-remote-RemoteStore  reason: not valid java name */
    public /* synthetic */ void m455lambda$new$1$comgooglefirebasefirestoreremoteRemoteStore(AsyncQueue workerQueue, ConnectivityMonitor.NetworkStatus networkStatus) {
        workerQueue.enqueueAndForget(new RemoteStore$$ExternalSyntheticLambda2(this, networkStatus));
    }

    /* renamed from: lambda$new$0$com-google-firebase-firestore-remote-RemoteStore  reason: not valid java name */
    public /* synthetic */ void m454lambda$new$0$comgooglefirebasefirestoreremoteRemoteStore(ConnectivityMonitor.NetworkStatus networkStatus) {
        if (networkStatus.equals(ConnectivityMonitor.NetworkStatus.REACHABLE) && this.onlineStateTracker.getState().equals(OnlineState.ONLINE)) {
            return;
        }
        if ((!networkStatus.equals(ConnectivityMonitor.NetworkStatus.UNREACHABLE) || !this.onlineStateTracker.getState().equals(OnlineState.OFFLINE)) && canUseNetwork()) {
            Logger.debug(LOG_TAG, "Restarting streams for network reachability change.", new Object[0]);
            restartNetwork();
        }
    }

    public void enableNetwork() {
        this.networkEnabled = true;
        if (canUseNetwork()) {
            this.writeStream.setLastStreamToken(this.localStore.getLastStreamToken());
            if (shouldStartWatchStream()) {
                startWatchStream();
            } else {
                this.onlineStateTracker.updateState(OnlineState.UNKNOWN);
            }
            fillWritePipeline();
        }
    }

    /* access modifiers changed from: package-private */
    public void forceEnableNetwork() {
        enableNetwork();
        this.onlineStateTracker.updateState(OnlineState.ONLINE);
    }

    public void disableNetwork() {
        this.networkEnabled = false;
        disableNetworkInternal();
        this.onlineStateTracker.updateState(OnlineState.OFFLINE);
    }

    private void disableNetworkInternal() {
        this.watchStream.stop();
        this.writeStream.stop();
        if (!this.writePipeline.isEmpty()) {
            Logger.debug(LOG_TAG, "Stopping write stream with %d pending writes", Integer.valueOf(this.writePipeline.size()));
            this.writePipeline.clear();
        }
        cleanUpWatchStreamState();
    }

    private void restartNetwork() {
        this.networkEnabled = false;
        disableNetworkInternal();
        this.onlineStateTracker.updateState(OnlineState.UNKNOWN);
        this.writeStream.inhibitBackoff();
        this.watchStream.inhibitBackoff();
        enableNetwork();
    }

    public void start() {
        enableNetwork();
    }

    public void shutdown() {
        Logger.debug(LOG_TAG, "Shutting down", new Object[0]);
        this.connectivityMonitor.shutdown();
        this.networkEnabled = false;
        disableNetworkInternal();
        this.datastore.shutdown();
        this.onlineStateTracker.updateState(OnlineState.UNKNOWN);
    }

    public void handleCredentialChange() {
        if (canUseNetwork()) {
            Logger.debug(LOG_TAG, "Restarting streams for new credential.", new Object[0]);
            restartNetwork();
        }
    }

    public void listen(TargetData targetData) {
        Integer targetId = Integer.valueOf(targetData.getTargetId());
        if (!this.listenTargets.containsKey(targetId)) {
            this.listenTargets.put(targetId, targetData);
            if (shouldStartWatchStream()) {
                startWatchStream();
            } else if (this.watchStream.isOpen()) {
                sendWatchRequest(targetData);
            }
        }
    }

    private void sendWatchRequest(TargetData targetData) {
        this.watchChangeAggregator.recordPendingTargetRequest(targetData.getTargetId());
        this.watchStream.watchQuery(targetData);
    }

    public void stopListening(int targetId) {
        Assert.hardAssert(this.listenTargets.remove(Integer.valueOf(targetId)) != null, "stopListening called on target no currently watched: %d", Integer.valueOf(targetId));
        if (this.watchStream.isOpen()) {
            sendUnwatchRequest(targetId);
        }
        if (!this.listenTargets.isEmpty()) {
            return;
        }
        if (this.watchStream.isOpen()) {
            this.watchStream.markIdle();
        } else if (canUseNetwork()) {
            this.onlineStateTracker.updateState(OnlineState.UNKNOWN);
        }
    }

    private void sendUnwatchRequest(int targetId) {
        this.watchChangeAggregator.recordPendingTargetRequest(targetId);
        this.watchStream.unwatchTarget(targetId);
    }

    private boolean shouldStartWriteStream() {
        return canUseNetwork() && !this.writeStream.isStarted() && !this.writePipeline.isEmpty();
    }

    private boolean shouldStartWatchStream() {
        return canUseNetwork() && !this.watchStream.isStarted() && !this.listenTargets.isEmpty();
    }

    private void cleanUpWatchStreamState() {
        this.watchChangeAggregator = null;
    }

    private void startWatchStream() {
        Assert.hardAssert(shouldStartWatchStream(), "startWatchStream() called when shouldStartWatchStream() is false.", new Object[0]);
        this.watchChangeAggregator = new WatchChangeAggregator(this);
        this.watchStream.start();
        this.onlineStateTracker.handleWatchStreamStart();
    }

    /* access modifiers changed from: private */
    public void handleWatchStreamOpen() {
        for (TargetData targetData : this.listenTargets.values()) {
            sendWatchRequest(targetData);
        }
    }

    /* access modifiers changed from: private */
    public void handleWatchChange(SnapshotVersion snapshotVersion, WatchChange watchChange) {
        this.onlineStateTracker.updateState(OnlineState.ONLINE);
        Assert.hardAssert((this.watchStream == null || this.watchChangeAggregator == null) ? false : true, "WatchStream and WatchStreamAggregator should both be non-null", new Object[0]);
        WatchChange.WatchTargetChange watchTargetChange = watchChange instanceof WatchChange.WatchTargetChange ? (WatchChange.WatchTargetChange) watchChange : null;
        if (watchTargetChange == null || !watchTargetChange.getChangeType().equals(WatchChange.WatchTargetChangeType.Removed) || watchTargetChange.getCause() == null) {
            if (watchChange instanceof WatchChange.DocumentChange) {
                this.watchChangeAggregator.handleDocumentChange((WatchChange.DocumentChange) watchChange);
            } else if (watchChange instanceof WatchChange.ExistenceFilterWatchChange) {
                this.watchChangeAggregator.handleExistenceFilter((WatchChange.ExistenceFilterWatchChange) watchChange);
            } else {
                Assert.hardAssert(watchChange instanceof WatchChange.WatchTargetChange, "Expected watchChange to be an instance of WatchTargetChange", new Object[0]);
                this.watchChangeAggregator.handleTargetChange((WatchChange.WatchTargetChange) watchChange);
            }
            if (!snapshotVersion.equals(SnapshotVersion.NONE) && snapshotVersion.compareTo(this.localStore.getLastRemoteSnapshotVersion()) >= 0) {
                raiseWatchSnapshot(snapshotVersion);
                return;
            }
            return;
        }
        processTargetError(watchTargetChange);
    }

    /* access modifiers changed from: private */
    public void handleWatchStreamClose(Status status) {
        if (status.isOk()) {
            Assert.hardAssert(!shouldStartWatchStream(), "Watch stream was stopped gracefully while still needed.", new Object[0]);
        }
        cleanUpWatchStreamState();
        if (shouldStartWatchStream()) {
            this.onlineStateTracker.handleWatchStreamFailure(status);
            startWatchStream();
            return;
        }
        this.onlineStateTracker.updateState(OnlineState.UNKNOWN);
    }

    public boolean canUseNetwork() {
        return this.networkEnabled;
    }

    private void raiseWatchSnapshot(SnapshotVersion snapshotVersion) {
        Assert.hardAssert(!snapshotVersion.equals(SnapshotVersion.NONE), "Can't raise event for unknown SnapshotVersion", new Object[0]);
        RemoteEvent remoteEvent = this.watchChangeAggregator.createRemoteEvent(snapshotVersion);
        for (Map.Entry<Integer, TargetChange> entry : remoteEvent.getTargetChanges().entrySet()) {
            TargetChange targetChange = entry.getValue();
            if (!targetChange.getResumeToken().isEmpty()) {
                int targetId = entry.getKey().intValue();
                TargetData targetData = this.listenTargets.get(Integer.valueOf(targetId));
                if (targetData != null) {
                    this.listenTargets.put(Integer.valueOf(targetId), targetData.withResumeToken(targetChange.getResumeToken(), snapshotVersion));
                }
            }
        }
        for (Integer intValue : remoteEvent.getTargetMismatches()) {
            int targetId2 = intValue.intValue();
            TargetData targetData2 = this.listenTargets.get(Integer.valueOf(targetId2));
            if (targetData2 != null) {
                this.listenTargets.put(Integer.valueOf(targetId2), targetData2.withResumeToken(ByteString.EMPTY, targetData2.getSnapshotVersion()));
                sendUnwatchRequest(targetId2);
                sendWatchRequest(new TargetData(targetData2.getTarget(), targetId2, targetData2.getSequenceNumber(), QueryPurpose.EXISTENCE_FILTER_MISMATCH));
            }
        }
        this.remoteStoreCallback.handleRemoteEvent(remoteEvent);
    }

    private void processTargetError(WatchChange.WatchTargetChange targetChange) {
        Assert.hardAssert(targetChange.getCause() != null, "Processing target error without a cause", new Object[0]);
        for (Integer targetId : targetChange.getTargetIds()) {
            if (this.listenTargets.containsKey(targetId)) {
                this.listenTargets.remove(targetId);
                this.watchChangeAggregator.removeTarget(targetId.intValue());
                this.remoteStoreCallback.handleRejectedListen(targetId.intValue(), targetChange.getCause());
            }
        }
    }

    public void fillWritePipeline() {
        int lastBatchIdRetrieved = this.writePipeline.isEmpty() ? -1 : this.writePipeline.getLast().getBatchId();
        while (true) {
            if (!canAddToWritePipeline()) {
                break;
            }
            MutationBatch batch = this.localStore.getNextMutationBatch(lastBatchIdRetrieved);
            if (batch != null) {
                addToWritePipeline(batch);
                lastBatchIdRetrieved = batch.getBatchId();
            } else if (this.writePipeline.size() == 0) {
                this.writeStream.markIdle();
            }
        }
        if (shouldStartWriteStream()) {
            startWriteStream();
        }
    }

    private boolean canAddToWritePipeline() {
        return canUseNetwork() && this.writePipeline.size() < 10;
    }

    private void addToWritePipeline(MutationBatch mutationBatch) {
        Assert.hardAssert(canAddToWritePipeline(), "addToWritePipeline called when pipeline is full", new Object[0]);
        this.writePipeline.add(mutationBatch);
        if (this.writeStream.isOpen() && this.writeStream.isHandshakeComplete()) {
            this.writeStream.writeMutations(mutationBatch.getMutations());
        }
    }

    private void startWriteStream() {
        Assert.hardAssert(shouldStartWriteStream(), "startWriteStream() called when shouldStartWriteStream() is false.", new Object[0]);
        this.writeStream.start();
    }

    /* access modifiers changed from: private */
    public void handleWriteStreamHandshakeComplete() {
        this.localStore.setLastStreamToken(this.writeStream.getLastStreamToken());
        for (MutationBatch batch : this.writePipeline) {
            this.writeStream.writeMutations(batch.getMutations());
        }
    }

    /* access modifiers changed from: private */
    public void handleWriteStreamMutationResults(SnapshotVersion commitVersion, List<MutationResult> results) {
        this.remoteStoreCallback.handleSuccessfulWrite(MutationBatchResult.create(this.writePipeline.poll(), commitVersion, results, this.writeStream.getLastStreamToken()));
        fillWritePipeline();
    }

    /* access modifiers changed from: private */
    public void handleWriteStreamClose(Status status) {
        if (status.isOk()) {
            Assert.hardAssert(!shouldStartWriteStream(), "Write stream was stopped gracefully while still needed.", new Object[0]);
        }
        if (!status.isOk() && !this.writePipeline.isEmpty()) {
            if (this.writeStream.isHandshakeComplete()) {
                handleWriteError(status);
            } else {
                handleWriteHandshakeError(status);
            }
        }
        if (shouldStartWriteStream()) {
            startWriteStream();
        }
    }

    private void handleWriteHandshakeError(Status status) {
        Assert.hardAssert(!status.isOk(), "Handling write error with status OK.", new Object[0]);
        if (Datastore.isPermanentError(status)) {
            Logger.debug(LOG_TAG, "RemoteStore error before completed handshake; resetting stream token %s: %s", Util.toDebugString(this.writeStream.getLastStreamToken()), status);
            this.writeStream.setLastStreamToken(WriteStream.EMPTY_STREAM_TOKEN);
            this.localStore.setLastStreamToken(WriteStream.EMPTY_STREAM_TOKEN);
        }
    }

    private void handleWriteError(Status status) {
        Assert.hardAssert(!status.isOk(), "Handling write error with status OK.", new Object[0]);
        if (Datastore.isPermanentWriteError(status)) {
            this.writeStream.inhibitBackoff();
            this.remoteStoreCallback.handleRejectedWrite(this.writePipeline.poll().getBatchId(), status);
            fillWritePipeline();
        }
    }

    public Transaction createTransaction() {
        return new Transaction(this.datastore);
    }

    public ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int targetId) {
        return this.remoteStoreCallback.getRemoteKeysForTarget(targetId);
    }

    public TargetData getTargetDataForTarget(int targetId) {
        return this.listenTargets.get(Integer.valueOf(targetId));
    }
}
