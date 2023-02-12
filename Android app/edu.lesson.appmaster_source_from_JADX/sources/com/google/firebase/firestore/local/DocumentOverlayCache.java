package com.google.firebase.firestore.local;

import com.google.firebase.firestore.model.DocumentKey;
import com.google.firebase.firestore.model.ResourcePath;
import com.google.firebase.firestore.model.mutation.Mutation;
import com.google.firebase.firestore.model.mutation.Overlay;
import java.util.Map;
import java.util.SortedSet;

public interface DocumentOverlayCache {
    Overlay getOverlay(DocumentKey documentKey);

    Map<DocumentKey, Overlay> getOverlays(ResourcePath resourcePath, int i);

    Map<DocumentKey, Overlay> getOverlays(String str, int i, int i2);

    Map<DocumentKey, Overlay> getOverlays(SortedSet<DocumentKey> sortedSet);

    void removeOverlaysForBatchId(int i);

    void saveOverlays(int i, Map<DocumentKey, Mutation> map);
}
