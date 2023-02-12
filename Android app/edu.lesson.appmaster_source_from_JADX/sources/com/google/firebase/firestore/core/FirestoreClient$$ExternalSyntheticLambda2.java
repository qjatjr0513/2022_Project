package com.google.firebase.firestore.core;

import com.google.firebase.firestore.LoadBundleTask;
import com.google.firebase.firestore.bundle.BundleReader;

public final /* synthetic */ class FirestoreClient$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ FirestoreClient f$0;
    public final /* synthetic */ BundleReader f$1;
    public final /* synthetic */ LoadBundleTask f$2;

    public /* synthetic */ FirestoreClient$$ExternalSyntheticLambda2(FirestoreClient firestoreClient, BundleReader bundleReader, LoadBundleTask loadBundleTask) {
        this.f$0 = firestoreClient;
        this.f$1 = bundleReader;
        this.f$2 = loadBundleTask;
    }

    public final void run() {
        this.f$0.mo8851x15cd9428(this.f$1, this.f$2);
    }
}
