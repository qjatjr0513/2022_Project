package com.google.firebase.database.core.persistence;

import com.google.firebase.database.core.Path;
import com.google.firebase.database.core.utilities.Clock;
import com.google.firebase.database.core.utilities.ImmutableTree;
import com.google.firebase.database.core.utilities.Predicate;
import com.google.firebase.database.core.utilities.Utilities;
import com.google.firebase.database.core.view.QueryParams;
import com.google.firebase.database.core.view.QuerySpec;
import com.google.firebase.database.logging.LogWrapper;
import com.google.firebase.database.snapshot.ChildKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TrackedQueryManager {
    private static final Predicate<Map<QueryParams, TrackedQuery>> HAS_ACTIVE_DEFAULT_PREDICATE = new Predicate<Map<QueryParams, TrackedQuery>>() {
        public boolean evaluate(Map<QueryParams, TrackedQuery> trackedQueries) {
            TrackedQuery trackedQuery = trackedQueries.get(QueryParams.DEFAULT_PARAMS);
            return trackedQuery != null && trackedQuery.active;
        }
    };
    private static final Predicate<Map<QueryParams, TrackedQuery>> HAS_DEFAULT_COMPLETE_PREDICATE = new Predicate<Map<QueryParams, TrackedQuery>>() {
        public boolean evaluate(Map<QueryParams, TrackedQuery> trackedQueries) {
            TrackedQuery trackedQuery = trackedQueries.get(QueryParams.DEFAULT_PARAMS);
            return trackedQuery != null && trackedQuery.complete;
        }
    };
    /* access modifiers changed from: private */
    public static final Predicate<TrackedQuery> IS_QUERY_PRUNABLE_PREDICATE = new Predicate<TrackedQuery>() {
        public boolean evaluate(TrackedQuery query) {
            return !query.active;
        }
    };
    private static final Predicate<TrackedQuery> IS_QUERY_UNPRUNABLE_PREDICATE = new Predicate<TrackedQuery>() {
        public boolean evaluate(TrackedQuery query) {
            return !TrackedQueryManager.IS_QUERY_PRUNABLE_PREDICATE.evaluate(query);
        }
    };
    private final Clock clock;
    private long currentQueryId = 0;
    private final LogWrapper logger;
    private final PersistenceStorageEngine storageLayer;
    private ImmutableTree<Map<QueryParams, TrackedQuery>> trackedQueryTree;

    private static void assertValidTrackedQuery(QuerySpec query) {
        Utilities.hardAssert(!query.loadsAllData() || query.isDefault(), "Can't have tracked non-default query that loads all data");
    }

    private static QuerySpec normalizeQuery(QuerySpec query) {
        return query.loadsAllData() ? QuerySpec.defaultQueryAtPath(query.getPath()) : query;
    }

    public TrackedQueryManager(PersistenceStorageEngine storageLayer2, LogWrapper logger2, Clock clock2) {
        this.storageLayer = storageLayer2;
        this.logger = logger2;
        this.clock = clock2;
        this.trackedQueryTree = new ImmutableTree<>(null);
        resetPreviouslyActiveTrackedQueries();
        for (TrackedQuery query : storageLayer2.loadTrackedQueries()) {
            this.currentQueryId = Math.max(query.f140id + 1, this.currentQueryId);
            cacheTrackedQuery(query);
        }
    }

    private void resetPreviouslyActiveTrackedQueries() {
        try {
            this.storageLayer.beginTransaction();
            this.storageLayer.resetPreviouslyActiveTrackedQueries(this.clock.millis());
            this.storageLayer.setTransactionSuccessful();
        } finally {
            this.storageLayer.endTransaction();
        }
    }

    public TrackedQuery findTrackedQuery(QuerySpec query) {
        QuerySpec query2 = normalizeQuery(query);
        Map<QueryParams, TrackedQuery> set = this.trackedQueryTree.get(query2.getPath());
        if (set != null) {
            return set.get(query2.getParams());
        }
        return null;
    }

    public void removeTrackedQuery(QuerySpec query) {
        QuerySpec query2 = normalizeQuery(query);
        TrackedQuery trackedQuery = findTrackedQuery(query2);
        Utilities.hardAssert(trackedQuery != null, "Query must exist to be removed.");
        this.storageLayer.deleteTrackedQuery(trackedQuery.f140id);
        Map<QueryParams, TrackedQuery> trackedQueries = this.trackedQueryTree.get(query2.getPath());
        trackedQueries.remove(query2.getParams());
        if (trackedQueries.isEmpty()) {
            this.trackedQueryTree = this.trackedQueryTree.remove(query2.getPath());
        }
    }

    public void setQueryActive(QuerySpec query) {
        setQueryActiveFlag(query, true);
    }

    public void setQueryInactive(QuerySpec query) {
        setQueryActiveFlag(query, false);
    }

    private void setQueryActiveFlag(QuerySpec query, boolean isActive) {
        TrackedQuery trackedQuery;
        QuerySpec query2 = normalizeQuery(query);
        TrackedQuery trackedQuery2 = findTrackedQuery(query2);
        long lastUse = this.clock.millis();
        if (trackedQuery2 != null) {
            trackedQuery = trackedQuery2.updateLastUse(lastUse).setActiveState(isActive);
        } else {
            Utilities.hardAssert(isActive, "If we're setting the query to inactive, we should already be tracking it!");
            long j = this.currentQueryId;
            this.currentQueryId = 1 + j;
            trackedQuery = new TrackedQuery(j, query2, lastUse, false, isActive);
        }
        saveTrackedQuery(trackedQuery);
    }

    public void setQueryCompleteIfExists(QuerySpec query) {
        TrackedQuery trackedQuery = findTrackedQuery(normalizeQuery(query));
        if (trackedQuery != null && !trackedQuery.complete) {
            saveTrackedQuery(trackedQuery.setComplete());
        }
    }

    public void setQueriesComplete(Path path) {
        this.trackedQueryTree.subtree(path).foreach(new ImmutableTree.TreeVisitor<Map<QueryParams, TrackedQuery>, Void>() {
            public Void onNodeValue(Path relativePath, Map<QueryParams, TrackedQuery> value, Void accum) {
                for (Map.Entry<QueryParams, TrackedQuery> e : value.entrySet()) {
                    TrackedQuery trackedQuery = e.getValue();
                    if (!trackedQuery.complete) {
                        TrackedQueryManager.this.saveTrackedQuery(trackedQuery.setComplete());
                    }
                }
                return null;
            }
        });
    }

    public boolean isQueryComplete(QuerySpec query) {
        if (includedInDefaultCompleteQuery(query.getPath())) {
            return true;
        }
        if (query.loadsAllData()) {
            return false;
        }
        Map<QueryParams, TrackedQuery> trackedQueries = this.trackedQueryTree.get(query.getPath());
        if (trackedQueries == null || !trackedQueries.containsKey(query.getParams()) || !trackedQueries.get(query.getParams()).complete) {
            return false;
        }
        return true;
    }

    public PruneForest pruneOldQueries(CachePolicy cachePolicy) {
        List<TrackedQuery> prunable = getQueriesMatching(IS_QUERY_PRUNABLE_PREDICATE);
        long countToPrune = calculateCountToPrune(cachePolicy, (long) prunable.size());
        PruneForest forest = new PruneForest();
        if (this.logger.logsDebug()) {
            this.logger.debug("Pruning old queries.  Prunable: " + prunable.size() + " Count to prune: " + countToPrune, new Object[0]);
        }
        Collections.sort(prunable, new Comparator<TrackedQuery>() {
            public int compare(TrackedQuery q1, TrackedQuery q2) {
                return Utilities.compareLongs(q1.lastUse, q2.lastUse);
            }
        });
        for (int i = 0; ((long) i) < countToPrune; i++) {
            TrackedQuery toPrune = prunable.get(i);
            forest = forest.prune(toPrune.querySpec.getPath());
            removeTrackedQuery(toPrune.querySpec);
        }
        for (int i2 = (int) countToPrune; i2 < prunable.size(); i2++) {
            forest = forest.keep(prunable.get(i2).querySpec.getPath());
        }
        List<TrackedQuery> unprunable = getQueriesMatching(IS_QUERY_UNPRUNABLE_PREDICATE);
        if (this.logger.logsDebug()) {
            this.logger.debug("Unprunable queries: " + unprunable.size(), new Object[0]);
        }
        for (TrackedQuery toKeep : unprunable) {
            forest = forest.keep(toKeep.querySpec.getPath());
        }
        return forest;
    }

    private static long calculateCountToPrune(CachePolicy cachePolicy, long prunableCount) {
        return prunableCount - Math.min((long) Math.floor((double) (((float) prunableCount) * (1.0f - cachePolicy.getPercentOfQueriesToPruneAtOnce()))), cachePolicy.getMaxNumberOfQueriesToKeep());
    }

    public Set<ChildKey> getKnownCompleteChildren(Path path) {
        Utilities.hardAssert(!isQueryComplete(QuerySpec.defaultQueryAtPath(path)), "Path is fully complete.");
        Set<ChildKey> completeChildren = new HashSet<>();
        Set<Long> queryIds = filteredQueryIdsAtPath(path);
        if (!queryIds.isEmpty()) {
            completeChildren.addAll(this.storageLayer.loadTrackedQueryKeys(queryIds));
        }
        Iterator<Map.Entry<ChildKey, ImmutableTree<Map<QueryParams, TrackedQuery>>>> it = this.trackedQueryTree.subtree(path).getChildren().iterator();
        while (it.hasNext()) {
            Map.Entry<ChildKey, ImmutableTree<Map<QueryParams, TrackedQuery>>> childEntry = it.next();
            ChildKey childKey = childEntry.getKey();
            ImmutableTree<Map<QueryParams, TrackedQuery>> childTree = childEntry.getValue();
            if (childTree.getValue() != null && HAS_DEFAULT_COMPLETE_PREDICATE.evaluate(childTree.getValue())) {
                completeChildren.add(childKey);
            }
        }
        return completeChildren;
    }

    public void ensureCompleteTrackedQuery(Path path) {
        TrackedQuery trackedQuery;
        if (!includedInDefaultCompleteQuery(path)) {
            QuerySpec querySpec = QuerySpec.defaultQueryAtPath(path);
            TrackedQuery trackedQuery2 = findTrackedQuery(querySpec);
            if (trackedQuery2 == null) {
                long j = this.currentQueryId;
                this.currentQueryId = 1 + j;
                trackedQuery = new TrackedQuery(j, querySpec, this.clock.millis(), true, false);
            } else {
                Utilities.hardAssert(!trackedQuery2.complete, "This should have been handled above!");
                trackedQuery = trackedQuery2.setComplete();
            }
            saveTrackedQuery(trackedQuery);
        }
    }

    public boolean hasActiveDefaultQuery(Path path) {
        return this.trackedQueryTree.rootMostValueMatching(path, HAS_ACTIVE_DEFAULT_PREDICATE) != null;
    }

    public long countOfPrunableQueries() {
        return (long) getQueriesMatching(IS_QUERY_PRUNABLE_PREDICATE).size();
    }

    /* access modifiers changed from: package-private */
    public void verifyCache() {
        List<TrackedQuery> storedTrackedQueries = this.storageLayer.loadTrackedQueries();
        final List<TrackedQuery> trackedQueries = new ArrayList<>();
        this.trackedQueryTree.foreach(new ImmutableTree.TreeVisitor<Map<QueryParams, TrackedQuery>, Void>() {
            public Void onNodeValue(Path relativePath, Map<QueryParams, TrackedQuery> value, Void accum) {
                for (TrackedQuery trackedQuery : value.values()) {
                    trackedQueries.add(trackedQuery);
                }
                return null;
            }
        });
        Collections.sort(trackedQueries, new Comparator<TrackedQuery>() {
            public int compare(TrackedQuery o1, TrackedQuery o2) {
                return Utilities.compareLongs(o1.f140id, o2.f140id);
            }
        });
        Utilities.hardAssert(storedTrackedQueries.equals(trackedQueries), "Tracked queries out of sync.  Tracked queries: " + trackedQueries + " Stored queries: " + storedTrackedQueries);
    }

    private boolean includedInDefaultCompleteQuery(Path path) {
        return this.trackedQueryTree.findRootMostMatchingPath(path, HAS_DEFAULT_COMPLETE_PREDICATE) != null;
    }

    private Set<Long> filteredQueryIdsAtPath(Path path) {
        Set<Long> ids = new HashSet<>();
        Map<QueryParams, TrackedQuery> queries = this.trackedQueryTree.get(path);
        if (queries != null) {
            for (TrackedQuery query : queries.values()) {
                if (!query.querySpec.loadsAllData()) {
                    ids.add(Long.valueOf(query.f140id));
                }
            }
        }
        return ids;
    }

    private void cacheTrackedQuery(TrackedQuery query) {
        assertValidTrackedQuery(query.querySpec);
        Map map = this.trackedQueryTree.get(query.querySpec.getPath());
        if (map == null) {
            map = new HashMap();
            this.trackedQueryTree = this.trackedQueryTree.set(query.querySpec.getPath(), map);
        }
        TrackedQuery existing = (TrackedQuery) map.get(query.querySpec.getParams());
        Utilities.hardAssert(existing == null || existing.f140id == query.f140id);
        map.put(query.querySpec.getParams(), query);
    }

    /* access modifiers changed from: private */
    public void saveTrackedQuery(TrackedQuery query) {
        cacheTrackedQuery(query);
        this.storageLayer.saveTrackedQuery(query);
    }

    private List<TrackedQuery> getQueriesMatching(Predicate<TrackedQuery> predicate) {
        List<TrackedQuery> matching = new ArrayList<>();
        Iterator<Map.Entry<Path, Map<QueryParams, TrackedQuery>>> it = this.trackedQueryTree.iterator();
        while (it.hasNext()) {
            for (TrackedQuery query : it.next().getValue().values()) {
                if (predicate.evaluate(query)) {
                    matching.add(query);
                }
            }
        }
        return matching;
    }
}
