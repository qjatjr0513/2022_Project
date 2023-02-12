package com.google.firebase.database.core.persistence;

import com.google.firebase.database.core.CompoundWrite;
import com.google.firebase.database.core.Context;
import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.UserWriteRecord;
import com.google.firebase.database.core.utilities.Clock;
import com.google.firebase.database.core.utilities.DefaultClock;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.CacheNode;
import com.google.firebase.database.core.view.QuerySpec;
import com.google.firebase.database.logging.LogWrapper;
import com.google.firebase.database.snapshot.ChildKey;
import com.google.firebase.database.snapshot.EmptyNode;
import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.Node;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

public class DefaultPersistenceManager implements PersistenceManager {
    private final CachePolicy cachePolicy;
    private final LogWrapper logger;
    private long serverCacheUpdatesSinceLastPruneCheck;
    private final PersistenceStorageEngine storageLayer;
    private final TrackedQueryManager trackedQueryManager;

    public DefaultPersistenceManager(Context ctx, PersistenceStorageEngine engine, CachePolicy cachePolicy2) {
        this(ctx, engine, cachePolicy2, new DefaultClock());
    }

    public DefaultPersistenceManager(Context ctx, PersistenceStorageEngine engine, CachePolicy cachePolicy2, Clock clock) {
        this.serverCacheUpdatesSinceLastPruneCheck = 0;
        this.storageLayer = engine;
        LogWrapper logger2 = ctx.getLogger("Persistence");
        this.logger = logger2;
        this.trackedQueryManager = new TrackedQueryManager(engine, logger2, clock);
        this.cachePolicy = cachePolicy2;
    }

    public void saveUserOverwrite(Path path, Node node, long writeId) {
        this.storageLayer.saveUserOverwrite(path, node, writeId);
    }

    public void saveUserMerge(Path path, CompoundWrite children, long writeId) {
        this.storageLayer.saveUserMerge(path, children, writeId);
    }

    public void removeUserWrite(long writeId) {
        this.storageLayer.removeUserWrite(writeId);
    }

    public void removeAllUserWrites() {
        this.storageLayer.removeAllUserWrites();
    }

    public void applyUserWriteToServerCache(Path path, Node node) {
        if (!this.trackedQueryManager.hasActiveDefaultQuery(path)) {
            this.storageLayer.overwriteServerCache(path, node);
            this.trackedQueryManager.ensureCompleteTrackedQuery(path);
        }
    }

    public void applyUserWriteToServerCache(Path path, CompoundWrite merge) {
        Iterator<Map.Entry<Path, Node>> it = merge.iterator();
        while (it.hasNext()) {
            Map.Entry<Path, Node> write = it.next();
            applyUserWriteToServerCache(path.child(write.getKey()), write.getValue());
        }
    }

    public List<UserWriteRecord> loadUserWrites() {
        return this.storageLayer.loadUserWrites();
    }

    public CacheNode serverCache(QuerySpec query) {
        Set<ChildKey> trackedKeys;
        boolean complete;
        if (this.trackedQueryManager.isQueryComplete(query)) {
            complete = true;
            TrackedQuery trackedQuery = this.trackedQueryManager.findTrackedQuery(query);
            if (query.loadsAllData() || trackedQuery == null || !trackedQuery.complete) {
                trackedKeys = null;
            } else {
                trackedKeys = this.storageLayer.loadTrackedQueryKeys(trackedQuery.f140id);
            }
        } else {
            complete = false;
            trackedKeys = this.trackedQueryManager.getKnownCompleteChildren(query.getPath());
        }
        Node serverCacheNode = this.storageLayer.serverCache(query.getPath());
        if (trackedKeys == null) {
            return new CacheNode(IndexedNode.from(serverCacheNode, query.getIndex()), complete, false);
        }
        Node filteredNode = EmptyNode.Empty();
        for (ChildKey key : trackedKeys) {
            filteredNode = filteredNode.updateImmediateChild(key, serverCacheNode.getImmediateChild(key));
        }
        return new CacheNode(IndexedNode.from(filteredNode, query.getIndex()), complete, true);
    }

    public void updateServerCache(QuerySpec query, Node node) {
        if (query.loadsAllData()) {
            this.storageLayer.overwriteServerCache(query.getPath(), node);
        } else {
            this.storageLayer.mergeIntoServerCache(query.getPath(), node);
        }
        setQueryComplete(query);
        doPruneCheckAfterServerUpdate();
    }

    public void updateServerCache(Path path, CompoundWrite children) {
        this.storageLayer.mergeIntoServerCache(path, children);
        doPruneCheckAfterServerUpdate();
    }

    public void setQueryActive(QuerySpec query) {
        this.trackedQueryManager.setQueryActive(query);
    }

    public void setQueryInactive(QuerySpec query) {
        this.trackedQueryManager.setQueryInactive(query);
    }

    public void setQueryComplete(QuerySpec query) {
        if (query.loadsAllData()) {
            this.trackedQueryManager.setQueriesComplete(query.getPath());
        } else {
            this.trackedQueryManager.setQueryCompleteIfExists(query);
        }
    }

    public void setTrackedQueryKeys(QuerySpec query, Set<ChildKey> keys) {
        boolean z = true;
        Utilities.hardAssert(!query.loadsAllData(), "We should only track keys for filtered queries.");
        TrackedQuery trackedQuery = this.trackedQueryManager.findTrackedQuery(query);
        if (trackedQuery == null || !trackedQuery.active) {
            z = false;
        }
        Utilities.hardAssert(z, "We only expect tracked keys for currently-active queries.");
        this.storageLayer.saveTrackedQueryKeys(trackedQuery.f140id, keys);
    }

    public void updateTrackedQueryKeys(QuerySpec query, Set<ChildKey> added, Set<ChildKey> removed) {
        boolean z = true;
        Utilities.hardAssert(!query.loadsAllData(), "We should only track keys for filtered queries.");
        TrackedQuery trackedQuery = this.trackedQueryManager.findTrackedQuery(query);
        if (trackedQuery == null || !trackedQuery.active) {
            z = false;
        }
        Utilities.hardAssert(z, "We only expect tracked keys for currently-active queries.");
        this.storageLayer.updateTrackedQueryKeys(trackedQuery.f140id, added, removed);
    }

    public <T> T runInTransaction(Callable<T> callable) {
        this.storageLayer.beginTransaction();
        try {
            T result = callable.call();
            this.storageLayer.setTransactionSuccessful();
            this.storageLayer.endTransaction();
            return result;
        } catch (Throwable e) {
            this.storageLayer.endTransaction();
            throw e;
        }
    }

    private void doPruneCheckAfterServerUpdate() {
        long j = this.serverCacheUpdatesSinceLastPruneCheck + 1;
        this.serverCacheUpdatesSinceLastPruneCheck = j;
        if (this.cachePolicy.shouldCheckCacheSize(j)) {
            if (this.logger.logsDebug()) {
                this.logger.debug("Reached prune check threshold.", new Object[0]);
            }
            this.serverCacheUpdatesSinceLastPruneCheck = 0;
            boolean canPrune = true;
            long cacheSize = this.storageLayer.serverCacheEstimatedSizeInBytes();
            if (this.logger.logsDebug()) {
                this.logger.debug("Cache size: " + cacheSize, new Object[0]);
            }
            while (canPrune && this.cachePolicy.shouldPrune(cacheSize, this.trackedQueryManager.countOfPrunableQueries())) {
                PruneForest pruneForest = this.trackedQueryManager.pruneOldQueries(this.cachePolicy);
                if (pruneForest.prunesAnything()) {
                    this.storageLayer.pruneCache(Path.getEmptyPath(), pruneForest);
                } else {
                    canPrune = false;
                }
                cacheSize = this.storageLayer.serverCacheEstimatedSizeInBytes();
                if (this.logger.logsDebug()) {
                    this.logger.debug("Cache size after prune: " + cacheSize, new Object[0]);
                }
            }
        }
    }
}
