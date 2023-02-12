package com.google.firebase.database.connection;

import com.google.firebase.database.logging.Logger;
import java.util.concurrent.ScheduledExecutorService;

public class ConnectionContext {
    private final ConnectionTokenProvider appCheckTokenProvider;
    private final String applicationId;
    private final ConnectionTokenProvider authTokenProvider;
    private final String clientSdkVersion;
    private final ScheduledExecutorService executorService;
    private final Logger logger;
    private final boolean persistenceEnabled;
    private final String sslCacheDirectory;
    private final String userAgent;

    public ConnectionContext(Logger logger2, ConnectionTokenProvider authTokenProvider2, ConnectionTokenProvider appCheckTokenProvider2, ScheduledExecutorService executorService2, boolean persistenceEnabled2, String clientSdkVersion2, String userAgent2, String applicationId2, String sslCacheDirectory2) {
        this.logger = logger2;
        this.authTokenProvider = authTokenProvider2;
        this.appCheckTokenProvider = appCheckTokenProvider2;
        this.executorService = executorService2;
        this.persistenceEnabled = persistenceEnabled2;
        this.clientSdkVersion = clientSdkVersion2;
        this.userAgent = userAgent2;
        this.applicationId = applicationId2;
        this.sslCacheDirectory = sslCacheDirectory2;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public ConnectionTokenProvider getAuthTokenProvider() {
        return this.authTokenProvider;
    }

    public ConnectionTokenProvider getAppCheckTokenProvider() {
        return this.appCheckTokenProvider;
    }

    public ScheduledExecutorService getExecutorService() {
        return this.executorService;
    }

    public boolean isPersistenceEnabled() {
        return this.persistenceEnabled;
    }

    public String getClientSdkVersion() {
        return this.clientSdkVersion;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public String getSslCacheDirectory() {
        return this.sslCacheDirectory;
    }

    public String getApplicationId() {
        return this.applicationId;
    }
}
