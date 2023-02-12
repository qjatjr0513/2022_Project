package com.google.firebase.firestore.remote;

import com.google.firebase.database.collection.ImmutableSortedSet;
import com.google.firebase.firestore.core.DocumentViewChange;
import com.google.firebase.firestore.core.Target;
import com.google.firebase.firestore.local.QueryPurpose;
import com.google.firebase.firestore.local.TargetData;
import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.MutableDocument;
import com.google.firebase.firestore.model.SnapshotVersion;
import com.google.firebase.firestore.remote.WatchChange;
import com.google.firebase.firestore.util.Assert;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WatchChangeAggregator {
    private Map<DocumentKey, Set<Integer>> pendingDocumentTargetMapping = new HashMap();
    private Map<DocumentKey, MutableDocument> pendingDocumentUpdates = new HashMap();
    private Set<Integer> pendingTargetResets = new HashSet();
    private final TargetMetadataProvider targetMetadataProvider;
    private final Map<Integer, TargetState> targetStates = new HashMap();

    public interface TargetMetadataProvider {
        ImmutableSortedSet<DocumentKey> getRemoteKeysForTarget(int i);

        TargetData getTargetDataForTarget(int i);
    }

    public WatchChangeAggregator(TargetMetadataProvider targetMetadataProvider2) {
        this.targetMetadataProvider = targetMetadataProvider2;
    }

    public void handleDocumentChange(WatchChange.DocumentChange documentChange) {
        MutableDocument document = documentChange.getNewDocument();
        DocumentKey documentKey = documentChange.getDocumentKey();
        for (Integer intValue : documentChange.getUpdatedTargetIds()) {
            int targetId = intValue.intValue();
            if (document == null || !document.isFoundDocument()) {
                removeDocumentFromTarget(targetId, documentKey, document);
            } else {
                addDocumentToTarget(targetId, document);
            }
        }
        for (Integer intValue2 : documentChange.getRemovedTargetIds()) {
            removeDocumentFromTarget(intValue2.intValue(), documentKey, documentChange.getNewDocument());
        }
    }

    public void handleTargetChange(WatchChange.WatchTargetChange targetChange) {
        for (Integer intValue : getTargetIds(targetChange)) {
            int targetId = intValue.intValue();
            TargetState targetState = ensureTargetState(targetId);
            boolean z = true;
            switch (C07961.f188x3bf9e295[targetChange.getChangeType().ordinal()]) {
                case 1:
                    if (!isActiveTarget(targetId)) {
                        break;
                    } else {
                        targetState.updateResumeToken(targetChange.getResumeToken());
                        break;
                    }
                case 2:
                    targetState.recordTargetResponse();
                    if (!targetState.isPending()) {
                        targetState.clearChanges();
                    }
                    targetState.updateResumeToken(targetChange.getResumeToken());
                    break;
                case 3:
                    targetState.recordTargetResponse();
                    if (!targetState.isPending()) {
                        removeTarget(targetId);
                    }
                    if (targetChange.getCause() != null) {
                        z = false;
                    }
                    Assert.hardAssert(z, "WatchChangeAggregator does not handle errored targets", new Object[0]);
                    break;
                case 4:
                    if (!isActiveTarget(targetId)) {
                        break;
                    } else {
                        targetState.markCurrent();
                        targetState.updateResumeToken(targetChange.getResumeToken());
                        break;
                    }
                case 5:
                    if (!isActiveTarget(targetId)) {
                        break;
                    } else {
                        resetTarget(targetId);
                        targetState.updateResumeToken(targetChange.getResumeToken());
                        break;
                    }
                default:
                    throw Assert.fail("Unknown target watch change state: %s", targetChange.getChangeType());
            }
        }
    }

    /* renamed from: com.google.firebase.firestore.remote.WatchChangeAggregator$1 */
    static /* synthetic */ class C07961 {

        /* renamed from: $SwitchMap$com$google$firebase$firestore$remote$WatchChange$WatchTargetChangeType */
        static final /* synthetic */ int[] f188x3bf9e295;

        static {
            int[] iArr = new int[WatchChange.WatchTargetChangeType.values().length];
            f188x3bf9e295 = iArr;
            try {
                iArr[WatchChange.WatchTargetChangeType.NoChange.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f188x3bf9e295[WatchChange.WatchTargetChangeType.Added.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f188x3bf9e295[WatchChange.WatchTargetChangeType.Removed.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f188x3bf9e295[WatchChange.WatchTargetChangeType.Current.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f188x3bf9e295[WatchChange.WatchTargetChangeType.Reset.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    private Collection<Integer> getTargetIds(WatchChange.WatchTargetChange targetChange) {
        List<Integer> targetIds = targetChange.getTargetIds();
        if (!targetIds.isEmpty()) {
            return targetIds;
        }
        List<Integer> activeIds = new ArrayList<>();
        for (Integer id : this.targetStates.keySet()) {
            if (isActiveTarget(id.intValue())) {
                activeIds.add(id);
            }
        }
        return activeIds;
    }

    public void handleExistenceFilter(WatchChange.ExistenceFilterWatchChange watchChange) {
        int targetId = watchChange.getTargetId();
        int expectedCount = watchChange.getExistenceFilter().getCount();
        TargetData targetData = queryDataForActiveTarget(targetId);
        if (targetData != null) {
            Target target = targetData.getTarget();
            if (target.isDocumentQuery()) {
                if (expectedCount == 0) {
                    DocumentKey key = DocumentKey.fromPath(target.getPath());
                    removeDocumentFromTarget(targetId, key, MutableDocument.newNoDocument(key, SnapshotVersion.NONE));
                    return;
                }
                Assert.hardAssert(expectedCount == 1, "Single document existence filter with count: %d", Integer.valueOf(expectedCount));
            } else if (((long) getCurrentDocumentCountForTarget(targetId)) != ((long) expectedCount)) {
                resetTarget(targetId);
                this.pendingTargetResets.add(Integer.valueOf(targetId));
            }
        }
    }

    public RemoteEvent createRemoteEvent(SnapshotVersion snapshotVersion) {
        Map<Integer, TargetChange> targetChanges = new HashMap<>();
        for (Map.Entry<Integer, TargetState> entry : this.targetStates.entrySet()) {
            int targetId = entry.getKey().intValue();
            TargetState targetState = entry.getValue();
            TargetData targetData = queryDataForActiveTarget(targetId);
            if (targetData != null) {
                if (targetState.isCurrent() && targetData.getTarget().isDocumentQuery()) {
                    DocumentKey key = DocumentKey.fromPath(targetData.getTarget().getPath());
                    if (this.pendingDocumentUpdates.get(key) == null && !targetContainsDocument(targetId, key)) {
                        removeDocumentFromTarget(targetId, key, MutableDocument.newNoDocument(key, snapshotVersion));
                    }
                }
                if (targetState.hasChanges()) {
                    targetChanges.put(Integer.valueOf(targetId), targetState.toTargetChange());
                    targetState.clearChanges();
                }
            }
        }
        Set<DocumentKey> resolvedLimboDocuments = new HashSet<>();
        for (Map.Entry<DocumentKey, Set<Integer>> entry2 : this.pendingDocumentTargetMapping.entrySet()) {
            DocumentKey key2 = entry2.getKey();
            boolean isOnlyLimboTarget = true;
            Iterator<Integer> it = entry2.getValue().iterator();
            while (true) {
                if (it.hasNext()) {
                    TargetData targetData2 = queryDataForActiveTarget(it.next().intValue());
                    if (targetData2 != null && !targetData2.getPurpose().equals(QueryPurpose.LIMBO_RESOLUTION)) {
                        isOnlyLimboTarget = false;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (isOnlyLimboTarget) {
                resolvedLimboDocuments.add(key2);
            }
        }
        for (MutableDocument document : this.pendingDocumentUpdates.values()) {
            document.setReadTime(snapshotVersion);
        }
        RemoteEvent remoteEvent = new RemoteEvent(snapshotVersion, Collections.unmodifiableMap(targetChanges), Collections.unmodifiableSet(this.pendingTargetResets), Collections.unmodifiableMap(this.pendingDocumentUpdates), Collections.unmodifiableSet(resolvedLimboDocuments));
        this.pendingDocumentUpdates = new HashMap();
        this.pendingDocumentTargetMapping = new HashMap();
        this.pendingTargetResets = new HashSet();
        return remoteEvent;
    }

    private void addDocumentToTarget(int targetId, MutableDocument document) {
        DocumentViewChange.Type changeType;
        if (isActiveTarget(targetId)) {
            if (targetContainsDocument(targetId, document.getKey())) {
                changeType = DocumentViewChange.Type.MODIFIED;
            } else {
                changeType = DocumentViewChange.Type.ADDED;
            }
            ensureTargetState(targetId).addDocumentChange(document.getKey(), changeType);
            this.pendingDocumentUpdates.put(document.getKey(), document);
            ensureDocumentTargetMapping(document.getKey()).add(Integer.valueOf(targetId));
        }
    }

    private void removeDocumentFromTarget(int targetId, DocumentKey key, MutableDocument updatedDocument) {
        if (isActiveTarget(targetId)) {
            TargetState targetState = ensureTargetState(targetId);
            if (targetContainsDocument(targetId, key)) {
                targetState.addDocumentChange(key, DocumentViewChange.Type.REMOVED);
            } else {
                targetState.removeDocumentChange(key);
            }
            ensureDocumentTargetMapping(key).add(Integer.valueOf(targetId));
            if (updatedDocument != null) {
                this.pendingDocumentUpdates.put(key, updatedDocument);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void removeTarget(int targetId) {
        this.targetStates.remove(Integer.valueOf(targetId));
    }

    private int getCurrentDocumentCountForTarget(int targetId) {
        TargetChange targetChange = ensureTargetState(targetId).toTargetChange();
        return (this.targetMetadataProvider.getRemoteKeysForTarget(targetId).size() + targetChange.getAddedDocuments().size()) - targetChange.getRemovedDocuments().size();
    }

    /* access modifiers changed from: package-private */
    public void recordPendingTargetRequest(int targetId) {
        ensureTargetState(targetId).recordPendingTargetRequest();
    }

    private TargetState ensureTargetState(int targetId) {
        TargetState targetState = this.targetStates.get(Integer.valueOf(targetId));
        if (targetState != null) {
            return targetState;
        }
        TargetState targetState2 = new TargetState();
        this.targetStates.put(Integer.valueOf(targetId), targetState2);
        return targetState2;
    }

    private Set<Integer> ensureDocumentTargetMapping(DocumentKey key) {
        Set<Integer> targetMapping = this.pendingDocumentTargetMapping.get(key);
        if (targetMapping != null) {
            return targetMapping;
        }
        Set<Integer> targetMapping2 = new HashSet<>();
        this.pendingDocumentTargetMapping.put(key, targetMapping2);
        return targetMapping2;
    }

    private boolean isActiveTarget(int targetId) {
        return queryDataForActiveTarget(targetId) != null;
    }

    private TargetData queryDataForActiveTarget(int targetId) {
        TargetState targetState = this.targetStates.get(Integer.valueOf(targetId));
        if (targetState == null || !targetState.isPending()) {
            return this.targetMetadataProvider.getTargetDataForTarget(targetId);
        }
        return null;
    }

    private void resetTarget(int targetId) {
        Assert.hardAssert(this.targetStates.get(Integer.valueOf(targetId)) != null && !this.targetStates.get(Integer.valueOf(targetId)).isPending(), "Should only reset active targets", new Object[0]);
        this.targetStates.put(Integer.valueOf(targetId), new TargetState());
        Iterator<DocumentKey> it = this.targetMetadataProvider.getRemoteKeysForTarget(targetId).iterator();
        while (it.hasNext()) {
            removeDocumentFromTarget(targetId, it.next(), (MutableDocument) null);
        }
    }

    private boolean targetContainsDocument(int targetId, DocumentKey key) {
        return this.targetMetadataProvider.getRemoteKeysForTarget(targetId).contains(key);
    }
}
