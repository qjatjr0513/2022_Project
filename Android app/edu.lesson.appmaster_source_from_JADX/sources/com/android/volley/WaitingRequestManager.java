package com.android.volley;

import com.android.volley.Request;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

class WaitingRequestManager implements Request.NetworkRequestCompleteListener {
    private final CacheDispatcher mCacheDispatcher;
    private final BlockingQueue<Request<?>> mNetworkQueue;
    private final RequestQueue mRequestQueue;
    private final ResponseDelivery mResponseDelivery;
    private final Map<String, List<Request<?>>> mWaitingRequests;

    WaitingRequestManager(RequestQueue requestQueue) {
        this.mWaitingRequests = new HashMap();
        this.mRequestQueue = requestQueue;
        this.mResponseDelivery = requestQueue.getResponseDelivery();
        this.mCacheDispatcher = null;
        this.mNetworkQueue = null;
    }

    WaitingRequestManager(CacheDispatcher cacheDispatcher, BlockingQueue<Request<?>> networkQueue, ResponseDelivery responseDelivery) {
        this.mWaitingRequests = new HashMap();
        this.mRequestQueue = null;
        this.mResponseDelivery = responseDelivery;
        this.mCacheDispatcher = cacheDispatcher;
        this.mNetworkQueue = networkQueue;
    }

    public void onResponseReceived(Request<?> request, Response<?> response) {
        List<Request<?>> waitingRequests;
        if (response.cacheEntry == null || response.cacheEntry.isExpired()) {
            onNoUsableResponseReceived(request);
            return;
        }
        String cacheKey = request.getCacheKey();
        synchronized (this) {
            waitingRequests = this.mWaitingRequests.remove(cacheKey);
        }
        if (waitingRequests != null) {
            if (VolleyLog.DEBUG) {
                VolleyLog.m394v("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(waitingRequests.size()), cacheKey);
            }
            for (Request<?> waiting : waitingRequests) {
                this.mResponseDelivery.postResponse(waiting, response);
            }
        }
    }

    public synchronized void onNoUsableResponseReceived(Request<?> request) {
        BlockingQueue<Request<?>> blockingQueue;
        String cacheKey = request.getCacheKey();
        List<Request<?>> waitingRequests = this.mWaitingRequests.remove(cacheKey);
        if (waitingRequests != null && !waitingRequests.isEmpty()) {
            if (VolleyLog.DEBUG) {
                VolleyLog.m394v("%d waiting requests for cacheKey=%s; resend to network", Integer.valueOf(waitingRequests.size()), cacheKey);
            }
            Request<?> nextInLine = waitingRequests.remove(0);
            this.mWaitingRequests.put(cacheKey, waitingRequests);
            nextInLine.setNetworkRequestCompleteListener(this);
            RequestQueue requestQueue = this.mRequestQueue;
            if (requestQueue != null) {
                requestQueue.sendRequestOverNetwork(nextInLine);
            } else if (!(this.mCacheDispatcher == null || (blockingQueue = this.mNetworkQueue) == null)) {
                try {
                    blockingQueue.put(nextInLine);
                } catch (InterruptedException iex) {
                    VolleyLog.m392e("Couldn't add request to queue. %s", iex.toString());
                    Thread.currentThread().interrupt();
                    this.mCacheDispatcher.quit();
                }
            }
        }
        return;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003a, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0052, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean maybeAddToWaitingRequests(com.android.volley.Request<?> r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            java.lang.String r0 = r7.getCacheKey()     // Catch:{ all -> 0x0053 }
            java.util.Map<java.lang.String, java.util.List<com.android.volley.Request<?>>> r1 = r6.mWaitingRequests     // Catch:{ all -> 0x0053 }
            boolean r1 = r1.containsKey(r0)     // Catch:{ all -> 0x0053 }
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x003b
            java.util.Map<java.lang.String, java.util.List<com.android.volley.Request<?>>> r1 = r6.mWaitingRequests     // Catch:{ all -> 0x0053 }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x0053 }
            java.util.List r1 = (java.util.List) r1     // Catch:{ all -> 0x0053 }
            if (r1 != 0) goto L_0x001f
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x0053 }
            r4.<init>()     // Catch:{ all -> 0x0053 }
            r1 = r4
        L_0x001f:
            java.lang.String r4 = "waiting-for-response"
            r7.addMarker(r4)     // Catch:{ all -> 0x0053 }
            r1.add(r7)     // Catch:{ all -> 0x0053 }
            java.util.Map<java.lang.String, java.util.List<com.android.volley.Request<?>>> r4 = r6.mWaitingRequests     // Catch:{ all -> 0x0053 }
            r4.put(r0, r1)     // Catch:{ all -> 0x0053 }
            boolean r4 = com.android.volley.VolleyLog.DEBUG     // Catch:{ all -> 0x0053 }
            if (r4 == 0) goto L_0x0039
            java.lang.String r4 = "Request for cacheKey=%s is in flight, putting on hold."
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ all -> 0x0053 }
            r5[r3] = r0     // Catch:{ all -> 0x0053 }
            com.android.volley.VolleyLog.m391d(r4, r5)     // Catch:{ all -> 0x0053 }
        L_0x0039:
            monitor-exit(r6)
            return r2
        L_0x003b:
            java.util.Map<java.lang.String, java.util.List<com.android.volley.Request<?>>> r1 = r6.mWaitingRequests     // Catch:{ all -> 0x0053 }
            r4 = 0
            r1.put(r0, r4)     // Catch:{ all -> 0x0053 }
            r7.setNetworkRequestCompleteListener(r6)     // Catch:{ all -> 0x0053 }
            boolean r1 = com.android.volley.VolleyLog.DEBUG     // Catch:{ all -> 0x0053 }
            if (r1 == 0) goto L_0x0051
            java.lang.String r1 = "new request, sending to network %s"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0053 }
            r2[r3] = r0     // Catch:{ all -> 0x0053 }
            com.android.volley.VolleyLog.m391d(r1, r2)     // Catch:{ all -> 0x0053 }
        L_0x0051:
            monitor-exit(r6)
            return r3
        L_0x0053:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.volley.WaitingRequestManager.maybeAddToWaitingRequests(com.android.volley.Request):boolean");
    }
}
