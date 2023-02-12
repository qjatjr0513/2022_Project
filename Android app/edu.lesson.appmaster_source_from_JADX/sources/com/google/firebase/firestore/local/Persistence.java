package com.google.firebase.firestore.local;

import com.google.firebase.firestore.auth.User;
import com.google.firebase.firestore.util.Supplier;

public abstract class Persistence {
    public static String DATA_MIGRATION_BUILD_OVERLAYS = "BUILD_OVERLAYS";
    static final String TAG = Persistence.class.getSimpleName();

    /* access modifiers changed from: package-private */
    public abstract BundleCache getBundleCache();

    /* access modifiers changed from: package-private */
    public abstract DocumentOverlayCache getDocumentOverlay(User user);

    /* access modifiers changed from: package-private */
    public abstract IndexManager getIndexManager(User user);

    /* access modifiers changed from: package-private */
    public abstract MutationQueue getMutationQueue(User user, IndexManager indexManager);

    /* access modifiers changed from: package-private */
    public abstract OverlayMigrationManager getOverlayMigrationManager();

    /* access modifiers changed from: package-private */
    public abstract ReferenceDelegate getReferenceDelegate();

    /* access modifiers changed from: package-private */
    public abstract RemoteDocumentCache getRemoteDocumentCache();

    /* access modifiers changed from: package-private */
    public abstract TargetCache getTargetCache();

    public abstract boolean isStarted();

    /* access modifiers changed from: package-private */
    public abstract <T> T runTransaction(String str, Supplier<T> supplier);

    /* access modifiers changed from: package-private */
    public abstract void runTransaction(String str, Runnable runnable);

    public abstract void shutdown();

    public abstract void start();

    Persistence() {
    }
}
