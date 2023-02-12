package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.Overlay;
import com.google.firebase.firestore.util.Preconditions;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

public class MemoryDocumentOverlayCache implements DocumentOverlayCache {
    private final Map<Integer, Set<DocumentKey>> overlayByBatchId = new HashMap();
    private final TreeMap<DocumentKey, Overlay> overlays = new TreeMap<>();

    public Overlay getOverlay(DocumentKey key) {
        return this.overlays.get(key);
    }

    public Map<DocumentKey, Overlay> getOverlays(SortedSet<DocumentKey> keys) {
        Map<DocumentKey, Overlay> result = new HashMap<>();
        for (DocumentKey key : keys) {
            Overlay overlay = this.overlays.get(key);
            if (overlay != null) {
                result.put(key, overlay);
            }
        }
        return result;
    }

    private void saveOverlay(int largestBatchId, Mutation mutation) {
        Overlay existing = this.overlays.get(mutation.getKey());
        if (existing != null) {
            this.overlayByBatchId.get(Integer.valueOf(existing.getLargestBatchId())).remove(mutation.getKey());
        }
        this.overlays.put(mutation.getKey(), Overlay.create(largestBatchId, mutation));
        if (this.overlayByBatchId.get(Integer.valueOf(largestBatchId)) == null) {
            this.overlayByBatchId.put(Integer.valueOf(largestBatchId), new HashSet());
        }
        this.overlayByBatchId.get(Integer.valueOf(largestBatchId)).add(mutation.getKey());
    }

    public void saveOverlays(int largestBatchId, Map<DocumentKey, Mutation> overlays2) {
        for (Map.Entry<DocumentKey, Mutation> entry : overlays2.entrySet()) {
            saveOverlay(largestBatchId, (Mutation) Preconditions.checkNotNull(entry.getValue(), "null value for key: %s", entry.getKey()));
        }
    }

    public void removeOverlaysForBatchId(int batchId) {
        if (this.overlayByBatchId.containsKey(Integer.valueOf(batchId))) {
            this.overlayByBatchId.remove(Integer.valueOf(batchId));
            for (DocumentKey key : this.overlayByBatchId.get(Integer.valueOf(batchId))) {
                this.overlays.remove(key);
            }
        }
    }

    public Map<DocumentKey, Overlay> getOverlays(ResourcePath collection, int sinceBatchId) {
        Map<DocumentKey, Overlay> result = new HashMap<>();
        int immediateChildrenPathLength = collection.length() + 1;
        for (Overlay overlay : this.overlays.tailMap(DocumentKey.fromPath((ResourcePath) collection.append(""))).values()) {
            DocumentKey key = overlay.getKey();
            if (!collection.isPrefixOf(key.getPath())) {
                break;
            } else if (key.getPath().length() == immediateChildrenPathLength && overlay.getLargestBatchId() > sinceBatchId) {
                result.put(overlay.getKey(), overlay);
            }
        }
        return result;
    }

    public Map<DocumentKey, Overlay> getOverlays(String collectionGroup, int sinceBatchId, int count) {
        SortedMap<Integer, Map<DocumentKey, Overlay>> batchIdToOverlays = new TreeMap<>();
        for (Overlay overlay : this.overlays.values()) {
            if (overlay.getKey().getCollectionGroup().equals(collectionGroup) && overlay.getLargestBatchId() > sinceBatchId) {
                Map<DocumentKey, Overlay> overlays2 = (Map) batchIdToOverlays.get(Integer.valueOf(overlay.getLargestBatchId()));
                if (overlays2 == null) {
                    overlays2 = new HashMap<>();
                    batchIdToOverlays.put(Integer.valueOf(overlay.getLargestBatchId()), overlays2);
                }
                overlays2.put(overlay.getKey(), overlay);
            }
        }
        Map<DocumentKey, Overlay> result = new HashMap<>();
        for (Map<DocumentKey, Overlay> overlays3 : batchIdToOverlays.values()) {
            result.putAll(overlays3);
            if (result.size() >= count) {
                break;
            }
        }
        return result;
    }
}
