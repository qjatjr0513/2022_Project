package com.google.firebase.database.connection;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.database.connection.Connection;
import com.google.firebase.database.connection.ConnectionTokenProvider;
import com.google.firebase.database.connection.PersistentConnection;
import com.google.firebase.database.connection.util.RetryHelper;
import com.google.firebase.database.logging.LogWrapper;
import com.google.firebase.database.util.GAuthToken;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class PersistentConnectionImpl implements Connection.Delegate, PersistentConnection {
    private static final long GET_CONNECT_TIMEOUT = 3000;
    private static final String IDLE_INTERRUPT_REASON = "connection_idle";
    private static final long IDLE_TIMEOUT = 60000;
    private static final String INVALID_APP_CHECK_TOKEN = "Invalid appcheck token";
    private static final long INVALID_TOKEN_THRESHOLD = 3;
    private static final String REQUEST_ACTION = "a";
    private static final String REQUEST_ACTION_APPCHECK = "appcheck";
    private static final String REQUEST_ACTION_AUTH = "auth";
    private static final String REQUEST_ACTION_GAUTH = "gauth";
    private static final String REQUEST_ACTION_GET = "g";
    private static final String REQUEST_ACTION_MERGE = "m";
    private static final String REQUEST_ACTION_ONDISCONNECT_CANCEL = "oc";
    private static final String REQUEST_ACTION_ONDISCONNECT_MERGE = "om";
    private static final String REQUEST_ACTION_ONDISCONNECT_PUT = "o";
    private static final String REQUEST_ACTION_PUT = "p";
    private static final String REQUEST_ACTION_QUERY = "q";
    private static final String REQUEST_ACTION_QUERY_UNLISTEN = "n";
    private static final String REQUEST_ACTION_STATS = "s";
    private static final String REQUEST_ACTION_UNAPPCHECK = "unappcheck";
    private static final String REQUEST_ACTION_UNAUTH = "unauth";
    private static final String REQUEST_APPCHECK_TOKEN = "token";
    private static final String REQUEST_AUTHVAR = "authvar";
    private static final String REQUEST_COMPOUND_HASH = "ch";
    private static final String REQUEST_COMPOUND_HASH_HASHES = "hs";
    private static final String REQUEST_COMPOUND_HASH_PATHS = "ps";
    private static final String REQUEST_COUNTERS = "c";
    private static final String REQUEST_CREDENTIAL = "cred";
    private static final String REQUEST_DATA_HASH = "h";
    private static final String REQUEST_DATA_PAYLOAD = "d";
    private static final String REQUEST_ERROR = "error";
    private static final String REQUEST_NUMBER = "r";
    private static final String REQUEST_PATH = "p";
    private static final String REQUEST_PAYLOAD = "b";
    private static final String REQUEST_QUERIES = "q";
    private static final String REQUEST_STATUS = "s";
    private static final String REQUEST_TAG = "t";
    private static final String RESPONSE_FOR_REQUEST = "b";
    private static final String SERVER_ASYNC_ACTION = "a";
    private static final String SERVER_ASYNC_APP_CHECK_REVOKED = "apc";
    private static final String SERVER_ASYNC_AUTH_REVOKED = "ac";
    private static final String SERVER_ASYNC_DATA_MERGE = "m";
    private static final String SERVER_ASYNC_DATA_RANGE_MERGE = "rm";
    private static final String SERVER_ASYNC_DATA_UPDATE = "d";
    private static final String SERVER_ASYNC_LISTEN_CANCELLED = "c";
    private static final String SERVER_ASYNC_PAYLOAD = "b";
    private static final String SERVER_ASYNC_SECURITY_DEBUG = "sd";
    private static final String SERVER_DATA_END_PATH = "e";
    private static final String SERVER_DATA_RANGE_MERGE = "m";
    private static final String SERVER_DATA_START_PATH = "s";
    private static final String SERVER_DATA_TAG = "t";
    private static final String SERVER_DATA_UPDATE_BODY = "d";
    private static final String SERVER_DATA_UPDATE_PATH = "p";
    private static final String SERVER_DATA_WARNINGS = "w";
    private static final String SERVER_KILL_INTERRUPT_REASON = "server_kill";
    private static final String SERVER_RESPONSE_DATA = "d";
    private static final long SUCCESSFUL_CONNECTION_ESTABLISHED_DELAY = 30000;
    private static final String TOKEN_REFRESH_INTERRUPT_REASON = "token_refresh";
    private static long connectionIds = 0;
    private String appCheckToken;
    private final ConnectionTokenProvider appCheckTokenProvider;
    /* access modifiers changed from: private */
    public String authToken;
    private final ConnectionTokenProvider authTokenProvider;
    private String cachedHost;
    /* access modifiers changed from: private */
    public ConnectionState connectionState = ConnectionState.Disconnected;
    private final ConnectionContext context;
    private long currentGetTokenAttempt = 0;
    /* access modifiers changed from: private */
    public final PersistentConnection.Delegate delegate;
    private final ScheduledExecutorService executorService;
    private boolean firstConnection = true;
    private boolean forceAppCheckTokenRefresh;
    /* access modifiers changed from: private */
    public boolean forceAuthTokenRefresh;
    private boolean hasOnDisconnects;
    private final HostInfo hostInfo;
    /* access modifiers changed from: private */
    public ScheduledFuture<?> inactivityTimer = null;
    private HashSet<String> interruptReasons = new HashSet<>();
    private int invalidAppCheckTokenCount = 0;
    /* access modifiers changed from: private */
    public int invalidAuthTokenCount = 0;
    private long lastConnectionEstablishedTime;
    private String lastSessionId;
    private long lastWriteTimestamp;
    /* access modifiers changed from: private */
    public Map<QuerySpec, OutstandingListen> listens;
    /* access modifiers changed from: private */
    public final LogWrapper logger;
    private List<OutstandingDisconnect> onDisconnectRequestQueue;
    /* access modifiers changed from: private */
    public Map<Long, OutstandingGet> outstandingGets;
    /* access modifiers changed from: private */
    public Map<Long, OutstandingPut> outstandingPuts;
    private long readCounter = 0;
    /* access modifiers changed from: private */
    public Connection realtime;
    private Map<Long, ConnectionRequestCallback> requestCBHash;
    private long requestCounter = 0;
    /* access modifiers changed from: private */
    public final RetryHelper retryHelper;
    private long writeCounter = 0;

    private interface ConnectionRequestCallback {
        void onResponse(Map<String, Object> map);
    }

    private enum ConnectionState {
        Disconnected,
        GettingToken,
        Connecting,
        Authenticating,
        Connected
    }

    static /* synthetic */ int access$1008(PersistentConnectionImpl x0) {
        int i = x0.invalidAuthTokenCount;
        x0.invalidAuthTokenCount = i + 1;
        return i;
    }

    private static class QuerySpec {
        /* access modifiers changed from: private */
        public final List<String> path;
        /* access modifiers changed from: private */
        public final Map<String, Object> queryParams;

        public QuerySpec(List<String> path2, Map<String, Object> queryParams2) {
            this.path = path2;
            this.queryParams = queryParams2;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof QuerySpec)) {
                return false;
            }
            QuerySpec that = (QuerySpec) o;
            if (!this.path.equals(that.path)) {
                return false;
            }
            return this.queryParams.equals(that.queryParams);
        }

        public int hashCode() {
            return (this.path.hashCode() * 31) + this.queryParams.hashCode();
        }

        public String toString() {
            return ConnectionUtils.pathToString(this.path) + " (params: " + this.queryParams + ")";
        }
    }

    private static class OutstandingListen {
        private final ListenHashProvider hashFunction;
        /* access modifiers changed from: private */
        public final QuerySpec query;
        /* access modifiers changed from: private */
        public final RequestResultCallback resultCallback;
        private final Long tag;

        private OutstandingListen(RequestResultCallback callback, QuerySpec query2, Long tag2, ListenHashProvider hashFunction2) {
            this.resultCallback = callback;
            this.query = query2;
            this.hashFunction = hashFunction2;
            this.tag = tag2;
        }

        public QuerySpec getQuery() {
            return this.query;
        }

        public Long getTag() {
            return this.tag;
        }

        public ListenHashProvider getHashFunction() {
            return this.hashFunction;
        }

        public String toString() {
            return this.query.toString() + " (Tag: " + this.tag + ")";
        }
    }

    private static class OutstandingGet {
        private final ConnectionRequestCallback onComplete;
        private final Map<String, Object> request;
        private boolean sent;

        private OutstandingGet(String action, Map<String, Object> request2, ConnectionRequestCallback onComplete2) {
            this.request = request2;
            this.onComplete = onComplete2;
            this.sent = false;
        }

        /* access modifiers changed from: private */
        public ConnectionRequestCallback getOnComplete() {
            return this.onComplete;
        }

        /* access modifiers changed from: private */
        public Map<String, Object> getRequest() {
            return this.request;
        }

        /* access modifiers changed from: private */
        public boolean markSent() {
            if (this.sent) {
                return false;
            }
            this.sent = true;
            return true;
        }
    }

    private static class OutstandingPut {
        private String action;
        /* access modifiers changed from: private */
        public RequestResultCallback onComplete;
        private Map<String, Object> request;
        private boolean sent;

        private OutstandingPut(String action2, Map<String, Object> request2, RequestResultCallback onComplete2) {
            this.action = action2;
            this.request = request2;
            this.onComplete = onComplete2;
        }

        public String getAction() {
            return this.action;
        }

        public Map<String, Object> getRequest() {
            return this.request;
        }

        public RequestResultCallback getOnComplete() {
            return this.onComplete;
        }

        public void markSent() {
            this.sent = true;
        }

        public boolean wasSent() {
            return this.sent;
        }
    }

    private static class OutstandingDisconnect {
        private final String action;
        private final Object data;
        /* access modifiers changed from: private */
        public final RequestResultCallback onComplete;
        private final List<String> path;

        private OutstandingDisconnect(String action2, List<String> path2, Object data2, RequestResultCallback onComplete2) {
            this.action = action2;
            this.path = path2;
            this.data = data2;
            this.onComplete = onComplete2;
        }

        public String getAction() {
            return this.action;
        }

        public List<String> getPath() {
            return this.path;
        }

        public Object getData() {
            return this.data;
        }

        public RequestResultCallback getOnComplete() {
            return this.onComplete;
        }
    }

    public PersistentConnectionImpl(ConnectionContext context2, HostInfo info, PersistentConnection.Delegate delegate2) {
        this.delegate = delegate2;
        this.context = context2;
        ScheduledExecutorService executorService2 = context2.getExecutorService();
        this.executorService = executorService2;
        this.authTokenProvider = context2.getAuthTokenProvider();
        this.appCheckTokenProvider = context2.getAppCheckTokenProvider();
        this.hostInfo = info;
        this.listens = new HashMap();
        this.requestCBHash = new HashMap();
        this.outstandingPuts = new HashMap();
        this.outstandingGets = new ConcurrentHashMap();
        this.onDisconnectRequestQueue = new ArrayList();
        this.retryHelper = new RetryHelper.Builder(executorService2, context2.getLogger(), "ConnectionRetryHelper").withMinDelayAfterFailure(1000).withRetryExponent(1.3d).withMaxDelay(SUCCESSFUL_CONNECTION_ESTABLISHED_DELAY).withJitterFactor(0.7d).build();
        long connId = connectionIds;
        connectionIds = 1 + connId;
        this.logger = new LogWrapper(context2.getLogger(), "PersistentConnection", "pc_" + connId);
        this.lastSessionId = null;
        doIdleCheck();
    }

    public void onReady(long timestamp, String sessionId) {
        if (this.logger.logsDebug()) {
            this.logger.debug("onReady", new Object[0]);
        }
        this.lastConnectionEstablishedTime = System.currentTimeMillis();
        handleTimestamp(timestamp);
        if (this.firstConnection) {
            sendConnectStats();
        }
        restoreTokens();
        this.firstConnection = false;
        this.lastSessionId = sessionId;
        this.delegate.onConnect();
    }

    public void onCacheHost(String host) {
        this.cachedHost = host;
    }

    public void listen(List<String> path, Map<String, Object> queryParams, ListenHashProvider currentHashFn, Long tag, RequestResultCallback listener) {
        QuerySpec query = new QuerySpec(path, queryParams);
        if (this.logger.logsDebug()) {
            this.logger.debug("Listening on " + query, new Object[0]);
        }
        ConnectionUtils.hardAssert(!this.listens.containsKey(query), "listen() called twice for same QuerySpec.", new Object[0]);
        if (this.logger.logsDebug()) {
            this.logger.debug("Adding listen query: " + query, new Object[0]);
        }
        OutstandingListen outstandingListen = new OutstandingListen(listener, query, tag, currentHashFn);
        this.listens.put(query, outstandingListen);
        if (connected()) {
            sendListen(outstandingListen);
        }
        doIdleCheck();
    }

    public Task<Object> get(List<String> path, Map<String, Object> queryParams) {
        QuerySpec query = new QuerySpec(path, queryParams);
        TaskCompletionSource<Object> source = new TaskCompletionSource<>();
        long readId = this.readCounter;
        this.readCounter = 1 + readId;
        Map<String, Object> request = new HashMap<>();
        request.put("p", ConnectionUtils.pathToString(query.path));
        request.put("q", query.queryParams);
        OutstandingGet outstandingGet = new OutstandingGet(REQUEST_ACTION_GET, request, new PersistentConnectionImpl$$ExternalSyntheticLambda2(this, query, source));
        this.outstandingGets.put(Long.valueOf(readId), outstandingGet);
        if (!connected()) {
            this.executorService.schedule(new PersistentConnectionImpl$$ExternalSyntheticLambda4(this, outstandingGet, readId, source), GET_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        }
        if (canSendReads()) {
            sendGet(Long.valueOf(readId));
        }
        doIdleCheck();
        return source.getTask();
    }

    /* renamed from: lambda$get$0$com-google-firebase-database-connection-PersistentConnectionImpl */
    public /* synthetic */ void mo7600xb452292f(QuerySpec query, TaskCompletionSource source, Map response) {
        if (((String) response.get("s")).equals("ok")) {
            Object body = response.get("d");
            this.delegate.onDataUpdate(query.path, body, false, (Long) null);
            source.setResult(body);
            return;
        }
        source.setException(new Exception((String) response.get("d")));
    }

    /* renamed from: lambda$get$1$com-google-firebase-database-connection-PersistentConnectionImpl */
    public /* synthetic */ void mo7601x1e81b14e(OutstandingGet outstandingGet, long readId, TaskCompletionSource source) {
        if (outstandingGet.markSent()) {
            if (this.logger.logsDebug()) {
                this.logger.debug("get " + readId + " timed out waiting for connection", new Object[0]);
            }
            this.outstandingGets.remove(Long.valueOf(readId));
            source.setException(new Exception("Client is offline"));
        }
    }

    public void initialize() {
        tryScheduleReconnect();
    }

    public void shutdown() {
        interrupt("shutdown");
    }

    public void put(List<String> path, Object data, RequestResultCallback onComplete) {
        putInternal("p", path, data, (String) null, onComplete);
    }

    public void compareAndPut(List<String> path, Object data, String hash, RequestResultCallback onComplete) {
        putInternal("p", path, data, hash, onComplete);
    }

    public void merge(List<String> path, Map<String, Object> data, RequestResultCallback onComplete) {
        putInternal("m", path, data, (String) null, onComplete);
    }

    public void purgeOutstandingWrites() {
        for (OutstandingPut put : this.outstandingPuts.values()) {
            if (put.onComplete != null) {
                put.onComplete.onRequestResult("write_canceled", (String) null);
            }
        }
        for (OutstandingDisconnect onDisconnect : this.onDisconnectRequestQueue) {
            if (onDisconnect.onComplete != null) {
                onDisconnect.onComplete.onRequestResult("write_canceled", (String) null);
            }
        }
        this.outstandingPuts.clear();
        this.onDisconnectRequestQueue.clear();
        if (!connected()) {
            this.hasOnDisconnects = false;
        }
        doIdleCheck();
    }

    public void onDataMessage(Map<String, Object> message) {
        if (message.containsKey(REQUEST_NUMBER)) {
            ConnectionRequestCallback responseListener = this.requestCBHash.remove(Long.valueOf((long) ((Integer) message.get(REQUEST_NUMBER)).intValue()));
            if (responseListener != null) {
                responseListener.onResponse((Map) message.get("b"));
            }
        } else if (!message.containsKey("error")) {
            if (message.containsKey("a")) {
                onDataPush((String) message.get("a"), (Map) message.get("b"));
            } else if (this.logger.logsDebug()) {
                this.logger.debug("Ignoring unknown message: " + message, new Object[0]);
            }
        }
    }

    public void onDisconnect(Connection.DisconnectReason reason) {
        boolean lastConnectionWasSuccessful;
        boolean z = false;
        if (this.logger.logsDebug()) {
            this.logger.debug("Got on disconnect due to " + reason.name(), new Object[0]);
        }
        this.connectionState = ConnectionState.Disconnected;
        this.realtime = null;
        this.hasOnDisconnects = false;
        this.requestCBHash.clear();
        cancelSentTransactions();
        if (shouldReconnect()) {
            long currentTimeMillis = System.currentTimeMillis();
            long j = this.lastConnectionEstablishedTime;
            long timeSinceLastConnectSucceeded = currentTimeMillis - j;
            if (j > 0) {
                if (timeSinceLastConnectSucceeded > SUCCESSFUL_CONNECTION_ESTABLISHED_DELAY) {
                    z = true;
                }
                lastConnectionWasSuccessful = z;
            } else {
                lastConnectionWasSuccessful = false;
            }
            if (reason == Connection.DisconnectReason.SERVER_RESET || lastConnectionWasSuccessful) {
                this.retryHelper.signalSuccess();
            }
            tryScheduleReconnect();
        }
        this.lastConnectionEstablishedTime = 0;
        this.delegate.onDisconnect();
    }

    public void onKill(String reason) {
        if (reason.equals(INVALID_APP_CHECK_TOKEN)) {
            int i = this.invalidAppCheckTokenCount;
            if (((long) i) < 3) {
                this.invalidAppCheckTokenCount = i + 1;
                this.logger.warn("Detected invalid AppCheck token. Reconnecting (" + (3 - ((long) this.invalidAppCheckTokenCount)) + " attempts remaining)");
                return;
            }
        }
        this.logger.warn("Firebase Database connection was forcefully killed by the server. Will not attempt reconnect. Reason: " + reason);
        interrupt(SERVER_KILL_INTERRUPT_REASON);
    }

    public void unlisten(List<String> path, Map<String, Object> queryParams) {
        QuerySpec query = new QuerySpec(path, queryParams);
        if (this.logger.logsDebug()) {
            this.logger.debug("unlistening on " + query, new Object[0]);
        }
        OutstandingListen listen = removeListen(query);
        if (listen != null && connected()) {
            sendUnlisten(listen);
        }
        doIdleCheck();
    }

    private boolean connected() {
        return this.connectionState == ConnectionState.Authenticating || this.connectionState == ConnectionState.Connected;
    }

    public void onDisconnectPut(List<String> path, Object data, RequestResultCallback onComplete) {
        this.hasOnDisconnects = true;
        if (canSendWrites()) {
            sendOnDisconnect(REQUEST_ACTION_ONDISCONNECT_PUT, path, data, onComplete);
        } else {
            this.onDisconnectRequestQueue.add(new OutstandingDisconnect(REQUEST_ACTION_ONDISCONNECT_PUT, path, data, onComplete));
        }
        doIdleCheck();
    }

    private boolean canSendWrites() {
        return this.connectionState == ConnectionState.Connected;
    }

    private boolean canSendReads() {
        return this.connectionState == ConnectionState.Connected;
    }

    public void onDisconnectMerge(List<String> path, Map<String, Object> updates, RequestResultCallback onComplete) {
        this.hasOnDisconnects = true;
        if (canSendWrites()) {
            sendOnDisconnect(REQUEST_ACTION_ONDISCONNECT_MERGE, path, updates, onComplete);
        } else {
            this.onDisconnectRequestQueue.add(new OutstandingDisconnect(REQUEST_ACTION_ONDISCONNECT_MERGE, path, updates, onComplete));
        }
        doIdleCheck();
    }

    public void onDisconnectCancel(List<String> path, RequestResultCallback onComplete) {
        if (canSendWrites()) {
            sendOnDisconnect(REQUEST_ACTION_ONDISCONNECT_CANCEL, path, (Object) null, onComplete);
        } else {
            this.onDisconnectRequestQueue.add(new OutstandingDisconnect(REQUEST_ACTION_ONDISCONNECT_CANCEL, path, (Object) null, onComplete));
        }
        doIdleCheck();
    }

    public void interrupt(String reason) {
        if (this.logger.logsDebug()) {
            this.logger.debug("Connection interrupted for: " + reason, new Object[0]);
        }
        this.interruptReasons.add(reason);
        Connection connection = this.realtime;
        if (connection != null) {
            connection.close();
            this.realtime = null;
        } else {
            this.retryHelper.cancel();
            this.connectionState = ConnectionState.Disconnected;
        }
        this.retryHelper.signalSuccess();
    }

    public void resume(String reason) {
        if (this.logger.logsDebug()) {
            this.logger.debug("Connection no longer interrupted for: " + reason, new Object[0]);
        }
        this.interruptReasons.remove(reason);
        if (shouldReconnect() && this.connectionState == ConnectionState.Disconnected) {
            tryScheduleReconnect();
        }
    }

    public boolean isInterrupted(String reason) {
        return this.interruptReasons.contains(reason);
    }

    /* access modifiers changed from: package-private */
    public boolean shouldReconnect() {
        return this.interruptReasons.size() == 0;
    }

    public void refreshAuthToken() {
        this.logger.debug("Auth token refresh requested", new Object[0]);
        interrupt(TOKEN_REFRESH_INTERRUPT_REASON);
        resume(TOKEN_REFRESH_INTERRUPT_REASON);
    }

    public void refreshAuthToken(String token) {
        this.logger.debug("Auth token refreshed.", new Object[0]);
        this.authToken = token;
        if (!connected()) {
            return;
        }
        if (token != null) {
            upgradeAuth();
        } else {
            sendUnauth();
        }
    }

    public void refreshAppCheckToken() {
        this.logger.debug("App check token refresh requested", new Object[0]);
        interrupt(TOKEN_REFRESH_INTERRUPT_REASON);
        resume(TOKEN_REFRESH_INTERRUPT_REASON);
    }

    public void refreshAppCheckToken(String token) {
        this.logger.debug("App check token refreshed.", new Object[0]);
        this.appCheckToken = token;
        if (!connected()) {
            return;
        }
        if (token != null) {
            upgradeAppCheck();
        } else {
            sendUnAppCheck();
        }
    }

    private void tryScheduleReconnect() {
        if (shouldReconnect()) {
            ConnectionUtils.hardAssert(this.connectionState == ConnectionState.Disconnected, "Not in disconnected state: %s", this.connectionState);
            boolean forceAuthTokenRefresh2 = this.forceAuthTokenRefresh;
            boolean forceAppCheckTokenRefresh2 = this.forceAppCheckTokenRefresh;
            this.logger.debug("Scheduling connection attempt", new Object[0]);
            this.forceAuthTokenRefresh = false;
            this.forceAppCheckTokenRefresh = false;
            this.retryHelper.retry(new PersistentConnectionImpl$$ExternalSyntheticLambda5(this, forceAuthTokenRefresh2, forceAppCheckTokenRefresh2));
        }
    }

    /* renamed from: lambda$tryScheduleReconnect$4$com-google-firebase-database-connection-PersistentConnectionImpl */
    public /* synthetic */ void mo7605xcbe04342(boolean forceAuthTokenRefresh2, boolean forceAppCheckTokenRefresh2) {
        ConnectionUtils.hardAssert(this.connectionState == ConnectionState.Disconnected, "Not in disconnected state: %s", this.connectionState);
        this.connectionState = ConnectionState.GettingToken;
        this.currentGetTokenAttempt++;
        long thisGetTokenAttempt = this.currentGetTokenAttempt;
        Task<String> authToken2 = fetchAuthToken(forceAuthTokenRefresh2);
        Task<String> appCheckToken2 = fetchAppCheckToken(forceAppCheckTokenRefresh2);
        Tasks.whenAll((Task<?>[]) new Task[]{authToken2, appCheckToken2}).addOnSuccessListener((Executor) this.executorService, new PersistentConnectionImpl$$ExternalSyntheticLambda1(this, thisGetTokenAttempt, authToken2, appCheckToken2)).addOnFailureListener((Executor) this.executorService, (OnFailureListener) new PersistentConnectionImpl$$ExternalSyntheticLambda0(this, thisGetTokenAttempt));
    }

    /* renamed from: lambda$tryScheduleReconnect$2$com-google-firebase-database-connection-PersistentConnectionImpl */
    public /* synthetic */ void mo7603xf7813304(long thisGetTokenAttempt, Task authToken2, Task appCheckToken2, Void aVoid) {
        if (thisGetTokenAttempt != this.currentGetTokenAttempt) {
            this.logger.debug("Ignoring getToken result, because this was not the latest attempt.", new Object[0]);
        } else if (this.connectionState == ConnectionState.GettingToken) {
            this.logger.debug("Successfully fetched token, opening connection", new Object[0]);
            openNetworkConnection((String) authToken2.getResult(), (String) appCheckToken2.getResult());
        } else if (this.connectionState == ConnectionState.Disconnected) {
            this.logger.debug("Not opening connection after token refresh, because connection was set to disconnected", new Object[0]);
        }
    }

    /* renamed from: lambda$tryScheduleReconnect$3$com-google-firebase-database-connection-PersistentConnectionImpl */
    public /* synthetic */ void mo7604x61b0bb23(long thisGetTokenAttempt, Exception error) {
        if (thisGetTokenAttempt == this.currentGetTokenAttempt) {
            this.connectionState = ConnectionState.Disconnected;
            this.logger.debug("Error fetching token: " + error, new Object[0]);
            tryScheduleReconnect();
            return;
        }
        this.logger.debug("Ignoring getToken error, because this was not the latest attempt.", new Object[0]);
    }

    private Task<String> fetchAuthToken(boolean forceAuthTokenRefresh2) {
        final TaskCompletionSource<String> taskCompletionSource = new TaskCompletionSource<>();
        this.logger.debug("Trying to fetch auth token", new Object[0]);
        this.authTokenProvider.getToken(forceAuthTokenRefresh2, new ConnectionTokenProvider.GetTokenCallback() {
            public void onSuccess(String token) {
                taskCompletionSource.setResult(token);
            }

            public void onError(String error) {
                taskCompletionSource.setException(new Exception(error));
            }
        });
        return taskCompletionSource.getTask();
    }

    private Task<String> fetchAppCheckToken(boolean forceAppCheckTokenRefresh2) {
        final TaskCompletionSource<String> taskCompletionSource = new TaskCompletionSource<>();
        this.logger.debug("Trying to fetch app check token", new Object[0]);
        this.appCheckTokenProvider.getToken(forceAppCheckTokenRefresh2, new ConnectionTokenProvider.GetTokenCallback() {
            public void onSuccess(String token) {
                taskCompletionSource.setResult(token);
            }

            public void onError(String error) {
                taskCompletionSource.setException(new Exception(error));
            }
        });
        return taskCompletionSource.getTask();
    }

    public void openNetworkConnection(String authToken2, String appCheckToken2) {
        ConnectionUtils.hardAssert(this.connectionState == ConnectionState.GettingToken, "Trying to open network connection while in the wrong state: %s", this.connectionState);
        if (authToken2 == null) {
            this.delegate.onConnectionStatus(false);
        }
        this.authToken = authToken2;
        this.appCheckToken = appCheckToken2;
        this.connectionState = ConnectionState.Connecting;
        Connection connection = new Connection(this.context, this.hostInfo, this.cachedHost, this, this.lastSessionId, appCheckToken2);
        this.realtime = connection;
        connection.open();
    }

    private void sendOnDisconnect(String action, List<String> path, Object data, final RequestResultCallback onComplete) {
        Map<String, Object> request = new HashMap<>();
        request.put("p", ConnectionUtils.pathToString(path));
        request.put("d", data);
        sendAction(action, request, new ConnectionRequestCallback() {
            public void onResponse(Map<String, Object> response) {
                String status = (String) response.get("s");
                String errorMessage = null;
                String errorCode = null;
                if (!status.equals("ok")) {
                    errorCode = status;
                    errorMessage = (String) response.get("d");
                }
                RequestResultCallback requestResultCallback = onComplete;
                if (requestResultCallback != null) {
                    requestResultCallback.onRequestResult(errorCode, errorMessage);
                }
            }
        });
    }

    private void cancelSentTransactions() {
        List<OutstandingPut> cancelledTransactionWrites = new ArrayList<>();
        Iterator<Map.Entry<Long, OutstandingPut>> iter = this.outstandingPuts.entrySet().iterator();
        while (iter.hasNext()) {
            OutstandingPut put = iter.next().getValue();
            if (put.getRequest().containsKey(REQUEST_DATA_HASH) && put.wasSent()) {
                cancelledTransactionWrites.add(put);
                iter.remove();
            }
        }
        for (OutstandingPut put2 : cancelledTransactionWrites) {
            put2.getOnComplete().onRequestResult("disconnected", (String) null);
        }
    }

    private void sendUnlisten(OutstandingListen listen) {
        Map<String, Object> request = new HashMap<>();
        request.put("p", ConnectionUtils.pathToString(listen.query.path));
        Long tag = listen.getTag();
        if (tag != null) {
            request.put("q", listen.getQuery().queryParams);
            request.put("t", tag);
        }
        sendAction(REQUEST_ACTION_QUERY_UNLISTEN, request, (ConnectionRequestCallback) null);
    }

    /* access modifiers changed from: private */
    public OutstandingListen removeListen(QuerySpec query) {
        if (this.logger.logsDebug()) {
            this.logger.debug("removing query " + query, new Object[0]);
        }
        if (this.listens.containsKey(query)) {
            OutstandingListen oldListen = this.listens.get(query);
            this.listens.remove(query);
            doIdleCheck();
            return oldListen;
        } else if (!this.logger.logsDebug()) {
            return null;
        } else {
            this.logger.debug("Trying to remove listener for QuerySpec " + query + " but no listener exists.", new Object[0]);
            return null;
        }
    }

    private Collection<OutstandingListen> removeListens(List<String> path) {
        if (this.logger.logsDebug()) {
            this.logger.debug("removing all listens at path " + path, new Object[0]);
        }
        List<OutstandingListen> removedListens = new ArrayList<>();
        for (Map.Entry<QuerySpec, OutstandingListen> entry : this.listens.entrySet()) {
            OutstandingListen listen = entry.getValue();
            if (entry.getKey().path.equals(path)) {
                removedListens.add(listen);
            }
        }
        for (OutstandingListen toRemove : removedListens) {
            this.listens.remove(toRemove.getQuery());
        }
        doIdleCheck();
        return removedListens;
    }

    private void onDataPush(String action, Map<String, Object> body) {
        String str = action;
        Map<String, Object> map = body;
        if (this.logger.logsDebug()) {
            this.logger.debug("handleServerMessage: " + str + " " + map, new Object[0]);
        }
        if (str.equals("d") || str.equals("m")) {
            boolean isMerge = str.equals("m");
            String pathString = (String) map.get("p");
            Object payloadData = map.get("d");
            Long tagNumber = ConnectionUtils.longFromObject(map.get("t"));
            if (!isMerge || !(payloadData instanceof Map) || ((Map) payloadData).size() != 0) {
                this.delegate.onDataUpdate(ConnectionUtils.stringToPath(pathString), payloadData, isMerge, tagNumber);
            } else if (this.logger.logsDebug()) {
                this.logger.debug("ignoring empty merge for path " + pathString, new Object[0]);
            }
        } else if (str.equals(SERVER_ASYNC_DATA_RANGE_MERGE)) {
            String pathString2 = (String) map.get("p");
            List<String> path = ConnectionUtils.stringToPath(pathString2);
            Object payloadData2 = map.get("d");
            Long tag = ConnectionUtils.longFromObject(map.get("t"));
            List<Map<String, Object>> ranges = (List) payloadData2;
            List<RangeMerge> rangeMerges = new ArrayList<>();
            Iterator<Map<String, Object>> it = ranges.iterator();
            while (it.hasNext()) {
                Map<String, Object> range = it.next();
                String startString = (String) range.get("s");
                String endString = (String) range.get(SERVER_DATA_END_PATH);
                List<String> end = null;
                List<String> start = startString != null ? ConnectionUtils.stringToPath(startString) : null;
                if (endString != null) {
                    end = ConnectionUtils.stringToPath(endString);
                }
                rangeMerges.add(new RangeMerge(start, end, range.get("m")));
                it = it;
                payloadData2 = payloadData2;
                ranges = ranges;
            }
            List<Map<String, Object>> list = ranges;
            if (!rangeMerges.isEmpty()) {
                this.delegate.onRangeMergeUpdate(path, rangeMerges, tag);
            } else if (this.logger.logsDebug()) {
                this.logger.debug("Ignoring empty range merge for path " + pathString2, new Object[0]);
            }
        } else if (str.equals("c")) {
            onListenRevoked(ConnectionUtils.stringToPath((String) map.get("p")));
        } else if (str.equals(SERVER_ASYNC_AUTH_REVOKED)) {
            onAuthRevoked((String) map.get("s"), (String) map.get("d"));
        } else if (str.equals(SERVER_ASYNC_APP_CHECK_REVOKED)) {
            onAppCheckRevoked((String) map.get("s"), (String) map.get("d"));
        } else if (str.equals(SERVER_ASYNC_SECURITY_DEBUG)) {
            onSecurityDebugPacket(map);
        } else if (this.logger.logsDebug()) {
            this.logger.debug("Unrecognized action from server: " + str, new Object[0]);
        }
    }

    private void onListenRevoked(List<String> path) {
        Collection<OutstandingListen> listens2 = removeListens(path);
        if (listens2 != null) {
            for (OutstandingListen listen : listens2) {
                listen.resultCallback.onRequestResult("permission_denied", (String) null);
            }
        }
    }

    private void onAuthRevoked(String errorCode, String errorMessage) {
        this.logger.debug("Auth token revoked: " + errorCode + " (" + errorMessage + ")", new Object[0]);
        this.authToken = null;
        this.forceAuthTokenRefresh = true;
        this.delegate.onConnectionStatus(false);
        this.realtime.close();
    }

    private void onAppCheckRevoked(String errorCode, String errorMessage) {
        this.logger.debug("App check token revoked: " + errorCode + " (" + errorMessage + ")", new Object[0]);
        this.appCheckToken = null;
        this.forceAppCheckTokenRefresh = true;
    }

    private void onSecurityDebugPacket(Map<String, Object> message) {
        this.logger.info((String) message.get(NotificationCompat.CATEGORY_MESSAGE));
    }

    private void upgradeAuth() {
        sendAuthHelper(false);
    }

    private void upgradeAppCheck() {
        sendAppCheckTokenHelper(false);
    }

    private void sendAuthAndRestoreState() {
        sendAuthHelper(true);
    }

    private void sendAuthHelper(final boolean restoreStateAfterComplete) {
        ConnectionUtils.hardAssert(connected(), "Must be connected to send auth, but was: %s", this.connectionState);
        if (this.logger.logsDebug()) {
            this.logger.debug("Sending auth.", new Object[0]);
        }
        ConnectionRequestCallback onComplete = new ConnectionRequestCallback() {
            public void onResponse(Map<String, Object> response) {
                String status = (String) response.get("s");
                if (status.equals("ok")) {
                    ConnectionState unused = PersistentConnectionImpl.this.connectionState = ConnectionState.Connected;
                    int unused2 = PersistentConnectionImpl.this.invalidAuthTokenCount = 0;
                    PersistentConnectionImpl.this.sendAppCheckTokenHelper(restoreStateAfterComplete);
                    return;
                }
                String unused3 = PersistentConnectionImpl.this.authToken = null;
                boolean unused4 = PersistentConnectionImpl.this.forceAuthTokenRefresh = true;
                PersistentConnectionImpl.this.delegate.onConnectionStatus(false);
                PersistentConnectionImpl.this.logger.debug("Authentication failed: " + status + " (" + ((String) response.get("d")) + ")", new Object[0]);
                PersistentConnectionImpl.this.realtime.close();
                if (status.equals("invalid_token")) {
                    PersistentConnectionImpl.access$1008(PersistentConnectionImpl.this);
                    if (((long) PersistentConnectionImpl.this.invalidAuthTokenCount) >= 3) {
                        PersistentConnectionImpl.this.retryHelper.setMaxDelay();
                        PersistentConnectionImpl.this.logger.warn("Provided authentication credentials are invalid. This usually indicates your FirebaseApp instance was not initialized correctly. Make sure your google-services.json file has the correct firebase_url and api_key. You can re-download google-services.json from https://console.firebase.google.com/.");
                    }
                }
            }
        };
        Map<String, Object> request = new HashMap<>();
        GAuthToken gAuthToken = GAuthToken.tryParseFromString(this.authToken);
        if (gAuthToken != null) {
            request.put(REQUEST_CREDENTIAL, gAuthToken.getToken());
            if (gAuthToken.getAuth() != null) {
                request.put(REQUEST_AUTHVAR, gAuthToken.getAuth());
            }
            sendSensitive(REQUEST_ACTION_GAUTH, true, request, onComplete);
            return;
        }
        request.put(REQUEST_CREDENTIAL, this.authToken);
        sendSensitive(REQUEST_ACTION_AUTH, true, request, onComplete);
    }

    /* access modifiers changed from: private */
    public void sendAppCheckTokenHelper(boolean restoreStateAfterComplete) {
        if (this.appCheckToken == null) {
            restoreState();
            return;
        }
        ConnectionUtils.hardAssert(connected(), "Must be connected to send auth, but was: %s", this.connectionState);
        if (this.logger.logsDebug()) {
            this.logger.debug("Sending app check.", new Object[0]);
        }
        ConnectionRequestCallback onComplete = new PersistentConnectionImpl$$ExternalSyntheticLambda3(this, restoreStateAfterComplete);
        Map<String, Object> request = new HashMap<>();
        ConnectionUtils.hardAssert(this.appCheckToken != null, "App check token must be set!", new Object[0]);
        request.put(REQUEST_APPCHECK_TOKEN, this.appCheckToken);
        sendSensitive(REQUEST_ACTION_APPCHECK, true, request, onComplete);
    }

    /* renamed from: lambda$sendAppCheckTokenHelper$5$com-google-firebase-database-connection-PersistentConnectionImpl */
    public /* synthetic */ void mo7602x9b5ee8e8(boolean restoreStateAfterComplete, Map response) {
        String status = (String) response.get("s");
        if (status.equals("ok")) {
            this.invalidAppCheckTokenCount = 0;
        } else {
            this.appCheckToken = null;
            this.forceAppCheckTokenRefresh = true;
            this.logger.debug("App check failed: " + status + " (" + ((String) response.get("d")) + ")", new Object[0]);
        }
        if (restoreStateAfterComplete) {
            restoreState();
        }
    }

    private void sendUnauth() {
        ConnectionUtils.hardAssert(connected(), "Must be connected to send unauth.", new Object[0]);
        ConnectionUtils.hardAssert(this.authToken == null, "Auth token must not be set.", new Object[0]);
        sendAction(REQUEST_ACTION_UNAUTH, Collections.emptyMap(), (ConnectionRequestCallback) null);
    }

    private void sendUnAppCheck() {
        ConnectionUtils.hardAssert(connected(), "Must be connected to send unauth.", new Object[0]);
        ConnectionUtils.hardAssert(this.appCheckToken == null, "App check token must not be set.", new Object[0]);
        sendAction(REQUEST_ACTION_UNAPPCHECK, Collections.emptyMap(), (ConnectionRequestCallback) null);
    }

    private void restoreTokens() {
        if (this.logger.logsDebug()) {
            this.logger.debug("calling restore tokens", new Object[0]);
        }
        ConnectionUtils.hardAssert(this.connectionState == ConnectionState.Connecting, "Wanted to restore tokens, but was in wrong state: %s", this.connectionState);
        if (this.authToken != null) {
            if (this.logger.logsDebug()) {
                this.logger.debug("Restoring auth.", new Object[0]);
            }
            this.connectionState = ConnectionState.Authenticating;
            sendAuthAndRestoreState();
            return;
        }
        if (this.logger.logsDebug()) {
            this.logger.debug("Not restoring auth because auth token is null.", new Object[0]);
        }
        this.connectionState = ConnectionState.Connected;
        sendAppCheckTokenHelper(true);
    }

    private void restoreState() {
        ConnectionUtils.hardAssert(this.connectionState == ConnectionState.Connected, "Should be connected if we're restoring state, but we are: %s", this.connectionState);
        if (this.logger.logsDebug()) {
            this.logger.debug("Restoring outstanding listens", new Object[0]);
        }
        for (OutstandingListen listen : this.listens.values()) {
            if (this.logger.logsDebug()) {
                this.logger.debug("Restoring listen " + listen.getQuery(), new Object[0]);
            }
            sendListen(listen);
        }
        if (this.logger.logsDebug()) {
            this.logger.debug("Restoring writes.", new Object[0]);
        }
        ArrayList<Long> outstanding = new ArrayList<>(this.outstandingPuts.keySet());
        Collections.sort(outstanding);
        Iterator<Long> it = outstanding.iterator();
        while (it.hasNext()) {
            sendPut(it.next().longValue());
        }
        for (OutstandingDisconnect disconnect : this.onDisconnectRequestQueue) {
            sendOnDisconnect(disconnect.getAction(), disconnect.getPath(), disconnect.getData(), disconnect.getOnComplete());
        }
        this.onDisconnectRequestQueue.clear();
        if (this.logger.logsDebug()) {
            this.logger.debug("Restoring reads.", new Object[0]);
        }
        ArrayList<Long> outstandingGetKeys = new ArrayList<>(this.outstandingGets.keySet());
        Collections.sort(outstandingGetKeys);
        Iterator<Long> it2 = outstandingGetKeys.iterator();
        while (it2.hasNext()) {
            sendGet(it2.next());
        }
    }

    private void handleTimestamp(long timestamp) {
        if (this.logger.logsDebug()) {
            this.logger.debug("handling timestamp", new Object[0]);
        }
        Map<String, Object> updates = new HashMap<>();
        updates.put(Constants.DOT_INFO_SERVERTIME_OFFSET, Long.valueOf(timestamp - System.currentTimeMillis()));
        this.delegate.onServerInfoUpdate(updates);
    }

    private Map<String, Object> getPutObject(List<String> path, Object data, String hash) {
        Map<String, Object> request = new HashMap<>();
        request.put("p", ConnectionUtils.pathToString(path));
        request.put("d", data);
        if (hash != null) {
            request.put(REQUEST_DATA_HASH, hash);
        }
        return request;
    }

    private void putInternal(String action, List<String> path, Object data, String hash, RequestResultCallback onComplete) {
        Map<String, Object> request = getPutObject(path, data, hash);
        long writeId = this.writeCounter;
        this.writeCounter = 1 + writeId;
        this.outstandingPuts.put(Long.valueOf(writeId), new OutstandingPut(action, request, onComplete));
        if (canSendWrites()) {
            sendPut(writeId);
        }
        this.lastWriteTimestamp = System.currentTimeMillis();
        doIdleCheck();
    }

    private void sendPut(long putId) {
        ConnectionUtils.hardAssert(canSendWrites(), "sendPut called when we can't send writes (we're disconnected or writes are paused).", new Object[0]);
        OutstandingPut put = this.outstandingPuts.get(Long.valueOf(putId));
        RequestResultCallback onComplete = put.getOnComplete();
        String action = put.getAction();
        put.markSent();
        final String str = action;
        final long j = putId;
        final OutstandingPut outstandingPut = put;
        final RequestResultCallback requestResultCallback = onComplete;
        sendAction(action, put.getRequest(), new ConnectionRequestCallback() {
            public void onResponse(Map<String, Object> response) {
                if (PersistentConnectionImpl.this.logger.logsDebug()) {
                    PersistentConnectionImpl.this.logger.debug(str + " response: " + response, new Object[0]);
                }
                if (((OutstandingPut) PersistentConnectionImpl.this.outstandingPuts.get(Long.valueOf(j))) == outstandingPut) {
                    PersistentConnectionImpl.this.outstandingPuts.remove(Long.valueOf(j));
                    if (requestResultCallback != null) {
                        String status = (String) response.get("s");
                        if (status.equals("ok")) {
                            requestResultCallback.onRequestResult((String) null, (String) null);
                        } else {
                            requestResultCallback.onRequestResult(status, (String) response.get("d"));
                        }
                    }
                } else if (PersistentConnectionImpl.this.logger.logsDebug()) {
                    PersistentConnectionImpl.this.logger.debug("Ignoring on complete for put " + j + " because it was removed already.", new Object[0]);
                }
                PersistentConnectionImpl.this.doIdleCheck();
            }
        });
    }

    private void sendGet(final Long readId) {
        ConnectionUtils.hardAssert(canSendReads(), "sendGet called when we can't send gets", new Object[0]);
        final OutstandingGet get = this.outstandingGets.get(readId);
        if (get.markSent() || !this.logger.logsDebug()) {
            sendAction(REQUEST_ACTION_GET, get.getRequest(), new ConnectionRequestCallback() {
                public void onResponse(Map<String, Object> response) {
                    if (((OutstandingGet) PersistentConnectionImpl.this.outstandingGets.get(readId)) == get) {
                        PersistentConnectionImpl.this.outstandingGets.remove(readId);
                        get.getOnComplete().onResponse(response);
                    } else if (PersistentConnectionImpl.this.logger.logsDebug()) {
                        PersistentConnectionImpl.this.logger.debug("Ignoring on complete for get " + readId + " because it was removed already.", new Object[0]);
                    }
                }
            });
        } else {
            this.logger.debug("get" + readId + " cancelled, ignoring.", new Object[0]);
        }
    }

    private void sendListen(final OutstandingListen listen) {
        Map<String, Object> request = new HashMap<>();
        request.put("p", ConnectionUtils.pathToString(listen.getQuery().path));
        Long tag = listen.getTag();
        if (tag != null) {
            request.put("q", listen.query.queryParams);
            request.put("t", tag);
        }
        ListenHashProvider hashFunction = listen.getHashFunction();
        request.put(REQUEST_DATA_HASH, hashFunction.getSimpleHash());
        if (hashFunction.shouldIncludeCompoundHash()) {
            CompoundHash compoundHash = hashFunction.getCompoundHash();
            List<String> posts = new ArrayList<>();
            for (List<String> path : compoundHash.getPosts()) {
                posts.add(ConnectionUtils.pathToString(path));
            }
            Map<String, Object> hash = new HashMap<>();
            hash.put(REQUEST_COMPOUND_HASH_HASHES, compoundHash.getHashes());
            hash.put(REQUEST_COMPOUND_HASH_PATHS, posts);
            request.put(REQUEST_COMPOUND_HASH, hash);
        }
        sendAction("q", request, new ConnectionRequestCallback() {
            public void onResponse(Map<String, Object> response) {
                String status = (String) response.get("s");
                if (status.equals("ok")) {
                    Map<String, Object> serverBody = (Map) response.get("d");
                    if (serverBody.containsKey(PersistentConnectionImpl.SERVER_DATA_WARNINGS)) {
                        PersistentConnectionImpl.this.warnOnListenerWarnings((List) serverBody.get(PersistentConnectionImpl.SERVER_DATA_WARNINGS), listen.query);
                    }
                }
                if (((OutstandingListen) PersistentConnectionImpl.this.listens.get(listen.getQuery())) != listen) {
                    return;
                }
                if (!status.equals("ok")) {
                    OutstandingListen unused = PersistentConnectionImpl.this.removeListen(listen.getQuery());
                    listen.resultCallback.onRequestResult(status, (String) response.get("d"));
                    return;
                }
                listen.resultCallback.onRequestResult((String) null, (String) null);
            }
        });
    }

    private void sendStats(Map<String, Integer> stats) {
        if (!stats.isEmpty()) {
            Map<String, Object> request = new HashMap<>();
            request.put("c", stats);
            sendAction("s", request, new ConnectionRequestCallback() {
                public void onResponse(Map<String, Object> response) {
                    String status = (String) response.get("s");
                    if (!status.equals("ok")) {
                        String errorMessage = (String) response.get("d");
                        if (PersistentConnectionImpl.this.logger.logsDebug()) {
                            PersistentConnectionImpl.this.logger.debug("Failed to send stats: " + status + " (message: " + errorMessage + ")", new Object[0]);
                        }
                    }
                }
            });
        } else if (this.logger.logsDebug()) {
            this.logger.debug("Not sending stats because stats are empty", new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void warnOnListenerWarnings(List<String> warnings, QuerySpec query) {
        if (warnings.contains("no_index")) {
            this.logger.warn("Using an unspecified index. Your data will be downloaded and filtered on the client. Consider adding '" + ("\".indexOn\": \"" + query.queryParams.get("i") + '\"') + "' at " + ConnectionUtils.pathToString(query.path) + " to your security and Firebase Database rules for better performance");
        }
    }

    private void sendConnectStats() {
        Map<String, Integer> stats = new HashMap<>();
        if (this.context.isPersistenceEnabled()) {
            stats.put("persistence.android.enabled", 1);
        }
        stats.put("sdk.android." + this.context.getClientSdkVersion().replace('.', '-'), 1);
        if (this.logger.logsDebug()) {
            this.logger.debug("Sending first connection stats", new Object[0]);
        }
        sendStats(stats);
    }

    private void sendAction(String action, Map<String, Object> message, ConnectionRequestCallback onResponse) {
        sendSensitive(action, false, message, onResponse);
    }

    private void sendSensitive(String action, boolean isSensitive, Map<String, Object> message, ConnectionRequestCallback onResponse) {
        long rn = nextRequestNumber();
        Map<String, Object> request = new HashMap<>();
        request.put(REQUEST_NUMBER, Long.valueOf(rn));
        request.put("a", action);
        request.put("b", message);
        this.realtime.sendRequest(request, isSensitive);
        this.requestCBHash.put(Long.valueOf(rn), onResponse);
    }

    private long nextRequestNumber() {
        long j = this.requestCounter;
        this.requestCounter = 1 + j;
        return j;
    }

    /* access modifiers changed from: private */
    public void doIdleCheck() {
        if (isIdle()) {
            ScheduledFuture<?> scheduledFuture = this.inactivityTimer;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            this.inactivityTimer = this.executorService.schedule(new Runnable() {
                public void run() {
                    ScheduledFuture unused = PersistentConnectionImpl.this.inactivityTimer = null;
                    if (PersistentConnectionImpl.this.idleHasTimedOut()) {
                        PersistentConnectionImpl.this.interrupt(PersistentConnectionImpl.IDLE_INTERRUPT_REASON);
                    } else {
                        PersistentConnectionImpl.this.doIdleCheck();
                    }
                }
            }, 60000, TimeUnit.MILLISECONDS);
        } else if (isInterrupted(IDLE_INTERRUPT_REASON)) {
            ConnectionUtils.hardAssert(!isIdle());
            resume(IDLE_INTERRUPT_REASON);
        }
    }

    private boolean isIdle() {
        return this.listens.isEmpty() && this.outstandingGets.isEmpty() && this.requestCBHash.isEmpty() && !this.hasOnDisconnects && this.outstandingPuts.isEmpty();
    }

    /* access modifiers changed from: private */
    public boolean idleHasTimedOut() {
        return isIdle() && System.currentTimeMillis() > this.lastWriteTimestamp + 60000;
    }

    public void injectConnectionFailure() {
        Connection connection = this.realtime;
        if (connection != null) {
            connection.injectConnectionFailure();
        }
    }
}
