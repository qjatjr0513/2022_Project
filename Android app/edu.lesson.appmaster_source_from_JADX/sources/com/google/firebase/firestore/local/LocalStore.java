package com.google.firebase.firestore.local;

import android.util.SparseArray;
import com.google.firebase.Timestamp;
import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.bundle.BundleCallback;
import com.google.firebase.firestore.bundle.BundleMetadata;
import com.google.firebase.firestore.bundle.NamedQuery;
import com.google.firebase.firestore.core.Query;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.core.TargetIdGenerator;
import com.google.firebase.firestore.local.LruGarbageCollector;
import com.google.firebase.firestore.model.Document;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.FieldIndex;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.ObjectValue;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.MutationBatch;
import com.google.firebase.firestore.model.mutation.MutationBatchResult;
import com.google.firebase.firestore.model.mutation.PatchMutation;
import com.google.firebase.firestore.model.mutation.Precondition;
import com.google.firebase.firestore.remote.RemoteEvent;
import com.google.firebase.firestore.remote.TargetChange;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Logger;
import com.google.firebase.firestore.util.Util;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public final class LocalStore implements BundleCallback {
    private static final long RESUME_TOKEN_MAX_AGE_SECONDS = TimeUnit.MINUTES.toSeconds(5);
    private final BundleCache bundleCache;
    private DocumentOverlayCache documentOverlayCache;
    private final IndexBackfiller indexBackfiller;
    private IndexManager indexManager;
    private LocalDocumentsView localDocuments;
    private final ReferenceSet localViewReferences;
    private MutationQueue mutationQueue;
    private final Persistence persistence;
    private final SparseArray<TargetData> queryDataByTarget = new SparseArray<>();
    private final QueryEngine queryEngine;
    private final RemoteDocumentCache remoteDocuments;
    private final TargetCache targetCache;
    private final Map<Target, Integer> targetIdByTarget = new HashMap();
    private final TargetIdGenerator targetIdGenerator;

    public LocalStore(Persistence persistence2, IndexBackfiller indexBackfiller2, QueryEngine queryEngine2, User initialUser) {
        Assert.hardAssert(persistence2.isStarted(), "LocalStore was passed an unstarted persistence implementation", new Object[0]);
        this.persistence = persistence2;
        this.queryEngine = queryEngine2;
        this.indexBackfiller = indexBackfiller2;
        TargetCache targetCache2 = persistence2.getTargetCache();
        this.targetCache = targetCache2;
        this.bundleCache = persistence2.getBundleCache();
        this.targetIdGenerator = TargetIdGenerator.forTargetCache(targetCache2.getHighestTargetId());
        this.remoteDocuments = persistence2.getRemoteDocumentCache();
        ReferenceSet referenceSet = new ReferenceSet();
        this.localViewReferences = referenceSet;
        persistence2.getReferenceDelegate().setInMemoryPins(referenceSet);
        initializeUserComponents(initialUser);
    }

    private void initializeUserComponents(User user) {
        IndexManager indexManager2 = this.persistence.getIndexManager(user);
        this.indexManager = indexManager2;
        this.mutationQueue = this.persistence.getMutationQueue(user, indexManager2);
        this.documentOverlayCache = this.persistence.getDocumentOverlay(user);
        this.localDocuments = new LocalDocumentsView(this.remoteDocuments, this.mutationQueue, this.documentOverlayCache, this.indexManager);
        this.remoteDocuments.setIndexManager(this.indexManager);
        this.queryEngine.initialize(this.localDocuments, this.indexManager);
        IndexBackfiller indexBackfiller2 = this.indexBackfiller;
        if (indexBackfiller2 != null) {
            indexBackfiller2.setIndexManager(this.indexManager);
            this.indexBackfiller.setLocalDocumentsView(this.localDocuments);
        }
    }

    public void start() {
        this.persistence.getOverlayMigrationManager().run();
        startIndexManager();
        startMutationQueue();
    }

    private void startIndexManager() {
        this.persistence.runTransaction("Start IndexManager", (Runnable) new LocalStore$$ExternalSyntheticLambda2(this));
    }

    /* renamed from: lambda$startIndexManager$0$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ void mo9167xf9470190() {
        this.indexManager.start();
    }

    private void startMutationQueue() {
        this.persistence.runTransaction("Start MutationQueue", (Runnable) new LocalStore$$ExternalSyntheticLambda3(this));
    }

    /* renamed from: lambda$startMutationQueue$1$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ void mo9168xa2ea7b8c() {
        this.mutationQueue.start();
    }

    public ImmutableSortedMap<DocumentKey, Document> handleUserChange(User user) {
        List<MutationBatch> oldBatches = this.mutationQueue.getAllMutationBatches();
        initializeUserComponents(user);
        startIndexManager();
        startMutationQueue();
        List<MutationBatch> newBatches = this.mutationQueue.getAllMutationBatches();
        ImmutableSortedSet<DocumentKey> changedKeys = DocumentKey.emptyKeySet();
        for (List<MutationBatch> batches : Arrays.asList(new List[]{oldBatches, newBatches})) {
            for (MutationBatch batch : batches) {
                for (Mutation mutation : batch.getMutations()) {
                    changedKeys = changedKeys.insert(mutation.getKey());
                }
            }
        }
        return this.localDocuments.getDocuments(changedKeys);
    }

    public LocalDocumentsResult writeLocally(List<Mutation> mutations) {
        Timestamp localWriteTime = Timestamp.now();
        Set<DocumentKey> keys = new HashSet<>();
        for (Mutation mutation : mutations) {
            keys.add(mutation.getKey());
        }
        return (LocalDocumentsResult) this.persistence.runTransaction("Locally write mutations", new LocalStore$$ExternalSyntheticLambda1(this, keys, mutations, localWriteTime));
    }

    /* renamed from: lambda$writeLocally$2$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ LocalDocumentsResult mo9169x4f0ff958(Set keys, List mutations, Timestamp localWriteTime) {
        Map<DocumentKey, MutableDocument> remoteDocs = this.remoteDocuments.getAll(keys);
        Set<DocumentKey> docsWithoutRemoteVersion = new HashSet<>();
        for (Map.Entry<DocumentKey, MutableDocument> entry : remoteDocs.entrySet()) {
            if (!entry.getValue().isValidDocument()) {
                docsWithoutRemoteVersion.add(entry.getKey());
            }
        }
        ImmutableSortedMap<DocumentKey, Document> documents = this.localDocuments.getLocalViewOfDocuments(remoteDocs);
        List<Mutation> baseMutations = new ArrayList<>();
        Iterator it = mutations.iterator();
        while (it.hasNext()) {
            Mutation mutation = (Mutation) it.next();
            ObjectValue baseValue = mutation.extractTransformBaseValue(documents.get(mutation.getKey()));
            if (baseValue != null) {
                baseMutations.add(new PatchMutation(mutation.getKey(), baseValue, baseValue.getFieldMask(), Precondition.exists(true)));
            }
        }
        MutationBatch batch = this.mutationQueue.addMutationBatch(localWriteTime, baseMutations, mutations);
        this.documentOverlayCache.saveOverlays(batch.getBatchId(), batch.applyToLocalDocumentSet(documents, docsWithoutRemoteVersion));
        return new LocalDocumentsResult(batch.getBatchId(), documents);
    }

    public ImmutableSortedMap<DocumentKey, Document> acknowledgeBatch(MutationBatchResult batchResult) {
        return (ImmutableSortedMap) this.persistence.runTransaction("Acknowledge batch", new LocalStore$$ExternalSyntheticLambda17(this, batchResult));
    }

    /* renamed from: lambda$acknowledgeBatch$3$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ ImmutableSortedMap mo9152xd0c8f3b2(MutationBatchResult batchResult) {
        MutationBatch batch = batchResult.getBatch();
        this.mutationQueue.acknowledgeBatch(batch, batchResult.getStreamToken());
        applyWriteToRemoteDocuments(batchResult);
        this.mutationQueue.performConsistencyCheck();
        this.documentOverlayCache.removeOverlaysForBatchId(batchResult.getBatch().getBatchId());
        this.localDocuments.recalculateAndSaveOverlays(getKeysWithTransformResults(batchResult));
        return this.localDocuments.getDocuments(batch.getKeys());
    }

    private Set<DocumentKey> getKeysWithTransformResults(MutationBatchResult batchResult) {
        Set<DocumentKey> result = new HashSet<>();
        for (int i = 0; i < batchResult.getMutationResults().size(); i++) {
            if (!batchResult.getMutationResults().get(i).getTransformResults().isEmpty()) {
                result.add(batchResult.getBatch().getMutations().get(i).getKey());
            }
        }
        return result;
    }

    public ImmutableSortedMap<DocumentKey, Document> rejectBatch(int batchId) {
        return (ImmutableSortedMap) this.persistence.runTransaction("Reject batch", new LocalStore$$ExternalSyntheticLambda13(this, batchId));
    }

    /* renamed from: lambda$rejectBatch$4$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ ImmutableSortedMap mo9162x1ab75fae(int batchId) {
        MutationBatch toReject = this.mutationQueue.lookupMutationBatch(batchId);
        Assert.hardAssert(toReject != null, "Attempt to reject nonexistent batch!", new Object[0]);
        this.mutationQueue.removeMutationBatch(toReject);
        this.mutationQueue.performConsistencyCheck();
        this.documentOverlayCache.removeOverlaysForBatchId(batchId);
        this.localDocuments.recalculateAndSaveOverlays(toReject.getKeys());
        return this.localDocuments.getDocuments(toReject.getKeys());
    }

    public int getHighestUnacknowledgedBatchId() {
        return this.mutationQueue.getHighestUnacknowledgedBatchId();
    }

    public ByteString getLastStreamToken() {
        return this.mutationQueue.getLastStreamToken();
    }

    public void setLastStreamToken(ByteString streamToken) {
        this.persistence.runTransaction("Set stream token", (Runnable) new LocalStore$$ExternalSyntheticLambda8(this, streamToken));
    }

    /* renamed from: lambda$setLastStreamToken$5$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ void mo9166x6c1835ed(ByteString streamToken) {
        this.mutationQueue.setLastStreamToken(streamToken);
    }

    public SnapshotVersion getLastRemoteSnapshotVersion() {
        return this.targetCache.getLastRemoteSnapshotVersion();
    }

    public ImmutableSortedMap<DocumentKey, Document> applyRemoteEvent(RemoteEvent remoteEvent) {
        return (ImmutableSortedMap) this.persistence.runTransaction("Apply remote event", new LocalStore$$ExternalSyntheticLambda18(this, remoteEvent, remoteEvent.getSnapshotVersion()));
    }

    /* renamed from: lambda$applyRemoteEvent$6$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ ImmutableSortedMap mo9155xfb616aa7(RemoteEvent remoteEvent, SnapshotVersion remoteVersion) {
        Map<Integer, TargetChange> targetChanges = remoteEvent.getTargetChanges();
        long sequenceNumber = this.persistence.getReferenceDelegate().getCurrentSequenceNumber();
        for (Map.Entry<Integer, TargetChange> entry : targetChanges.entrySet()) {
            int targetId = entry.getKey().intValue();
            TargetChange change = entry.getValue();
            TargetData oldTargetData = this.queryDataByTarget.get(targetId);
            if (oldTargetData != null) {
                this.targetCache.removeMatchingKeys(change.getRemovedDocuments(), targetId);
                this.targetCache.addMatchingKeys(change.getAddedDocuments(), targetId);
                TargetData newTargetData = oldTargetData.withSequenceNumber(sequenceNumber);
                if (remoteEvent.getTargetMismatches().contains(Integer.valueOf(targetId))) {
                    newTargetData = newTargetData.withResumeToken(ByteString.EMPTY, SnapshotVersion.NONE).withLastLimboFreeSnapshotVersion(SnapshotVersion.NONE);
                } else if (!change.getResumeToken().isEmpty()) {
                    newTargetData = newTargetData.withResumeToken(change.getResumeToken(), remoteEvent.getSnapshotVersion());
                }
                this.queryDataByTarget.put(targetId, newTargetData);
                if (shouldPersistTargetData(oldTargetData, newTargetData, change)) {
                    this.targetCache.updateTargetData(newTargetData);
                }
            }
        }
        Map<DocumentKey, MutableDocument> documentUpdates = remoteEvent.getDocumentUpdates();
        Set<DocumentKey> limboDocuments = remoteEvent.getResolvedLimboDocuments();
        for (DocumentKey key : documentUpdates.keySet()) {
            if (limboDocuments.contains(key)) {
                this.persistence.getReferenceDelegate().updateLimboDocument(key);
            }
        }
        DocumentChangeResult result = populateDocumentChanges(documentUpdates);
        Map<DocumentKey, MutableDocument> changedDocs = result.changedDocuments;
        SnapshotVersion lastRemoteVersion = this.targetCache.getLastRemoteSnapshotVersion();
        if (!remoteVersion.equals(SnapshotVersion.NONE)) {
            Assert.hardAssert(remoteVersion.compareTo(lastRemoteVersion) >= 0, "Watch stream reverted to previous snapshot?? (%s < %s)", remoteVersion, lastRemoteVersion);
            this.targetCache.setLastRemoteSnapshotVersion(remoteVersion);
        }
        return this.localDocuments.getLocalViewOfDocuments(changedDocs, result.existenceChangedKeys);
    }

    private static class DocumentChangeResult {
        /* access modifiers changed from: private */
        public final Map<DocumentKey, MutableDocument> changedDocuments;
        /* access modifiers changed from: private */
        public final Set<DocumentKey> existenceChangedKeys;

        private DocumentChangeResult(Map<DocumentKey, MutableDocument> changedDocuments2, Set<DocumentKey> existenceChangedKeys2) {
            this.changedDocuments = changedDocuments2;
            this.existenceChangedKeys = existenceChangedKeys2;
        }
    }

    private DocumentChangeResult populateDocumentChanges(Map<DocumentKey, MutableDocument> documents) {
        Map<DocumentKey, MutableDocument> changedDocs = new HashMap<>();
        List<DocumentKey> removedDocs = new ArrayList<>();
        Set<DocumentKey> conditionChanged = new HashSet<>();
        Map<DocumentKey, MutableDocument> existingDocs = this.remoteDocuments.getAll(documents.keySet());
        for (Map.Entry<DocumentKey, MutableDocument> entry : documents.entrySet()) {
            DocumentKey key = entry.getKey();
            MutableDocument doc = entry.getValue();
            MutableDocument existingDoc = existingDocs.get(key);
            if (doc.isFoundDocument() != existingDoc.isFoundDocument()) {
                conditionChanged.add(key);
            }
            if (doc.isNoDocument() && doc.getVersion().equals(SnapshotVersion.NONE)) {
                removedDocs.add(doc.getKey());
                changedDocs.put(key, doc);
            } else if (!existingDoc.isValidDocument() || doc.getVersion().compareTo(existingDoc.getVersion()) > 0 || (doc.getVersion().compareTo(existingDoc.getVersion()) == 0 && existingDoc.hasPendingWrites())) {
                Assert.hardAssert(!SnapshotVersion.NONE.equals(doc.getReadTime()), "Cannot add a document when the remote version is zero", new Object[0]);
                this.remoteDocuments.add(doc, doc.getReadTime());
                changedDocs.put(key, doc);
            } else {
                Logger.debug("LocalStore", "Ignoring outdated watch update for %s.Current version: %s  Watch version: %s", key, existingDoc.getVersion(), doc.getVersion());
            }
        }
        this.remoteDocuments.removeAll(removedDocs);
        return new DocumentChangeResult(changedDocs, conditionChanged);
    }

    private static boolean shouldPersistTargetData(TargetData oldTargetData, TargetData newTargetData, TargetChange change) {
        if (!oldTargetData.getResumeToken().isEmpty() && newTargetData.getSnapshotVersion().getTimestamp().getSeconds() - oldTargetData.getSnapshotVersion().getTimestamp().getSeconds() < RESUME_TOKEN_MAX_AGE_SECONDS && change.getAddedDocuments().size() + change.getModifiedDocuments().size() + change.getRemovedDocuments().size() <= 0) {
            return false;
        }
        return true;
    }

    public void notifyLocalViewChanges(List<LocalViewChanges> viewChanges) {
        this.persistence.runTransaction("notifyLocalViewChanges", (Runnable) new LocalStore$$ExternalSyntheticLambda10(this, viewChanges));
    }

    /* renamed from: lambda$notifyLocalViewChanges$7$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ void mo9161x6c8bed50(List viewChanges) {
        Iterator it = viewChanges.iterator();
        while (it.hasNext()) {
            LocalViewChanges viewChange = (LocalViewChanges) it.next();
            int targetId = viewChange.getTargetId();
            this.localViewReferences.addReferences(viewChange.getAdded(), targetId);
            ImmutableSortedSet<DocumentKey> removed = viewChange.getRemoved();
            Iterator<DocumentKey> it2 = removed.iterator();
            while (it2.hasNext()) {
                this.persistence.getReferenceDelegate().removeReference(it2.next());
            }
            this.localViewReferences.removeReferences(removed, targetId);
            if (!viewChange.isFromCache()) {
                TargetData targetData = this.queryDataByTarget.get(targetId);
                Assert.hardAssert(targetData != null, "Can't set limbo-free snapshot version for unknown target: %s", Integer.valueOf(targetId));
                this.queryDataByTarget.put(targetId, targetData.withLastLimboFreeSnapshotVersion(targetData.getSnapshotVersion()));
            }
        }
    }

    public MutationBatch getNextMutationBatch(int afterBatchId) {
        return this.mutationQueue.getNextMutationBatchAfterBatchId(afterBatchId);
    }

    public Document readDocument(DocumentKey key) {
        return this.localDocuments.getDocument(key);
    }

    public TargetData allocateTarget(Target target) {
        int targetId;
        TargetData cached = this.targetCache.getTargetData(target);
        if (cached != null) {
            targetId = cached.getTargetId();
        } else {
            AllocateQueryHolder holder = new AllocateQueryHolder();
            this.persistence.runTransaction("Allocate target", (Runnable) new LocalStore$$ExternalSyntheticLambda7(this, holder, target));
            int targetId2 = holder.targetId;
            cached = holder.cached;
            targetId = targetId2;
        }
        if (this.queryDataByTarget.get(targetId) == null) {
            this.queryDataByTarget.put(targetId, cached);
            this.targetIdByTarget.put(target, Integer.valueOf(targetId));
        }
        return cached;
    }

    /* renamed from: lambda$allocateTarget$8$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ void mo9153x5de91a9d(AllocateQueryHolder holder, Target target) {
        holder.targetId = this.targetIdGenerator.nextId();
        holder.cached = new TargetData(target, holder.targetId, this.persistence.getReferenceDelegate().getCurrentSequenceNumber(), QueryPurpose.LISTEN);
        this.targetCache.addTargetData(holder.cached);
    }

    /* access modifiers changed from: package-private */
    public TargetData getTargetData(Target target) {
        Integer targetId = this.targetIdByTarget.get(target);
        if (targetId != null) {
            return this.queryDataByTarget.get(targetId.intValue());
        }
        return this.targetCache.getTargetData(target);
    }

    public boolean hasNewerBundle(BundleMetadata bundleMetadata) {
        return ((Boolean) this.persistence.runTransaction("Has newer bundle", new LocalStore$$ExternalSyntheticLambda15(this, bundleMetadata))).booleanValue();
    }

    /* renamed from: lambda$hasNewerBundle$9$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ Boolean mo9160x622d6455(BundleMetadata bundleMetadata) {
        BundleMetadata cachedMetadata = this.bundleCache.getBundleMetadata(bundleMetadata.getBundleId());
        return Boolean.valueOf(cachedMetadata != null && cachedMetadata.getCreateTime().compareTo(bundleMetadata.getCreateTime()) >= 0);
    }

    public void saveBundle(BundleMetadata bundleMetadata) {
        this.persistence.runTransaction("Save bundle", (Runnable) new LocalStore$$ExternalSyntheticLambda5(this, bundleMetadata));
    }

    /* renamed from: lambda$saveBundle$10$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ void mo9164x98d21eb5(BundleMetadata bundleMetadata) {
        this.bundleCache.saveBundleMetadata(bundleMetadata);
    }

    public ImmutableSortedMap<DocumentKey, Document> applyBundledDocuments(ImmutableSortedMap<DocumentKey, MutableDocument> documents, String bundleId) {
        return (ImmutableSortedMap) this.persistence.runTransaction("Apply bundle documents", new LocalStore$$ExternalSyntheticLambda14(this, documents, allocateTarget(newUmbrellaTarget(bundleId))));
    }

    /* renamed from: lambda$applyBundledDocuments$11$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ ImmutableSortedMap mo9154xda7cc59f(ImmutableSortedMap documents, TargetData umbrellaTargetData) {
        ImmutableSortedSet<DocumentKey> documentKeys = DocumentKey.emptyKeySet();
        Map<DocumentKey, MutableDocument> documentMap = new HashMap<>();
        Iterator it = documents.iterator();
        while (it.hasNext()) {
            Map.Entry<DocumentKey, MutableDocument> entry = (Map.Entry) it.next();
            DocumentKey documentKey = entry.getKey();
            MutableDocument document = entry.getValue();
            if (document.isFoundDocument()) {
                documentKeys = documentKeys.insert(documentKey);
            }
            documentMap.put(documentKey, document);
        }
        this.targetCache.removeMatchingKeysForTargetId(umbrellaTargetData.getTargetId());
        this.targetCache.addMatchingKeys(documentKeys, umbrellaTargetData.getTargetId());
        DocumentChangeResult result = populateDocumentChanges(documentMap);
        return this.localDocuments.getLocalViewOfDocuments(result.changedDocuments, result.existenceChangedKeys);
    }

    public void saveNamedQuery(NamedQuery namedQuery, ImmutableSortedSet<DocumentKey> documentKeys) {
        TargetData existingTargetData = allocateTarget(namedQuery.getBundledQuery().getTarget());
        this.persistence.runTransaction("Saved named query", (Runnable) new LocalStore$$ExternalSyntheticLambda6(this, namedQuery, existingTargetData, existingTargetData.getTargetId(), documentKeys));
    }

    /* renamed from: lambda$saveNamedQuery$12$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ void mo9165x3730c4c0(NamedQuery namedQuery, TargetData existingTargetData, int targetId, ImmutableSortedSet documentKeys) {
        if (namedQuery.getReadTime().compareTo(existingTargetData.getSnapshotVersion()) > 0) {
            TargetData newTargetData = existingTargetData.withResumeToken(ByteString.EMPTY, namedQuery.getReadTime());
            this.queryDataByTarget.append(targetId, newTargetData);
            this.targetCache.updateTargetData(newTargetData);
            this.targetCache.removeMatchingKeysForTargetId(targetId);
            this.targetCache.addMatchingKeys(documentKeys, targetId);
        }
        this.bundleCache.saveNamedQuery(namedQuery);
    }

    public NamedQuery getNamedQuery(String queryName) {
        return (NamedQuery) this.persistence.runTransaction("Get named query", new LocalStore$$ExternalSyntheticLambda19(this, queryName));
    }

    /* renamed from: lambda$getNamedQuery$13$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ NamedQuery mo9159x4cee2ade(String queryName) {
        return this.bundleCache.getNamedQuery(queryName);
    }

    /* access modifiers changed from: package-private */
    public Collection<FieldIndex> getFieldIndexes() {
        return (Collection) this.persistence.runTransaction("Get indexes", new LocalStore$$ExternalSyntheticLambda12(this));
    }

    /* renamed from: lambda$getFieldIndexes$14$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ Collection mo9158x64aa3c54() {
        return this.indexManager.getFieldIndexes();
    }

    public void configureFieldIndexes(List<FieldIndex> newFieldIndexes) {
        this.persistence.runTransaction("Configure indexes", (Runnable) new LocalStore$$ExternalSyntheticLambda9(this, newFieldIndexes));
    }

    /* renamed from: lambda$configureFieldIndexes$15$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ void mo9157x4fad3f83(List newFieldIndexes) {
        Collection<FieldIndex> fieldIndexes = this.indexManager.getFieldIndexes();
        Comparator<FieldIndex> comparator = FieldIndex.SEMANTIC_COMPARATOR;
        IndexManager indexManager2 = this.indexManager;
        Objects.requireNonNull(indexManager2);
        LocalStore$$ExternalSyntheticLambda0 localStore$$ExternalSyntheticLambda0 = new LocalStore$$ExternalSyntheticLambda0(indexManager2);
        IndexManager indexManager3 = this.indexManager;
        Objects.requireNonNull(indexManager3);
        Util.diffCollections(fieldIndexes, (Collection<FieldIndex>) newFieldIndexes, comparator, localStore$$ExternalSyntheticLambda0, (LocalStore$$ExternalSyntheticLambda0) new LocalStore$$ExternalSyntheticLambda11(indexManager3));
    }

    private static class AllocateQueryHolder {
        TargetData cached;
        int targetId;

        private AllocateQueryHolder() {
        }
    }

    public void releaseTarget(int targetId) {
        this.persistence.runTransaction("Release target", (Runnable) new LocalStore$$ExternalSyntheticLambda4(this, targetId));
    }

    /* renamed from: lambda$releaseTarget$16$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ void mo9163x1dfd2d2e(int targetId) {
        TargetData targetData = this.queryDataByTarget.get(targetId);
        Assert.hardAssert(targetData != null, "Tried to release nonexistent target: %s", Integer.valueOf(targetId));
        Iterator<DocumentKey> it = this.localViewReferences.removeReferencesForId(targetId).iterator();
        while (it.hasNext()) {
            this.persistence.getReferenceDelegate().removeReference(it.next());
        }
        this.persistence.getReferenceDelegate().removeTarget(targetData);
        this.queryDataByTarget.remove(targetId);
        this.targetIdByTarget.remove(targetData.getTarget());
    }

    public QueryResult executeQuery(Query query, boolean usePreviousResults) {
        TargetData targetData = getTargetData(query.toTarget());
        SnapshotVersion lastLimboFreeSnapshotVersion = SnapshotVersion.NONE;
        ImmutableSortedSet<DocumentKey> remoteKeys = DocumentKey.emptyKeySet();
        if (targetData != null) {
            lastLimboFreeSnapshotVersion = targetData.getLastLimboFreeSnapshotVersion();
            remoteKeys = this.targetCache.getMatchingKeysForTargetId(targetData.getTargetId());
        }
        return new QueryResult(this.queryEngine.getDocumentsMatchingQuery(query, usePreviousResults ? lastLimboFreeSnapshotVersion : SnapshotVersion.NONE, remoteKeys), remoteKeys);
    }

    public ImmutableSortedSet<DocumentKey> getRemoteDocumentKeys(int targetId) {
        return this.targetCache.getMatchingKeysForTargetId(targetId);
    }

    private void applyWriteToRemoteDocuments(MutationBatchResult batchResult) {
        MutationBatch batch = batchResult.getBatch();
        for (DocumentKey docKey : batch.getKeys()) {
            MutableDocument doc = this.remoteDocuments.get(docKey);
            SnapshotVersion ackVersion = batchResult.getDocVersions().get(docKey);
            Assert.hardAssert(ackVersion != null, "docVersions should contain every doc in the write.", new Object[0]);
            if (doc.getVersion().compareTo(ackVersion) < 0) {
                batch.applyToRemoteDocument(doc, batchResult);
                if (doc.isValidDocument()) {
                    this.remoteDocuments.add(doc, batchResult.getCommitVersion());
                }
            }
        }
        this.mutationQueue.removeMutationBatch(batch);
    }

    public LruGarbageCollector.Results collectGarbage(LruGarbageCollector garbageCollector) {
        return (LruGarbageCollector.Results) this.persistence.runTransaction("Collect garbage", new LocalStore$$ExternalSyntheticLambda16(this, garbageCollector));
    }

    /* renamed from: lambda$collectGarbage$17$com-google-firebase-firestore-local-LocalStore */
    public /* synthetic */ LruGarbageCollector.Results mo9156x1db5499a(LruGarbageCollector garbageCollector) {
        return garbageCollector.collect(this.queryDataByTarget);
    }

    private static Target newUmbrellaTarget(String bundleName) {
        return Query.atPath(ResourcePath.fromString("__bundle__/docs/" + bundleName)).toTarget();
    }
}
