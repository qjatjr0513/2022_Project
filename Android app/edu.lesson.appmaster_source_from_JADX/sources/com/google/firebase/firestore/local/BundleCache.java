package com.google.firebase.firestore.local;

import com.google.firebase.firestore.bundle.BundleMetadata;
import com.google.firebase.firestore.bundle.NamedQuery;

public interface BundleCache {
    BundleMetadata getBundleMetadata(String str);

    NamedQuery getNamedQuery(String str);

    void saveBundleMetadata(BundleMetadata bundleMetadata);

    void saveNamedQuery(NamedQuery namedQuery);
}
