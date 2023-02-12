package com.google.firebase.firestore;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseAppLifecycleListener;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.remote.GrpcMetadataProvider;
import com.google.firebase.firestore.util.Assert;
import com.google.firebase.inject.Deferred;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class FirestoreMultiDbComponent implements FirebaseAppLifecycleListener, FirebaseFirestore.InstanceRegistry {

    /* renamed from: app  reason: collision with root package name */
    private final FirebaseApp f512app;
    private final Deferred<InternalAppCheckTokenProvider> appCheckProvider;
    private final Deferred<InternalAuthProvider> authProvider;
    private final Context context;
    private final Map<String, FirebaseFirestore> instances = new HashMap();
    private final GrpcMetadataProvider metadataProvider;

    FirestoreMultiDbComponent(Context context2, FirebaseApp app2, Deferred<InternalAuthProvider> authProvider2, Deferred<InternalAppCheckTokenProvider> appCheckProvider2, GrpcMetadataProvider metadataProvider2) {
        this.context = context2;
        this.f512app = app2;
        this.authProvider = authProvider2;
        this.appCheckProvider = appCheckProvider2;
        this.metadataProvider = metadataProvider2;
        app2.addLifecycleEventListener(this);
    }

    /* access modifiers changed from: package-private */
    public synchronized FirebaseFirestore get(String databaseId) {
        FirebaseFirestore firestore;
        firestore = this.instances.get(databaseId);
        if (firestore == null) {
            firestore = FirebaseFirestore.newInstance(this.context, this.f512app, this.authProvider, this.appCheckProvider, databaseId, this, this.metadataProvider);
            this.instances.put(databaseId, firestore);
        }
        return firestore;
    }

    public synchronized void remove(String databaseId) {
        this.instances.remove(databaseId);
    }

    public synchronized void onDeleted(String firebaseAppName, FirebaseOptions options) {
        Iterator it = new ArrayList(this.instances.entrySet()).iterator();
        while (it.hasNext()) {
            Map.Entry<String, FirebaseFirestore> entry = (Map.Entry) it.next();
            entry.getValue().terminate();
            Assert.hardAssert(!this.instances.containsKey(entry.getKey()), "terminate() should have removed its entry from `instances` for key: %s", entry.getKey());
        }
    }
}
