package com.google.firebase.firestore.local;

import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Supplier;
import java.util.HashMap;
import java.util.Map;

public final class MemoryPersistence extends Persistence {
    private final MemoryBundleCache bundleCache = new MemoryBundleCache();
    private final MemoryIndexManager indexManager = new MemoryIndexManager();
    private final Map<User, MemoryMutationQueue> mutationQueues = new HashMap();
    private final Map<User, MemoryDocumentOverlayCache> overlays = new HashMap();
    private ReferenceDelegate referenceDelegate;
    private final MemoryRemoteDocumentCache remoteDocumentCache = new MemoryRemoteDocumentCache();
    private boolean started;
    private final MemoryTargetCache targetCache = new MemoryTargetCache(this);

    public static MemoryPersistence createEagerGcMemoryPersistence() {
        MemoryPersistence persistence = new MemoryPersistence();
        persistence.setReferenceDelegate(new MemoryEagerReferenceDelegate(persistence));
        return persistence;
    }

    public static MemoryPersistence createLruGcMemoryPersistence(LruGarbageCollector.Params params, LocalSerializer serializer) {
        MemoryPersistence persistence = new MemoryPersistence();
        persistence.setReferenceDelegate(new MemoryLruReferenceDelegate(persistence, params, serializer));
        return persistence;
    }

    private MemoryPersistence() {
    }

    public void start() {
        Assert.hardAssert(!this.started, "MemoryPersistence double-started!", new Object[0]);
        this.started = true;
    }

    public void shutdown() {
        Assert.hardAssert(this.started, "MemoryPersistence shutdown without start", new Object[0]);
        this.started = false;
    }

    public boolean isStarted() {
        return this.started;
    }

    /* access modifiers changed from: package-private */
    public ReferenceDelegate getReferenceDelegate() {
        return this.referenceDelegate;
    }

    private void setReferenceDelegate(ReferenceDelegate delegate) {
        this.referenceDelegate = delegate;
    }

    /* access modifiers changed from: package-private */
    public MutationQueue getMutationQueue(User user, IndexManager indexManager2) {
        MemoryMutationQueue queue = this.mutationQueues.get(user);
        if (queue != null) {
            return queue;
        }
        MemoryMutationQueue queue2 = new MemoryMutationQueue(this, user);
        this.mutationQueues.put(user, queue2);
        return queue2;
    }

    /* access modifiers changed from: package-private */
    public Iterable<MemoryMutationQueue> getMutationQueues() {
        return this.mutationQueues.values();
    }

    /* access modifiers changed from: package-private */
    public MemoryTargetCache getTargetCache() {
        return this.targetCache;
    }

    /* access modifiers changed from: package-private */
    public MemoryRemoteDocumentCache getRemoteDocumentCache() {
        return this.remoteDocumentCache;
    }

    /* access modifiers changed from: package-private */
    public MemoryIndexManager getIndexManager(User user) {
        return this.indexManager;
    }

    /* access modifiers changed from: package-private */
    public BundleCache getBundleCache() {
        return this.bundleCache;
    }

    /* access modifiers changed from: package-private */
    public DocumentOverlayCache getDocumentOverlay(User user) {
        MemoryDocumentOverlayCache overlay = this.overlays.get(user);
        if (overlay != null) {
            return overlay;
        }
        MemoryDocumentOverlayCache overlay2 = new MemoryDocumentOverlayCache();
        this.overlays.put(user, overlay2);
        return overlay2;
    }

    /* access modifiers changed from: package-private */
    public OverlayMigrationManager getOverlayMigrationManager() {
        return new MemoryOverlayMigrationManager();
    }

    /* access modifiers changed from: package-private */
    public void runTransaction(String action, Runnable operation) {
        this.referenceDelegate.onTransactionStarted();
        try {
            operation.run();
        } finally {
            this.referenceDelegate.onTransactionCommitted();
        }
    }

    /* access modifiers changed from: package-private */
    public <T> T runTransaction(String action, Supplier<T> operation) {
        this.referenceDelegate.onTransactionStarted();
        try {
            return operation.get();
        } finally {
            this.referenceDelegate.onTransactionCommitted();
        }
    }
}
