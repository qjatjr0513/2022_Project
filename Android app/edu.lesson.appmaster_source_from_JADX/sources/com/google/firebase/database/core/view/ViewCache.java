package com.google.firebase.database.core.view;

import com.google.firebase.database.snapshot.IndexedNode;
import com.google.firebase.database.snapshot.Node;

public class ViewCache {
    private final CacheNode eventSnap;
    private final CacheNode serverSnap;

    public ViewCache(CacheNode eventSnap2, CacheNode serverSnap2) {
        this.eventSnap = eventSnap2;
        this.serverSnap = serverSnap2;
    }

    public ViewCache updateEventSnap(IndexedNode eventSnap2, boolean complete, boolean filtered) {
        return new ViewCache(new CacheNode(eventSnap2, complete, filtered), this.serverSnap);
    }

    public ViewCache updateServerSnap(IndexedNode serverSnap2, boolean complete, boolean filtered) {
        return new ViewCache(this.eventSnap, new CacheNode(serverSnap2, complete, filtered));
    }

    public CacheNode getEventCache() {
        return this.eventSnap;
    }

    public Node getCompleteEventSnap() {
        if (this.eventSnap.isFullyInitialized()) {
            return this.eventSnap.getNode();
        }
        return null;
    }

    public CacheNode getServerCache() {
        return this.serverSnap;
    }

    public Node getCompleteServerSnap() {
        if (this.serverSnap.isFullyInitialized()) {
            return this.serverSnap.getNode();
        }
        return null;
    }
}
