package com.google.firebase.firestore.core;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.core.SyncEngine;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.firestore.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import p004io.grpc.Status;

public final class EventManager implements SyncEngine.SyncEngineCallback {
    private OnlineState onlineState = OnlineState.UNKNOWN;
    private final Map<Query, QueryListenersInfo> queries;
    private final Set<EventListener<Void>> snapshotsInSyncListeners = new HashSet();
    private final SyncEngine syncEngine;

    public static class ListenOptions {
        public boolean includeDocumentMetadataChanges;
        public boolean includeQueryMetadataChanges;
        public boolean waitForSyncWhenOnline;
    }

    private static class QueryListenersInfo {
        /* access modifiers changed from: private */
        public final List<QueryListener> listeners = new ArrayList();
        /* access modifiers changed from: private */
        public int targetId;
        /* access modifiers changed from: private */
        public ViewSnapshot viewSnapshot;

        QueryListenersInfo() {
        }
    }

    public EventManager(SyncEngine syncEngine2) {
        this.syncEngine = syncEngine2;
        this.queries = new HashMap();
        syncEngine2.setCallback(this);
    }

    public int addQueryListener(QueryListener queryListener) {
        Query query = queryListener.getQuery();
        QueryListenersInfo queryInfo = this.queries.get(query);
        boolean firstListen = queryInfo == null;
        if (firstListen) {
            queryInfo = new QueryListenersInfo();
            this.queries.put(query, queryInfo);
        }
        queryInfo.listeners.add(queryListener);
        Assert.hardAssert(!queryListener.onOnlineStateChanged(this.onlineState), "onOnlineStateChanged() shouldn't raise an event for brand-new listeners.", new Object[0]);
        if (queryInfo.viewSnapshot != null && queryListener.onViewSnapshot(queryInfo.viewSnapshot)) {
            raiseSnapshotsInSyncEvent();
        }
        if (firstListen) {
            int unused = queryInfo.targetId = this.syncEngine.listen(query);
        }
        return queryInfo.targetId;
    }

    public void removeQueryListener(QueryListener listener) {
        Query query = listener.getQuery();
        QueryListenersInfo queryInfo = this.queries.get(query);
        boolean lastListen = false;
        if (queryInfo != null) {
            queryInfo.listeners.remove(listener);
            lastListen = queryInfo.listeners.isEmpty();
        }
        if (lastListen) {
            this.queries.remove(query);
            this.syncEngine.stopListening(query);
        }
    }

    public void addSnapshotsInSyncListener(EventListener<Void> listener) {
        this.snapshotsInSyncListeners.add(listener);
        listener.onEvent(null, (FirebaseFirestoreException) null);
    }

    public void removeSnapshotsInSyncListener(EventListener<Void> listener) {
        this.snapshotsInSyncListeners.remove(listener);
    }

    private void raiseSnapshotsInSyncEvent() {
        for (EventListener<Void> listener : this.snapshotsInSyncListeners) {
            listener.onEvent(null, (FirebaseFirestoreException) null);
        }
    }

    public void onViewSnapshots(List<ViewSnapshot> snapshotList) {
        boolean raisedEvent = false;
        for (ViewSnapshot viewSnapshot : snapshotList) {
            QueryListenersInfo info = this.queries.get(viewSnapshot.getQuery());
            if (info != null) {
                for (QueryListener listener : info.listeners) {
                    if (listener.onViewSnapshot(viewSnapshot)) {
                        raisedEvent = true;
                    }
                }
                ViewSnapshot unused = info.viewSnapshot = viewSnapshot;
            }
        }
        if (raisedEvent) {
            raiseSnapshotsInSyncEvent();
        }
    }

    public void onError(Query query, Status error) {
        QueryListenersInfo info = this.queries.get(query);
        if (info != null) {
            for (QueryListener listener : info.listeners) {
                listener.onError(Util.exceptionFromStatus(error));
            }
        }
        this.queries.remove(query);
    }

    public void handleOnlineStateChange(OnlineState onlineState2) {
        boolean raisedEvent = false;
        this.onlineState = onlineState2;
        for (QueryListenersInfo info : this.queries.values()) {
            for (QueryListener listener : info.listeners) {
                if (listener.onOnlineStateChanged(onlineState2)) {
                    raisedEvent = true;
                }
            }
        }
        if (raisedEvent) {
            raiseSnapshotsInSyncEvent();
        }
    }
}
