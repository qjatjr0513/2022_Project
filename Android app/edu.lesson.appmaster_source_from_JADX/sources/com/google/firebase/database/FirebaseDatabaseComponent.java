package com.google.firebase.database;

import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.interop.InternalAppCheckTokenProvider;
import com.google.firebase.auth.internal.InternalAuthProvider;
import com.google.firebase.database.android.AndroidAppCheckTokenProvider;
import com.google.firebase.database.android.AndroidAuthTokenProvider;
import com.google.firebase.database.core.DatabaseConfig;
import com.google.firebase.database.core.RepoInfo;
import com.google.firebase.database.core.TokenProvider;
import com.google.firebase.inject.Deferred;
import java.util.HashMap;
import java.util.Map;

class FirebaseDatabaseComponent {

    /* renamed from: app  reason: collision with root package name */
    private final FirebaseApp f511app;
    private final TokenProvider appCheckProvider;
    private final TokenProvider authProvider;
    private final Map<RepoInfo, FirebaseDatabase> instances = new HashMap();

    FirebaseDatabaseComponent(FirebaseApp app2, Deferred<InternalAuthProvider> authProvider2, Deferred<InternalAppCheckTokenProvider> appCheckProvider2) {
        this.f511app = app2;
        this.authProvider = new AndroidAuthTokenProvider(authProvider2);
        this.appCheckProvider = new AndroidAppCheckTokenProvider(appCheckProvider2);
    }

    /* access modifiers changed from: package-private */
    public synchronized FirebaseDatabase get(RepoInfo repo) {
        FirebaseDatabase database;
        database = this.instances.get(repo);
        if (database == null) {
            DatabaseConfig config = new DatabaseConfig();
            if (!this.f511app.isDefaultApp()) {
                config.setSessionPersistenceKey(this.f511app.getName());
            }
            config.setFirebaseApp(this.f511app);
            config.setAuthTokenProvider(this.authProvider);
            config.setAppCheckTokenProvider(this.appCheckProvider);
            database = new FirebaseDatabase(this.f511app, repo, config);
            this.instances.put(repo, database);
        }
        return database;
    }
}
