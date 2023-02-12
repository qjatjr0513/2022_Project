package com.android.volley;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import com.android.volley.AsyncCache;
import com.android.volley.AsyncNetwork;
import com.android.volley.Cache;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AsyncRequestQueue extends RequestQueue {
    private static final int DEFAULT_BLOCKING_THREAD_POOL_SIZE = 4;
    /* access modifiers changed from: private */
    public final AsyncCache mAsyncCache;
    /* access modifiers changed from: private */
    public ExecutorService mBlockingExecutor;
    private final Object mCacheInitializationLock;
    private ExecutorFactory mExecutorFactory;
    private volatile boolean mIsCacheInitialized;
    /* access modifiers changed from: private */
    public final AsyncNetwork mNetwork;
    /* access modifiers changed from: private */
    public ExecutorService mNonBlockingExecutor;
    private ScheduledExecutorService mNonBlockingScheduledExecutor;
    private final List<Request<?>> mRequestsAwaitingCacheInitialization;
    /* access modifiers changed from: private */
    public final WaitingRequestManager mWaitingRequestManager;

    public static abstract class ExecutorFactory {
        public abstract ExecutorService createBlockingExecutor(BlockingQueue<Runnable> blockingQueue);

        public abstract ExecutorService createNonBlockingExecutor(BlockingQueue<Runnable> blockingQueue);

        public abstract ScheduledExecutorService createNonBlockingScheduledExecutor();
    }

    private AsyncRequestQueue(Cache cache, AsyncNetwork network, AsyncCache asyncCache, ResponseDelivery responseDelivery, ExecutorFactory executorFactory) {
        super(cache, network, 0, responseDelivery);
        this.mWaitingRequestManager = new WaitingRequestManager(this);
        this.mRequestsAwaitingCacheInitialization = new ArrayList();
        this.mIsCacheInitialized = false;
        this.mCacheInitializationLock = new Object[0];
        this.mAsyncCache = asyncCache;
        this.mNetwork = network;
        this.mExecutorFactory = executorFactory;
    }

    public void start() {
        stop();
        this.mNonBlockingExecutor = this.mExecutorFactory.createNonBlockingExecutor(getBlockingQueue());
        this.mBlockingExecutor = this.mExecutorFactory.createBlockingExecutor(getBlockingQueue());
        this.mNonBlockingScheduledExecutor = this.mExecutorFactory.createNonBlockingScheduledExecutor();
        this.mNetwork.setBlockingExecutor(this.mBlockingExecutor);
        this.mNetwork.setNonBlockingExecutor(this.mNonBlockingExecutor);
        this.mNetwork.setNonBlockingScheduledExecutor(this.mNonBlockingScheduledExecutor);
        if (this.mAsyncCache != null) {
            this.mNonBlockingExecutor.execute(new Runnable() {
                public void run() {
                    AsyncRequestQueue.this.mAsyncCache.initialize(new AsyncCache.OnWriteCompleteCallback() {
                        public void onWriteComplete() {
                            AsyncRequestQueue.this.onCacheInitializationComplete();
                        }
                    });
                }
            });
        } else {
            this.mBlockingExecutor.execute(new Runnable() {
                public void run() {
                    AsyncRequestQueue.this.getCache().initialize();
                    AsyncRequestQueue.this.mNonBlockingExecutor.execute(new Runnable() {
                        public void run() {
                            AsyncRequestQueue.this.onCacheInitializationComplete();
                        }
                    });
                }
            });
        }
    }

    public void stop() {
        ExecutorService executorService = this.mNonBlockingExecutor;
        if (executorService != null) {
            executorService.shutdownNow();
            this.mNonBlockingExecutor = null;
        }
        ExecutorService executorService2 = this.mBlockingExecutor;
        if (executorService2 != null) {
            executorService2.shutdownNow();
            this.mBlockingExecutor = null;
        }
        ScheduledExecutorService scheduledExecutorService = this.mNonBlockingScheduledExecutor;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.mNonBlockingScheduledExecutor = null;
        }
    }

    /* access modifiers changed from: package-private */
    public <T> void beginRequest(Request<T> request) {
        if (!this.mIsCacheInitialized) {
            synchronized (this.mCacheInitializationLock) {
                if (!this.mIsCacheInitialized) {
                    this.mRequestsAwaitingCacheInitialization.add(request);
                    return;
                }
            }
        }
        if (!request.shouldCache()) {
            sendRequestOverNetwork(request);
        } else if (this.mAsyncCache != null) {
            this.mNonBlockingExecutor.execute(new CacheTask(request));
        } else {
            this.mBlockingExecutor.execute(new CacheTask(request));
        }
    }

    /* access modifiers changed from: private */
    public void onCacheInitializationComplete() {
        List<Request<?>> requestsToDispatch;
        synchronized (this.mCacheInitializationLock) {
            requestsToDispatch = new ArrayList<>(this.mRequestsAwaitingCacheInitialization);
            this.mRequestsAwaitingCacheInitialization.clear();
            this.mIsCacheInitialized = true;
        }
        for (Request<?> request : requestsToDispatch) {
            beginRequest(request);
        }
    }

    /* access modifiers changed from: package-private */
    public <T> void sendRequestOverNetwork(Request<T> request) {
        this.mNonBlockingExecutor.execute(new NetworkTask(request));
    }

    private class CacheTask<T> extends RequestTask<T> {
        CacheTask(Request<T> request) {
            super(request);
        }

        public void run() {
            if (this.mRequest.isCanceled()) {
                this.mRequest.finish("cache-discard-canceled");
                return;
            }
            this.mRequest.addMarker("cache-queue-take");
            if (AsyncRequestQueue.this.mAsyncCache != null) {
                AsyncRequestQueue.this.mAsyncCache.get(this.mRequest.getCacheKey(), new AsyncCache.OnGetCompleteCallback() {
                    public void onGetComplete(Cache.Entry entry) {
                        AsyncRequestQueue.this.handleEntry(entry, CacheTask.this.mRequest);
                    }
                });
                return;
            }
            AsyncRequestQueue.this.handleEntry(AsyncRequestQueue.this.getCache().get(this.mRequest.getCacheKey()), this.mRequest);
        }
    }

    /* access modifiers changed from: private */
    public void handleEntry(Cache.Entry entry, Request<?> mRequest) {
        if (entry == null) {
            mRequest.addMarker("cache-miss");
            if (!this.mWaitingRequestManager.maybeAddToWaitingRequests(mRequest)) {
                sendRequestOverNetwork(mRequest);
                return;
            }
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (entry.isExpired(currentTimeMillis)) {
            mRequest.addMarker("cache-hit-expired");
            mRequest.setCacheEntry(entry);
            if (!this.mWaitingRequestManager.maybeAddToWaitingRequests(mRequest)) {
                sendRequestOverNetwork(mRequest);
                return;
            }
            return;
        }
        this.mBlockingExecutor.execute(new CacheParseTask(mRequest, entry, currentTimeMillis));
    }

    private class CacheParseTask<T> extends RequestTask<T> {
        Cache.Entry entry;
        long startTimeMillis;

        CacheParseTask(Request<T> request, Cache.Entry entry2, long startTimeMillis2) {
            super(request);
            this.entry = entry2;
            this.startTimeMillis = startTimeMillis2;
        }

        public void run() {
            this.mRequest.addMarker("cache-hit");
            Response<?> response = this.mRequest.parseNetworkResponse(new NetworkResponse(200, this.entry.data, false, 0, this.entry.allResponseHeaders));
            this.mRequest.addMarker("cache-hit-parsed");
            if (!this.entry.refreshNeeded(this.startTimeMillis)) {
                AsyncRequestQueue.this.getResponseDelivery().postResponse(this.mRequest, response);
                return;
            }
            this.mRequest.addMarker("cache-hit-refresh-needed");
            this.mRequest.setCacheEntry(this.entry);
            response.intermediate = true;
            if (!AsyncRequestQueue.this.mWaitingRequestManager.maybeAddToWaitingRequests(this.mRequest)) {
                AsyncRequestQueue.this.getResponseDelivery().postResponse(this.mRequest, response, new Runnable() {
                    public void run() {
                        AsyncRequestQueue.this.sendRequestOverNetwork(CacheParseTask.this.mRequest);
                    }
                });
            } else {
                AsyncRequestQueue.this.getResponseDelivery().postResponse(this.mRequest, response);
            }
        }
    }

    private class ParseErrorTask<T> extends RequestTask<T> {
        VolleyError volleyError;

        ParseErrorTask(Request<T> request, VolleyError volleyError2) {
            super(request);
            this.volleyError = volleyError2;
        }

        public void run() {
            AsyncRequestQueue.this.getResponseDelivery().postError(this.mRequest, this.mRequest.parseNetworkError(this.volleyError));
            this.mRequest.notifyListenerResponseNotUsable();
        }
    }

    private class NetworkTask<T> extends RequestTask<T> {
        NetworkTask(Request<T> request) {
            super(request);
        }

        public void run() {
            if (this.mRequest.isCanceled()) {
                this.mRequest.finish("network-discard-cancelled");
                this.mRequest.notifyListenerResponseNotUsable();
                return;
            }
            final long startTimeMs = SystemClock.elapsedRealtime();
            this.mRequest.addMarker("network-queue-take");
            AsyncRequestQueue.this.mNetwork.performRequest(this.mRequest, new AsyncNetwork.OnRequestComplete() {
                public void onSuccess(NetworkResponse networkResponse) {
                    NetworkTask.this.mRequest.addMarker("network-http-complete");
                    if (!networkResponse.notModified || !NetworkTask.this.mRequest.hasHadResponseDelivered()) {
                        AsyncRequestQueue.this.mBlockingExecutor.execute(new NetworkParseTask(NetworkTask.this.mRequest, networkResponse));
                        return;
                    }
                    NetworkTask.this.mRequest.finish("not-modified");
                    NetworkTask.this.mRequest.notifyListenerResponseNotUsable();
                }

                public void onError(VolleyError volleyError) {
                    volleyError.setNetworkTimeMs(SystemClock.elapsedRealtime() - startTimeMs);
                    AsyncRequestQueue.this.mBlockingExecutor.execute(new ParseErrorTask(NetworkTask.this.mRequest, volleyError));
                }
            });
        }
    }

    private class NetworkParseTask<T> extends RequestTask<T> {
        NetworkResponse networkResponse;

        NetworkParseTask(Request<T> request, NetworkResponse networkResponse2) {
            super(request);
            this.networkResponse = networkResponse2;
        }

        public void run() {
            Response<?> response = this.mRequest.parseNetworkResponse(this.networkResponse);
            this.mRequest.addMarker("network-parse-complete");
            if (!this.mRequest.shouldCache() || response.cacheEntry == null) {
                AsyncRequestQueue.this.finishRequest(this.mRequest, response, false);
            } else if (AsyncRequestQueue.this.mAsyncCache != null) {
                AsyncRequestQueue.this.mNonBlockingExecutor.execute(new CachePutTask(this.mRequest, response));
            } else {
                AsyncRequestQueue.this.mBlockingExecutor.execute(new CachePutTask(this.mRequest, response));
            }
        }
    }

    private class CachePutTask<T> extends RequestTask<T> {
        Response<?> response;

        CachePutTask(Request<T> request, Response<?> response2) {
            super(request);
            this.response = response2;
        }

        public void run() {
            if (AsyncRequestQueue.this.mAsyncCache != null) {
                AsyncRequestQueue.this.mAsyncCache.put(this.mRequest.getCacheKey(), this.response.cacheEntry, new AsyncCache.OnWriteCompleteCallback() {
                    public void onWriteComplete() {
                        AsyncRequestQueue.this.finishRequest(CachePutTask.this.mRequest, CachePutTask.this.response, true);
                    }
                });
                return;
            }
            AsyncRequestQueue.this.getCache().put(this.mRequest.getCacheKey(), this.response.cacheEntry);
            AsyncRequestQueue.this.finishRequest(this.mRequest, this.response, true);
        }
    }

    /* access modifiers changed from: private */
    public void finishRequest(Request<?> mRequest, Response<?> response, boolean cached) {
        if (cached) {
            mRequest.addMarker("network-cache-written");
        }
        mRequest.markDelivered();
        getResponseDelivery().postResponse(mRequest, response);
        mRequest.notifyListenerResponseReceived(response);
    }

    private static PriorityBlockingQueue<Runnable> getBlockingQueue() {
        return new PriorityBlockingQueue<>(11, new Comparator<Runnable>() {
            public int compare(Runnable r1, Runnable r2) {
                if (!(r1 instanceof RequestTask)) {
                    return r2 instanceof RequestTask ? -1 : 0;
                }
                if (r2 instanceof RequestTask) {
                    return ((RequestTask) r1).compareTo((RequestTask) r2);
                }
                return 1;
            }
        });
    }

    public static class Builder {
        private AsyncCache mAsyncCache = null;
        private Cache mCache = null;
        private ExecutorFactory mExecutorFactory = null;
        private final AsyncNetwork mNetwork;
        private ResponseDelivery mResponseDelivery = null;

        public Builder(AsyncNetwork asyncNetwork) {
            if (asyncNetwork != null) {
                this.mNetwork = asyncNetwork;
                return;
            }
            throw new IllegalArgumentException("Network cannot be null");
        }

        public Builder setExecutorFactory(ExecutorFactory executorFactory) {
            this.mExecutorFactory = executorFactory;
            return this;
        }

        public Builder setResponseDelivery(ResponseDelivery responseDelivery) {
            this.mResponseDelivery = responseDelivery;
            return this;
        }

        public Builder setAsyncCache(AsyncCache asyncCache) {
            this.mAsyncCache = asyncCache;
            return this;
        }

        public Builder setCache(Cache cache) {
            this.mCache = cache;
            return this;
        }

        private ExecutorFactory getDefaultExecutorFactory() {
            return new ExecutorFactory() {
                public ExecutorService createNonBlockingExecutor(BlockingQueue<Runnable> taskQueue) {
                    return getNewThreadPoolExecutor(1, "Non-BlockingExecutor", taskQueue);
                }

                public ExecutorService createBlockingExecutor(BlockingQueue<Runnable> taskQueue) {
                    return getNewThreadPoolExecutor(4, "BlockingExecutor", taskQueue);
                }

                public ScheduledExecutorService createNonBlockingScheduledExecutor() {
                    return new ScheduledThreadPoolExecutor(0, getThreadFactory("ScheduledExecutor"));
                }

                private ThreadPoolExecutor getNewThreadPoolExecutor(int maximumPoolSize, String threadNameSuffix, BlockingQueue<Runnable> taskQueue) {
                    return new ThreadPoolExecutor(0, maximumPoolSize, 60, TimeUnit.SECONDS, taskQueue, getThreadFactory(threadNameSuffix));
                }

                private ThreadFactory getThreadFactory(final String threadNameSuffix) {
                    return new ThreadFactory() {
                        public Thread newThread(Runnable runnable) {
                            Thread t = Executors.defaultThreadFactory().newThread(runnable);
                            t.setName("Volley-" + threadNameSuffix);
                            return t;
                        }
                    };
                }
            };
        }

        public AsyncRequestQueue build() {
            Cache cache = this.mCache;
            if (cache == null && this.mAsyncCache == null) {
                throw new IllegalArgumentException("You must set one of the cache objects");
            }
            if (cache == null) {
                this.mCache = new ThrowingCache();
            }
            if (this.mResponseDelivery == null) {
                this.mResponseDelivery = new ExecutorDelivery(new Handler(Looper.getMainLooper()));
            }
            if (this.mExecutorFactory == null) {
                this.mExecutorFactory = getDefaultExecutorFactory();
            }
            return new AsyncRequestQueue(this.mCache, this.mNetwork, this.mAsyncCache, this.mResponseDelivery, this.mExecutorFactory);
        }
    }

    private static class ThrowingCache implements Cache {
        private ThrowingCache() {
        }

        public Cache.Entry get(String key) {
            throw new UnsupportedOperationException();
        }

        public void put(String key, Cache.Entry entry) {
            throw new UnsupportedOperationException();
        }

        public void initialize() {
            throw new UnsupportedOperationException();
        }

        public void invalidate(String key, boolean fullExpire) {
            throw new UnsupportedOperationException();
        }

        public void remove(String key) {
            throw new UnsupportedOperationException();
        }

        public void clear() {
            throw new UnsupportedOperationException();
        }
    }
}
