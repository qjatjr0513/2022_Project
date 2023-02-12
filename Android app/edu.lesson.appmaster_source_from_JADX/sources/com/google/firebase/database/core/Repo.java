package com.google.firebase.database.core;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.InternalHelpers;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.connection.HostInfo;
import com.google.firebase.database.connection.ListenHashProvider;
import com.google.firebase.database.connection.PersistentConnection;
import com.google.firebase.database.connection.RangeMerge;
import com.google.firebase.database.connection.RequestResultCallback;
import com.google.firebase.database.core.SparseSnapshotTree;
import com.google.firebase.database.core.SyncTree;
import com.google.firebase.database.core.TokenProvider;
import com.google.firebase.database.core.persistence.NoopPersistenceManager;
import com.google.firebase.database.core.persistence.PersistenceManager;
import com.google.firebase.database.core.utilities.DefaultClock;
import com.google.firebase.database.core.utilities.DefaultRunLoop;
import com.google.firebase.database.core.utilities.OffsetClock;
import com.google.firebase.database.core.utilities.Tree;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.Event;
import com.google.firebase.database.core.view.EventRaiser;
import com.google.firebase.database.core.view.QuerySpec;
import com.google.firebase.database.logging.LogWrapper;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.Node;
import com.google.firebase.database.snapshot.NodeUtilities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

public class Repo implements PersistentConnection.Delegate {
    private static final String INTERRUPT_REASON = "repo_interrupt";
    private static final int TRANSACTION_MAX_RETRIES = 25;
    private static final String TRANSACTION_OVERRIDE_BY_SET = "overriddenBySet";
    private static final String TRANSACTION_TOO_MANY_RETRIES = "maxretries";
    /* access modifiers changed from: private */
    public PersistentConnection connection;
    /* access modifiers changed from: private */
    public final Context ctx;
    private final LogWrapper dataLogger;
    public long dataUpdateCount = 0;
    private FirebaseDatabase database;
    private final EventRaiser eventRaiser;
    private boolean hijackHash = false;
    /* access modifiers changed from: private */
    public SnapshotHolder infoData;
    /* access modifiers changed from: private */
    public SyncTree infoSyncTree;
    private boolean loggedTransactionPersistenceWarning = false;
    private long nextWriteId = 1;
    /* access modifiers changed from: private */
    public SparseSnapshotTree onDisconnect;
    /* access modifiers changed from: private */
    public final LogWrapper operationLogger;
    private final RepoInfo repoInfo;
    /* access modifiers changed from: private */
    public final OffsetClock serverClock = new OffsetClock(new DefaultClock(), 0);
    /* access modifiers changed from: private */
    public SyncTree serverSyncTree;
    private final LogWrapper transactionLogger;
    private long transactionOrder = 0;
    /* access modifiers changed from: private */
    public Tree<List<TransactionData>> transactionQueueTree;

    private enum TransactionStatus {
        INITIALIZING,
        RUN,
        SENT,
        COMPLETED,
        SENT_NEEDS_ABORT,
        NEEDS_ABORT
    }

    Repo(RepoInfo repoInfo2, Context ctx2, FirebaseDatabase database2) {
        this.repoInfo = repoInfo2;
        this.ctx = ctx2;
        this.database = database2;
        this.operationLogger = ctx2.getLogger("RepoOperation");
        this.transactionLogger = ctx2.getLogger("Transaction");
        this.dataLogger = ctx2.getLogger("DataOperation");
        this.eventRaiser = new EventRaiser(ctx2);
        scheduleNow(new Runnable() {
            public void run() {
                Repo.this.deferredInitialization();
            }
        });
    }

    /* access modifiers changed from: private */
    public void deferredInitialization() {
        this.connection = this.ctx.newPersistentConnection(new HostInfo(this.repoInfo.host, this.repoInfo.namespace, this.repoInfo.secure), this);
        this.ctx.getAuthTokenProvider().addTokenChangeListener(((DefaultRunLoop) this.ctx.getRunLoop()).getExecutorService(), new TokenProvider.TokenChangeListener() {
            public void onTokenChange() {
                Repo.this.operationLogger.debug("Auth token changed, triggering auth token refresh", new Object[0]);
                Repo.this.connection.refreshAuthToken();
            }

            public void onTokenChange(String token) {
                Repo.this.operationLogger.debug("Auth token changed, triggering auth token refresh", new Object[0]);
                Repo.this.connection.refreshAuthToken(token);
            }
        });
        this.ctx.getAppCheckTokenProvider().addTokenChangeListener(((DefaultRunLoop) this.ctx.getRunLoop()).getExecutorService(), new TokenProvider.TokenChangeListener() {
            public void onTokenChange() {
                Repo.this.operationLogger.debug("App check token changed, triggering app check token refresh", new Object[0]);
                Repo.this.connection.refreshAppCheckToken();
            }

            public void onTokenChange(String token) {
                Repo.this.operationLogger.debug("App check token changed, triggering app check token refresh", new Object[0]);
                Repo.this.connection.refreshAppCheckToken(token);
            }
        });
        this.connection.initialize();
        PersistenceManager persistenceManager = this.ctx.getPersistenceManager(this.repoInfo.host);
        this.infoData = new SnapshotHolder();
        this.onDisconnect = new SparseSnapshotTree();
        this.transactionQueueTree = new Tree<>();
        this.infoSyncTree = new SyncTree(this.ctx, new NoopPersistenceManager(), new SyncTree.ListenProvider() {
            public void startListening(final QuerySpec query, Tag tag, ListenHashProvider hash, final SyncTree.CompletionListener onComplete) {
                Repo.this.scheduleNow(new Runnable() {
                    public void run() {
                        Node node = Repo.this.infoData.getNode(query.getPath());
                        if (!node.isEmpty()) {
                            Repo.this.postEvents(Repo.this.infoSyncTree.applyServerOverwrite(query.getPath(), node));
                            onComplete.onListenComplete((DatabaseError) null);
                        }
                    }
                });
            }

            public void stopListening(QuerySpec query, Tag tag) {
            }
        });
        this.serverSyncTree = new SyncTree(this.ctx, persistenceManager, new SyncTree.ListenProvider() {
            public void startListening(QuerySpec query, Tag tag, ListenHashProvider hash, final SyncTree.CompletionListener onListenComplete) {
                Repo.this.connection.listen(query.getPath().asList(), query.getParams().getWireProtocolParams(), hash, tag != null ? Long.valueOf(tag.getTagNumber()) : null, new RequestResultCallback() {
                    public void onRequestResult(String optErrorCode, String optErrorMessage) {
                        Repo.this.postEvents(onListenComplete.onListenComplete(Repo.fromErrorCode(optErrorCode, optErrorMessage)));
                    }
                });
            }

            public void stopListening(QuerySpec query, Tag tag) {
                Repo.this.connection.unlisten(query.getPath().asList(), query.getParams().getWireProtocolParams());
            }
        });
        restoreWrites(persistenceManager);
        updateInfo(Constants.DOT_INFO_AUTHENTICATED, false);
        updateInfo(Constants.DOT_INFO_CONNECTED, false);
    }

    private void restoreWrites(PersistenceManager persistenceManager) {
        List<UserWriteRecord> writes = persistenceManager.loadUserWrites();
        Map<String, Object> serverValues = ServerValues.generateServerValues(this.serverClock);
        long lastWriteId = Long.MIN_VALUE;
        for (final UserWriteRecord write : writes) {
            RequestResultCallback onComplete = new RequestResultCallback() {
                public void onRequestResult(String optErrorCode, String optErrorMessage) {
                    DatabaseError error = Repo.fromErrorCode(optErrorCode, optErrorMessage);
                    Repo.this.warnIfWriteFailed("Persisted write", write.getPath(), error);
                    Repo.this.ackWriteAndRerunTransactions(write.getWriteId(), write.getPath(), error);
                }
            };
            if (lastWriteId < write.getWriteId()) {
                lastWriteId = write.getWriteId();
                this.nextWriteId = write.getWriteId() + 1;
                if (write.isOverwrite()) {
                    if (this.operationLogger.logsDebug()) {
                        this.operationLogger.debug("Restoring overwrite with id " + write.getWriteId(), new Object[0]);
                    }
                    this.connection.put(write.getPath().asList(), write.getOverwrite().getValue(true), onComplete);
                    this.serverSyncTree.applyUserOverwrite(write.getPath(), write.getOverwrite(), ServerValues.resolveDeferredValueSnapshot(write.getOverwrite(), this.serverSyncTree, write.getPath(), serverValues), write.getWriteId(), true, false);
                } else {
                    if (this.operationLogger.logsDebug()) {
                        this.operationLogger.debug("Restoring merge with id " + write.getWriteId(), new Object[0]);
                    }
                    this.connection.merge(write.getPath().asList(), write.getMerge().getValue(true), onComplete);
                    this.serverSyncTree.applyUserMerge(write.getPath(), write.getMerge(), ServerValues.resolveDeferredValueMerge(write.getMerge(), this.serverSyncTree, write.getPath(), serverValues), write.getWriteId(), false);
                }
            } else {
                throw new IllegalStateException("Write ids were not in order.");
            }
        }
    }

    public FirebaseDatabase getDatabase() {
        return this.database;
    }

    public String toString() {
        return this.repoInfo.toString();
    }

    public RepoInfo getRepoInfo() {
        return this.repoInfo;
    }

    public void scheduleNow(Runnable r) {
        this.ctx.requireStarted();
        this.ctx.getRunLoop().scheduleNow(r);
    }

    public void postEvent(Runnable r) {
        this.ctx.requireStarted();
        this.ctx.getEventTarget().postEvent(r);
    }

    /* access modifiers changed from: private */
    public void postEvents(List<? extends Event> events) {
        if (!events.isEmpty()) {
            this.eventRaiser.raiseEvents(events);
        }
    }

    public long getServerTime() {
        return this.serverClock.millis();
    }

    /* access modifiers changed from: package-private */
    public boolean hasListeners() {
        return !this.infoSyncTree.isEmpty() || !this.serverSyncTree.isEmpty();
    }

    public void onDataUpdate(List<String> pathSegments, Object message, boolean isMerge, Long optTag) {
        List<? extends Event> events;
        Path path = new Path(pathSegments);
        if (this.operationLogger.logsDebug()) {
            this.operationLogger.debug("onDataUpdate: " + path, new Object[0]);
        }
        if (this.dataLogger.logsDebug()) {
            this.operationLogger.debug("onDataUpdate: " + path + " " + message, new Object[0]);
        }
        this.dataUpdateCount++;
        if (optTag != null) {
            try {
                Tag tag = new Tag(optTag.longValue());
                if (isMerge) {
                    Map<Path, Node> taggedChildren = new HashMap<>();
                    for (Map.Entry<String, Object> entry : ((Map) message).entrySet()) {
                        taggedChildren.put(new Path(entry.getKey()), NodeUtilities.NodeFromJSON(entry.getValue()));
                    }
                    events = this.serverSyncTree.applyTaggedQueryMerge(path, taggedChildren, tag);
                } else {
                    events = this.serverSyncTree.applyTaggedQueryOverwrite(path, NodeUtilities.NodeFromJSON(message), tag);
                }
            } catch (DatabaseException e) {
                this.operationLogger.error("FIREBASE INTERNAL ERROR", e);
                return;
            }
        } else if (isMerge) {
            Map<Path, Node> changedChildren = new HashMap<>();
            for (Map.Entry<String, Object> entry2 : ((Map) message).entrySet()) {
                changedChildren.put(new Path(entry2.getKey()), NodeUtilities.NodeFromJSON(entry2.getValue()));
            }
            events = this.serverSyncTree.applyServerMerge(path, changedChildren);
        } else {
            events = this.serverSyncTree.applyServerOverwrite(path, NodeUtilities.NodeFromJSON(message));
        }
        if (events.size() > 0) {
            rerunTransactions(path);
        }
        postEvents(events);
    }

    public void onRangeMergeUpdate(List<String> pathSegments, List<RangeMerge> merges, Long tagNumber) {
        List<? extends Event> events;
        Path path = new Path(pathSegments);
        if (this.operationLogger.logsDebug()) {
            this.operationLogger.debug("onRangeMergeUpdate: " + path, new Object[0]);
        }
        if (this.dataLogger.logsDebug()) {
            this.operationLogger.debug("onRangeMergeUpdate: " + path + " " + merges, new Object[0]);
        }
        this.dataUpdateCount++;
        List<com.google.firebase.database.snapshot.RangeMerge> parsedMerges = new ArrayList<>(merges.size());
        for (RangeMerge merge : merges) {
            parsedMerges.add(new com.google.firebase.database.snapshot.RangeMerge(merge));
        }
        if (tagNumber != null) {
            events = this.serverSyncTree.applyTaggedRangeMerges(path, parsedMerges, new Tag(tagNumber.longValue()));
        } else {
            events = this.serverSyncTree.applyServerRangeMerges(path, parsedMerges);
        }
        if (events.size() > 0) {
            rerunTransactions(path);
        }
        postEvents(events);
    }

    /* access modifiers changed from: package-private */
    public void callOnComplete(final DatabaseReference.CompletionListener onComplete, final DatabaseError error, Path path) {
        final DatabaseReference ref;
        if (onComplete != null) {
            ChildKey last = path.getBack();
            if (last == null || !last.isPriorityChildName()) {
                ref = InternalHelpers.createReference(this, path);
            } else {
                ref = InternalHelpers.createReference(this, path.getParent());
            }
            postEvent(new Runnable() {
                public void run() {
                    onComplete.onComplete(error, ref);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void ackWriteAndRerunTransactions(long writeId, Path path, DatabaseError error) {
        if (error == null || error.getCode() != -25) {
            List<? extends Event> clearEvents = this.serverSyncTree.ackUserWrite(writeId, !(error == null), true, this.serverClock);
            if (clearEvents.size() > 0) {
                rerunTransactions(path);
            }
            postEvents(clearEvents);
        }
    }

    public void setValue(Path path, Node newValueUnresolved, DatabaseReference.CompletionListener onComplete) {
        Path path2 = path;
        Node node = newValueUnresolved;
        if (this.operationLogger.logsDebug()) {
            this.operationLogger.debug("set: " + path2, new Object[0]);
        }
        if (this.dataLogger.logsDebug()) {
            this.dataLogger.debug("set: " + path2 + " " + node, new Object[0]);
        }
        Map<String, Object> serverValues = ServerValues.generateServerValues(this.serverClock);
        Node existing = this.serverSyncTree.calcCompleteEventCache(path2, new ArrayList());
        Node newValue = ServerValues.resolveDeferredValueSnapshot(node, existing, serverValues);
        long writeId = getNextWriteId();
        postEvents(this.serverSyncTree.applyUserOverwrite(path, newValueUnresolved, newValue, writeId, true, true));
        PersistentConnection persistentConnection = this.connection;
        List<String> asList = path.asList();
        Object value = node.getValue(true);
        final Path path3 = path;
        Map<String, Object> map = serverValues;
        Node node2 = existing;
        final long j = writeId;
        final DatabaseReference.CompletionListener completionListener = onComplete;
        persistentConnection.put(asList, value, new RequestResultCallback() {
            public void onRequestResult(String optErrorCode, String optErrorMessage) {
                DatabaseError error = Repo.fromErrorCode(optErrorCode, optErrorMessage);
                Repo.this.warnIfWriteFailed("setValue", path3, error);
                Repo.this.ackWriteAndRerunTransactions(j, path3, error);
                Repo.this.callOnComplete(completionListener, error, path3);
            }
        });
        rerunTransactions(abortTransactions(path2, -9));
    }

    public Task<DataSnapshot> getValue(final Query query) {
        final TaskCompletionSource<DataSnapshot> source = new TaskCompletionSource<>();
        scheduleNow(new Runnable() {
            public void run() {
                Node serverValue = Repo.this.serverSyncTree.getServerValue(query.getSpec());
                if (serverValue != null) {
                    source.setResult(InternalHelpers.createDataSnapshot(query.getRef(), IndexedNode.from(serverValue)));
                    return;
                }
                Repo.this.serverSyncTree.setQueryActive(query.getSpec());
                Repo.this.connection.get(query.getPath().asList(), query.getSpec().getParams().getWireProtocolParams()).addOnCompleteListener((Executor) ((DefaultRunLoop) Repo.this.ctx.getRunLoop()).getExecutorService(), new OnCompleteListener<Object>() {
                    public void onComplete(Task<Object> task) {
                        if (!task.isSuccessful()) {
                            Repo.this.operationLogger.info("get for query " + query.getPath() + " falling back to disk cache after error: " + task.getException().getMessage());
                            DataSnapshot cached = Repo.this.serverSyncTree.persistenceServerCache(query);
                            if (!cached.exists()) {
                                source.setException(task.getException());
                            } else {
                                source.setResult(cached);
                            }
                        } else {
                            Node serverNode = NodeUtilities.NodeFromJSON(task.getResult());
                            Repo.this.postEvents(Repo.this.serverSyncTree.applyServerOverwrite(query.getPath(), serverNode));
                            source.setResult(InternalHelpers.createDataSnapshot(query.getRef(), IndexedNode.from(serverNode, query.getSpec().getIndex())));
                        }
                        Repo.this.serverSyncTree.setQueryInactive(query.getSpec());
                    }
                });
            }
        });
        return source.getTask();
    }

    public void updateChildren(Path path, CompoundWrite updates, DatabaseReference.CompletionListener onComplete, Map<String, Object> unParsedUpdates) {
        Path path2 = path;
        Map<String, Object> map = unParsedUpdates;
        if (this.operationLogger.logsDebug()) {
            this.operationLogger.debug("update: " + path2, new Object[0]);
        }
        if (this.dataLogger.logsDebug()) {
            this.dataLogger.debug("update: " + path2 + " " + map, new Object[0]);
        }
        if (updates.isEmpty()) {
            if (this.operationLogger.logsDebug()) {
                this.operationLogger.debug("update called with no changes. No-op", new Object[0]);
            }
            callOnComplete(onComplete, (DatabaseError) null, path2);
            return;
        }
        final DatabaseReference.CompletionListener completionListener = onComplete;
        Map<String, Object> serverValues = ServerValues.generateServerValues(this.serverClock);
        CompoundWrite resolved = ServerValues.resolveDeferredValueMerge(updates, this.serverSyncTree, path2, serverValues);
        long writeId = getNextWriteId();
        postEvents(this.serverSyncTree.applyUserMerge(path, updates, resolved, writeId, true));
        final Path path3 = path;
        Map<String, Object> map2 = serverValues;
        final long j = writeId;
        this.connection.merge(path.asList(), map, new RequestResultCallback() {
            public void onRequestResult(String optErrorCode, String optErrorMessage) {
                DatabaseError error = Repo.fromErrorCode(optErrorCode, optErrorMessage);
                Repo.this.warnIfWriteFailed("updateChildren", path3, error);
                Repo.this.ackWriteAndRerunTransactions(j, path3, error);
                Repo.this.callOnComplete(completionListener, error, path3);
            }
        });
        Iterator<Map.Entry<Path, Node>> it = updates.iterator();
        while (it.hasNext()) {
            rerunTransactions(abortTransactions(path2.child(it.next().getKey()), -9));
        }
    }

    public void purgeOutstandingWrites() {
        if (this.operationLogger.logsDebug()) {
            this.operationLogger.debug("Purging writes", new Object[0]);
        }
        postEvents(this.serverSyncTree.removeAllWrites());
        abortTransactions(Path.getEmptyPath(), -25);
        this.connection.purgeOutstandingWrites();
    }

    public void removeEventCallback(EventRegistration eventRegistration) {
        List<Event> events;
        if (Constants.DOT_INFO.equals(eventRegistration.getQuerySpec().getPath().getFront())) {
            events = this.infoSyncTree.removeEventRegistration(eventRegistration);
        } else {
            events = this.serverSyncTree.removeEventRegistration(eventRegistration);
        }
        postEvents(events);
    }

    public void onDisconnectSetValue(final Path path, final Node newValue, final DatabaseReference.CompletionListener onComplete) {
        this.connection.onDisconnectPut(path.asList(), newValue.getValue(true), new RequestResultCallback() {
            public void onRequestResult(String optErrorCode, String optErrorMessage) {
                DatabaseError error = Repo.fromErrorCode(optErrorCode, optErrorMessage);
                Repo.this.warnIfWriteFailed("onDisconnect().setValue", path, error);
                if (error == null) {
                    Repo.this.onDisconnect.remember(path, newValue);
                }
                Repo.this.callOnComplete(onComplete, error, path);
            }
        });
    }

    public void onDisconnectUpdate(final Path path, final Map<Path, Node> newChildren, final DatabaseReference.CompletionListener listener, Map<String, Object> unParsedUpdates) {
        this.connection.onDisconnectMerge(path.asList(), unParsedUpdates, new RequestResultCallback() {
            public void onRequestResult(String optErrorCode, String optErrorMessage) {
                DatabaseError error = Repo.fromErrorCode(optErrorCode, optErrorMessage);
                Repo.this.warnIfWriteFailed("onDisconnect().updateChildren", path, error);
                if (error == null) {
                    for (Map.Entry<Path, Node> entry : newChildren.entrySet()) {
                        Repo.this.onDisconnect.remember(path.child(entry.getKey()), entry.getValue());
                    }
                }
                Repo.this.callOnComplete(listener, error, path);
            }
        });
    }

    public void onDisconnectCancel(final Path path, final DatabaseReference.CompletionListener onComplete) {
        this.connection.onDisconnectCancel(path.asList(), new RequestResultCallback() {
            public void onRequestResult(String optErrorCode, String optErrorMessage) {
                DatabaseError error = Repo.fromErrorCode(optErrorCode, optErrorMessage);
                if (error == null) {
                    Repo.this.onDisconnect.forget(path);
                }
                Repo.this.callOnComplete(onComplete, error, path);
            }
        });
    }

    public void onConnect() {
        onServerInfoUpdate(Constants.DOT_INFO_CONNECTED, true);
    }

    public void onDisconnect() {
        onServerInfoUpdate(Constants.DOT_INFO_CONNECTED, false);
        runOnDisconnectEvents();
    }

    public void onConnectionStatus(boolean connectionOk) {
        onServerInfoUpdate(Constants.DOT_INFO_AUTHENTICATED, Boolean.valueOf(connectionOk));
    }

    public void onServerInfoUpdate(ChildKey key, Object value) {
        updateInfo(key, value);
    }

    public void onServerInfoUpdate(Map<String, Object> updates) {
        for (Map.Entry<String, Object> entry : updates.entrySet()) {
            updateInfo(ChildKey.fromString(entry.getKey()), entry.getValue());
        }
    }

    /* access modifiers changed from: package-private */
    public void interrupt() {
        this.connection.interrupt(INTERRUPT_REASON);
    }

    /* access modifiers changed from: package-private */
    public void resume() {
        this.connection.resume(INTERRUPT_REASON);
    }

    public void addEventCallback(EventRegistration eventRegistration) {
        List<? extends Event> events;
        ChildKey front = eventRegistration.getQuerySpec().getPath().getFront();
        if (front == null || !front.equals(Constants.DOT_INFO)) {
            events = this.serverSyncTree.addEventRegistration(eventRegistration);
        } else {
            events = this.infoSyncTree.addEventRegistration(eventRegistration);
        }
        postEvents(events);
    }

    public void keepSynced(QuerySpec query, boolean keep) {
        Utilities.hardAssert(query.getPath().isEmpty() || !query.getPath().getFront().equals(Constants.DOT_INFO));
        this.serverSyncTree.keepSynced(query, keep);
    }

    /* access modifiers changed from: package-private */
    public PersistentConnection getConnection() {
        return this.connection;
    }

    private void updateInfo(ChildKey childKey, Object value) {
        if (childKey.equals(Constants.DOT_INFO_SERVERTIME_OFFSET)) {
            this.serverClock.setOffset(((Long) value).longValue());
        }
        Path path = new Path(Constants.DOT_INFO, childKey);
        try {
            Node node = NodeUtilities.NodeFromJSON(value);
            this.infoData.update(path, node);
            postEvents(this.infoSyncTree.applyServerOverwrite(path, node));
        } catch (DatabaseException e) {
            this.operationLogger.error("Failed to parse info update", e);
        }
    }

    private long getNextWriteId() {
        long j = this.nextWriteId;
        this.nextWriteId = 1 + j;
        return j;
    }

    private void runOnDisconnectEvents() {
        final Map<String, Object> serverValues = ServerValues.generateServerValues(this.serverClock);
        final List<Event> events = new ArrayList<>();
        this.onDisconnect.forEachTree(Path.getEmptyPath(), new SparseSnapshotTree.SparseSnapshotTreeVisitor() {
            public void visitTree(Path prefixPath, Node node) {
                events.addAll(Repo.this.serverSyncTree.applyServerOverwrite(prefixPath, ServerValues.resolveDeferredValueSnapshot(node, Repo.this.serverSyncTree.calcCompleteEventCache(prefixPath, new ArrayList()), (Map<String, Object>) serverValues)));
                Path unused = Repo.this.rerunTransactions(Repo.this.abortTransactions(prefixPath, -9));
            }
        });
        this.onDisconnect = new SparseSnapshotTree();
        postEvents(events);
    }

    /* access modifiers changed from: private */
    public void warnIfWriteFailed(String writeType, Path path, DatabaseError error) {
        if (error != null && error.getCode() != -1 && error.getCode() != -25) {
            this.operationLogger.warn(writeType + " at " + path.toString() + " failed: " + error.toString());
        }
    }

    private static class TransactionData implements Comparable<TransactionData> {
        /* access modifiers changed from: private */
        public DatabaseError abortReason;
        /* access modifiers changed from: private */
        public boolean applyLocally;
        /* access modifiers changed from: private */
        public Node currentInputSnapshot;
        /* access modifiers changed from: private */
        public Node currentOutputSnapshotRaw;
        /* access modifiers changed from: private */
        public Node currentOutputSnapshotResolved;
        /* access modifiers changed from: private */
        public long currentWriteId;
        /* access modifiers changed from: private */
        public Transaction.Handler handler;
        private long order;
        /* access modifiers changed from: private */
        public ValueEventListener outstandingListener;
        /* access modifiers changed from: private */
        public Path path;
        /* access modifiers changed from: private */
        public int retryCount;
        /* access modifiers changed from: private */
        public TransactionStatus status;

        static /* synthetic */ int access$2108(TransactionData x0) {
            int i = x0.retryCount;
            x0.retryCount = i + 1;
            return i;
        }

        private TransactionData(Path path2, Transaction.Handler handler2, ValueEventListener outstandingListener2, TransactionStatus status2, boolean applyLocally2, long order2) {
            this.path = path2;
            this.handler = handler2;
            this.outstandingListener = outstandingListener2;
            this.status = status2;
            this.retryCount = 0;
            this.applyLocally = applyLocally2;
            this.order = order2;
            this.abortReason = null;
            this.currentInputSnapshot = null;
            this.currentOutputSnapshotRaw = null;
            this.currentOutputSnapshotResolved = null;
        }

        public int compareTo(TransactionData o) {
            long j = this.order;
            long j2 = o.order;
            if (j < j2) {
                return -1;
            }
            if (j == j2) {
                return 0;
            }
            return 1;
        }
    }

    public void startTransaction(Path path, Transaction.Handler handler, boolean applyLocally) {
        DatabaseError error;
        Transaction.Result result;
        List<TransactionData> nodeQueue;
        Path path2 = path;
        final Transaction.Handler handler2 = handler;
        if (this.operationLogger.logsDebug()) {
            this.operationLogger.debug("transaction: " + path2, new Object[0]);
        }
        if (this.dataLogger.logsDebug()) {
            this.operationLogger.debug("transaction: " + path2, new Object[0]);
        }
        if (this.ctx.isPersistenceEnabled() && !this.loggedTransactionPersistenceWarning) {
            this.loggedTransactionPersistenceWarning = true;
            this.transactionLogger.info("runTransaction() usage detected while persistence is enabled. Please be aware that transactions *will not* be persisted across database restarts.  See https://www.firebase.com/docs/android/guide/offline-capabilities.html#section-handling-transactions-offline for more details.");
        }
        DatabaseReference watchRef = InternalHelpers.createReference(this, path);
        ValueEventListener listener = new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
            }

            public void onCancelled(DatabaseError error) {
            }
        };
        addEventCallback(new ValueEventRegistration(this, listener, watchRef.getSpec()));
        TransactionData transaction = new TransactionData(path, handler, listener, TransactionStatus.INITIALIZING, applyLocally, nextTransactionOrder());
        Node currentState = getLatestState(path);
        Node unused = transaction.currentInputSnapshot = currentState;
        MutableData mutableCurrent = InternalHelpers.createMutableData(currentState);
        try {
            result = handler2.doTransaction(mutableCurrent);
            if (result != null) {
                error = null;
                if (!result.isSuccess()) {
                    Node unused2 = transaction.currentOutputSnapshotRaw = null;
                    Node unused3 = transaction.currentOutputSnapshotResolved = null;
                    final DatabaseError innerClassError = error;
                    final DataSnapshot snap = InternalHelpers.createDataSnapshot(watchRef, IndexedNode.from(transaction.currentInputSnapshot));
                    postEvent(new Runnable() {
                        public void run() {
                            handler2.onComplete(innerClassError, false, snap);
                        }
                    });
                    MutableData mutableData = mutableCurrent;
                    return;
                }
                TransactionStatus unused4 = transaction.status = TransactionStatus.RUN;
                Tree<List<TransactionData>> queueNode = this.transactionQueueTree.subTree(path2);
                List<TransactionData> nodeQueue2 = queueNode.getValue();
                if (nodeQueue2 == null) {
                    nodeQueue = new ArrayList<>();
                } else {
                    nodeQueue = nodeQueue2;
                }
                nodeQueue.add(transaction);
                queueNode.setValue(nodeQueue);
                Map<String, Object> serverValues = ServerValues.generateServerValues(this.serverClock);
                Node newNodeUnresolved = result.getNode();
                Node newNode = ServerValues.resolveDeferredValueSnapshot(newNodeUnresolved, transaction.currentInputSnapshot, serverValues);
                Node unused5 = transaction.currentOutputSnapshotRaw = newNodeUnresolved;
                Node unused6 = transaction.currentOutputSnapshotResolved = newNode;
                long unused7 = transaction.currentWriteId = getNextWriteId();
                Node node = newNodeUnresolved;
                List<TransactionData> list = nodeQueue;
                Map<String, Object> map = serverValues;
                Tree<List<TransactionData>> tree = queueNode;
                MutableData mutableData2 = mutableCurrent;
                postEvents(this.serverSyncTree.applyUserOverwrite(path, newNodeUnresolved, newNode, transaction.currentWriteId, applyLocally, false));
                sendAllReadyTransactions();
                return;
            }
            throw new NullPointerException("Transaction returned null as result");
        } catch (Throwable e) {
            this.operationLogger.error("Caught Throwable.", e);
            error = DatabaseError.fromException(e);
            result = Transaction.abort();
        }
    }

    private Node getLatestState(Path path) {
        return getLatestState(path, new ArrayList());
    }

    private Node getLatestState(Path path, List<Long> excudeSets) {
        Node state = this.serverSyncTree.calcCompleteEventCache(path, excudeSets);
        if (state == null) {
            return EmptyNode.Empty();
        }
        return state;
    }

    public void setHijackHash(boolean hijackHash2) {
        this.hijackHash = hijackHash2;
    }

    /* access modifiers changed from: private */
    public void sendAllReadyTransactions() {
        Tree<List<TransactionData>> node = this.transactionQueueTree;
        pruneCompletedTransactions(node);
        sendReadyTransactions(node);
    }

    /* access modifiers changed from: private */
    public void sendReadyTransactions(Tree<List<TransactionData>> node) {
        if (node.getValue() != null) {
            List<TransactionData> queue = buildTransactionQueue(node);
            Utilities.hardAssert(queue.size() > 0);
            Boolean allRun = true;
            Iterator<TransactionData> it = queue.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().status != TransactionStatus.RUN) {
                        allRun = false;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (allRun.booleanValue()) {
                sendTransactionQueue(queue, node.getPath());
            }
        } else if (node.hasChildren()) {
            node.forEachChild(new Tree.TreeVisitor<List<TransactionData>>() {
                public void visitTree(Tree<List<TransactionData>> tree) {
                    Repo.this.sendReadyTransactions(tree);
                }
            });
        }
    }

    private void sendTransactionQueue(final List<TransactionData> queue, final Path path) {
        List<Long> setsToIgnore = new ArrayList<>();
        for (TransactionData txn : queue) {
            setsToIgnore.add(Long.valueOf(txn.currentWriteId));
        }
        Node latestState = getLatestState(path, setsToIgnore);
        Node snapToSend = latestState;
        String latestHash = "badhash";
        if (!this.hijackHash) {
            latestHash = latestState.getHash();
        }
        Iterator<TransactionData> it = queue.iterator();
        while (true) {
            boolean z = true;
            if (it.hasNext()) {
                TransactionData txn2 = it.next();
                if (txn2.status != TransactionStatus.RUN) {
                    z = false;
                }
                Utilities.hardAssert(z);
                TransactionStatus unused = txn2.status = TransactionStatus.SENT;
                TransactionData.access$2108(txn2);
                snapToSend = snapToSend.updateChild(Path.getRelative(path, txn2.path), txn2.currentOutputSnapshotRaw);
            } else {
                this.connection.compareAndPut(path.asList(), snapToSend.getValue(true), latestHash, new RequestResultCallback() {
                    public void onRequestResult(String optErrorCode, String optErrorMessage) {
                        DatabaseError error = Repo.fromErrorCode(optErrorCode, optErrorMessage);
                        Repo.this.warnIfWriteFailed("Transaction", path, error);
                        List<Event> events = new ArrayList<>();
                        if (error == null) {
                            List<Runnable> callbacks = new ArrayList<>();
                            for (final TransactionData txn : queue) {
                                TransactionStatus unused = txn.status = TransactionStatus.COMPLETED;
                                events.addAll(Repo.this.serverSyncTree.ackUserWrite(txn.currentWriteId, false, false, Repo.this.serverClock));
                                final DataSnapshot snap = InternalHelpers.createDataSnapshot(InternalHelpers.createReference(this, txn.path), IndexedNode.from(txn.currentOutputSnapshotResolved));
                                callbacks.add(new Runnable() {
                                    public void run() {
                                        txn.handler.onComplete((DatabaseError) null, true, snap);
                                    }
                                });
                                Repo.this.removeEventCallback(new ValueEventRegistration(Repo.this, txn.outstandingListener, QuerySpec.defaultQueryAtPath(txn.path)));
                            }
                            Repo repo = Repo.this;
                            repo.pruneCompletedTransactions(repo.transactionQueueTree.subTree(path));
                            Repo.this.sendAllReadyTransactions();
                            this.postEvents(events);
                            for (int i = 0; i < callbacks.size(); i++) {
                                Repo.this.postEvent(callbacks.get(i));
                            }
                            return;
                        }
                        if (error.getCode() == -1) {
                            for (TransactionData transaction : queue) {
                                if (transaction.status == TransactionStatus.SENT_NEEDS_ABORT) {
                                    TransactionStatus unused2 = transaction.status = TransactionStatus.NEEDS_ABORT;
                                } else {
                                    TransactionStatus unused3 = transaction.status = TransactionStatus.RUN;
                                }
                            }
                        } else {
                            for (TransactionData transaction2 : queue) {
                                TransactionStatus unused4 = transaction2.status = TransactionStatus.NEEDS_ABORT;
                                DatabaseError unused5 = transaction2.abortReason = error;
                            }
                        }
                        Path unused6 = Repo.this.rerunTransactions(path);
                    }
                });
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public void pruneCompletedTransactions(Tree<List<TransactionData>> node) {
        List<TransactionData> queue = node.getValue();
        if (queue != null) {
            int i = 0;
            while (i < queue.size()) {
                if (queue.get(i).status == TransactionStatus.COMPLETED) {
                    queue.remove(i);
                } else {
                    i++;
                }
            }
            if (queue.size() > 0) {
                node.setValue(queue);
            } else {
                node.setValue(null);
            }
        }
        node.forEachChild(new Tree.TreeVisitor<List<TransactionData>>() {
            public void visitTree(Tree<List<TransactionData>> tree) {
                Repo.this.pruneCompletedTransactions(tree);
            }
        });
    }

    private long nextTransactionOrder() {
        long j = this.transactionOrder;
        this.transactionOrder = 1 + j;
        return j;
    }

    /* access modifiers changed from: private */
    public Path rerunTransactions(Path changedPath) {
        Tree<List<TransactionData>> rootMostTransactionNode = getAncestorTransactionNode(changedPath);
        Path path = rootMostTransactionNode.getPath();
        rerunTransactionQueue(buildTransactionQueue(rootMostTransactionNode), path);
        return path;
    }

    private void rerunTransactionQueue(List<TransactionData> queue, Path path) {
        Iterator<TransactionData> it;
        Transaction.Result result;
        if (!queue.isEmpty()) {
            List<Runnable> callbacks = new ArrayList<>();
            List<Long> setsToIgnore = new ArrayList<>();
            for (TransactionData transaction : queue) {
                setsToIgnore.add(Long.valueOf(transaction.currentWriteId));
            }
            Iterator<TransactionData> it2 = queue.iterator();
            while (it2.hasNext()) {
                final TransactionData transaction2 = it2.next();
                Path relativePath = Path.getRelative(path, transaction2.path);
                boolean abortTransaction = false;
                DatabaseError abortReason = null;
                List<Event> events = new ArrayList<>();
                Utilities.hardAssert(relativePath != null);
                if (transaction2.status == TransactionStatus.NEEDS_ABORT) {
                    abortTransaction = true;
                    abortReason = transaction2.abortReason;
                    if (abortReason.getCode() != -25) {
                        events.addAll(this.serverSyncTree.ackUserWrite(transaction2.currentWriteId, true, false, this.serverClock));
                    }
                    it = it2;
                    Path path2 = relativePath;
                } else if (transaction2.status != TransactionStatus.RUN) {
                    it = it2;
                    Path path3 = relativePath;
                } else if (transaction2.retryCount >= 25) {
                    abortTransaction = true;
                    abortReason = DatabaseError.fromStatus(TRANSACTION_TOO_MANY_RETRIES);
                    events.addAll(this.serverSyncTree.ackUserWrite(transaction2.currentWriteId, true, false, this.serverClock));
                    it = it2;
                    Path path4 = relativePath;
                } else {
                    Node currentNode = getLatestState(transaction2.path, setsToIgnore);
                    Node unused = transaction2.currentInputSnapshot = currentNode;
                    DatabaseError error = null;
                    try {
                        result = transaction2.handler.doTransaction(InternalHelpers.createMutableData(currentNode));
                    } catch (Throwable e) {
                        this.operationLogger.error("Caught Throwable.", e);
                        error = DatabaseError.fromException(e);
                        result = Transaction.abort();
                    }
                    if (result.isSuccess()) {
                        Long oldWriteId = Long.valueOf(transaction2.currentWriteId);
                        Map<String, Object> serverValues = ServerValues.generateServerValues(this.serverClock);
                        it = it2;
                        Node newDataNode = result.getNode();
                        Transaction.Result result2 = result;
                        Node newNodeResolved = ServerValues.resolveDeferredValueSnapshot(newDataNode, currentNode, serverValues);
                        Node unused2 = transaction2.currentOutputSnapshotRaw = newDataNode;
                        Node unused3 = transaction2.currentOutputSnapshotResolved = newNodeResolved;
                        Path path5 = relativePath;
                        long unused4 = transaction2.currentWriteId = getNextWriteId();
                        setsToIgnore.remove(oldWriteId);
                        events.addAll(this.serverSyncTree.applyUserOverwrite(transaction2.path, newDataNode, newNodeResolved, transaction2.currentWriteId, transaction2.applyLocally, false));
                        events.addAll(this.serverSyncTree.ackUserWrite(oldWriteId.longValue(), true, false, this.serverClock));
                    } else {
                        it = it2;
                        Path path6 = relativePath;
                        abortTransaction = true;
                        abortReason = error;
                        events.addAll(this.serverSyncTree.ackUserWrite(transaction2.currentWriteId, true, false, this.serverClock));
                    }
                }
                postEvents(events);
                if (abortTransaction) {
                    TransactionStatus unused5 = transaction2.status = TransactionStatus.COMPLETED;
                    final DataSnapshot snapshot = InternalHelpers.createDataSnapshot(InternalHelpers.createReference(this, transaction2.path), IndexedNode.from(transaction2.currentInputSnapshot));
                    scheduleNow(new Runnable() {
                        public void run() {
                            Repo.this.removeEventCallback(new ValueEventRegistration(Repo.this, transaction2.outstandingListener, QuerySpec.defaultQueryAtPath(transaction2.path)));
                        }
                    });
                    final DatabaseError callbackError = abortReason;
                    callbacks.add(new Runnable() {
                        public void run() {
                            transaction2.handler.onComplete(callbackError, false, snapshot);
                        }
                    });
                }
                it2 = it;
            }
            pruneCompletedTransactions(this.transactionQueueTree);
            for (int i = 0; i < callbacks.size(); i++) {
                postEvent(callbacks.get(i));
            }
            sendAllReadyTransactions();
        }
    }

    private Tree<List<TransactionData>> getAncestorTransactionNode(Path path) {
        Tree<List<TransactionData>> transactionNode = this.transactionQueueTree;
        while (!path.isEmpty() && transactionNode.getValue() == null) {
            transactionNode = transactionNode.subTree(new Path(path.getFront()));
            path = path.popFront();
        }
        return transactionNode;
    }

    private List<TransactionData> buildTransactionQueue(Tree<List<TransactionData>> transactionNode) {
        List<TransactionData> queue = new ArrayList<>();
        aggregateTransactionQueues(queue, transactionNode);
        Collections.sort(queue);
        return queue;
    }

    /* access modifiers changed from: private */
    public void aggregateTransactionQueues(final List<TransactionData> queue, Tree<List<TransactionData>> node) {
        List<TransactionData> childQueue = node.getValue();
        if (childQueue != null) {
            queue.addAll(childQueue);
        }
        node.forEachChild(new Tree.TreeVisitor<List<TransactionData>>() {
            public void visitTree(Tree<List<TransactionData>> tree) {
                Repo.this.aggregateTransactionQueues(queue, tree);
            }
        });
    }

    /* access modifiers changed from: private */
    public Path abortTransactions(Path path, final int reason) {
        Path affectedPath = getAncestorTransactionNode(path).getPath();
        if (this.transactionLogger.logsDebug()) {
            this.operationLogger.debug("Aborting transactions for path: " + path + ". Affected: " + affectedPath, new Object[0]);
        }
        Tree<List<TransactionData>> transactionNode = this.transactionQueueTree.subTree(path);
        transactionNode.forEachAncestor(new Tree.TreeFilter<List<TransactionData>>() {
            public boolean filterTreeNode(Tree<List<TransactionData>> tree) {
                Repo.this.abortTransactionsAtNode(tree, reason);
                return false;
            }
        });
        abortTransactionsAtNode(transactionNode, reason);
        transactionNode.forEachDescendant(new Tree.TreeVisitor<List<TransactionData>>() {
            public void visitTree(Tree<List<TransactionData>> tree) {
                Repo.this.abortTransactionsAtNode(tree, reason);
            }
        });
        return affectedPath;
    }

    /* access modifiers changed from: private */
    public void abortTransactionsAtNode(Tree<List<TransactionData>> node, int reason) {
        final DatabaseError abortError;
        Tree<List<TransactionData>> tree = node;
        int i = reason;
        List<TransactionData> queue = node.getValue();
        List<Event> events = new ArrayList<>();
        if (queue != null) {
            List<Runnable> callbacks = new ArrayList<>();
            if (i == -9) {
                abortError = DatabaseError.fromStatus(TRANSACTION_OVERRIDE_BY_SET);
            } else {
                Utilities.hardAssert(i == -25, "Unknown transaction abort reason: " + i);
                abortError = DatabaseError.fromCode(-25);
            }
            int lastSent = -1;
            for (int i2 = 0; i2 < queue.size(); i2++) {
                final TransactionData transaction = queue.get(i2);
                if (transaction.status != TransactionStatus.SENT_NEEDS_ABORT) {
                    if (transaction.status == TransactionStatus.SENT) {
                        Utilities.hardAssert(lastSent == i2 + -1);
                        TransactionStatus unused = transaction.status = TransactionStatus.SENT_NEEDS_ABORT;
                        DatabaseError unused2 = transaction.abortReason = abortError;
                        lastSent = i2;
                    } else {
                        Utilities.hardAssert(transaction.status == TransactionStatus.RUN);
                        removeEventCallback(new ValueEventRegistration(this, transaction.outstandingListener, QuerySpec.defaultQueryAtPath(transaction.path)));
                        if (i == -9) {
                            events.addAll(this.serverSyncTree.ackUserWrite(transaction.currentWriteId, true, false, this.serverClock));
                        } else {
                            Utilities.hardAssert(i == -25, "Unknown transaction abort reason: " + i);
                        }
                        callbacks.add(new Runnable() {
                            public void run() {
                                transaction.handler.onComplete(abortError, false, (DataSnapshot) null);
                            }
                        });
                    }
                }
            }
            if (lastSent == -1) {
                tree.setValue(null);
            } else {
                tree.setValue(queue.subList(0, lastSent + 1));
            }
            postEvents(events);
            for (Runnable r : callbacks) {
                postEvent(r);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public SyncTree getServerSyncTree() {
        return this.serverSyncTree;
    }

    /* access modifiers changed from: package-private */
    public SyncTree getInfoSyncTree() {
        return this.infoSyncTree;
    }

    /* access modifiers changed from: private */
    public static DatabaseError fromErrorCode(String optErrorCode, String optErrorReason) {
        if (optErrorCode != null) {
            return DatabaseError.fromStatus(optErrorCode, optErrorReason);
        }
        return null;
    }
}
